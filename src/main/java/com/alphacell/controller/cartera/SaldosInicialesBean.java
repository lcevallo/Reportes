package com.alphacell.controller.cartera;

import com.alphacell.model.cartera.ClienteVista;
import com.alphacell.model.cartera.ClientesLC;
import com.alphacell.repository.cxc.SaldosInicialesRepository;
import com.alphacell.util.reporte.Reporte;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by luis on 13/06/16.
 */
@Named
@ViewScoped
public class SaldosInicialesBean implements Serializable {

    private static final long serialVersionUID = 2624324863598507528L;


    private ClientesLC recordTmpcxcsaldosiniciales;
    private String[] selectedClients;
    private boolean todosClientes = true;
    private boolean condicionFacturaOpen = false;
    private boolean condicionFacturaClose = false;
    private ClienteVista clienteSelected;
    private List<ClienteVista> comboClientes;
    private List<ClientesLC> tblClientesLCs;
    private List<ClientesLC> tblClientesLCOriginal;
    private List<String> empleadosID;
    private BigDecimal valorCondicionFacturaAbierta;
    private BigDecimal valorCondicionFacturaCerrada;
    private String condicionFacturaCerrada;
    private String condicionFacturaAbierta;
    private String clientesSeleccionados;


    private String estado;
    private Integer estadoByte;

    private List<String> estadoLista;
    private HashMap<String, Object> parametros;
    private Reporte rpt;


    @Inject
    private SaldosInicialesRepository saldosInicialesRepository;


    @PostConstruct
    public void inicializar() {

        this.comboClientes = this.saldosInicialesRepository.obtenerClientes();
        this.parametros = new HashMap<String, Object>();
        this.estadoLista = new ArrayList<String>();
        this.estadoLista.add("Abiertas y Cerradas");
        this.estadoLista.add("Abiertas");
        this.estadoLista.add("Cerradas");

/*
        this.empleadosID = this.tblSaldosIniciales.stream()
                //.filter(distinctByKey(p -> p.getName())
                .map(tmpcxcsaldosiniciales -> tmpcxcsaldosiniciales.getAccountnum())
                .distinct()
                .collect(toList());
*/
    }

