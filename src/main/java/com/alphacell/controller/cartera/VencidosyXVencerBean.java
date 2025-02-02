package com.alphacell.controller.cartera;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.alphacell.model.cartera.TmpCxcVyv;
import com.alphacell.repository.cxc.VencidosYxVencerRepository;
import com.alphacell.util.jsf.FormatoExcelPoi;

/**
 * Created by luis on 25/08/16.
 */
@Named
@ViewScoped
public class VencidosyXVencerBean implements Serializable{

    private static final long serialVersionUID = -3733880695499133729L;

    private List<TmpCxcVyv>  tableVencidosyXVencer;
    private List<TmpCxcVyv>  tableVencidosyXVencerFiltered;


    @Inject
    private VencidosYxVencerRepository vencidosYxVencerRepository;


    @PostConstruct
    public void init()
    {
        //Aqui voy a llamar al metodo del repositorio para llenar el datatable
        this.tableVencidosyXVencer= this.vencidosYxVencerRepository.cargarTablaCXCVencidosyXVencer();

    }

    @SuppressWarnings("unchecked")
	public void postProcessXLS(Object document) {

        HashSet<Integer> omitirColumnas = new HashSet<Integer>();
        //add elements to HashSet object

        omitirColumnas.add(new Integer("1"));
        omitirColumnas.add(new Integer("2"));
        omitirColumnas.add(new Integer("3"));
        omitirColumnas.add(new Integer("4"));
        omitirColumnas.add(new Integer("5"));
        omitirColumnas.add(new Integer("6"));
        omitirColumnas.add(new Integer("7"));
        omitirColumnas.add(new Integer("9"));
        omitirColumnas.add(new Integer("10"));
        omitirColumnas.add(new Integer("11"));
        omitirColumnas.add(new Integer("12"));
        omitirColumnas.add(new Integer("8"));

        FormatoExcelPoi.formatearArchivoExcel(document,omitirColumnas,0);

    }


    public List<TmpCxcVyv> getTableVencidosyXVencer() {
        return tableVencidosyXVencer;
    }

    public void setTableVencidosyXVencer(List<TmpCxcVyv> tableVencidosyXVencer) {
        this.tableVencidosyXVencer = tableVencidosyXVencer;
    }

    public List<TmpCxcVyv> getTableVencidosyXVencerFiltered() {
        return tableVencidosyXVencerFiltered;
    }

    public void setTableVencidosyXVencerFiltered(List<TmpCxcVyv> tableVencidosyXVencerFiltered) {
        this.tableVencidosyXVencerFiltered = tableVencidosyXVencerFiltered;
    }
}
