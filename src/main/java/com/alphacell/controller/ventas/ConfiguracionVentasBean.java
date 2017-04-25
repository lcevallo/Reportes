/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphacell.controller.ventas;


import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.alphacell.model.ventas.*;
import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Ajax;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import com.alphacell.model.xls.LCExcelVentas1;
import com.alphacell.model.xls.LcCadenaItemsXLS;
import com.alphacell.repository.ventas.ConfigRepository;
import com.alphacell.services.NegocioException;
import com.alphacell.services.ventas.ServiceConfigVentas;
import com.alphacell.util.file.ExcelHelper;
import com.alphacell.util.jpa.filter.CadenaItemFilter;
import com.alphacell.util.jsf.FacesMessages;
import com.alphacell.util.jsf.FormatoExcelPoi;


/**
 *
 * @author luis.cevallos
 */
@Named
@ViewScoped
public class ConfiguracionVentasBean implements Serializable{

	private static final long serialVersionUID = 1L;


    private List<LcCadenaAlph> cmbCadenaAlph;
    private LcCadenaAlph cadenaSelected;
    private LcCadenaAlph cadenaNew= new LcCadenaAlph();


    private List<LcVistaExcelVentasInicial> tableVistaExcelVentasIniciales;
    private List<LcVistaExcelVentasInicial> tableVistaExcelVentasInicialesFiltered;
    private LcVistaExcelVentasInicial lcVistaExcelVentasInicialSelected;
    private LcVistaExcelVentasInicial lcVistaExcelVentasInicialNew;

    private CadenaItemFilter cadenaItemFilter;


    private List<LCExcelVentas1> tableListaCadenaItemsXlsx;
    private List<LcCadenaItemsXLS> cmbListItemERP;
    private LcCadenaItemsXLS listaItemXLSselected;

    private LCExcelVentas1 listaLcExcelVentas1;

    private StreamedContent file;

    @Inject
    private ConfigRepository configRepository;

    @Inject
    private ServiceConfigVentas serviceConfigVentas;

    @Inject
    private FacesMessages messages;

    @PostConstruct
    public void init()
    {
        InputStream stream= FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/media/TemplateItemsCadenaAlpha.xlsx");
        file= new DefaultStreamedContent(stream,"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet","TemplateItemsCadena.xlsx");


        this.cadenaItemFilter= new CadenaItemFilter();

        this.cargarCadenas();
        this.tableVistaExcelVentasIniciales= new ArrayList<>();
        this.tableVistaExcelVentasInicialesFiltered= new ArrayList<>();

        this.tableListaCadenaItemsXlsx= new ArrayList<>();
        this.lcVistaExcelVentasInicialSelected= new LcVistaExcelVentasInicial();
        this.lcVistaExcelVentasInicialNew= new LcVistaExcelVentasInicial();

        this.cargarItemsERP();
    }

    public void prepararNuevoItemCadena()
    {
        /*
        this.cadenaItemsNew=new LcCadenaItems();
        this.cadenaItemsNew.setLcCadenaItemsPK(new LcCadenaItemsPK("",this.cadenaSelected.getCodigoCadena()));
        */

    }

    public void buscar(){
        //TODO: tengo que hacer el metodo buscar

        this.tableVistaExcelVentasIniciales=configRepository.filtrados(this.cadenaSelected.getCodigoCadena(),this.cadenaItemFilter);

        /*
        this.tableCadenaItems=configRepository.filtrados(this.cadenaSelected.getCodigoCadena(),this.cadenaItemFilter);
        AtomicInteger atomicInteger = new AtomicInteger(0);
        tableCadenaItems.forEach(obj->obj.setRowkey(atomicInteger.getAndIncrement()));
        */
    }

    public void postProcessXLS(Object document)
    {
        
    	HashSet omitirColumnas = new HashSet();

        //add elements to HashSet object
        omitirColumnas.add(new Integer("1"));
        omitirColumnas.add(new Integer("2"));
        omitirColumnas.add(new Integer("3"));
        omitirColumnas.add(new Integer("4"));


        FormatoExcelPoi.formatearArchivoExcel(document,omitirColumnas,0);
        
    }

