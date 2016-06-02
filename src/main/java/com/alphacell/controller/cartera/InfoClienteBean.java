package com.alphacell.controller.cartera;

import com.alphacell.model.cartera.Cxcinfocliente;
import com.alphacell.repository.InfoClienteRepository;
import com.alphacell.util.jsf.FormatoExcelPoi;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;

/**
 * Created by luis on 02/06/16.
 */
@Named
@ViewScoped
public class InfoClienteBean implements Serializable {

    private static final long serialVersionUID = 6588996935406231871L;

    @Inject
    private InfoClienteRepository infoClienteRepository;

    private List<Cxcinfocliente> infoClienteTable;
    private List<Cxcinfocliente> infoClienteTableFiltered;


    @PostConstruct
    public void generarTablaPivote() {
        this.infoClienteTable = this.infoClienteRepository.buscarInformacionCliente();
    }


    public void postProcessXLS(Object document) {

        HashSet omitirColumnas = new HashSet();
        //add elements to HashSet object
        omitirColumnas.add(new Integer("0"));
        omitirColumnas.add(new Integer("1"));
        omitirColumnas.add(new Integer("2"));
        omitirColumnas.add(new Integer("4"));
        omitirColumnas.add(new Integer("5"));
        omitirColumnas.add(new Integer("6"));
        omitirColumnas.add(new Integer("7"));
        omitirColumnas.add(new Integer("8"));
        omitirColumnas.add(new Integer("9"));
        omitirColumnas.add(new Integer("10"));
        omitirColumnas.add(new Integer("11"));
        omitirColumnas.add(new Integer("12"));

        FormatoExcelPoi.formatearArchivoExcel(document, omitirColumnas);

    }

    public List<Cxcinfocliente> getInfoClienteTable() {
        return infoClienteTable;
    }

    public void setInfoClienteTable(List<Cxcinfocliente> infoClienteTable) {
        this.infoClienteTable = infoClienteTable;
    }

    public List<Cxcinfocliente> getInfoClienteTableFiltered() {
        return infoClienteTableFiltered;
    }

    public void setInfoClienteTableFiltered(List<Cxcinfocliente> infoClienteTableFiltered) {
        this.infoClienteTableFiltered = infoClienteTableFiltered;
    }
}
