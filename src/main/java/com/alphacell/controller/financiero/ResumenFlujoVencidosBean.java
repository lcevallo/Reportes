/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphacell.controller.financiero;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.alphacell.model.financiero.CXPSumatoriaVencidas;
import com.alphacell.repository.cxp.FlujoVencidosRepository;
import com.alphacell.util.jsf.FormatoExcelPoi;


/**
 *
 * @author luis
 */
@Named
@ViewScoped
public class ResumenFlujoVencidosBean implements Serializable{

    private static final long serialVersionUID = 1L;

    private List<CXPSumatoriaVencidas> tableResumen;
    private List<CXPSumatoriaVencidas>  tableResumenFiltered;

    @Inject
    private FlujoVencidosRepository flujoVencidosRepository;

    @PostConstruct
    public void init()
    {
        this.tableResumen=this.flujoVencidosRepository.cargarResumenFlujoVencido();
    }

    public List<CXPSumatoriaVencidas> getTableResumen() {
        return tableResumen;
    }

    public void setTableResumen(List<CXPSumatoriaVencidas> tableResumen) {
        this.tableResumen = tableResumen;
    }

    public List<CXPSumatoriaVencidas> getTableResumenFiltered() {
        return tableResumenFiltered;
    }

    public void setTableResumenFiltered(List<CXPSumatoriaVencidas> tableResumenFiltered) {
        this.tableResumenFiltered = tableResumenFiltered;
    }


    public void postProcessXLS(Object document) {

        HashSet omitirColumnas = new HashSet();
        //add elements to HashSet object
        omitirColumnas.add(new Integer("0"));
        omitirColumnas.add(new Integer("1"));



        FormatoExcelPoi.formatearArchivoExcel(document,omitirColumnas,0);

    }

}