    public void cargarTablaSaldosIniciales(String[] clientes) {

        String condicionAbierta = null;
        String condicionCerrada = null;


        if (this.condicionFacturaAbierta != null) {
            switch (this.condicionFacturaAbierta) {
                case "graterequalthan":
                    condicionAbierta = ">=";
                    break;

                case "graterthan":
                    condicionAbierta = ">";
                    break;

                case "equals":
                    condicionAbierta = "=";
                    break;

                case "lessequalsthan":
                    condicionAbierta = "<=";
                    break;

                case "lessthan":
                    condicionAbierta = "<";
                    break;
                default:
                    condicionAbierta = (String) null;
            }
        }

        if (this.condicionFacturaCerrada != null) {
            switch (this.condicionFacturaCerrada) {
                case "graterequalthan":
                    condicionCerrada = ">=";
                    break;

                case "graterthan":
                    condicionCerrada = ">";
                    break;

                case "equals":
                    condicionCerrada = "=";
                    break;

                case "lessequalsthan":
                    condicionCerrada = "<=";
                    break;

                case "lessthan":
                    condicionCerrada = "<";
                    break;
                default:
                    condicionCerrada = (String) null;
            }
        }
        if (clientes != null) {
            String[] clientes2 = Stream.of(clientes).map(t -> "\'" + t + "\'").toArray(String[]::new);
            String parametro2 = Stream.of(clientes2).reduce((t, u) -> t + "," + u).get();
            this.clientesSeleccionados = "(" + parametro2 + ")";
            this.tblClientesLCOriginal = saldosInicialesRepository.cargarTablaSaldosIniciales(this.clientesSeleccionados, this.estadoByte, this.valorCondicionFacturaAbierta, this.valorCondicionFacturaCerrada, condicionAbierta, condicionCerrada);

        } else {
            this.tblClientesLCOriginal = saldosInicialesRepository.cargarTablaSaldosIniciales((String) null,
                    this.estadoByte == null ? (Integer) null : this.estadoByte,
                    this.valorCondicionFacturaAbierta == null ? (BigDecimal) null : this.valorCondicionFacturaAbierta,
                    this.valorCondicionFacturaCerrada == null ? (BigDecimal) null : this.valorCondicionFacturaCerrada,
                    condicionAbierta,
                    condicionCerrada);

        }

        this.tblClientesLCs = this.tblClientesLCOriginal;


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

    public String onFlowProcess(FlowEvent event) {

        if (event.getNewStep().equals("SItableTab")) {

            this.cargarTablaSaldosIniciales(this.selectedClients);
            this.tblClientesLCs = this.tblClientesLCOriginal;
                /*
                this.tblClientesLCs = this.tblClientesLCOriginal.stream().filter(u ->
                        u.getTmpcxcsaldosinicialesList().stream().anyMatch(tmpcxcsaldosiniciales -> tmpcxcsaldosiniciales.getPagada().equals("CERRADA"))
                ).collect(Collectors.toList());
                */
        } else {
            this.tblClientesLCOriginal = null;
            this.tblClientesLCs = null;
            RequestContext.getCurrentInstance().reset("frmSaldosIniciales");
        }

        RequestContext.getCurrentInstance().update("frmSaldosIniciales:tableSaldosIniciales");
        return event.getNewStep();
    }

    public void addMessage() {
        String summary = this.todosClientes ? "Todos" : "X Seleccion";
        this.parametros.put("bbTodos", this.todosClientes);
        if (this.todosClientes == true) {
            RequestContext.getCurrentInstance().reset("frmSaldosIniciales:clientecombo");
            RequestContext.getCurrentInstance().update("frmSaldosIniciales:clientecombo");
            this.selectedClients = null;
        } else {
            RequestContext.getCurrentInstance().update("frmSaldosIniciales:clientecombo");
        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
    }

    public void estadoChangeHandler() {
        switch (this.estado) {
            case "Abiertas y Cerradas":
                this.condicionFacturaClose = false;
                this.condicionFacturaOpen = false;
                this.estadoByte = null;
                break;
            case "Abiertas":
                this.condicionFacturaClose = true;
                this.condicionFacturaOpen = false;
                this.estadoByte = 1;
                RequestContext.getCurrentInstance().reset("frmSaldosIniciales:siValorCondicionFacturaAbierta");
                RequestContext.getCurrentInstance().reset("frmSaldosIniciales:siValorCondicionFacturaCerrada");
                break;
            case "Cerradas":
                this.condicionFacturaClose = false;
                this.condicionFacturaOpen = true;
                this.estadoByte = 0;
                RequestContext.getCurrentInstance().reset("frmSaldosIniciales:siValorCondicionFacturaAbierta");
                RequestContext.getCurrentInstance().reset("frmSaldosIniciales:siValorCondicionFacturaCerrada");
                break;
        }
    }

    public boolean isTodosClientes() {
        return todosClientes;
    }

    public void setTodosClientes(boolean todosClientes) {
        this.todosClientes = todosClientes;
    }

    public String[] getSelectedClients() {
        return selectedClients;
    }

    public void setSelectedClients(String[] selectedClients) {
        this.selectedClients = selectedClients;
    }


    public String getCondicionFacturaCerrada() {
        return condicionFacturaCerrada;
    }

    public void setCondicionFacturaCerrada(String condicionFacturaCerrada) {
        this.condicionFacturaCerrada = condicionFacturaCerrada;
    }

    public String getCondicionFacturaAbierta() {
        return condicionFacturaAbierta;
    }

    public void setCondicionFacturaAbierta(String condicionFacturaAbierta) {
        this.condicionFacturaAbierta = condicionFacturaAbierta;
    }

    public HashMap<String, Object> getParametros() {
        return parametros;
    }

    public void setParametros(HashMap<String, Object> parametros) {
        this.parametros = parametros;
    }

    public Reporte getRpt() {
        return rpt;
    }

    public void setRpt(Reporte rpt) {
        this.rpt = rpt;
    }

    public String getClientesSeleccionados() {
        return clientesSeleccionados;
    }

    public void setClientesSeleccionados(String clientesSeleccionados) {
        this.clientesSeleccionados = clientesSeleccionados;
    }

    public List<ClientesLC> getTblClientesLCOriginal() {
        return tblClientesLCOriginal;
    }

    public void setTblClientesLCOriginal(List<ClientesLC> tblClientesLCOriginal) {
        this.tblClientesLCOriginal = tblClientesLCOriginal;
    }

    public Integer getEstadoByte() {
        return estadoByte;
    }

    public void setEstadoByte(Integer estadoByte) {
        this.estadoByte = estadoByte;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<String> getEstadoLista() {
        return estadoLista;
    }

    public void setEstadoLista(List<String> estadoLista) {
        this.estadoLista = estadoLista;
    }

    public boolean isCondicionFacturaOpen() {
        return condicionFacturaOpen;
    }

    public void setCondicionFacturaOpen(boolean condicionFacturaOpen) {
        this.condicionFacturaOpen = condicionFacturaOpen;
    }

    public boolean isCondicionFacturaClose() {
        return condicionFacturaClose;
    }

    public void setCondicionFacturaClose(boolean condicionFacturaClose) {
        this.condicionFacturaClose = condicionFacturaClose;
    }

    public BigDecimal getValorCondicionFacturaAbierta() {
        return valorCondicionFacturaAbierta;
    }

    public void setValorCondicionFacturaAbierta(BigDecimal valorCondicionFacturaAbierta) {
        this.valorCondicionFacturaAbierta = valorCondicionFacturaAbierta;
    }

    public BigDecimal getValorCondicionFacturaCerrada() {
        return valorCondicionFacturaCerrada;
    }

    public void setValorCondicionFacturaCerrada(BigDecimal valorCondicionFacturaCerrada) {
        this.valorCondicionFacturaCerrada = valorCondicionFacturaCerrada;
    }
}
