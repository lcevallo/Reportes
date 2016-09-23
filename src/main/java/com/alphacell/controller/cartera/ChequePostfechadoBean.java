package com.alphacell.controller.cartera;

import com.alphacell.model.cartera.LcVistaChequesPostfechados;
import com.alphacell.repository.cxc.CXCChequesPostfechadosRepository;
import org.primefaces.event.SelectEvent;

import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    private List<LcVistaChequesPostfechados> tableChequesPostfechados= new ArrayList<LcVistaChequesPostfechados>();
    private List<LcVistaChequesPostfechados> tableChequesPostfechadosFiltered= new ArrayList<LcVistaChequesPostfechados>();

    @Inject
    private CXCChequesPostfechadosRepository cxcChequesPostfechadosRepository;




    public void consultarPorFecha()
    {
        if(this.activarFecha)
        this.tableChequesPostfechados= this.cxcChequesPostfechadosRepository.obtenerPorFecha(this.fechaUnica);
        else
        this.tableChequesPostfechados=this.cxcChequesPostfechadosRepository.obtenerEntreFechas(this.fechaInicial,this.fechaFinal);


    }

    public void postProcessXLS()
    {

    }

    public void cambiarFormaBusqueda(ValueChangeEvent e)
    {
       this.activarFecha=(Boolean) e.getNewValue();

        if(this.activarFecha)
            this.activarEntreFecha=false;
        else
            this.activarEntreFecha=true;

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
