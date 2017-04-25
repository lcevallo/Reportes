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
public class LcCadenaItemRErpPK implements Serializable {

  
	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "fk_codigo_item")
    private String fkCodigoItem;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "fk_codigo_cadena")
    private String fkCodigoCadena;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "ITEMID")
    private String itemid;

    public LcCadenaItemRErpPK() {
    }

    public LcCadenaItemRErpPK(String fkCodigoItem, String fkCodigoCadena, String itemid) {
        this.fkCodigoItem = fkCodigoItem;
        this.fkCodigoCadena = fkCodigoCadena;
        this.itemid = itemid;
    }

    public String getFkCodigoItem() {
        return fkCodigoItem;
    }

    public void setFkCodigoItem(String fkCodigoItem) {
        this.fkCodigoItem = fkCodigoItem;
    }

    public String getFkCodigoCadena() {
        return fkCodigoCadena;
    }

    public void setFkCodigoCadena(String fkCodigoCadena) {
        this.fkCodigoCadena = fkCodigoCadena;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fkCodigoItem != null ? fkCodigoItem.hashCode() : 0);
        hash += (fkCodigoCadena != null ? fkCodigoCadena.hashCode() : 0);
        hash += (itemid != null ? itemid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LcCadenaItemRErpPK)) {
            return false;
        }
        LcCadenaItemRErpPK other = (LcCadenaItemRErpPK) object;
        if ((this.fkCodigoItem == null && other.fkCodigoItem != null) || (this.fkCodigoItem != null && !this.fkCodigoItem.equals(other.fkCodigoItem))) {
            return false;
        }
        if ((this.fkCodigoCadena == null && other.fkCodigoCadena != null) || (this.fkCodigoCadena != null && !this.fkCodigoCadena.equals(other.fkCodigoCadena))) {
            return false;
        }
        if ((this.itemid == null && other.itemid != null) || (this.itemid != null && !this.itemid.equals(other.itemid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.alphacell.model.ventas.LcCadenaItemRErpPK[ fkCodigoItem=" + fkCodigoItem + ", fkCodigoCadena=" + fkCodigoCadena + ", itemid=" + itemid + " ]";
    }
    
}
