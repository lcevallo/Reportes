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
@Table(name = "TMPCXPTRAMO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tmpcxptramo.findAll", query = "SELECT t FROM Tmpcxptramo t"),
    @NamedQuery(name = "Tmpcxptramo.findByAccountnum", query = "SELECT t FROM Tmpcxptramo t WHERE t.accountnum = :accountnum"),
    @NamedQuery(name = "Tmpcxptramo.findByNombreCliente", query = "SELECT t FROM Tmpcxptramo t WHERE t.nombreCliente = :nombreCliente"),
    @NamedQuery(name = "Tmpcxptramo.findByAmountcur", query = "SELECT t FROM Tmpcxptramo t WHERE t.amountcur = :amountcur"),
    @NamedQuery(name = "Tmpcxptramo.findByAmountmst", query = "SELECT t FROM Tmpcxptramo t WHERE t.amountmst = :amountmst"),
    @NamedQuery(name = "Tmpcxptramo.findByTransdate", query = "SELECT t FROM Tmpcxptramo t WHERE t.transdate = :transdate"),
    @NamedQuery(name = "Tmpcxptramo.findByDuedate", query = "SELECT t FROM Tmpcxptramo t WHERE t.duedate = :duedate"),
    @NamedQuery(name = "Tmpcxptramo.findByRango", query = "SELECT t FROM Tmpcxptramo t WHERE t.rango = :rango"),
    @NamedQuery(name = "Tmpcxptramo.findByNumsemana", query = "SELECT t FROM Tmpcxptramo t WHERE t.numsemana = :numsemana"),
    @NamedQuery(name = "Tmpcxptramo.findBySemana", query = "SELECT t FROM Tmpcxptramo t WHERE t.semana = :semana"),
    @NamedQuery(name = "Tmpcxptramo.findByInvoice", query = "SELECT t FROM Tmpcxptramo t WHERE t.invoice = :invoice"),
    @NamedQuery(name = "Tmpcxptramo.findByTxt", query = "SELECT t FROM Tmpcxptramo t WHERE t.txt = :txt"),
    @NamedQuery(name = "Tmpcxptramo.findById", query = "SELECT t FROM Tmpcxptramo t WHERE t.id = :id")})
public class Tmpcxptramo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 20)
    @Column(name = "ACCOUNTNUM")
    private String accountnum;
    @Size(max = 100)
    @Column(name = "NOMBRE_CLIENTE")
    private String nombreCliente;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "AMOUNTCUR")
    private BigDecimal amountcur;
    @Column(name = "AMOUNTMST")
    private BigDecimal amountmst;
    @Column(name = "TRANSDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transdate;
    @Column(name = "DUEDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date duedate;
    @Size(max = 21)
    @Column(name = "RANGO")
    private String rango;
    @Column(name = "NUMSEMANA")
    private Integer numsemana;
    @Size(max = 14)
    @Column(name = "SEMANA")
    private String semana;
    @Size(max = 20)
    @Column(name = "INVOICE")
    private String invoice;
    @Size(max = 60)
    @Column(name = "TXT")
    private String txt;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;

    public Tmpcxptramo() {
    }

    public Tmpcxptramo(Integer id) {
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

    public BigDecimal getAmountcur() {
        return amountcur;
    }

    public void setAmountcur(BigDecimal amountcur) {
        this.amountcur = amountcur;
    }

    public BigDecimal getAmountmst() {
        return amountmst;
    }

    public void setAmountmst(BigDecimal amountmst) {
        this.amountmst = amountmst;
    }

    public Date getTransdate() {
        return transdate;
    }

    public void setTransdate(Date transdate) {
        this.transdate = transdate;
    }

    public Date getDuedate() {
        return duedate;
    }

    public void setDuedate(Date duedate) {
        this.duedate = duedate;
    }

    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }

    public Integer getNumsemana() {
        return numsemana;
    }

    public void setNumsemana(Integer numsemana) {
        this.numsemana = numsemana;
    }

    public String getSemana() {
        return semana;
    }

    public void setSemana(String semana) {
        this.semana = semana;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        if (!(object instanceof Tmpcxptramo)) {
            return false;
        }
        Tmpcxptramo other = (Tmpcxptramo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.alphacell.model.financiero.Tmpcxptramo[ id=" + id + " ]";
    }
    
}
