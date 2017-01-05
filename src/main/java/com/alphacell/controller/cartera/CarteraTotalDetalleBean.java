package com.alphacell.controller.cartera;

import com.alphacell.model.cartera.TmpCarteraTotalXTramo1;
import com.alphacell.repository.cxc.CarteraTotalRepository;
import com.alphacell.util.jsf.FormatoExcelPoi;
import org.primefaces.context.RequestContext;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;

/**
 * Created by luis.cevallos on 6/4/2016.
 */
@Named
@ViewScoped
public class CarteraTotalDetalleBean implements Serializable {
    private static final long serialVersionUID = -3060389374192888390L;

    private String nombreCliente;
    private String codigoCliente;
    private BigDecimal valorTotalDeuda;
    private List<TmpCarteraTotalXTramo1> tmpCarteraTotalXTramo1List;
    private String[] grupoDias = {"YA VENCIDOS","15 dias", "30 dias", "45 dias","60 dias","90 dias","120 dias","Mayores a 120 dias"};

    @Inject
    private CarteraTotalRepository carteraTotalRepository;


    public void invocarDialogo (String codigo, String nombreCliente, BigDecimal valor,Integer columna)
    {
        this.codigoCliente=codigo;
        this.nombreCliente=nombreCliente;
        this.valorTotalDeuda=valor;
        RequestContext context = RequestContext.getCurrentInstance();

        this.tmpCarteraTotalXTramo1List=this.carteraTotalRepository.cargarInfoDialogoCartera(this.codigoCliente,this.grupoDias[columna-1]);

        RequestContext.getCurrentInstance().update("cartertaTotalDialog");
        RequestContext.getCurrentInstance().update("cartertaTotalDetalleTbl");

        context.execute("PF('cartertaTotalDialogWidget').show();");

    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public List<TmpCarteraTotalXTramo1> getTmpCarteraTotalXTramo1List() {
        return tmpCarteraTotalXTramo1List;
    }

    public void setTmpCarteraTotalXTramo1List(List<TmpCarteraTotalXTramo1> tmpCarteraTotalXTramo1List) {
        this.tmpCarteraTotalXTramo1List = tmpCarteraTotalXTramo1List;
    }

    public String[] getGrupoDias() {
        return grupoDias;
    }

    public void setGrupoDias(String[] grupoDias) {
        this.grupoDias = grupoDias;
    }

    public BigDecimal getValorTotalDeuda() {
        return valorTotalDeuda;
    }

    public void setValorTotalDeuda(BigDecimal valorTotalDeuda) {
        this.valorTotalDeuda = valorTotalDeuda;
    }


    public void postProcessXLS(Object document) {

        HashSet omitirColumnas = new HashSet();
        //add elements to HashSet object

        omitirColumnas.add(new Integer("0"));
        omitirColumnas.add(new Integer("1"));
        omitirColumnas.add(new Integer("2"));

        FormatoExcelPoi.formatearArchivoExcel(document,omitirColumnas,0);

    }

}
