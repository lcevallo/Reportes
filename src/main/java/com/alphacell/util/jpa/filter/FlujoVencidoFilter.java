package com.alphacell.util.jpa.filter;

import java.io.Serializable;
import java.util.Date;

public class FlujoVencidoFilter implements Serializable{

	private static final long serialVersionUID = 1L;
	
	  private String codProveedor;
      private Date fechaDocumento;


	public String getCodProveedor() {
		return codProveedor;
	}

	public void setCodProveedor(String codProveedor) {
		this.codProveedor = codProveedor;
	}

	public Date getFechaDocumento() {
		return fechaDocumento;
	}

	public void setFechaDocumento(Date fechaDocumento) {
		this.fechaDocumento = fechaDocumento;
	}
}