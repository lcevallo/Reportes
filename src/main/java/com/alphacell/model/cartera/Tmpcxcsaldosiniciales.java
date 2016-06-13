/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphacell.model.cartera;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author luis.cevallos
 */
@Entity
@Table(name = "TMPCXCSALDOSINICIALES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tmpcxcsaldosiniciales.findAll", query = "SELECT t FROM Tmpcxcsaldosiniciales t"),
    @NamedQuery(name = "Tmpcxcsaldosiniciales.findById", query = "SELECT t FROM Tmpcxcsaldosiniciales t WHERE t.id = :id"),
    @NamedQuery(name = "Tmpcxcsaldosiniciales.findByAccountnum", query = "SELECT t FROM Tmpcxcsaldosiniciales t WHERE t.accountnum = :accountnum"),
    @NamedQuery(name = "Tmpcxcsaldosiniciales.findByTransdate", query = "SELECT t FROM Tmpcxcsaldosiniciales t WHERE t.transdate = :transdate"),
    @NamedQuery(name = "Tmpcxcsaldosiniciales.findByVoucher", query = "SELECT t FROM Tmpcxcsaldosiniciales t WHERE t.voucher = :voucher"),
    @NamedQuery(name = "Tmpcxcsaldosiniciales.findByInvoice", query = "SELECT t FROM Tmpcxcsaldosiniciales t WHERE t.invoice = :invoice"),
    @NamedQuery(name = "Tmpcxcsaldosiniciales.findByTxt", query = "SELECT t FROM Tmpcxcsaldosiniciales t WHERE t.txt = :txt"),
    @NamedQuery(name = "Tmpcxcsaldosiniciales.findByAmountcur", query = "SELECT t FROM Tmpcxcsaldosiniciales t WHERE t.amountcur = :amountcur"),
    @NamedQuery(name = "Tmpcxcsaldosiniciales.findByDuedate", query = "SELECT t FROM Tmpcxcsaldosiniciales t WHERE t.duedate = :duedate"),
    @NamedQuery(name = "Tmpcxcsaldosiniciales.findByPaymmode", query = "SELECT t FROM Tmpcxcsaldosiniciales t WHERE t.paymmode = :paymmode"),
    @NamedQuery(name = "Tmpcxcsaldosiniciales.findByPagada", query = "SELECT t FROM Tmpcxcsaldosiniciales t WHERE t.pagada = :pagada"),
    @NamedQuery(name = "Tmpcxcsaldosiniciales.findByCantidadpagos", query = "SELECT t FROM Tmpcxcsaldosiniciales t WHERE t.cantidadpagos = :cantidadpagos"),
    @NamedQuery(name = "Tmpcxcsaldosiniciales.findByValorpagado", query = "SELECT t FROM Tmpcxcsaldosiniciales t WHERE t.valorpagado = :valorpagado")})
public class Tmpcxcsaldosiniciales implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 20)
    @Column(name = "ACCOUNTNUM")
    private String accountnum;
    @Column(name = "TRANSDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transdate;
    @Size(max = 20)
    @Column(name = "VOUCHER")
    private String voucher;
    @Size(max = 20)
    @Column(name = "INVOICE")
    private String invoice;
    @Size(max = 60)
    @Column(name = "TXT")
    private String txt;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "AMOUNTCUR")
    private BigDecimal amountcur;
    @Column(name = "DUEDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date duedate;
    @Size(max = 10)
    @Column(name = "PAYMMODE")
    private String paymmode;
    @Size(max = 10)
    @Column(name = "PAGADA")
    private String pagada;
    @Column(name = "CANTIDADPAGOS")
    private Integer cantidadpagos;
    @Column(name = "VALORPAGADO")
    private BigDecimal valorpagado;

    public Tmpcxcsaldosiniciales() {
    }

    public Tmpcxcsaldosiniciales(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountnum() {
        return accountnum;
    }

    public void setAccountnum(String accountnum) {
        this.accountnum = accountnum;
    }

    public Date getTransdate() {
        return transdate;
    }

    public void setTransdate(Date transdate) {
        this.transdate = transdate;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public BigDecimal getAmountcur() {
        return amountcur;
    }

    public void setAmountcur(BigDecimal amountcur) {
        this.amountcur = amountcur;
    }

    public Date getDuedate() {
        return duedate;
    }

    public void setDuedate(Date duedate) {
        this.duedate = duedate;
    }

    public String getPaymmode() {
        return paymmode;
    }

    public void setPaymmode(String paymmode) {
        this.paymmode = paymmode;
    }

    public String getPagada() {
        return pagada;
    }

    public void setPagada(String pagada) {
        this.pagada = pagada;
    }

    public Integer getCantidadpagos() {
        return cantidadpagos;
    }

    public void setCantidadpagos(Integer cantidadpagos) {
        this.cantidadpagos = cantidadpagos;
    }

    public BigDecimal getValorpagado() {
        return valorpagado;
    }

    public void setValorpagado(BigDecimal valorpagado) {
        this.valorpagado = valorpagado;
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
        if (!(object instanceof Tmpcxcsaldosiniciales)) {
            return false;
        }
        Tmpcxcsaldosiniciales other = (Tmpcxcsaldosiniciales) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.alphacell.model.cartera.Tmpcxcsaldosiniciales[ id=" + id + " ]";
    }
    
}
