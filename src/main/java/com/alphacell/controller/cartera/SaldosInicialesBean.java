package com.alphacell.controller.cartera;

import com.alphacell.model.cartera.ClienteVista;
import com.alphacell.model.cartera.ClientesLC;
import com.alphacell.model.cartera.Tmpcxcsaldosiniciales;
import com.alphacell.repository.cxc.SaldosInicialesRepository;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Created by luis on 13/06/16.
 */
@Named
@ViewScoped
public class SaldosInicialesBean implements Serializable {

    private static final long serialVersionUID = 2624324863598507528L;


    private ClientesLC recordTmpcxcsaldosiniciales;
    private ClienteVista clienteSelected;
    private List<ClienteVista> comboClientes;
    private List<ClientesLC> tblClientesLCs;
    private List<String> empleadosID;

    @Inject
    private SaldosInicialesRepository saldosInicialesRepository;


    @PostConstruct
    public void inicializar() {

        this.comboClientes=this.saldosInicialesRepository.obtenerClientes();
/*
        this.empleadosID = this.tblSaldosIniciales.stream()
                //.filter(distinctByKey(p -> p.getName())
                .map(tmpcxcsaldosiniciales -> tmpcxcsaldosiniciales.getAccountnum())
                .distinct()
                .collect(toList());
*/
    }

    public void cargarTablaSaldosIniciales()
    {
        this.tblClientesLCs = saldosInicialesRepository.cargarTablaSaldosIniciales();

    }

    public List<String> getEmpleadosID() {
        return empleadosID;
    }

    public void setEmpleadosID(List<String> empleadosID) {
        this.empleadosID = empleadosID;
    }

    public List<ClientesLC> getTblClientesLCs() {
        return tblClientesLCs;
    }

    public void setTblClientesLCs(List<ClientesLC> tblClientesLCs) {
        this.tblClientesLCs = tblClientesLCs;
    }

    public ClientesLC getRecordTmpcxcsaldosiniciales() {
        return recordTmpcxcsaldosiniciales;
    }

    public void setRecordTmpcxcsaldosiniciales(ClientesLC recordTmpcxcsaldosiniciales) {
        this.recordTmpcxcsaldosiniciales = recordTmpcxcsaldosiniciales;
    }

    public List<ClienteVista> getComboClientes() {
        return comboClientes;
    }

    public void setComboClientes(List<ClienteVista> comboClientes) {
        this.comboClientes = comboClientes;
    }


    public ClienteVista getClienteSelected() {
        return clienteSelected;
    }

    public void setClienteSelected(ClienteVista clienteSelected) {
        this.clienteSelected = clienteSelected;
    }
}