    public void cargarItemsERP()
    {
        this.cmbListItemERP=configRepository.getAllItemsERP();
    }

    public void excluir() {
        try {

            serviceConfigVentas.removerItemCadenaItemVistaInicial(this.lcVistaExcelVentasInicialSelected);
            tableVistaExcelVentasIniciales.remove(this.lcVistaExcelVentasInicialSelected);

            messages.info("Item: " + this.lcVistaExcelVentasInicialSelected.getItemid()
                    + " eliminado con exito.");

        } catch (NegocioException ne) {
            messages.error(ne.getMessage());
        }

    }

    public void handleCadenaChange(AjaxBehaviorEvent event)
    {

        this.tableVistaExcelVentasIniciales.clear();

        if(cadenaSelected!=null)
        this.cadenaNew= this.cadenaSelected;

       
        this.tableVistaExcelVentasIniciales=  this.configRepository.getByCadena(this.cadenaSelected.getCodigoCadena());

        /*
        this.tableCadenaItems= this.configRepository.traerPorCadena(this.cadenaSelected.getCodigoCadena());
        AtomicInteger atomicInteger = new AtomicInteger(0);
        tableCadenaItems.forEach(obj->obj.setRowkey(atomicInteger.getAndIncrement()));
        */
    }

    public void prepararNuevaCadena(ActionEvent event) {

        if(cadenaSelected!=null)
        {
            cadenaNew = new LcCadenaAlph();
            cadenaNew = this.configRepository.getByCadenaCodigo(cadenaSelected.getCodigoCadena());
        }
        else
        {
            cadenaNew = new LcCadenaAlph();
        }
    }

    public void cargarCadenas()
    {
        this.cmbCadenaAlph=this.configRepository.getAllCadenas();
    }


    //Metodo para subir archivo de excel
    public void upload(FileUploadEvent event) {
        UploadedFile file= event.getFile();
        if(this.cadenaSelected==null)
        {
            this.messages.error("Debe de haber seleccionado una cadena");
            return;
        }

        if(file != null) {
        try {
            Path archivoTemporal = Files.createTempFile(null, null);
            Files.copy(file.getInputstream(), archivoTemporal, StandardCopyOption.REPLACE_EXISTING);
            String rutaArchivoExcel=archivoTemporal.toString();

            ExcelHelper excelHelper= new ExcelHelper(rutaArchivoExcel);

            this.tableListaCadenaItemsXlsx= excelHelper.readData("com.alphacell.model.xls.",LCExcelVentas1.class.getName());

            //Luego con esa lista hago una subida de informacion de cada objeto
            this.tableListaCadenaItemsXlsx.forEach(obj->{

                LcVistaExcelVentasInicial lcVistaExcelVentasInicial= new LcVistaExcelVentasInicial(this.cadenaSelected.getCodigoCadena(),obj.getMarca(),obj.getModelo_unificado(),obj.getCod_alpha(),obj.getDescripcion_cadena());
                this.configRepository.guardarSPExcelVentasR(lcVistaExcelVentasInicial);

            });

            //Aqui debo de buscar en la Vista sobre
            this.tableVistaExcelVentasIniciales=this.configRepository.getByCadena(this.cadenaSelected.getCodigoCadena());

        }
        catch (Exception e) {
                e.printStackTrace();
                messages.error(e.getMessage());
                RequestContext.getCurrentInstance().update(
                        Arrays.asList("form-Config-Ventas:messagesConfigVentas"));
            }

        }//fin del if
    }

