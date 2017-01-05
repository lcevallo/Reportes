package com.alphacell.controller.cartera;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.alphacell.model.cartera.ColumnasComportamiento;
import com.alphacell.model.cartera.ComportamientoPivote;
import com.alphacell.repository.ComportamientoPagoRepository;
import com.alphacell.util.jsf.FormatoExcelPoi;
import com.alphacell.util.reporte.Reporte;

import net.sf.jasperreports.engine.JRException;


/**
 * Created by luis.cevallos on 7/4/2016.
 */
@Named
@ViewScoped
public class ComportamientoPagoBean implements Serializable {
   private static final long serialVersionUID = -578627604844709528L;

    private List<ColumnasComportamiento> dataTableColumns = new ArrayList<ColumnasComportamiento>();
    private List<ColumnasComportamiento> dataTableSubColumns = new ArrayList<ColumnasComportamiento>();



    private Integer anioSelecionado;

    private List<ComportamientoPivote> dtblComportamientoPivotes;
    private List<ComportamientoPivote> dtblComportamientoPivotesFiltered;

    private Reporte rpt;
    private HashMap<String, Object> parametros;


    @Inject
    private ComportamientoPagoRepository comportamientoPagoRepository;


    @PostConstruct
    public void init(){

        //this.LlenarTablaColumnas();
        //this.LlenarTablaMultivencimientos();

    }

    public void LlenarTablaColumnas()
    {
        this.dataTableColumns.add(new ColumnasComportamiento ("Cod Cliente","accountnum",2));
        this.dataTableColumns.add(new ColumnasComportamiento ("Cliente","nombreCliente",2));
        this.dataTableColumns.add(new ColumnasComportamiento ("Factura","invoice",2));
        this.dataTableColumns.add(new ColumnasComportamiento ("Valor","amountcur",2));
        this.dataTableColumns.add(new ColumnasComportamiento ("Fecha de Emision","transdate",2));

        this.dataTableColumns.add(new ColumnasComportamiento ("30 dias",3));
        this.dataTableColumns.add(new ColumnasComportamiento ("45 dias",3));
        this.dataTableColumns.add(new ColumnasComportamiento ("60 dias",3));
        this.dataTableColumns.add(new ColumnasComportamiento ("90 dias",3));
        this.dataTableColumns.add(new ColumnasComportamiento ("120 dias",3));
        this.dataTableColumns.add(new ColumnasComportamiento (">120 dias",3));
        
        
        this.dataTableSubColumns.add(new ColumnasComportamiento ("Fecha de Pago","fechas30P"));
        this.dataTableSubColumns.add(new ColumnasComportamiento ("Fecha Cobrado","fechas30C"));
        this.dataTableSubColumns.add(new ColumnasComportamiento ("Dias Tomado","fechas30DT"));


        this.dataTableSubColumns.add(new ColumnasComportamiento ("Fecha de Pago","fecha45P"));
        this.dataTableSubColumns.add(new ColumnasComportamiento ("Fecha Cobrado","fecha45C"));
        this.dataTableSubColumns.add(new ColumnasComportamiento ("Dias Tomado","fecha45DT"));

        this.dataTableSubColumns.add(new ColumnasComportamiento ("Fecha de Pago","fecha60P"));
        this.dataTableSubColumns.add(new ColumnasComportamiento ("Fecha Cobrado","fecha60C"));
        this.dataTableSubColumns.add(new ColumnasComportamiento ("Dias Tomado","fecha60DT"));

        this.dataTableSubColumns.add(new ColumnasComportamiento ("Fecha de Pago","fecha90P"));
        this.dataTableSubColumns.add(new ColumnasComportamiento ("Fecha Cobrado","fecha90C"));
        this.dataTableSubColumns.add(new ColumnasComportamiento ("Dias Tomado","fecha90DT"));

        this.dataTableSubColumns.add(new ColumnasComportamiento ("Fecha de Pago","fecha120P"));
        this.dataTableSubColumns.add(new ColumnasComportamiento ("Fecha Cobrado","fecha120C"));
        this.dataTableSubColumns.add(new ColumnasComportamiento ("Dias Tomado","fecha120DT"));

        this.dataTableSubColumns.add(new ColumnasComportamiento ("Fecha de Pago","fechaM120P"));
        this.dataTableSubColumns.add(new ColumnasComportamiento ("Fecha Cobrado","fechaM120C"));
        this.dataTableSubColumns.add(new ColumnasComportamiento ("Dias Tomado","fechaM120DT"));

    }


