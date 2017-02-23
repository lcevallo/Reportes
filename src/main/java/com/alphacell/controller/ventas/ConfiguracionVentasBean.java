/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphacell.controller.ventas;


import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;
import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Ajax;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.alphacell.model.ventas.LcCadenaAlph;
import com.alphacell.model.ventas.LcCadenaItems;
import com.alphacell.model.ventas.LcCadenaItemsPK;
import com.alphacell.model.xls.LcCadenaItemsXLS;
import com.alphacell.repository.ventas.ConfigRepository;
import com.alphacell.services.NegocioException;
import com.alphacell.services.ventas.ServiceConfigVentas;
import com.alphacell.util.file.ExcelHelper;
import com.alphacell.util.jsf.FacesMessages;


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

    private List<LcCadenaItems> tableCadenaItems;
    private List<LcCadenaItems> tableCadenaItemsFiltered;
    private LcCadenaItems cadenaItemsSelected;

    private List<LcCadenaItemsXLS> tableListaCadenaItemsXlsx;
    private List<LcCadenaItemsXLS> cmbListItemERP;
    private LcCadenaItemsXLS listaItemXLSselected;

    @Inject
    private ConfigRepository configRepository;

    @Inject
    private ServiceConfigVentas serviceConfigVentas;

    @Inject
    private FacesMessages messages;

    @PostConstruct
    public void init()
    {
        this.cargarCadenas();
        this.tableCadenaItems= new ArrayList<>();
        this.tableListaCadenaItemsXlsx= new ArrayList<>();
        this.cargarItemsERP();
    }


    public void cargarItemsERP()
    {
        this.cmbListItemERP=configRepository.getAllItemsERP();
    }

    public void excluir() {
        try {
            serviceConfigVentas.removerItemCadena(cadenaItemsSelected);
            tableCadenaItems.remove(cadenaItemsSelected);
            messages.info("Produto " + cadenaItemsSelected.getLcCadenaItemsPK().getCodigoItem()
                    + " eliminado con exito.");
        } catch (NegocioException ne) {
            messages.error(ne.getMessage());
        }
    }


    public void handleCadenaChange(AjaxBehaviorEvent event)
    {
        if(cadenaSelected!=null)
        this.cadenaNew= this.cadenaSelected;
    }

    public void prepararNuevaCadena(ActionEvent event) {

        if(cadenaSelected!=null)
        {
            cadenaNew = new LcCadenaAlph();
            cadenaNew = this.configRepository.getByCodigo(cadenaSelected.getCodigoCadena());
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
            //Primero voy a llenar una lista con la informacion que me viene del excel

           //tableCadenaItems= excelHelper.readData("com.alphacell",LcCadenaItems.class.getSimpleName());
            this.tableListaCadenaItemsXlsx= excelHelper.readData("com.alphacell.model.xls.",LcCadenaItemsXLS.class.getName());
            //Luego con esa lista hago una subida de informacion de cada objeto
            AtomicInteger atomicInteger = new AtomicInteger(0);

            this.tableListaCadenaItemsXlsx.forEach(obj->{
                LcCadenaItems lcCadenaItemsLocal= new LcCadenaItems();
                lcCadenaItemsLocal.setLcCadenaItemsPK(new LcCadenaItemsPK(obj.getCodigo(),this.cadenaSelected.getCodigoCadena()));
                lcCadenaItemsLocal.setDescripcionCadena(obj.getDescripcion());
                lcCadenaItemsLocal.setRowkey(atomicInteger.getAndIncrement());
                this.tableCadenaItems.add(lcCadenaItemsLocal);
            });


            }
        catch (Exception e) {
                e.printStackTrace();
                messages.error(e.getMessage());
                RequestContext.getCurrentInstance().update(
                        Arrays.asList("form-Config-Ventas:messagesConfigVentas"));
            }

        }//fin del if
    }



    public void changeDescripcionAlpha(ValueChangeEvent event)
    {
        DataTable table1 = getParentDatatable((UIComponent) event.getSource());
        Object o = table1.getRowData();

        // Espero funcione
        int index = table1.getRowIndex();

        LcCadenaItemsXLS oldValue= (LcCadenaItemsXLS) event.getOldValue();
        LcCadenaItemsXLS newValue= (LcCadenaItemsXLS) event.getNewValue();

        this.tableCadenaItems.get(index).setLcCadenaItemsXLS(newValue);


       // String IdcolumnaDescripcion=table.getChildren().get(4).getId();

        // Aqui lo complemente con un p:ajax update por que no lo estaba haciendo no se por que pero no actualizaba
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

    public List<LcCadenaItems> getTableCadenaItems() {
        return tableCadenaItems;
    }

    public void setTableCadenaItems(List<LcCadenaItems> tableCadenaItems) {
        this.tableCadenaItems = tableCadenaItems;
    }

    public LcCadenaItems getCadenaItemsSelected() {
        return cadenaItemsSelected;
    }

    public void setCadenaItemsSelected(LcCadenaItems cadenaItemsSelected) {
        this.cadenaItemsSelected = cadenaItemsSelected;
    }

    public List<LcCadenaItems> getTableCadenaItemsFiltered() {
        return tableCadenaItemsFiltered;
    }

    public void setTableCadenaItemsFiltered(List<LcCadenaItems> tableCadenaItemsFiltered) {
        this.tableCadenaItemsFiltered = tableCadenaItemsFiltered;
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


}
