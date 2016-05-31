package com.alphacell.controller;

import com.alphacell.model.financiero.JpaAlphafactuview;
import com.alphacell.repository.VentasRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Named
@RequestScoped
public class VentasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private VentasRepository ventasRepository;
	
	
	private Date fechaInicial;
	private Date fechaFinal;
	private JpaAlphafactuview jpaAlphafactuview;
	private List<JpaAlphafactuview> listaAlphaFacturaView;

   public VentasBean(){

   }

	public List<JpaAlphafactuview> getListaAlphaFacturaView() {
		return listaAlphaFacturaView;
	}

	public void setListaAlphaFacturaView(List<JpaAlphafactuview> listaAlphaFacturaView) {
		this.listaAlphaFacturaView = listaAlphaFacturaView;
	}

	public Date getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public JpaAlphafactuview getJpaAlphafactuview() {
		return jpaAlphafactuview;
	}


	public void setJpaAlphafactuview(JpaAlphafactuview jpaAlphafactuview) {
		this.jpaAlphafactuview = jpaAlphafactuview;
	}

    @PostConstruct
	public void inicializar(){
		listaAlphaFacturaView= ventasRepository.ObtenerTodos();
	}

	public void encontrarPorFecha()
	{

		listaAlphaFacturaView = ventasRepository.obtenerVentasPorFecha(this.fechaInicial,this.fechaFinal) ;


	}
	
	

}
