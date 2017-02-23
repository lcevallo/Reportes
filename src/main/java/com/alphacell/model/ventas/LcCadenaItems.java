/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphacell.model.ventas;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.alphacell.model.xls.LcCadenaItemsXLS;

/**
 *
 * @author luis.cevallos
 */
@Entity
@Table(name = "LC_CADENA_ITEMS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LcCadenaItems.findAll", query = "SELECT l FROM LcCadenaItems l"),
    @NamedQuery(name = "LcCadenaItems.findByCodigoItem", query = "SELECT l FROM LcCadenaItems l WHERE l.lcCadenaItemsPK.codigoItem = :codigoItem"),
    @NamedQuery(name = "LcCadenaItems.findByFkCodigoCadena", query = "SELECT l FROM LcCadenaItems l WHERE l.lcCadenaItemsPK.fkCodigoCadena = :fkCodigoCadena"),
    @NamedQuery(name = "LcCadenaItems.findByFkCodigoAlph", query = "SELECT l FROM LcCadenaItems l WHERE l.fkCodigoAlph = :fkCodigoAlph")})
public class LcCadenaItems implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LcCadenaItemsPK lcCadenaItemsPK;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "descripcion_cadena")
    private String descripcionCadena;
    @Size(max = 60)
    @Column(name = "fk_codigo_alph")
    private String fkCodigoAlph;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "descripcion_alph")
    private String descripcionAlph;

    @Transient
    private Integer rowkey;

    @Transient
    private LcCadenaItemsXLS lcCadenaItemsXLS;


    public LcCadenaItems() {
    }

    public LcCadenaItems(LcCadenaItemsPK lcCadenaItemsPK) {
        this.lcCadenaItemsPK = lcCadenaItemsPK;
    }

    public LcCadenaItems(String codigoItem, String fkCodigoCadena) {
        this.lcCadenaItemsPK = new LcCadenaItemsPK(codigoItem, fkCodigoCadena);
    }

    public Integer getRowkey() {
        return rowkey;
    }

    public void setRowkey(Integer rowkey) {
        this.rowkey = rowkey;
    }

    public LcCadenaItemsPK getLcCadenaItemsPK() {
        return lcCadenaItemsPK;
    }

    public void setLcCadenaItemsPK(LcCadenaItemsPK lcCadenaItemsPK) {
        this.lcCadenaItemsPK = lcCadenaItemsPK;
    }

    public String getDescripcionCadena() {
        return descripcionCadena;
    }

    public void setDescripcionCadena(String descripcionCadena) {
        this.descripcionCadena = descripcionCadena;
    }

    public String getFkCodigoAlph() {
        return fkCodigoAlph;
    }

    public void setFkCodigoAlph(String fkCodigoAlph) {
        this.fkCodigoAlph = fkCodigoAlph;
    }

    public String getDescripcionAlph() {
        return descripcionAlph;
    }

    public void setDescripcionAlph(String descripcionAlph) {
        this.descripcionAlph = descripcionAlph;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lcCadenaItemsPK != null ? lcCadenaItemsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LcCadenaItems)) {
            return false;
        }
        LcCadenaItems other = (LcCadenaItems) object;
        if ((this.lcCadenaItemsPK == null && other.lcCadenaItemsPK != null) || (this.lcCadenaItemsPK != null && !this.lcCadenaItemsPK.equals(other.lcCadenaItemsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("LcCadenaItems[%s,%s]",lcCadenaItemsPK.getCodigoItem(),lcCadenaItemsPK.getFkCodigoCadena());
        //return "com.alphacell.model.ventas.LcCadenaItems[ lcCadenaItemsPK=" + lcCadenaItemsPK + " ]";
    }

    public LcCadenaItemsXLS getLcCadenaItemsXLS() {
        return lcCadenaItemsXLS;
    }

    public void setLcCadenaItemsXLS(LcCadenaItemsXLS lcCadenaItemsXLS) {
        this.lcCadenaItemsXLS = lcCadenaItemsXLS;
        this.setFkCodigoAlph(lcCadenaItemsXLS.getCodigo());
        this.setDescripcionAlph(lcCadenaItemsXLS.getDescripcion());
    }
}
