package com.alphacell.controller.cartera;


import java.io.Serializable;
import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.alphacell.model.cartera.TmpPvtHistorialProtestoAfter;
import com.alphacell.repository.HistorialProtestoRepository;
import com.alphacell.util.jsf.FormatoExcelPoi;

/**
 * Created by luis.cevallos on 4/4/2016.
 */
@Named
@ViewScoped
public class HistorialProtestoBean implements Serializable {
    private static final long serialVersionUID = -8966596974956834421L;

    private List<TmpPvtHistorialProtestoAfter> tmpPvtHistorialProtestoAfterList;
    private List<TmpPvtHistorialProtestoAfter> tmpPvtHistorialProtestoAfterListFiltered ;

    private TmpPvtHistorialProtestoAfter tmpPvtHistorialProtestoAfterSelected;

    @Inject
    private HistorialProtestoRepository historialProtestoRepository;

    public List<TmpPvtHistorialProtestoAfter> getTmpPvtHistorialProtestoAfterList() {
        return tmpPvtHistorialProtestoAfterList;
    }

    public void setTmpPvtHistorialProtestoAfterList(List<TmpPvtHistorialProtestoAfter> tmpPvtHistorialProtestoAfterList) {
        this.tmpPvtHistorialProtestoAfterList = tmpPvtHistorialProtestoAfterList;
    }

    public List<TmpPvtHistorialProtestoAfter> getTmpPvtHistorialProtestoAfterListFiltered() {
        return tmpPvtHistorialProtestoAfterListFiltered;
    }

    public void setTmpPvtHistorialProtestoAfterListFiltered(List<TmpPvtHistorialProtestoAfter> tmpPvtHistorialProtestoAfterListFiltered) {
        this.tmpPvtHistorialProtestoAfterListFiltered = tmpPvtHistorialProtestoAfterListFiltered;
    }

    public TmpPvtHistorialProtestoAfter getTmpPvtHistorialProtestoAfterSelected() {
        return tmpPvtHistorialProtestoAfterSelected;
    }

    public void setTmpPvtHistorialProtestoAfterSelected(TmpPvtHistorialProtestoAfter tmpPvtHistorialProtestoAfterSelected) {
        this.tmpPvtHistorialProtestoAfterSelected = tmpPvtHistorialProtestoAfterSelected;
    }

    @PostConstruct
    public void generarTablaPivote()
    {
        this.tmpPvtHistorialProtestoAfterList=this.historialProtestoRepository.buscarPivoteHistorialProtesto();
    }



    public void postProcessXLS(Object document) {

        HashSet omitirColumnas = new HashSet();
        //add elements to HashSet object
        omitirColumnas.add(new Integer("0"));
        omitirColumnas.add(new Integer("1"));

        FormatoExcelPoi.formatearArchivoExcel(document,omitirColumnas,0);

    }
}