    private void guardarSinSp() {
        AtomicInteger atomicInteger = new AtomicInteger(0);

        this.tableListaCadenaItemsXlsx.forEach(obj->{

            LcCadenaItemsPK pk_lcCadenaItems= new LcCadenaItemsPK(obj.getModelo_unificado(),this.cadenaSelected.getCodigoCadena());
            LcCadenaItems lcCadenaItems= new LcCadenaItems(pk_lcCadenaItems);
            lcCadenaItems.setDescripcionCadena(obj.getDescripcion_cadena());
            lcCadenaItems.setMarca(obj.getMarca());

            lcCadenaItems= this.serviceConfigVentas.salvarCadenaItem(lcCadenaItems);

            if(lcCadenaItems!=null)
            {
                LcCadenaItemRErpPK lcCadenaItemRErpPK= new LcCadenaItemRErpPK(lcCadenaItems.getLcCadenaItemsPK().getCodigoItem(),lcCadenaItems.getLcCadenaItemsPK().getFkCodigoCadena(),obj.getCod_alpha());
                LcCadenaItemRErp lcCadenaItemRErp= new LcCadenaItemRErp(lcCadenaItemRErpPK);
                lcCadenaItemRErp= this.serviceConfigVentas.salvarLcCadenaItemRERP(lcCadenaItemRErp);

            }//fin del bloque if
            //this.tableCadenaItems.add(lcCadenaItemsLocal);
        });
    }


    public void changeDescripcionAlpha(ValueChangeEvent event)
    {
        //DataTable table1 = getParentDatatable((UIComponent) event.getSource());
        //Esta es otra forma de obtener el datatable a partir del id
        DataTable table1 = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("form-Config-Ventas:table-cadenas-items-alpha");

        Object o = table1.getRowData();


        int index = table1.getRowIndex();

        LcCadenaItemsXLS oldValue= (LcCadenaItemsXLS) event.getOldValue();
        LcCadenaItemsXLS newValue= (LcCadenaItemsXLS) event.getNewValue();

        //TODO: Debo de arreglar este tema

        LcVistaExcelVentasInicial lcVistaExcelVentasInicial= this.tableVistaExcelVentasIniciales.get(index);
        lcVistaExcelVentasInicial.setLcCadenaItemsXLS(newValue);

        lcVistaExcelVentasInicial.setCodigoItem(newValue.getCodigo());
        lcVistaExcelVentasInicial.setName(newValue.getDescripcion());

        Integer valor= this.configRepository.guardarSPExcelVentasR(lcVistaExcelVentasInicial);


        messages.info("Se ha modificado el valor del item: "+oldValue.getDescripcion()+"  y se ha enlazado con el item ALPH: "+newValue.getDescripcion() );
        /*
        LcCadenaItems lcCadenaItems= this.tableCadenaItems.get(index);

        lcCadenaItems.setLcCadenaItemsXLS(newValue);

        lcCadenaItems= this.serviceConfigVentas.guardar(lcCadenaItems);
        lcCadenaItems.setRowkey(index);

        messages.info("Se ha modificado el valor del item: "+lcCadenaItems.getDescripcionCadena()+"  y se ha enlazado con el item ALPH: "+newValue.getDescripcion() );

*/

        table1.reset();
        RequestContext.getCurrentInstance().update("form-Config-Ventas:table-cadenas-items-alpha");
        Ajax.update("form-Config-Ventas:table-cadenas-items-alpha");

    }//Aqui termina changeDescripcionAlpha

  
    /**
     * Funcion que retorna un componente HtmlDatatable
     * Me ayudo a saber que row era la marcada cuando
     * se da clic sobre un elemento del celledit
     * En mi caso un select one menu item
     * @param compo
     * @return DataTable
     */
    private DataTable getParentDatatable(UIComponent compo) {
        if (compo == null) {
            return null;
        }
        if (compo instanceof DataTable) {
            return (DataTable) compo;
        }
        return getParentDatatable(compo.getParent());
    }




    public void onCellEdit(CellEditEvent event)
    {
        Object oldValue=event.getOldValue();
        Object newValue= event.getNewValue();

    }


    public void guardarCadena()
    {

        this.serviceConfigVentas.salvarCadena(this.cadenaNew);
        this.cargarCadenas();

        messages.info("Se ha creado la Cadena!");

        RequestContext.getCurrentInstance().update(
                Arrays.asList("form-Config-Ventas:messagesConfigVentas", "form-Config-Ventas:cmb-cfgventas-cadenas"));
    }

    public void guardarItemCadena()
    {

        /*
        String codigoItem= this.cadenaItemsNew.getLcCadenaItemsPK().getCodigoItem();

        //this.cadenaItemsNew.setLcCadenaItemsPK(new LcCadenaItemsPK(codigoItem,this.cadenaSelected.getCodigoCadena()));

        this.serviceConfigVentas.guardar(this.cadenaItemsNew);
*/
        messages.info("Se ha creado un Item en la cadena!");
        this.cargarItemsERP();
        RequestContext.getCurrentInstance().update(Arrays.asList("form-Config-Ventas:table-cadenas-items-alpha","form-Config-Ventas:messagesConfigVentas"));

    }

