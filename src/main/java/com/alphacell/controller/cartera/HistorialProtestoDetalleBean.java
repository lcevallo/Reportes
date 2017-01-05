package com.alphacell.controller.cartera;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.alphacell.model.cartera.Historialprotesto;
import com.alphacell.repository.HistorialProtestoRepository;
import com.alphacell.util.jsf.FormatoExcelPoi;

/**
 * Created by luis.cevallos on 4/4/2016.
 */
@Named
@ViewScoped
public class HistorialProtestoDetalleBean implements Serializable {
    private static final long serialVersionUID = -5403343795185866163L;

    private Integer mes;
    private String nombre_cliente;
    private String codigo_cliente;
    private List<Historialprotesto> historialprotestoList;

    @Inject
    private HistorialProtestoRepository historialProtestoRepository;

    public void invocarDialogo(String codigo,String nombre,Integer mes,Integer cantidad)
    {
        this.mes=mes;
        this.codigo_cliente=codigo;
        this.nombre_cliente=nombre;


        RequestContext context = RequestContext.getCurrentInstance();

        this.historialprotestoList=this.historialProtestoRepository.buscarHistorialProtesto(codigo,mes);

        RequestContext.getCurrentInstance().update("historialProtestoDialog");
        RequestContext.getCurrentInstance().update("historialProtestoDetalleTbl");

        context.execute("PF('historialProtestoDialogWidget').show();");


    }

    public List<Historialprotesto> getHistorialprotestoList() {
        return historialprotestoList;
    }

    public void setHistorialprotestoList(List<Historialprotesto> historialprotestoList) {
        this.historialprotestoList = historialprotestoList;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getCodigo_cliente() {
        return codigo_cliente;
    }

    public void setCodigo_cliente(String codigo_cliente) {
        this.codigo_cliente = codigo_cliente;
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
