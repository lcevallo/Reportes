/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphacell.model.financiero;

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
@Table(name = "LC_TBL_CXP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LcTblCxp.findAll", query = "SELECT l FROM LcTblCxp l"),
    @NamedQuery(name = "LcTblCxp.findById", query = "SELECT l FROM LcTblCxp l WHERE l.id = :id"),
    @NamedQuery(name = "LcTblCxp.findByAccountnum", query = "SELECT l FROM LcTblCxp l WHERE l.accountnum = :accountnum"),
    @NamedQuery(name = "LcTblCxp.findByNombreCliente", query = "SELECT l FROM LcTblCxp l WHERE l.nombreCliente = :nombreCliente"),
    @NamedQuery(name = "LcTblCxp.findByVoucher", query = "SELECT l FROM LcTblCxp l WHERE l.voucher = :voucher"),
    @NamedQuery(name = "LcTblCxp.findByInvoice", query = "SELECT l FROM LcTblCxp l WHERE l.invoice = :invoice"),
    @NamedQuery(name = "LcTblCxp.findByLastsettlevoucher", query = "SELECT l FROM LcTblCxp l WHERE l.lastsettlevoucher = :lastsettlevoucher"),
    @NamedQuery(name = "LcTblCxp.findByPaymReference", query = "SELECT l FROM LcTblCxp l WHERE l.paymReference = :paymReference"),
    @NamedQuery(name = "LcTblCxp.findByTransdate", query = "SELECT l FROM LcTblCxp l WHERE l.transdate = :transdate"),
    @NamedQuery(name = "LcTblCxp.findByDebito", query = "SELECT l FROM LcTblCxp l WHERE l.debito = :debito"),
    @NamedQuery(name = "LcTblCxp.findByCredito", query = "SELECT l FROM LcTblCxp l WHERE l.credito = :credito"),
    @NamedQuery(name = "LcTblCxp.findByDes", query = "SELECT l FROM LcTblCxp l WHERE l.des = :des"),
    @NamedQuery(name = "LcTblCxp.findByLastexchadjvoucher", query = "SELECT l FROM LcTblCxp l WHERE l.lastexchadjvoucher = :lastexchadjvoucher"),
    @NamedQuery(name = "LcTblCxp.findByTaxinvoicepurchid", query = "SELECT l FROM LcTblCxp l WHERE l.taxinvoicepurchid = :taxinvoicepurchid")})
public class LcTblCxp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 20)
    @Column(name = "ACCOUNTNUM")
    private String accountnum;
    @Size(max = 100)
    @Column(name = "NOMBRE_CLIENTE")
    private String nombreCliente;
    @Size(max = 10)
    @Column(name = "TIPO")
    private String tipo;
    @Size(max = 20)
    @Column(name = "VOUCHER")
    private String voucher;
    @Size(max = 20)
    @Column(name = "INVOICE")
    private String invoice;
    @Size(max = 20)
    @Column(name = "LASTSETTLEVOUCHER")
    private String lastsettlevoucher;
    @Size(max = 20)
    @Column(name = "PaymReference")
    private String paymReference;
    @Column(name = "TRANSDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transdate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "DEBITO")
    private BigDecimal debito;
    @Column(name = "CREDITO")
    private BigDecimal credito;
    @Size(max = 110)
    @Column(name = "DES")
    private String des;
    @Size(max = 20)
    @Column(name = "LASTEXCHADJVOUCHER")
    private String lastexchadjvoucher;
    @Size(max = 20)
    @Column(name = "TAXINVOICEPURCHID")
    private String taxinvoicepurchid;

    public LcTblCxp() {
    }

    public LcTblCxp(Integer id) {
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

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
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

    public String getLastsettlevoucher() {
        return lastsettlevoucher;
    }

    public void setLastsettlevoucher(String lastsettlevoucher) {
        this.lastsettlevoucher = lastsettlevoucher;
    }

    public String getPaymReference() {
        return paymReference;
    }

    public void setPaymReference(String paymReference) {
        this.paymReference = paymReference;
    }

    public Date getTransdate() {
        return transdate;
    }

    public void setTransdate(Date transdate) {
        this.transdate = transdate;
    }

    public BigDecimal getDebito() {
        return debito;
    }

    public void setDebito(BigDecimal debito) {
        this.debito = debito;
    }

    public BigDecimal getCredito() {
        return credito;
    }

    public void setCredito(BigDecimal credito) {
        this.credito = credito;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getLastexchadjvoucher() {
        return lastexchadjvoucher;
    }

    public void setLastexchadjvoucher(String lastexchadjvoucher) {
        this.lastexchadjvoucher = lastexchadjvoucher;
    }

    public String getTaxinvoicepurchid() {
        return taxinvoicepurchid;
    }

    public void setTaxinvoicepurchid(String taxinvoicepurchid) {
        this.taxinvoicepurchid = taxinvoicepurchid;
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
        if (!(object instanceof LcTblCxp)) {
            return false;
        }
        LcTblCxp other = (LcTblCxp) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.alphacell.model.financiero.LcTblCxp[ id=" + id + " ]";
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
