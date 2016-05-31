package com.alphacell.controller.financiero;

import com.alphacell.model.Semanatemp;
import com.alphacell.model.cartera.TablaCxcpivote;
import com.alphacell.repository.CxCFlujoRepository;
import com.alphacell.util.ManejoFechas;
import com.alphacell.util.reporte.Reporte;
import net.sf.jasperreports.engine.JRException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by luis.cevallos on 30/3/2016.
 */
@Named
@ViewScoped
public class CxCFlujoBean implements Serializable {


    private static final long serialVersionUID = 1L;


    private List<Date> semanaInicialList;
    private List<Semanatemp> semanatempList;

    private List<TablaCxcpivote> tablaCxcpivoteList;
    private List<TablaCxcpivote> tablaCxcpivoteListFiltered;

    private TablaCxcpivote tablaCxcpivoteSelected;
    private Date fechaInicialBusqueda;
    private int  NumeroSemana;
    private Date fechaFinalBusqueda;
    private Reporte rpt;
    private HashMap<String, Object> parametros;

    @Inject
    private CxCFlujoRepository cxCFlujoRepository;



    public Date getFechaInicialBusqueda() {
        return fechaInicialBusqueda;
    }

    public void setFechaInicialBusqueda(Date fechaInicialBusqueda) {
        this.fechaInicialBusqueda = fechaInicialBusqueda;
    }

    public Date getFechaFinalBusqueda() {
        return fechaFinalBusqueda;
    }

    public void setFechaFinalBusqueda(Date fechaFinalBusqueda) {
        this.fechaFinalBusqueda = fechaFinalBusqueda;
    }

    public int getNumeroSemana() {
        return NumeroSemana;
    }

    public void setNumeroSemana(int numeroSemana) {
        NumeroSemana = numeroSemana;
    }


    public void generarNumeroSemana(){
        this.NumeroSemana= ManejoFechas.getNumeroSemana(this.fechaInicialBusqueda);
        this.semanaInicialList =ManejoFechas.FechasExtremas(this.fechaInicialBusqueda);
        Iterator i = this.semanaInicialList.iterator();
        while (i.hasNext())
        {
            Date fecha2 = (Date) i.next();
        }
    }

    public void cargarTablaFlujo()
    {
        this.semanatempList=cxCFlujoRepository.buscar8Semanas(this.semanaInicialList.get(0));
        this.tablaCxcpivoteList=cxCFlujoRepository.buscarPivoteFlujo(this.semanaInicialList.get(0));
    }

    public void exportarExcelFlujoCxCFinanciero(){

        rpt= new Reporte();
        parametros= new HashMap<String, Object>();

        LocalDate localDate = this.semanaInicialList.get(0).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year  = localDate.getYear();
        int month = localDate.getMonthValue();
        int day   = localDate.getDayOfMonth();


        parametros.put("dia",day);
        parametros.put("mes",month);
        parametros.put("anio",year);


        String path="/reportes/CxCFlujoFinanciero.jasper";


        try{

            rpt.exportarXLS(parametros,path,"CxCFlujoFinanciero");
        } catch (JRException e) {
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error",e.getMessage()));
        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error",e.getMessage()));
        }


    }


    public List<Date> getSemanaInicialList() {
        return semanaInicialList;
    }

    public void setSemanaInicialList(List<Date> semanaInicialList) {
        this.semanaInicialList = semanaInicialList;
    }


    public List<TablaCxcpivote> getTablaCxcpivoteList() {
        return tablaCxcpivoteList;
    }

    public void setTablaCxcpivoteList(List<TablaCxcpivote> tablaCxcpivoteList) {
        this.tablaCxcpivoteList = tablaCxcpivoteList;
    }

    public TablaCxcpivote getTablaCxcpivoteSelected() {
        return tablaCxcpivoteSelected;
    }

    public void setTablaCxcpivoteSelected(TablaCxcpivote tablaCxcpivoteSelected) {
        this.tablaCxcpivoteSelected = tablaCxcpivoteSelected;
    }

    public List<TablaCxcpivote> getTablaCxcpivoteListFiltered() {
        return tablaCxcpivoteListFiltered;
    }

    public void setTablaCxcpivoteListFiltered(List<TablaCxcpivote> tablaCxcpivoteListFiltered) {
        this.tablaCxcpivoteListFiltered = tablaCxcpivoteListFiltered;
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
