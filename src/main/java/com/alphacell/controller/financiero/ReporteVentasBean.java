package com.alphacell.controller.financiero;

import com.alphacell.model.financiero.TblReporteVentas;
import com.alphacell.repository.ReporteVentasRepository;
import com.alphacell.util.jsf.FormatoExcelPoi;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * Created by admin on 05/04/2016.
 */
@Named
@ViewScoped
public class ReporteVentasBean implements Serializable {

    private static final long serialVersionUID = 3214132647744631330L;
    private Date fechaInicial;
    private Date fechaFinal;
    private List<TblReporteVentas> tblReporteVentasList;
    private List<TblReporteVentas> tblReporteVentasListFiltered;
    private TblReporteVentas tblReporteVentasSelected;

    @Inject
    private ReporteVentasRepository reporteVentasRepository;

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

    public List<TblReporteVentas> getTblReporteVentasList() {
        return tblReporteVentasList;
    }

    public void setTblReporteVentasList(List<TblReporteVentas> tblReporteVentasList) {
        this.tblReporteVentasList = tblReporteVentasList;
    }

    public List<TblReporteVentas> getTblReporteVentasListFiltered() {
        return tblReporteVentasListFiltered;
    }

    public void setTblReporteVentasListFiltered(List<TblReporteVentas> tblReporteVentasListFiltered) {
        this.tblReporteVentasListFiltered = tblReporteVentasListFiltered;
    }

    public TblReporteVentas getTblReporteVentasSelected() {
        return tblReporteVentasSelected;
    }

    public void setTblReporteVentasSelected(TblReporteVentas tblReporteVentasSelected) {
        this.tblReporteVentasSelected = tblReporteVentasSelected;
    }

    public void cargarTablaReporte() {
        this.tblReporteVentasList = this.reporteVentasRepository.obtenerReporteVentas(this.fechaInicial, this.fechaFinal);

    }

    public void postProcessXLS(Object document) {

        HashSet omitirColumnas = new HashSet();
        //add elements to HashSet object
        omitirColumnas.add(new Integer("0"));
        omitirColumnas.add(new Integer("1"));
        omitirColumnas.add(new Integer("2"));
        omitirColumnas.add(new Integer("3"));
        omitirColumnas.add(new Integer("4"));
        omitirColumnas.add(new Integer("5"));
        omitirColumnas.add(new Integer("6"));
        omitirColumnas.add(new Integer("9"));
        omitirColumnas.add(new Integer("13"));
        omitirColumnas.add(new Integer("15"));

        FormatoExcelPoi.formatearArchivoExcel(document,omitirColumnas);

    }

}
