package com.alphacell.model.financiero;

import java.util.Date;

public class FechasCorte {

	private Date fechaCorte;
	private Boolean esCorte;
	private Integer numeroSemana;


    public FechasCorte(Date fechaCorte, Integer numeroSemana) {
        this.fechaCorte = fechaCorte;
        this.numeroSemana = numeroSemana;
    }

    public FechasCorte(Date fechaCorte, Boolean esCorte, Integer numeroSemana) {
		this.fechaCorte = fechaCorte;
		this.esCorte = esCorte;
		this.numeroSemana = numeroSemana;
	}


	public Date getFechaCorte() {
		return fechaCorte;
	}

	public void setFechaCorte(Date fechaCorte) {
		this.fechaCorte = fechaCorte;
	}

	public Boolean getEsCorte() {
		return esCorte;
	}

	public void setEsCorte(Boolean esCorte) {
		this.esCorte = esCorte;
	}

	public Integer getNumeroSemana() {
		return numeroSemana;
	}

	public void setNumeroSemana(Integer numeroSemana) {
		this.numeroSemana = numeroSemana;
	}
}
