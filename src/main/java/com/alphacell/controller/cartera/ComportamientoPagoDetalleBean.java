package com.alphacell.controller.cartera;

import com.alphacell.model.cartera.*;
import com.alphacell.repository.ComportamientoPagoRepository;
import com.alphacell.util.jsf.FormatoExcelPoi;
import org.primefaces.context.RequestContext;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;

/**
 * Created by luis.cevallos on 14/4/2016.
 */
@Named
@ViewScoped
public class ComportamientoPagoDetalleBean implements Serializable{

    private static final long serialVersionUID = 4285754518902857855L;

    private List<Tmpcxccomportamientodetalle> tmpcxccomportamientodetalleList;
    private List<Tmpcxccomportamientooffsetrecid> tmpcxccomportamientooffsetrecidList;

    private Map FechasDP;
    private ComportamientoPivote comportamientoPivoteSelected;

    @Inject
    private ComportamientoPagoRepository comportamientoPagoRepository;

    public List<Tmpcxccomportamientodetalle> getTmpcxccomportamientodetalleList() {
        return tmpcxccomportamientodetalleList;
    }

    public void setTmpcxccomportamientodetalleList(List<Tmpcxccomportamientodetalle> tmpcxccomportamientodetalleList) {
        this.tmpcxccomportamientodetalleList = tmpcxccomportamientodetalleList;
    }


    public void invocarDialogo(ComportamientoPivote cp,String columna)
    {
        RequestContext context = RequestContext.getCurrentInstance();
        this.comportamientoPivoteSelected=cp;
        this.FechasDP=new HashMap<String,Date>();

        //Agregamos las fechas
        this.FechasDP.put("fecha30P",cp.getFechas30P());
        this.FechasDP.put("fecha45P",cp.getFechas45P());
        this.FechasDP.put("fecha60P",cp.getFechas60P());
        this.FechasDP.put("fecha90P",cp.getFechas90P());
        this.FechasDP.put("fecha120P",cp.getFechas120P());
        this.FechasDP.put("fecha150P",cp.getFechas150P());

        String fechaSP="";

        switch (columna){
            case "30C":
                fechaSP="fecha30P";
                   break;
            case "45C":
                fechaSP="fecha45P";
                break;
            case "60C":
                fechaSP="fecha60P";;
                break;
            case "90C":
                fechaSP="fecha90P";
                break;
            case "120C":
                fechaSP="fecha120P";
                break;
            case "150C":
                fechaSP="fecha150P";
                break;

        }

        this.tmpcxccomportamientodetalleList=this.comportamientoPagoRepository.obtenerDetalleFacturasClientes(cp.getInvoice(),fechaSP,1,this.FechasDP);
        this.tmpcxccomportamientooffsetrecidList= this.comportamientoPagoRepository.obtenerDetalleFacturasClientesCobradas(cp.getInvoice(),fechaSP,2,this.FechasDP);
        RequestContext.getCurrentInstance().update("comportamientoPagoDialog");
        RequestContext.getCurrentInstance().update("comportamientoPagoDetalleTbl");
        RequestContext.getCurrentInstance().update("comportamientoPagoDetalleTbl2");
        context.execute("PF('comportamientoPagoDialogWidget').show();");
    }


    public ComportamientoPivote getComportamientoPivoteSelected() {
        return comportamientoPivoteSelected;
    }

    public void setComportamientoPivoteSelected(ComportamientoPivote comportamientoPivoteSelected) {
        this.comportamientoPivoteSelected = comportamientoPivoteSelected;
    }


    public void postProcessXLS(Object document) {

        HashSet omitirColumnas = new HashSet();
        //add elements to HashSet object
        omitirColumnas.add(new Integer("0"));
        omitirColumnas.add(new Integer("1"));
        omitirColumnas.add(new Integer("3"));

        FormatoExcelPoi.formatearArchivoExcel(document,omitirColumnas);

    }


    public void postProcessXLS2(Object document) {

        HashSet omitirColumnas = new HashSet();
        //add elements to HashSet object
        omitirColumnas.add(new Integer("0"));
        omitirColumnas.add(new Integer("1"));
        omitirColumnas.add(new Integer("2"));
        omitirColumnas.add(new Integer("4"));

        FormatoExcelPoi.formatearArchivoExcel(document,omitirColumnas);

    }

    public Map getFechasDP() {
        return FechasDP;
    }

    public void setFechasDP(Map fechasDP) {
        FechasDP = fechasDP;
    }


    public List<Tmpcxccomportamientooffsetrecid> getTmpcxccomportamientooffsetrecidList() {
        return tmpcxccomportamientooffsetrecidList;
    }

    public void setTmpcxccomportamientooffsetrecidList(List<Tmpcxccomportamientooffsetrecid> tmpcxccomportamientooffsetrecidList) {
        this.tmpcxccomportamientooffsetrecidList = tmpcxccomportamientooffsetrecidList;
    }
}
