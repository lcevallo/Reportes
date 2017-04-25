/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphacell.model.ventas;

import com.alphacell.model.xls.LcCadenaItemsXLS;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author luis.cevallos
 */
@Entity
@Table(name = "LC_CADENA_ITEMS", schema = "dbo", catalog = "Produccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LcCadenaItems.findAll", query = "SELECT l FROM LcCadenaItems l"),
    @NamedQuery(name = "LcCadenaItems.findByCodigoItem", query = "SELECT l FROM LcCadenaItems l WHERE l.lcCadenaItemsPK.codigoItem = :codigoItem"),
    @NamedQuery(name = "LcCadenaItems.findByFkCodigoCadena", query = "SELECT l FROM LcCadenaItems l WHERE l.lcCadenaItemsPK.fkCodigoCadena = :fkCodigoCadena"),
    @NamedQuery(name = "LcCadenaItems.findByMarca", query = "SELECT l FROM LcCadenaItems l WHERE l.marca = :marca")})
public class LcCadenaItems implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LcCadenaItemsPK lcCadenaItemsPK;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "descripcion_cadena")
    private String descripcionCadena;
    @Size(max = 70)
    @Column(name = "marca")
    private String marca;

    @Transient
    private Integer rowkey;

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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
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
        //return "com.alphacell.model.ventas.LcCadenaItems[ lcCadenaItemsPK=" + lcCadenaItemsPK + " ]";
        return  String.format("LcCadenaItems[%s,%s]",lcCadenaItemsPK.getCodigoItem(),lcCadenaItemsPK.getFkCodigoCadena());
    }
    
}
