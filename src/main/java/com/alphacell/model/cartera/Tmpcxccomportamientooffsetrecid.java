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
@Table(name = "TMPCXCCOMPORTAMIENTOOFFSETRECID")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tmpcxccomportamientooffsetrecid.findAll", query = "SELECT t FROM Tmpcxccomportamientooffsetrecid t"),
    @NamedQuery(name = "Tmpcxccomportamientooffsetrecid.findById", query = "SELECT t FROM Tmpcxccomportamientooffsetrecid t WHERE t.id = :id"),
    @NamedQuery(name = "Tmpcxccomportamientooffsetrecid.findByAccountnum", query = "SELECT t FROM Tmpcxccomportamientooffsetrecid t WHERE t.accountnum = :accountnum"),
    @NamedQuery(name = "Tmpcxccomportamientooffsetrecid.findByInvoice", query = "SELECT t FROM Tmpcxccomportamientooffsetrecid t WHERE t.invoice = :invoice"),
    @NamedQuery(name = "Tmpcxccomportamientooffsetrecid.findByOffsettransvoucher", query = "SELECT t FROM Tmpcxccomportamientooffsetrecid t WHERE t.offsettransvoucher = :offsettransvoucher"),
    @NamedQuery(name = "Tmpcxccomportamientooffsetrecid.findByVoucher", query = "SELECT t FROM Tmpcxccomportamientooffsetrecid t WHERE t.voucher = :voucher"),
    @NamedQuery(name = "Tmpcxccomportamientooffsetrecid.findByTxt", query = "SELECT t FROM Tmpcxccomportamientooffsetrecid t WHERE t.txt = :txt"),
    @NamedQuery(name = "Tmpcxccomportamientooffsetrecid.findByTransrecid", query = "SELECT t FROM Tmpcxccomportamientooffsetrecid t WHERE t.transrecid = :transrecid"),
    @NamedQuery(name = "Tmpcxccomportamientooffsetrecid.findByTransdate", query = "SELECT t FROM Tmpcxccomportamientooffsetrecid t WHERE t.transdate = :transdate"),
    @NamedQuery(name = "Tmpcxccomportamientooffsetrecid.findBySettleamountmst", query = "SELECT t FROM Tmpcxccomportamientooffsetrecid t WHERE t.settleamountmst = :settleamountmst")})
public class Tmpcxccomportamientooffsetrecid implements Serializable {

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
    @Size(max = 20)
    @Column(name = "OFFSETTRANSVOUCHER")
    private String offsettransvoucher;
    @Size(max = 20)
    @Column(name = "VOUCHER")
    private String voucher;
    @Size(max = 60)
    @Column(name = "TXT")
    private String txt;
    @Column(name = "TRANSRECID")
    private BigInteger transrecid;
    @Column(name = "TRANSDATE")
    @Temporal(TemporalType.DATE)
    private Date transdate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SETTLEAMOUNTMST")
    private BigDecimal settleamountmst;

    public Tmpcxccomportamientooffsetrecid() {
    }

    public Tmpcxccomportamientooffsetrecid(Integer id) {
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

    public String getOffsettransvoucher() {
        return offsettransvoucher;
    }

    public void setOffsettransvoucher(String offsettransvoucher) {
        this.offsettransvoucher = offsettransvoucher;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public BigInteger getTransrecid() {
        return transrecid;
    }

    public void setTransrecid(BigInteger transrecid) {
        this.transrecid = transrecid;
    }

    public Date getTransdate() {
        return transdate;
    }

    public void setTransdate(Date transdate) {
        this.transdate = transdate;
    }

    public BigDecimal getSettleamountmst() {
        return settleamountmst;
    }

    public void setSettleamountmst(BigDecimal settleamountmst) {
        this.settleamountmst = settleamountmst;
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
        if (!(object instanceof Tmpcxccomportamientooffsetrecid)) {
            return false;
        }
        Tmpcxccomportamientooffsetrecid other = (Tmpcxccomportamientooffsetrecid) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.alphacell.model.cartera.Tmpcxccomportamientooffsetrecid[ id=" + id + " ]";
    }
    
}
