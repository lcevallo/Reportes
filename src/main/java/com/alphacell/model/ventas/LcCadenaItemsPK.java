/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphacell.model.ventas;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author luis.cevallos
 */
@Embeddable
public class LcCadenaItemsPK implements Serializable {

   
	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "codigo_item")
    private String codigoItem;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "fk_codigo_cadena")
    private String fkCodigoCadena;

    public LcCadenaItemsPK() {
    }

    public LcCadenaItemsPK(String codigoItem, String fkCodigoCadena) {
        this.codigoItem = codigoItem;
        this.fkCodigoCadena = fkCodigoCadena;
    }

    public String getCodigoItem() {
        return codigoItem;
    }

    public void setCodigoItem(String codigoItem) {
        this.codigoItem = codigoItem;
    }

    public String getFkCodigoCadena() {
        return fkCodigoCadena;
    }

    public void setFkCodigoCadena(String fkCodigoCadena) {
        this.fkCodigoCadena = fkCodigoCadena;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoItem != null ? codigoItem.hashCode() : 0);
        hash += (fkCodigoCadena != null ? fkCodigoCadena.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LcCadenaItemsPK)) {
            return false;
        }
        LcCadenaItemsPK other = (LcCadenaItemsPK) object;
        if ((this.codigoItem == null && other.codigoItem != null) || (this.codigoItem != null && !this.codigoItem.equals(other.codigoItem))) {
            return false;
        }
        if ((this.fkCodigoCadena == null && other.fkCodigoCadena != null) || (this.fkCodigoCadena != null && !this.fkCodigoCadena.equals(other.fkCodigoCadena))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.alphacell.model.ventas.LcCadenaItemsPK[ codigoItem=" + codigoItem + ", fkCodigoCadena=" + fkCodigoCadena + " ]";
    }
    
}
