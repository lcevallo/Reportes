package com.alphacell.controller.cartera;

import com.alphacell.model.cartera.LcVistaChequesPostfechados;
import com.alphacell.repository.cxc.CXCChequesPostfechadosRepository;
import com.alphacell.util.jsf.FormatoExcelPoi;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by luis on 23/09/16.
 */
@Named
@ViewScoped
public class ChequePostfechadoBean implements Serializable{


    private static final long serialVersionUID = 815826429781636871L;

    private Date fechaUnica;
    private Date fechaInicial;
    private Date fechaFinal;
    private Boolean activarFecha=false;
    private Boolean activarEntreFecha=false;

    private List<LcVistaChequesPostfechados> tableChequesPostfechados;
    private List<LcVistaChequesPostfechados> tableChequesPostfechadosFiltered;

    @Inject
    private CXCChequesPostfechadosRepository cxcChequesPostfechadosRepository;


    public void consultarPorFecha2() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        this.fechaUnica= sdf.parse("27/9/2016");

        this.tableChequesPostfechados= this.cxcChequesPostfechadosRepository.obtenerPorFecha(this.fechaUnica);
    }


    public void consultarPorFecha()
    {
        if(this.activarFecha && this.fechaUnica!=null){
            this.tableChequesPostfechados= this.cxcChequesPostfechadosRepository.obtenerPorFecha(this.fechaUnica);
        }

        else if(this.fechaInicial!=null && this.fechaFinal!=null)
        {
            this.tableChequesPostfechados=this.cxcChequesPostfechadosRepository.obtenerEntreFechas(this.fechaInicial,this.fechaFinal);
        }



/*
        RequestContext.getCurrentInstance().update(
                Arrays.asList("frm-tableChPfC:table-ChequePostF"));
*/
    }

    public void onDateSelectFechaIni()
    {
        System.out.println("p:ajax event=\"blur\" listener=\"#{chequePostfechadoBean.onDateSelectFechaIni}\" ");
    }
    public void onDateSelectFechaFin()
    {
        System.out.println(" p:ajax event=\"blur\" listener=\"#{chequePostfechadoBean.onDateSelectFechaFin}\"  ");
    }

    public void postProcessXLS(Object document)
    {
        HashSet omitirColumnas = new HashSet();
        //add elements to HashSet object
        omitirColumnas.add(new Integer("1"));
        omitirColumnas.add(new Integer("2"));
        omitirColumnas.add(new Integer("3"));
        omitirColumnas.add(new Integer("4"));
        omitirColumnas.add(new Integer("5"));
        omitirColumnas.add(new Integer("7"));

        FormatoExcelPoi.formatearArchivoExcel(document,omitirColumnas);
    }

    public void cambiarFormaBusqueda()
    {
        if (this.tableChequesPostfechados!=null)
            this.tableChequesPostfechados.clear();

        if(this.activarFecha)
        {
            this.activarEntreFecha=false;
            this.fechaInicial=null;
            this.fechaFinal=null;
        }

        else
        {
            this.activarEntreFecha=true;
            this.fechaUnica=null;
        }



        RequestContext.getCurrentInstance().update(
                Arrays.asList("frm-CXCChequePostF","frm-tableChPfC:table-ChequePostF"));


    }

    public Date getFechaUnica() {
        return fechaUnica;
    }

    public void setFechaUnica(Date fechaUnica) {
        this.fechaUnica = fechaUnica;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public List<LcVistaChequesPostfechados> getTableChequesPostfechados() {
        return tableChequesPostfechados;
    }

    public void setTableChequesPostfechados(List<LcVistaChequesPostfechados> tableChequesPostfechados) {
        this.tableChequesPostfechados = tableChequesPostfechados;
    }

    public List<LcVistaChequesPostfechados> getTableChequesPostfechadosFiltered() {
        return tableChequesPostfechadosFiltered;
    }

    public void setTableChequesPostfechadosFiltered(List<LcVistaChequesPostfechados> tableChequesPostfechadosFiltered) {
        this.tableChequesPostfechadosFiltered = tableChequesPostfechadosFiltered;
    }

    public Boolean getActivarFecha() {
        return activarFecha;
    }

    public void setActivarFecha(Boolean activarFecha) {
        this.activarFecha = activarFecha;
    }

    public Boolean getActivarEntreFecha() {
        return activarEntreFecha;
    }

    public void setActivarEntreFecha(Boolean activarEntreFecha) {
        this.activarEntreFecha = activarEntreFecha;
    }
}
