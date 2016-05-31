package com.alphacell.model.cartera;

/**
 * Created by admin on 02/04/2016.
 */

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author admin
 */
@Entity
@Table(name = "TABLA_CXCPIVOTE")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "TablaCxcpivote.findAll", query = "SELECT t FROM TablaCxcpivote t"),
        @NamedQuery(name = "TablaCxcpivote.findById", query = "SELECT t FROM TablaCxcpivote t WHERE t.id = :id"),
        @NamedQuery(name = "TablaCxcpivote.findByCliente", query = "SELECT t FROM TablaCxcpivote t WHERE t.cliente = :cliente"),
        @NamedQuery(name = "TablaCxcpivote.findByCodigoCliente", query = "SELECT t FROM TablaCxcpivote t WHERE t.codigoCliente = :codigoCliente"),
        @NamedQuery(name = "TablaCxcpivote.findByCreditmax", query = "SELECT t FROM TablaCxcpivote t WHERE t.creditmax = :creditmax"),
        @NamedQuery(name = "TablaCxcpivote.findByA", query = "SELECT t FROM TablaCxcpivote t WHERE t.a = :a"),
        @NamedQuery(name = "TablaCxcpivote.findByA1", query = "SELECT t FROM TablaCxcpivote t WHERE t.a1 = :a1"),
        @NamedQuery(name = "TablaCxcpivote.findByA2", query = "SELECT t FROM TablaCxcpivote t WHERE t.a2 = :a2"),
        @NamedQuery(name = "TablaCxcpivote.findByA3", query = "SELECT t FROM TablaCxcpivote t WHERE t.a3 = :a3"),
        @NamedQuery(name = "TablaCxcpivote.findByA4", query = "SELECT t FROM TablaCxcpivote t WHERE t.a4 = :a4"),
        @NamedQuery(name = "TablaCxcpivote.findByA5", query = "SELECT t FROM TablaCxcpivote t WHERE t.a5 = :a5"),
        @NamedQuery(name = "TablaCxcpivote.findByA6", query = "SELECT t FROM TablaCxcpivote t WHERE t.a6 = :a6"),
        @NamedQuery(name = "TablaCxcpivote.findByA7", query = "SELECT t FROM TablaCxcpivote t WHERE t.a7 = :a7")}
)
public class TablaCxcpivote implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 100)
    @Column(name = "NOMBRE_CLIENTE")
    private String cliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ACCOUNTNUM")
    private String codigoCliente;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREDITMAX")
    private BigDecimal creditmax;

    @Basic(optional = false)
    @Column(name = "UTILIZADO")
    private BigDecimal utilizado;

    @Column(name = "Saldo")
    private BigDecimal saldo;

    @Column(name = "1")
    private BigDecimal a;
    @Column(name = "2")
    private BigDecimal a1;
    @Column(name = "3")
    private BigDecimal a2;
    @Column(name = "4")
    private BigDecimal a3;
    @Column(name = "5")
    private BigDecimal a4;
    @Column(name = "6")
    private BigDecimal a5;
    @Column(name = "7")
    private BigDecimal a6;
    @Column(name = "8")
    private BigDecimal a7;
    @Column(name = "9")
    private BigDecimal a8;
    @Column(name = "10")
    private BigDecimal a9;

    public TablaCxcpivote() {
    }

    public TablaCxcpivote(Integer id) {
        this.id = id;
    }

    public TablaCxcpivote(Integer id, String codigoCliente, BigDecimal creditmax) {
        this.id = id;
        this.codigoCliente = codigoCliente;
        this.creditmax = creditmax;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public BigDecimal getCreditmax() {
        return creditmax;
    }

    public void setCreditmax(BigDecimal creditmax) {
        this.creditmax = creditmax;
    }

    public BigDecimal getA() {
        return a;
    }

    public void setA(BigDecimal a) {
        this.a = a;
    }

    public BigDecimal getA1() {
        return a1;
    }

    public void setA1(BigDecimal a1) {
        this.a1 = a1;
    }

    public BigDecimal getA2() {
        return a2;
    }

    public void setA2(BigDecimal a2) {
        this.a2 = a2;
    }

    public BigDecimal getA3() {
        return a3;
    }

    public void setA3(BigDecimal a3) {
        this.a3 = a3;
    }

    public BigDecimal getA4() {
        return a4;
    }

    public void setA4(BigDecimal a4) {
        this.a4 = a4;
    }

    public BigDecimal getA5() {
        return a5;
    }

    public void setA5(BigDecimal a5) {
        this.a5 = a5;
    }

    public BigDecimal getA6() {
        return a6;
    }

    public void setA6(BigDecimal a6) {
        this.a6 = a6;
    }

    public BigDecimal getA7() {
        return a7;
    }

    public void setA7(BigDecimal a7) {
        this.a7 = a7;
    }

    public BigDecimal getA8() {
        return a8;
    }

    public void setA8(BigDecimal a8) {
        this.a8 = a8;
    }

    public BigDecimal getA9() {
        return a9;
    }

    public void setA9(BigDecimal a9) {
        this.a9 = a9;
    }


    public BigDecimal getUtilizado() {
        return utilizado;
    }

    public void setUtilizado(BigDecimal utilizado) {
        this.utilizado = utilizado;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
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
        if (!(object instanceof TablaCxcpivote)) {
            return false;
        }
        TablaCxcpivote other = (TablaCxcpivote) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.TablaCxcpivote[ id=" + id + " ]";
    }


}
