package com.alphacell.controller.financiero;

import com.alphacell.model.financiero.LcTblCxp;
import com.alphacell.model.financiero.Tmppivotcxp;
import com.alphacell.repository.ReporteCxPxTramosRepository;
import com.alphacell.util.ManejoFechas;
import com.alphacell.util.jsf.FormatoExcelPoi;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * Created by luis.cevallos on 26/4/2016.
 */
@Named
@ViewScoped
public class ReporteCxPTramoBean implements Serializable {
    private static final long serialVersionUID = -3689336948294382603L;

    private List<Date> semanaInicialList;
    private Date fechaInicialBusqueda;
    private List<Tmppivotcxp>  tablaCxPPivoteList;
    private List<Tmppivotcxp>  tablaCxPPivoteListFiltered;
    private Tmppivotcxp tmppivotcxpSelected;

    @Inject
    private ReporteCxPxTramosRepository reporteCxPxTramosRepository;



    public List<Tmppivotcxp> getTablaCxPPivoteList() {
        return tablaCxPPivoteList;
    }

    public void setTablaCxPPivoteList(List<Tmppivotcxp> tablaCxPPivoteList) {
        this.tablaCxPPivoteList = tablaCxPPivoteList;
    }

    public List<Tmppivotcxp> getTablaCxPPivoteListFiltered() {
        return tablaCxPPivoteListFiltered;
    }

    public void setTablaCxPPivoteListFiltered(List<Tmppivotcxp> tablaCxPPivoteListFiltered) {
        this.tablaCxPPivoteListFiltered = tablaCxPPivoteListFiltered;
    }




    public Date getFechaInicialBusqueda() {
        return fechaInicialBusqueda;
    }

    public void setFechaInicialBusqueda(Date fechaInicialBusqueda) {
        this.fechaInicialBusqueda = fechaInicialBusqueda;
    }

    public List<Date> getSemanaInicialList() {
        return semanaInicialList;
    }

    public void setSemanaInicialList(List<Date> semanaInicialList) {
        this.semanaInicialList = semanaInicialList;
    }


   public void generarTablaPivoteada()
   {
       this.semanaInicialList = ManejoFechas.FechasExtremas(this.fechaInicialBusqueda);
       this.tablaCxPPivoteList=reporteCxPxTramosRepository.buscarTramosXSemana(this.semanaInicialList.get(0));

   }


    public Tmppivotcxp getTmppivotcxpSelected() {
        return tmppivotcxpSelected;
    }

    public void setTmppivotcxpSelected(Tmppivotcxp tmppivotcxpSelected) {
        this.tmppivotcxpSelected = tmppivotcxpSelected;
    }

    public void postProcessXLS(Object document) {

        HashSet omitirColumnas = new HashSet();
        //add elements to HashSet object
        omitirColumnas.add(new Integer("0"));
        omitirColumnas.add(new Integer("1"));

        FormatoExcelPoi.formatearArchivoExcel(document,omitirColumnas);

    }

}
