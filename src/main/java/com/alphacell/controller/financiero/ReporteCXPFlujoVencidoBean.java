package com.alphacell.controller.financiero;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.alphacell.model.financiero.TmpCxpFlujoVencidos;
import com.alphacell.repository.cxp.FlujoVencidosRepository;
import com.alphacell.util.jpa.filter.FlujoVencidoFilter;
import com.alphacell.util.jsf.FormatoExcelPoi;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by luis on 18/08/16.
 */

@Named
@ViewScoped
public class ReporteCXPFlujoVencidoBean implements Serializable {

    private static final long serialVersionUID = 9004947568336717399L;

    private List<TmpCxpFlujoVencidos> tableCxpFlujoVencido;
    private List<TmpCxpFlujoVencidos> tableCxpFlujoVencidoFiltered;

    private FlujoVencidoFilter flujoVencidoFilter;


    @Inject
    private FlujoVencidosRepository flujoVencidosRepository;


    @PostConstruct
    public void init()
    {
        //Aqui voy a llamar al metodo del repositorio para llenar el datatable
        flujoVencidoFilter= new FlujoVencidoFilter();
        tableCxpFlujoVencido=new ArrayList<>();
        tableCxpFlujoVencidoFiltered= new ArrayList<>();
        this.tableCxpFlujoVencido= this.flujoVencidosRepository.cargarTablaCXPFlujoVencidos();

    }

    public void postProcessXLS(Object document) {

        HashSet omitirColumnas = new HashSet();
        //add elements to HashSet object
        omitirColumnas.add(new Integer("0"));
        omitirColumnas.add(new Integer("1"));
        omitirColumnas.add(new Integer("2"));
        omitirColumnas.add(new Integer("3"));
        omitirColumnas.add(new Integer("4"));
        omitirColumnas.add(new Integer("6"));
        omitirColumnas.add(new Integer("7"));
        omitirColumnas.add(new Integer("8"));
        omitirColumnas.add(new Integer("9"));
        omitirColumnas.add(new Integer("10"));
        omitirColumnas.add(new Integer("11"));

        FormatoExcelPoi.formatearArchivoExcel(document,omitirColumnas,0);
    }

    public void buscarFiltro()
    {
        //this.tableCxpFlujoVencidoFiltered=this.flujoVencidosRepository.filtrados(this.flujoVencidoFilter);

        if(StringUtils.isNotBlank(this.flujoVencidoFilter.getCodProveedor()) && this.flujoVencidoFilter.getFechaDocumento()!=null)
        {
            this.tableCxpFlujoVencido= this.tableCxpFlujoVencido.stream()
                    .filter(Objects::nonNull)
                    .filter(x-> (x.getAccountnum().equals(this.flujoVencidoFilter.getCodProveedor()) && ( x.getTransdate().compareTo(this.flujoVencidoFilter.getFechaDocumento()))<=0))
                    .collect(Collectors.toList());
        }
        else if(StringUtils.isNotBlank(this.flujoVencidoFilter.getCodProveedor()) && this.flujoVencidoFilter.getFechaDocumento()==null)
        {
            this.tableCxpFlujoVencido= this.tableCxpFlujoVencido.stream()
                    .filter(Objects::nonNull)
                    .filter((x)-> (x.getAccountnum().equals(this.flujoVencidoFilter.getCodProveedor()) ))
                    .collect(Collectors.toList());
        }
        else if(StringUtils.isBlank(this.flujoVencidoFilter.getCodProveedor()) && this.flujoVencidoFilter.getFechaDocumento()!=null)
        {
            this.tableCxpFlujoVencido= this.tableCxpFlujoVencido.stream()
                    .filter(Objects::nonNull)
                    .filter((x)-> (x.getTransdate().compareTo(this.flujoVencidoFilter.getFechaDocumento()))<=0)
                    .collect(Collectors.toList());
        }
        else
        {
            this.tableCxpFlujoVencido= this.flujoVencidosRepository.cargarTablaCXPFlujoVencidos();
        }
        //this.tableCxpFlujoVencido=this.tableCxpFlujoVencidoFiltered;
        //System.out.println(this.tableCxpFlujoVencidoFiltered);
    }



    public List<TmpCxpFlujoVencidos> getTableCxpFlujoVencido() {
        return tableCxpFlujoVencido;
    }

    public void setTableCxpFlujoVencido(List<TmpCxpFlujoVencidos> tableCxpFlujoVencido) {
        this.tableCxpFlujoVencido = tableCxpFlujoVencido;
    }

    public List<TmpCxpFlujoVencidos> getTableCxpFlujoVencidoFiltered() {
        return tableCxpFlujoVencidoFiltered;
    }

    public void setTableCxpFlujoVencidoFiltered(List<TmpCxpFlujoVencidos> tableCxpFlujoVencidoFiltered) {
        this.tableCxpFlujoVencidoFiltered = tableCxpFlujoVencidoFiltered;
    }

    public FlujoVencidoFilter getFlujoVencidoFilter() {
        return flujoVencidoFilter;
    }

    public void setFlujoVencidoFilter(FlujoVencidoFilter flujoVencidoFilter) {
        this.flujoVencidoFilter = flujoVencidoFilter;
    }
}