    public void LlenarTablaMultivencimientos()
    {

        if(this.anioSelecionado!=null)
        {
            this.dtblComportamientoPivotes=this.comportamientoPagoRepository.obtenerPivoteFacturas();
            //Aqui sacare la media
            //Stream.of(Locale.getAvailableLocales()).map(Locale::getDisplayLanguage).forEach(System.out::println);

        }

        else
        {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Año","Debe escoger un año valido");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

        /*
         this.dyaxTblFacturasList.stream().forEach((factura)->{
            this.dtblComportamientoPivotes.add(new ComportamientoPivote(factura.getAccountnum(), factura.getNombreCliente(), factura.getInvoice(), factura.getVoucher(),factura.getTransdate() ,factura.getAmountcur(), factura.getDebe()));
        }
        );
        this.dyaxTblPivotList30Dias=resultadoLista.stream().filter(pivote->  pivote.getGrupo().equals("30 dias") || pivote.getGrupo().equals("Contenido") || pivote.getGrupo().equals("15 dias") ).collect(Collectors.toList());
        this.dyaxTblPivotList45Dias=resultadoLista.stream().filter(pivote->  pivote.getGrupo().equals("45 dias") ).collect(Collectors.toList());
        this.dyaxTblPivotList60Dias=resultadoLista.stream().filter(pivote->  pivote.getGrupo().equals("60 dias") ).collect(Collectors.toList());
        this.dyaxTblPivotList90Dias=resultadoLista.stream().filter(pivote->  pivote.getGrupo().equals("90 dias") ).collect(Collectors.toList());
        this.dyaxTblPivotList120Dias=resultadoLista.stream().filter(pivote->  pivote.getGrupo().equals("120 dias") ).collect(Collectors.toList());
        this.dyaxTblPivotList150Dias=resultadoLista.stream().filter(pivote->  pivote.getGrupo().equals("150 dias") ).collect(Collectors.toList());
            */
    }

    public List<ComportamientoPivote> getDtblComportamientoPivotes() {
        return dtblComportamientoPivotes;
    }

    public void setDtblComportamientoPivotes(List<ComportamientoPivote> dtblComportamientoPivotes) {
        this.dtblComportamientoPivotes = dtblComportamientoPivotes;
    }

    public List<ColumnasComportamiento> getDataTableColumns() {
        return dataTableColumns;
    }

    public void setDataTableColumns(List<ColumnasComportamiento> dataTableColumns) {
        this.dataTableColumns = dataTableColumns;
    }

    public List<ColumnasComportamiento> getDataTableSubColumns() {
        return dataTableSubColumns;
    }

    public void setDataTableSubColumns(List<ColumnasComportamiento> dataTableSubColumns) {
        this.dataTableSubColumns = dataTableSubColumns;
    }

    public Integer getAnioSelecionado() {
        return anioSelecionado;
    }

    public void setAnioSelecionado(Integer anioSelecionado) {
        this.anioSelecionado = anioSelecionado;
    }

    public List<ComportamientoPivote> getDtblComportamientoPivotesFiltered() {
        return dtblComportamientoPivotesFiltered;
    }

    public void setDtblComportamientoPivotesFiltered(List<ComportamientoPivote> dtblComportamientoPivotesFiltered) {
        this.dtblComportamientoPivotesFiltered = dtblComportamientoPivotesFiltered;
    }


    public void postProcessXLS(Object document) {

        HashSet omitirColumnas = new HashSet();
        //add elements to HashSet object
        omitirColumnas.add(new Integer("0"));
        omitirColumnas.add(new Integer("1"));
        omitirColumnas.add(new Integer("2"));
        omitirColumnas.add(new Integer("4"));
        omitirColumnas.add(new Integer("5"));
        omitirColumnas.add(new Integer("6"));
        omitirColumnas.add(new Integer("8"));
        omitirColumnas.add(new Integer("9"));
        omitirColumnas.add(new Integer("11"));
        omitirColumnas.add(new Integer("12"));
        omitirColumnas.add(new Integer("14"));
        omitirColumnas.add(new Integer("15"));
        omitirColumnas.add(new Integer("17"));
        omitirColumnas.add(new Integer("18"));
        omitirColumnas.add(new Integer("20"));
        omitirColumnas.add(new Integer("21"));

        FormatoExcelPoi.formatearArchivoExcel(document,omitirColumnas,0);

    }

    public void exportarExcelComportamiento(){

       rpt= new Reporte();
       parametros= new HashMap<String, Object>();

        String path="/reportes/CXCComportamientoPagoPivote.jasper";

        try{

            rpt.exportarXLS(parametros,path,"comportamientoPagoPivote");
        } catch (JRException e) {
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error",e.getMessage()));
        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error",e.getMessage()));
        }


    }

    public Reporte getRpt() {
        return rpt;
    }

    public void setRpt(Reporte rpt) {
        this.rpt = rpt;
    }

    public HashMap<String, Object> getParametros() {
        return parametros;
    }

    public void setParametros(HashMap<String, Object> parametros) {
        this.parametros = parametros;
    }
}


