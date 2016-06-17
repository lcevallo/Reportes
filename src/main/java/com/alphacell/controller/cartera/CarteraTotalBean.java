package com.alphacell.controller.cartera;

import com.alphacell.model.cartera.TmpCarteraTotalPvt;
import com.alphacell.repository.cxc.CarteraTotalRepository;
import com.alphacell.util.jsf.FormatoExcelPoi;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;

/**
 * Created by luis.cevallos on 6/4/2016.
 */
@Named
@ViewScoped
public class CarteraTotalBean implements Serializable {
    private static final long serialVersionUID = 9030397488997166423L;

    List<TmpCarteraTotalPvt> tmpCarteraTotalPvtList;
    List<TmpCarteraTotalPvt> tmpCarteraTotalPvtListFiltered;

    TmpCarteraTotalPvt tmpCarteraTotalPvtSelected;

    @Inject
    private CarteraTotalRepository carteraTotalRepository;

    public List<TmpCarteraTotalPvt> getTmpCarteraTotalPvtList() {
        return tmpCarteraTotalPvtList;
    }

    public void setTmpCarteraTotalPvtList(List<TmpCarteraTotalPvt> tmpCarteraTotalPvtList) {
        this.tmpCarteraTotalPvtList = tmpCarteraTotalPvtList;
    }

    public List<TmpCarteraTotalPvt> getTmpCarteraTotalPvtListFiltered() {
        return tmpCarteraTotalPvtListFiltered;
    }

    public void setTmpCarteraTotalPvtListFiltered(List<TmpCarteraTotalPvt> tmpCarteraTotalPvtListFiltered) {
        this.tmpCarteraTotalPvtListFiltered = tmpCarteraTotalPvtListFiltered;
    }

    public TmpCarteraTotalPvt getTmpCarteraTotalPvtSelected() {
        return tmpCarteraTotalPvtSelected;
    }

    public void setTmpCarteraTotalPvtSelected(TmpCarteraTotalPvt tmpCarteraTotalPvtSelected) {
        this.tmpCarteraTotalPvtSelected = tmpCarteraTotalPvtSelected;
    }

    @PostConstruct
    public void generarCarteraPivoteada()
    {
        this.tmpCarteraTotalPvtList= this.carteraTotalRepository.cargarTablaCarteraPivote();
    }


    public void postProcessXLS(Object document) {

        HashSet omitirColumnas = new HashSet();
        //add elements to HashSet object
        omitirColumnas.add(new Integer("0"));
        omitirColumnas.add(new Integer("1"));

        FormatoExcelPoi.formatearArchivoExcel(document,omitirColumnas);

    }

}
