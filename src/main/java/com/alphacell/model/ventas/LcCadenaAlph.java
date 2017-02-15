/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphacell.model.ventas;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author luis.cevallos
 */
@Entity
@Table(name = "LC_CADENA_ALPH")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LcCadenaAlph.findAll", query = "SELECT l FROM LcCadenaAlph l"),
    @NamedQuery(name = "LcCadenaAlph.findByCodigoCadena", query = "SELECT l FROM LcCadenaAlph l WHERE l.codigoCadena = :codigoCadena"),
    @NamedQuery(name = "LcCadenaAlph.findByNombre", query = "SELECT l FROM LcCadenaAlph l WHERE l.nombre = :nombre")})
public class LcCadenaAlph implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "codigo_cadena")
    private String codigoCadena;
    @Size(max = 150)
    @Column(name = "nombre")
    private String nombre;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;

    public LcCadenaAlph() {
    }

    public LcCadenaAlph(String codigoCadena) {
        this.codigoCadena = codigoCadena;
    }

    public String getCodigoCadena() {
        return codigoCadena;
    }

    public void setCodigoCadena(String codigoCadena) {
        this.codigoCadena = codigoCadena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoCadena != null ? codigoCadena.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LcCadenaAlph)) {
            return false;
        }
        LcCadenaAlph other = (LcCadenaAlph) object;
        if ((this.codigoCadena == null && other.codigoCadena != null) || (this.codigoCadena != null && !this.codigoCadena.equals(other.codigoCadena))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

       // return "com.alphacell.model.ventas.LcCadenaAlph[ codigoCadena=" + codigoCadena + " ]";
        return String.format("LcCadenaAlph[%s,%s]",codigoCadena,descripcion);
    }
    
}
