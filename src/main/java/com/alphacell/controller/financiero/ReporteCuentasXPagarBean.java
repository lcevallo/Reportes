package com.alphacell.controller.financiero;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.alphacell.model.financiero.LcTblCxp;
import com.alphacell.model.financiero.LcVistaProveedoresAlpha;
import com.alphacell.repository.CuentasxPagarRepository;
import com.alphacell.util.jsf.FormatoExcelPoi;

/**
 * Created by luis.cevallos on 20/4/2016.
 */
@Named
@ViewScoped
public class ReporteCuentasXPagarBean implements Serializable{

    private static final long serialVersionUID = 4598305325245546588L;

    private LcVistaProveedoresAlpha proveedor;
    private String tipoProveedor;
    private Date fechaInicial;
    private Date fechaFinal;
    private List<LcTblCxp> dtblCuentasXPagar;
    private LcTblCxp recordTblCxP;

    @Inject
    CuentasxPagarRepository cuentasxPagarRepository;

    public LcVistaProveedoresAlpha getProveedor() {
        return proveedor;
    }

    public void setProveedor(LcVistaProveedoresAlpha proveedor) {
        this.proveedor = proveedor;
    }


    public String getTipoProveedor() {
        return tipoProveedor;
    }

    public void setTipoProveedor(String tipoProveedor) {
        this.tipoProveedor = tipoProveedor;
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

    public void adicionarMensaje(String msg)
    {
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(msg));
    }

    public List<LcVistaProveedoresAlpha> sugerirProveedor(String input)
    {
        List<LcVistaProveedoresAlpha> listaSugerida= new ArrayList<LcVistaProveedoresAlpha>();
        listaSugerida=cuentasxPagarRepository.findByNombre(input);

        return listaSugerida;
    }

    public void cargarTablaReporte()
    {

       // System.out.println("El codigo del proveedor es: "+this.proveedor.getAccountnum());

        //CASO DE SOLO FECHAS
        this.dtblCuentasXPagar=this.cuentasxPagarRepository.obtenerDataTableFechas(this.fechaInicial,this.fechaFinal);
        RequestContext context = RequestContext.getCurrentInstance();

    }

    public List<LcTblCxp> getDtblCuentasXPagar() {
        return dtblCuentasXPagar;
    }

    public void setDtblCuentasXPagar(List<LcTblCxp> dtblCuentasXPagar) {
        this.dtblCuentasXPagar = dtblCuentasXPagar;
    }

    public LcTblCxp getRecordTblCxP() {
        return recordTblCxP;
    }

    public void setRecordTblCxP(LcTblCxp recordTblCxP) {
        this.recordTblCxP = recordTblCxP;
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
        omitirColumnas.add(new Integer("7"));

        FormatoExcelPoi.formatearArchivoExcel(document,omitirColumnas,0);

    }


}
