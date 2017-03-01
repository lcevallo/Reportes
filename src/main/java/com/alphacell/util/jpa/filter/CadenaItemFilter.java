package com.alphacell.util.jpa.filter;

import java.io.Serializable;

/**
 * Created by luis.cevallos on 1/3/2017.
 */
public class CadenaItemFilter implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codigoItemCadena;
	
	private String descripcionItemCadena;
	
	private String codigoAlpha;
	
	private String descripcionAlpha;

	public String getCodigoItemCadena() {
		return codigoItemCadena;
	}

	public void setCodigoItemCadena(String codigoItemCadena) {
		this.codigoItemCadena = codigoItemCadena;
	}

	public String getDescripcionItemCadena() {
		return descripcionItemCadena;
	}

	public void setDescripcionItemCadena(String descripcionItemCadena) {
		this.descripcionItemCadena = descripcionItemCadena;
	}

	public String getCodigoAlpha() {
		return codigoAlpha;
	}

	public void setCodigoAlpha(String codigoAlpha) {
		this.codigoAlpha = codigoAlpha;
	}

	public String getDescripcionAlpha() {
		return descripcionAlpha;
	}

	public void setDescripcionAlpha(String descripcionAlpha) {
		this.descripcionAlpha = descripcionAlpha;
	}
	
	
	
	
}