    public List<LcCadenaAlph> getCmbCadenaAlph() {
        return cmbCadenaAlph;
    }

    public void setCmbCadenaAlph(List<LcCadenaAlph> cmbCadenaAlph) {
        this.cmbCadenaAlph = cmbCadenaAlph;
    }

    public LcCadenaAlph getCadenaSelected() {
        return cadenaSelected;
    }

    public void setCadenaSelected(LcCadenaAlph cadenaSelected) {
        this.cadenaSelected = cadenaSelected;
    }

    public LcCadenaAlph getCadenaNew() {
        return cadenaNew;
    }

    public void setCadenaNew(LcCadenaAlph cadenaNew) {
        this.cadenaNew = cadenaNew;
    }

    public List<LcVistaExcelVentasInicial> getTableVistaExcelVentasIniciales() {
        return tableVistaExcelVentasIniciales;
    }

    public void setTableVistaExcelVentasIniciales(List<LcVistaExcelVentasInicial> tableVistaExcelVentasIniciales) {
        this.tableVistaExcelVentasIniciales = tableVistaExcelVentasIniciales;
    }

    public List<LcVistaExcelVentasInicial> getTableVistaExcelVentasInicialesFiltered() {
        return tableVistaExcelVentasInicialesFiltered;
    }

    public void setTableVistaExcelVentasInicialesFiltered(List<LcVistaExcelVentasInicial> tableVistaExcelVentasInicialesFiltered) {
        this.tableVistaExcelVentasInicialesFiltered = tableVistaExcelVentasInicialesFiltered;
    }

    public LcVistaExcelVentasInicial getLcVistaExcelVentasInicialSelected() {
        return lcVistaExcelVentasInicialSelected;
    }

    public void setLcVistaExcelVentasInicialSelected(LcVistaExcelVentasInicial lcVistaExcelVentasInicialSelected) {
        this.lcVistaExcelVentasInicialSelected = lcVistaExcelVentasInicialSelected;
    }

    public LcVistaExcelVentasInicial getLcVistaExcelVentasInicialNew() {
        return lcVistaExcelVentasInicialNew;
    }

    public void setLcVistaExcelVentasInicialNew(LcVistaExcelVentasInicial lcVistaExcelVentasInicialNew) {
        this.lcVistaExcelVentasInicialNew = lcVistaExcelVentasInicialNew;
    }

    public List<LCExcelVentas1> getTableListaCadenaItemsXlsx() {
        return tableListaCadenaItemsXlsx;
    }

    public void setTableListaCadenaItemsXlsx(List<LCExcelVentas1> tableListaCadenaItemsXlsx) {
        this.tableListaCadenaItemsXlsx = tableListaCadenaItemsXlsx;
    }

    public List<LcCadenaItemsXLS> getCmbListItemERP() {
        return cmbListItemERP;
    }

    public void setCmbListItemERP(List<LcCadenaItemsXLS> cmbListItemERP) {
        this.cmbListItemERP = cmbListItemERP;
    }

    public LcCadenaItemsXLS getListaItemXLSselected() {
        return listaItemXLSselected;
    }

    public void setListaItemXLSselected(LcCadenaItemsXLS listaItemXLSselected) {
        this.listaItemXLSselected = listaItemXLSselected;
    }

    public LCExcelVentas1 getListaLcExcelVentas1() {
        return listaLcExcelVentas1;
    }

    public void setListaLcExcelVentas1(LCExcelVentas1 listaLcExcelVentas1) {
        this.listaLcExcelVentas1 = listaLcExcelVentas1;
    }

    public CadenaItemFilter getCadenaItemFilter() {
		return cadenaItemFilter;
	}

	public void setCadenaItemFilter(CadenaItemFilter cadenaItemFilter) {
		this.cadenaItemFilter = cadenaItemFilter;
	}

    public StreamedContent getFile() {
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }

}
