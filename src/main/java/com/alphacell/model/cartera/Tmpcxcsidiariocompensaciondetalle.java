/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphacell.model.cartera;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
@Table(name = "TMPCXCSIDIARIOCOMPENSACIONDETALLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tmpcxcsidiariocompensaciondetalle.findAll", query = "SELECT t FROM Tmpcxcsidiariocompensaciondetalle t"),
    @NamedQuery(name = "Tmpcxcsidiariocompensaciondetalle.findById", query = "SELECT t FROM Tmpcxcsidiariocompensaciondetalle t WHERE t.id = :id"),
    @NamedQuery(name = "Tmpcxcsidiariocompensaciondetalle.findByAccountnum", query = "SELECT t FROM Tmpcxcsidiariocompensaciondetalle t WHERE t.accountnum = :accountnum"),
    @NamedQuery(name = "Tmpcxcsidiariocompensaciondetalle.findByInvoice", query = "SELECT t FROM Tmpcxcsidiariocompensaciondetalle t WHERE t.invoice = :invoice"),
    @NamedQuery(name = "Tmpcxcsidiariocompensaciondetalle.findByAmountcur", query = "SELECT t FROM Tmpcxcsidiariocompensaciondetalle t WHERE t.amountcur = :amountcur"),
    @NamedQuery(name = "Tmpcxcsidiariocompensaciondetalle.findByOffsettransvoucher", query = "SELECT t FROM Tmpcxcsidiariocompensaciondetalle t WHERE t.offsettransvoucher = :offsettransvoucher"),
    @NamedQuery(name = "Tmpcxcsidiariocompensaciondetalle.findByJournalnum", query = "SELECT t FROM Tmpcxcsidiariocompensaciondetalle t WHERE t.journalnum = :journalnum"),
    @NamedQuery(name = "Tmpcxcsidiariocompensaciondetalle.findByTxt", query = "SELECT t FROM Tmpcxcsidiariocompensaciondetalle t WHERE t.txt = :txt"),
    @NamedQuery(name = "Tmpcxcsidiariocompensaciondetalle.findByAmountcurcredit", query = "SELECT t FROM Tmpcxcsidiariocompensaciondetalle t WHERE t.amountcurcredit = :amountcurcredit"),
    @NamedQuery(name = "Tmpcxcsidiariocompensaciondetalle.findByAcknowledgementdate", query = "SELECT t FROM Tmpcxcsidiariocompensaciondetalle t WHERE t.acknowledgementdate = :acknowledgementdate"),
    @NamedQuery(name = "Tmpcxcsidiariocompensaciondetalle.findByOffsetaccounttype", query = "SELECT t FROM Tmpcxcsidiariocompensaciondetalle t WHERE t.offsetaccounttype = :offsetaccounttype"),
    @NamedQuery(name = "Tmpcxcsidiariocompensaciondetalle.findByPaymreference", query = "SELECT t FROM Tmpcxcsidiariocompensaciondetalle t WHERE t.paymreference = :paymreference"),
    @NamedQuery(name = "Tmpcxcsidiariocompensaciondetalle.findByPaymmode", query = "SELECT t FROM Tmpcxcsidiariocompensaciondetalle t WHERE t.paymmode = :paymmode"),
    @NamedQuery(name = "Tmpcxcsidiariocompensaciondetalle.findBySettlevoucher", query = "SELECT t FROM Tmpcxcsidiariocompensaciondetalle t WHERE t.settlevoucher = :settlevoucher"),
    @NamedQuery(name = "Tmpcxcsidiariocompensaciondetalle.findByLedgerdimension", query = "SELECT t FROM Tmpcxcsidiariocompensaciondetalle t WHERE t.ledgerdimension = :ledgerdimension"),
    @NamedQuery(name = "Tmpcxcsidiariocompensaciondetalle.findByOffsetledgerdimension", query = "SELECT t FROM Tmpcxcsidiariocompensaciondetalle t WHERE t.offsetledgerdimension = :offsetledgerdimension"),
    @NamedQuery(name = "Tmpcxcsidiariocompensaciondetalle.findByContrapartidacuenta", query = "SELECT t FROM Tmpcxcsidiariocompensaciondetalle t WHERE t.contrapartidacuenta = :contrapartidacuenta")})
public class Tmpcxcsidiariocompensaciondetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 20)
    @Column(name = "ACCOUNTNUM")
    private String accountnum;
    @Size(max = 20)
    @Column(name = "INVOICE")
    private String invoice;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "AMOUNTCUR")
    private BigDecimal amountcur;
    @Size(max = 20)
    @Column(name = "OFFSETTRANSVOUCHER")
    private String offsettransvoucher;
    @Size(max = 10)
    @Column(name = "JOURNALNUM")
    private String journalnum;
    @Size(max = 60)
    @Column(name = "TXT")
    private String txt;
    @Column(name = "AMOUNTCURCREDIT")
    private BigDecimal amountcurcredit;
    @Column(name = "ACKNOWLEDGEMENTDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date acknowledgementdate;
    @Column(name = "OFFSETACCOUNTTYPE")
    private Integer offsetaccounttype;
    @Size(max = 20)
    @Column(name = "PAYMREFERENCE")
    private String paymreference;
    @Size(max = 10)
    @Column(name = "PAYMMODE")
    private String paymmode;
    @Column(name = "SETTLEVOUCHER")
    private Integer settlevoucher;
    @Column(name = "LEDGERDIMENSION")
    private BigInteger ledgerdimension;
    @Column(name = "OFFSETLEDGERDIMENSION")
    private BigInteger offsetledgerdimension;
    @Size(max = 60)
    @Column(name = "CONTRAPARTIDACUENTA")
    private String contrapartidacuenta;

    public Tmpcxcsidiariocompensaciondetalle() {
    }

    public Tmpcxcsidiariocompensaciondetalle(Integer id) {
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

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public BigDecimal getAmountcur() {
        return amountcur;
    }

    public void setAmountcur(BigDecimal amountcur) {
        this.amountcur = amountcur;
    }

    public String getOffsettransvoucher() {
        return offsettransvoucher;
    }

    public void setOffsettransvoucher(String offsettransvoucher) {
        this.offsettransvoucher = offsettransvoucher;
    }

    public String getJournalnum() {
        return journalnum;
    }

    public void setJournalnum(String journalnum) {
        this.journalnum = journalnum;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public BigDecimal getAmountcurcredit() {
        return amountcurcredit;
    }

    public void setAmountcurcredit(BigDecimal amountcurcredit) {
        this.amountcurcredit = amountcurcredit;
    }

    public Date getAcknowledgementdate() {
        return acknowledgementdate;
    }

    public void setAcknowledgementdate(Date acknowledgementdate) {
        this.acknowledgementdate = acknowledgementdate;
    }

    public Integer getOffsetaccounttype() {
        return offsetaccounttype;
    }

    public void setOffsetaccounttype(Integer offsetaccounttype) {
        this.offsetaccounttype = offsetaccounttype;
    }

    public String getPaymreference() {
        return paymreference;
    }

    public void setPaymreference(String paymreference) {
        this.paymreference = paymreference;
    }

    public String getPaymmode() {
        return paymmode;
    }

    public void setPaymmode(String paymmode) {
        this.paymmode = paymmode;
    }

    public Integer getSettlevoucher() {
        return settlevoucher;
    }

    public void setSettlevoucher(Integer settlevoucher) {
        this.settlevoucher = settlevoucher;
    }

    public BigInteger getLedgerdimension() {
        return ledgerdimension;
    }

    public void setLedgerdimension(BigInteger ledgerdimension) {
        this.ledgerdimension = ledgerdimension;
    }

    public BigInteger getOffsetledgerdimension() {
        return offsetledgerdimension;
    }

    public void setOffsetledgerdimension(BigInteger offsetledgerdimension) {
        this.offsetledgerdimension = offsetledgerdimension;
    }

    public String getContrapartidacuenta() {
        return contrapartidacuenta;
    }

    public void setContrapartidacuenta(String contrapartidacuenta) {
        this.contrapartidacuenta = contrapartidacuenta;
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
        if (!(object instanceof Tmpcxcsidiariocompensaciondetalle)) {
            return false;
        }
        Tmpcxcsidiariocompensaciondetalle other = (Tmpcxcsidiariocompensaciondetalle) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.alphacell.model.cartera.Tmpcxcsidiariocompensaciondetalle[ id=" + id + " ]";
    }
    
}
