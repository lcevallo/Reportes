package com.alphacell.model;

/**
 * Created by luis.cevallos on 5/4/2016.
 */
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "SEMANATEMP")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Semanatemp.findAll", query = "SELECT s FROM Semanatemp s"),
        @NamedQuery(name = "Semanatemp.findById", query = "SELECT s FROM Semanatemp s WHERE s.id = :id"),
        @NamedQuery(name = "Semanatemp.findByRango", query = "SELECT s FROM Semanatemp s WHERE s.rango = :rango")})
public class Semanatemp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 21)
    @Column(name = "RANGO")
    private String rango;

    public Semanatemp() {
    }

    public Semanatemp(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Semanatemp)) {
            return false;
        }
        Semanatemp other = (Semanatemp) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Semanatemp[ id=" + id + " ]";
    }

}