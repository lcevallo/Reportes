package com.alphacell.controller.logistica;

import com.alphacell.model.logistica.VistaLogiIndicemensualinventario;
import com.alphacell.repository.logistica.IndiceInventarioRepository;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class RotacionInventarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<VistaLogiIndicemensualinventario> tableIndiceInventario;
    private List<VistaLogiIndicemensualinventario> tableIndiceInventarioFiltered;
	private VistaLogiIndicemensualinventario vistaLogiIndicemensualinventarioSelected;
	private String tipo="Todos";

	@Inject
    private IndiceInventarioRepository indiceInventarioRepository;

	@PostConstruct
    public void init(){

    }

	public  void cargarDatatableRotacion()
    {
        this.tableIndiceInventario= this.indiceInventarioRepository.buscarIndicesInventario(tipo);
    }

    public String cambiarColor(Integer index)
    {
        return this.tableIndiceInventario.get(index-1).getColor();

    }

    public List<VistaLogiIndicemensualinventario> getTableIndiceInventario() {
        return tableIndiceInventario;
    }

    public void setTableIndiceInventario(List<VistaLogiIndicemensualinventario> tableIndiceInventario) {
        this.tableIndiceInventario = tableIndiceInventario;
    }

    public VistaLogiIndicemensualinventario getVistaLogiIndicemensualinventarioSelected() {
        return vistaLogiIndicemensualinventarioSelected;
    }

    public void setVistaLogiIndicemensualinventarioSelected(VistaLogiIndicemensualinventario vistaLogiIndicemensualinventarioSelected) {
        this.vistaLogiIndicemensualinventarioSelected = vistaLogiIndicemensualinventarioSelected;
    }

    public List<VistaLogiIndicemensualinventario> getTableIndiceInventarioFiltered() {
        return tableIndiceInventarioFiltered;
    }

    public void setTableIndiceInventarioFiltered(List<VistaLogiIndicemensualinventario> tableIndiceInventarioFiltered) {
        this.tableIndiceInventarioFiltered = tableIndiceInventarioFiltered;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
