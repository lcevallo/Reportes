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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
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
    private boolean condicionFacturaClose=false;
    private ClienteVista clienteSelected;
    private List<ClienteVista> comboClientes;
    private List<ClientesLC> tblClientesLCs;
    private List<ClientesLC> tblClientesLCOriginal;
    private List<String> empleadosID;
    private Double valorCondicionFacturaAbierta;
    private Double valorCondicionFacturaCerrada;
    private String condicionFacturaCerrada;
    private String condicionFacturaAbierta;
    private String clientesSeleccionados;
    private String estado;
    private List<String> estadoLista;
    private HashMap<String, Object> parametros;
    private Reporte rpt;


    @Inject
    private SaldosInicialesRepository saldosInicialesRepository;


    @PostConstruct
    public void inicializar() {

        this.comboClientes = this.saldosInicialesRepository.obtenerClientes();
        this.parametros = new HashMap<String, Object>();
        this.estadoLista= new ArrayList<String>();
        this.estadoLista.add("Todos");
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

    public void cargarTablaSaldosIniciales() {
        this.tblClientesLCOriginal = saldosInicialesRepository.cargarTablaSaldosIniciales(null);
    }

    public void cargarTablaSaldosIniciales(String[] clientes) {

        String[] clientes2 = Stream.of(clientes).map(t -> "\'" + t + "\'").toArray(String[]::new);
        String parametro2 = Stream.of(clientes2).reduce((t, u) -> t + "," + u).get();
        this.clientesSeleccionados = "(" + parametro2 + ")";
        this.tblClientesLCOriginal = saldosInicialesRepository.cargarTablaSaldosIniciales(this.clientesSeleccionados);
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

            //AQUI DEBO DE HACER LA BUSQUEDA SI ES QUE HAY EMPLEADOS SELECCIONADOS O SON TODOS

            if (todosClientes) {
                this.cargarTablaSaldosIniciales();
                this.tblClientesLCs = this.tblClientesLCOriginal;

            } else {
                this.cargarTablaSaldosIniciales(this.selectedClients);
                this.tblClientesLCs = this.tblClientesLCOriginal;

                /*
                this.tblClientesLCs = this.tblClientesLCOriginal.stream().filter(u ->
                        u.getTmpcxcsaldosinicialesList().stream().anyMatch(tmpcxcsaldosiniciales -> tmpcxcsaldosiniciales.getPagada().equals("CERRADA"))
                ).collect(Collectors.toList());
                */

            }

            RequestContext.getCurrentInstance().update("frmSaldosIniciales:tableSaldosIniciales");

            System.out.println(this.condicionFacturaAbierta);
            System.out.println(this.condicionFacturaCerrada);

        }

        return event.getNewStep();
    }

    public void addMessage() {
        String summary = this.todosClientes ? "Checked" : "Unchecked";
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

    public void estadoChangeHandler()
    {
        System.out.println(this.estado);
        switch (this.estado)
        {
            case "Todos":
                this.condicionFacturaClose=false;
                this.condicionFacturaOpen=false;
                break;
            case "Abiertas":
                this.condicionFacturaClose=true;
                this.condicionFacturaOpen=false;
                break;
            case "Cerradas":
                this.condicionFacturaClose=false;
                this.condicionFacturaOpen=true;
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

    public Double getValorCondicionFacturaAbierta() {
        return valorCondicionFacturaAbierta;
    }

    public void setValorCondicionFacturaAbierta(Double valorCondicionFacturaAbierta) {
        this.valorCondicionFacturaAbierta = valorCondicionFacturaAbierta;
    }

    public Double getValorCondicionFacturaCerrada() {
        return valorCondicionFacturaCerrada;
    }

    public void setValorCondicionFacturaCerrada(Double valorCondicionFacturaCerrada) {
        this.valorCondicionFacturaCerrada = valorCondicionFacturaCerrada;
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
}
