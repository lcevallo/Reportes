/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphacell.controller.ventas;


import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.alphacell.model.ventas.LcCadenaAlph;
import com.alphacell.model.ventas.LcCadenaItems;
import com.alphacell.repository.ventas.ConfigRepository;
import com.alphacell.services.ventas.ServiceConfigVentas;
import com.alphacell.util.jsf.FacesMessages;
import org.primefaces.context.RequestContext;


/**
 *
 * @author luis.cevallos
 */
@Named
@ViewScoped
public class ConfiguracionVentasBean implements Serializable{

	private static final long serialVersionUID = 1L;

    private List<LcCadenaAlph> cmbCadenaAlph;
    private LcCadenaAlph cadenaSelected;
    private LcCadenaAlph cadenaNew= new LcCadenaAlph();

    private List<LcCadenaItems> tableCadenaItems;
    private List<LcCadenaItems> tableCadenaItemsFiltered;
    private LcCadenaItems cadenaItemsSelected;

    @Inject
    private ConfigRepository configRepository;

    @Inject
    private ServiceConfigVentas serviceConfigVentas;

    @Inject
    private FacesMessages messages;

    @PostConstruct
    public void init()
    {
        this.cargarCadenas();
    }


    public void prepararNuevaCadena(ActionEvent event) {

        if(cadenaSelected!=null)
        {
            cadenaNew = new LcCadenaAlph();
            cadenaNew = this.configRepository.getByCodigo(cadenaSelected.getCodigoCadena());
        }
        else
        {
            cadenaNew = new LcCadenaAlph();
        }
    }

    public void cargarCadenas()
    {
        this.cmbCadenaAlph=this.configRepository.getAllCadenas();
    }

    public void guardarCadena()
    {

        this.serviceConfigVentas.salvarCadena(this.cadenaNew);
        this.cargarCadenas();

        messages.info("Se ha creado la Cadena!");

        RequestContext.getCurrentInstance().update(
                Arrays.asList("form-Config-Ventas:messagesConfigVentas", "form-Config-Ventas:cmb-cfgventas-cadenas"));

    }

    public List<LcCadenaAlph> getCmbCadenaAlph() {
        return cmbCadenaAlph;
    }

    public void setCmbCadenaAlph(List<LcCadenaAlph> cmbCadenaAlph) {
        this.cmbCadenaAlph = cmbCadenaAlph;
    }

    public LcCadenaAlph getCadenaSelected() {
        return cadenaSelected;
    }

    public void setCadenaSelected(LcCadenaAlph cadenaSelected) {
        this.cadenaSelected = cadenaSelected;
    }

    public LcCadenaAlph getCadenaNew() {
        return cadenaNew;
    }

    public void setCadenaNew(LcCadenaAlph cadenaNew) {
        this.cadenaNew = cadenaNew;
    }

    public List<LcCadenaItems> getTableCadenaItems() {
        return tableCadenaItems;
    }

    public void setTableCadenaItems(List<LcCadenaItems> tableCadenaItems) {
        this.tableCadenaItems = tableCadenaItems;
    }

    public LcCadenaItems getCadenaItemsSelected() {
        return cadenaItemsSelected;
    }

    public void setCadenaItemsSelected(LcCadenaItems cadenaItemsSelected) {
        this.cadenaItemsSelected = cadenaItemsSelected;
    }

    public List<LcCadenaItems> getTableCadenaItemsFiltered() {
        return tableCadenaItemsFiltered;
    }

    public void setTableCadenaItemsFiltered(List<LcCadenaItems> tableCadenaItemsFiltered) {
        this.tableCadenaItemsFiltered = tableCadenaItemsFiltered;
    }
}
