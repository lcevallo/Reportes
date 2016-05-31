package com.alphacell.controller.financiero;

import com.alphacell.model.financiero.Tablaflujo;
import com.alphacell.repository.CxCFlujoRepository;
import com.alphacell.util.jsf.FormatoExcelPoi;
import org.primefaces.context.RequestContext;


import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * Created by luis.cevallos on 4/4/2016.
 */

@Named
@ViewScoped
public class CxCFlujoDetalleBean implements Serializable{
    private static final long serialVersionUID = 3390753376056480782L;
    private List<Tablaflujo> tablaflujosList;
    private String codigoCliente;
    private String nombreCliente;
    private BigDecimal deuda;
    private Integer semana;
    private String Rango;

    @Inject
    private CxCFlujoRepository cxCFlujoRepository;

    @Inject
    private CxCFlujoBean cxCFlujoBean;

    public List<Tablaflujo> getTablaflujosList() {
        return tablaflujosList;
    }

    public void setTablaflujosList(List<Tablaflujo> tablaflujosList) {
        this.tablaflujosList = tablaflujosList;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public Integer getSemana() {
        return semana;
    }

    public void setSemana(Integer semana) {
        this.semana = semana;
    }

    public String getRango() {
        return Rango;
    }

    public void setRango(String rango) {
        Rango = rango;
    }

    public CxCFlujoRepository getCxCFlujoRepository() {
        return cxCFlujoRepository;
    }

    public void setCxCFlujoRepository(CxCFlujoRepository cxCFlujoRepository) {
        this.cxCFlujoRepository = cxCFlujoRepository;
    }


    public void encontrarDetalleCliente(Date fechaInicial)
    {
        this.tablaflujosList=this.cxCFlujoRepository.buscarFlujoSemanas(fechaInicial,this.codigoCliente,this.semana);

    }

    public void invocarDialogo(String nombre_cliente, String codigoCliente, BigDecimal deuda,Integer semana)
    {
        this.codigoCliente=codigoCliente;
        this.nombreCliente=nombre_cliente;
        this.deuda=deuda;
        this.semana=semana;

        RequestContext context = RequestContext.getCurrentInstance();

        this.encontrarDetalleCliente(cxCFlujoBean.getSemanaInicialList().get(0));


        RequestContext.getCurrentInstance().update("cxcFlujoDialog");
        RequestContext.getCurrentInstance().update("cxcFlujoDetalleTbl");

        context.execute("PF('cxcFlujoDialogWidget').show();");


    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public BigDecimal getDeuda() {
        return deuda;
    }

    public void setDeuda(BigDecimal deuda) {
        this.deuda = deuda;
    }


    public void postProcessXLS(Object document) {

        HashSet omitirColumnas = new HashSet();
        //add elements to HashSet object
        omitirColumnas.add(new Integer("0"));
        omitirColumnas.add(new Integer("1"));
        omitirColumnas.add(new Integer("2"));
        omitirColumnas.add(new Integer("3"));

        FormatoExcelPoi.formatearArchivoExcel(document,omitirColumnas);

    }
}
