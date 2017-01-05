package com.alphacell.controller.financiero;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.alphacell.model.financiero.TmpCxpFlujoVencidos;
import com.alphacell.repository.cxp.FlujoVencidosRepository;
import com.alphacell.util.jsf.FormatoExcelPoi;

/**
 * Created by luis on 18/08/16.
 */

@Named
@ViewScoped
public class ReporteCXPFlujoVencidoBean implements Serializable {

    private static final long serialVersionUID = 9004947568336717399L;

    private List<TmpCxpFlujoVencidos> tableCxpFlujoVencido;
    private List<TmpCxpFlujoVencidos>  tableCxpFlujoVencidoFiltered;

    @Inject
    private FlujoVencidosRepository flujoVencidosRepository;



    @PostConstruct
    public void init()
    {
        //Aqui voy a llamar al metodo del repositorio para llenar el datatable
        this.tableCxpFlujoVencido= this.flujoVencidosRepository.cargarTablaCXPFlujoVencidos();


    }

    public void postProcessXLS(Object document) {

        HashSet omitirColumnas = new HashSet();
        //add elements to HashSet object
        omitirColumnas.add(new Integer("0"));
        omitirColumnas.add(new Integer("1"));
        omitirColumnas.add(new Integer("2"));
        omitirColumnas.add(new Integer("3"));
        omitirColumnas.add(new Integer("4"));
        omitirColumnas.add(new Integer("6"));
        omitirColumnas.add(new Integer("7"));
        omitirColumnas.add(new Integer("8"));
        omitirColumnas.add(new Integer("9"));
        omitirColumnas.add(new Integer("10"));
        omitirColumnas.add(new Integer("11"));



        FormatoExcelPoi.formatearArchivoExcel(document,omitirColumnas,0);

    }

    public List<TmpCxpFlujoVencidos> getTableCxpFlujoVencido() {
        return tableCxpFlujoVencido;
    }

    public void setTableCxpFlujoVencido(List<TmpCxpFlujoVencidos> tableCxpFlujoVencido) {
        this.tableCxpFlujoVencido = tableCxpFlujoVencido;
    }

    public List<TmpCxpFlujoVencidos> getTableCxpFlujoVencidoFiltered() {
        return tableCxpFlujoVencidoFiltered;
    }

    public void setTableCxpFlujoVencidoFiltered(List<TmpCxpFlujoVencidos> tableCxpFlujoVencidoFiltered) {
        this.tableCxpFlujoVencidoFiltered = tableCxpFlujoVencidoFiltered;
    }


}
