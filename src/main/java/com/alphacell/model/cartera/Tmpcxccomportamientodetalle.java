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
@Table(name = "TMPCXCCOMPORTAMIENTODETALLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tmpcxccomportamientodetalle.findAll", query = "SELECT t FROM Tmpcxccomportamientodetalle t"),
    @NamedQuery(name = "Tmpcxccomportamientodetalle.findByRecid", query = "SELECT t FROM Tmpcxccomportamientodetalle t WHERE t.recid = :recid"),
    @NamedQuery(name = "Tmpcxccomportamientodetalle.findByTranstype", query = "SELECT t FROM Tmpcxccomportamientodetalle t WHERE t.transtype = :transtype"),
    @NamedQuery(name = "Tmpcxccomportamientodetalle.findByAccountnum", query = "SELECT t FROM Tmpcxccomportamientodetalle t WHERE t.accountnum = :accountnum"),
    @NamedQuery(name = "Tmpcxccomportamientodetalle.findByNombrecliente", query = "SELECT t FROM Tmpcxccomportamientodetalle t WHERE t.nombrecliente = :nombrecliente"),
    @NamedQuery(name = "Tmpcxccomportamientodetalle.findByVoucher", query = "SELECT t FROM Tmpcxccomportamientodetalle t WHERE t.voucher = :voucher"),
    @NamedQuery(name = "Tmpcxccomportamientodetalle.findByInvoice", query = "SELECT t FROM Tmpcxccomportamientodetalle t WHERE t.invoice = :invoice"),
    @NamedQuery(name = "Tmpcxccomportamientodetalle.findByOffsettransvoucher", query = "SELECT t FROM Tmpcxccomportamientodetalle t WHERE t.offsettransvoucher = :offsettransvoucher"),
    @NamedQuery(name = "Tmpcxccomportamientodetalle.findBySettleamountmst", query = "SELECT t FROM Tmpcxccomportamientodetalle t WHERE t.settleamountmst = :settleamountmst"),
    @NamedQuery(name = "Tmpcxccomportamientodetalle.findBySettleamountcur", query = "SELECT t FROM Tmpcxccomportamientodetalle t WHERE t.settleamountcur = :settleamountcur"),
    @NamedQuery(name = "Tmpcxccomportamientodetalle.findByTransdate", query = "SELECT t FROM Tmpcxccomportamientodetalle t WHERE t.transdate = :transdate"),
    @NamedQuery(name = "Tmpcxccomportamientodetalle.findByTxt", query = "SELECT t FROM Tmpcxccomportamientodetalle t WHERE t.txt = :txt"),
    @NamedQuery(name = "Tmpcxccomportamientodetalle.findByTxt2", query = "SELECT t FROM Tmpcxccomportamientodetalle t WHERE t.txt2 = :txt2"),
    @NamedQuery(name = "Tmpcxccomportamientodetalle.findById", query = "SELECT t FROM Tmpcxccomportamientodetalle t WHERE t.id = :id"),
    @NamedQuery(name = "Tmpcxccomportamientodetalle.findByOffsetrecid", query = "SELECT t FROM Tmpcxccomportamientodetalle t WHERE t.offsetrecid = :offsetrecid")})
public class Tmpcxccomportamientodetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RECID")
    private long recid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRANSTYPE")
    private int transtype;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ACCOUNTNUM")
    private String accountnum;
    @Size(max = 100)
    @Column(name = "NOMBRECLIENTE")
    private String nombrecliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "VOUCHER")
    private String voucher;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "INVOICE")
    private String invoice;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "OFFSETTRANSVOUCHER")
    private String offsettransvoucher;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "SETTLEAMOUNTMST")
    private BigDecimal settleamountmst;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SETTLEAMOUNTCUR")
    private BigDecimal settleamountcur;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRANSDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transdate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "TXT")
    private String txt;
    @Size(max = 60)
    @Column(name = "txt2")
    private String txt2;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Column(name = "OFFSETRECID")
    private BigInteger offsetrecid;

    public Tmpcxccomportamientodetalle() {
    }

    public Tmpcxccomportamientodetalle(Integer id) {
        this.id = id;
    }

    public Tmpcxccomportamientodetalle(Integer id, long recid, int transtype, String accountnum, String voucher, String invoice, String offsettransvoucher, BigDecimal settleamountmst, BigDecimal settleamountcur, Date transdate, String txt) {
        this.id = id;
        this.recid = recid;
        this.transtype = transtype;
        this.accountnum = accountnum;
        this.voucher = voucher;
        this.invoice = invoice;
        this.offsettransvoucher = offsettransvoucher;
        this.settleamountmst = settleamountmst;
        this.settleamountcur = settleamountcur;
        this.transdate = transdate;
        this.txt = txt;
    }

    public long getRecid() {
        return recid;
    }

    public void setRecid(long recid) {
        this.recid = recid;
    }

    public int getTranstype() {
        return transtype;
    }

    public void setTranstype(int transtype) {
        this.transtype = transtype;
    }

    public String getAccountnum() {
        return accountnum;
    }

    public void setAccountnum(String accountnum) {
        this.accountnum = accountnum;
    }

    public String getNombrecliente() {
        return nombrecliente;
    }

    public void setNombrecliente(String nombrecliente) {
        this.nombrecliente = nombrecliente;
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

    public String getOffsettransvoucher() {
        return offsettransvoucher;
    }

    public void setOffsettransvoucher(String offsettransvoucher) {
        this.offsettransvoucher = offsettransvoucher;
    }

    public BigDecimal getSettleamountmst() {
        return settleamountmst;
    }

    public void setSettleamountmst(BigDecimal settleamountmst) {
        this.settleamountmst = settleamountmst;
    }

    public BigDecimal getSettleamountcur() {
        return settleamountcur;
    }

    public void setSettleamountcur(BigDecimal settleamountcur) {
        this.settleamountcur = settleamountcur;
    }

    public Date getTransdate() {
        return transdate;
    }

    public void setTransdate(Date transdate) {
        this.transdate = transdate;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getTxt2() {
        return txt2;
    }

    public void setTxt2(String txt2) {
        this.txt2 = txt2;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigInteger getOffsetrecid() {
        return offsetrecid;
    }

    public void setOffsetrecid(BigInteger offsetrecid) {
        this.offsetrecid = offsetrecid;
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
        if (!(object instanceof Tmpcxccomportamientodetalle)) {
            return false;
        }
        Tmpcxccomportamientodetalle other = (Tmpcxccomportamientodetalle) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.alphacell.controller.cartera.Tmpcxccomportamientodetalle[ id=" + id + " ]";
    }
    
}
