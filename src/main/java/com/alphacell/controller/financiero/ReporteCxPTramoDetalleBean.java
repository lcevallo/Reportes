package com.alphacell.controller.financiero;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.alphacell.model.financiero.Tmpcxptramo;
import com.alphacell.model.financiero.Tmppivotcxp;
import com.alphacell.repository.ReporteCxPxTramosRepository;
import com.alphacell.util.jsf.FormatoExcelPoi;

/**
 * Created by luis on 24/05/16.
 */
@Named
@ViewScoped
public class ReporteCxPTramoDetalleBean implements Serializable {

    private static final long serialVersionUID = 772453453616376201L;
    private Tmppivotcxp tmppivotcxp;
    private List<Tmpcxptramo> tmpcxptramoList;
    private String nombreProveedor;
    private String codigoProveedor;

    private BigDecimal valorAPagar;

    @Inject
    private ReporteCxPxTramosRepository reporteCxPxTramosRepository;



    public void invocarDialogo(Tmppivotcxp record, Date fecha_inicial, int num_semana)
    {

        this.tmppivotcxp=record;
        this.codigoProveedor=record.getAccountnum();
        this.nombreProveedor=record.getNombreCliente();

        switch (num_semana){
            case 1:
                this.valorAPagar=record.getPrimeraSemana();
                break;
            case 2:
                this.valorAPagar=record.getSegundaSemana();
                break;
            case 3:
                this.valorAPagar=record.getTerceraSemana();
                break;
            case 4:
                this.valorAPagar=record.getCuartaSemana();
                break;
            case 5:
                this.valorAPagar=record.getQuintaSemana();
                break;
            case 6:
                this.valorAPagar=record.getSextaSemana();
                break;
            case 7:
                this.valorAPagar=record.getSeptimaSemana();
                break;
            case 8:
                this.valorAPagar=record.getOctavaSemana();
                break;
            case 9:
                this.valorAPagar=record.getNovenaSemana();
                break;
            case 10:
                this.valorAPagar=record.getDecimaSemana();
                break;
            case 11:
                this.valorAPagar=record.getOnceavaSemana();
                break;
            case 12:
                this.valorAPagar=record.getDoceavaSemana();
                break;
            default:
                this.valorAPagar=new BigDecimal(0);
                break;
        }


        RequestContext context = RequestContext.getCurrentInstance();
        this.tmpcxptramoList=reporteCxPxTramosRepository.buscarDetalleTramosXSemana(fecha_inicial,record.getAccountnum(),num_semana);

        RequestContext.getCurrentInstance().update("cxpTramoDialog");
        RequestContext.getCurrentInstance().update("cxpTramoDetalleTbl");

        context.execute("PF('cxpTramoDialogWidget').show();");

    }

    public void postProcessXLS(Object document) {

        HashSet omitirColumnas = new HashSet();
        //add elements to HashSet object

        omitirColumnas.add(new Integer("0"));
        omitirColumnas.add(new Integer("1"));
        omitirColumnas.add(new Integer("2"));

        FormatoExcelPoi.formatearArchivoExcel(document,omitirColumnas,0);

    }


    public Tmppivotcxp getTmppivotcxp() {
        return tmppivotcxp;
    }

    public void setTmppivotcxp(Tmppivotcxp tmppivotcxp) {
        this.tmppivotcxp = tmppivotcxp;
    }

    public List<Tmpcxptramo> getTmpcxptramoList() {
        return tmpcxptramoList;
    }

    public void setTmpcxptramoList(List<Tmpcxptramo> tmpcxptramoList) {
        this.tmpcxptramoList = tmpcxptramoList;
    }


    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getCodigoProveedor() {
        return codigoProveedor;
    }

    public void setCodigoProveedor(String codigoProveedor) {
        this.codigoProveedor = codigoProveedor;
    }



    public BigDecimal getValorAPagar() {
        return valorAPagar;
    }

    public void setValorAPagar(BigDecimal valorAPagar) {
        this.valorAPagar = valorAPagar;
    }
}