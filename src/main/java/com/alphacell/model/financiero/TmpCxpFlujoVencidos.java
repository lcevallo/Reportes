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
@Table(name = "tmp_cxp_flujo_vencidos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TmpCxpFlujoVencidos.findAll", query = "SELECT t FROM TmpCxpFlujoVencidos t"),
    @NamedQuery(name = "TmpCxpFlujoVencidos.findById", query = "SELECT t FROM TmpCxpFlujoVencidos t WHERE t.id = :id"),
    @NamedQuery(name = "TmpCxpFlujoVencidos.findByAccountnum", query = "SELECT t FROM TmpCxpFlujoVencidos t WHERE t.accountnum = :accountnum"),
    @NamedQuery(name = "TmpCxpFlujoVencidos.findByNombreCliente", query = "SELECT t FROM TmpCxpFlujoVencidos t WHERE t.nombreCliente = :nombreCliente"),
    @NamedQuery(name = "TmpCxpFlujoVencidos.findByAmountcur", query = "SELECT t FROM TmpCxpFlujoVencidos t WHERE t.amountcur = :amountcur"),
    @NamedQuery(name = "TmpCxpFlujoVencidos.findByAmountmst", query = "SELECT t FROM TmpCxpFlujoVencidos t WHERE t.amountmst = :amountmst"),
    @NamedQuery(name = "TmpCxpFlujoVencidos.findByTransdate", query = "SELECT t FROM TmpCxpFlujoVencidos t WHERE t.transdate = :transdate"),
    @NamedQuery(name = "TmpCxpFlujoVencidos.findByPaymTermId", query = "SELECT t FROM TmpCxpFlujoVencidos t WHERE t.paymTermId = :paymTermId"),
    @NamedQuery(name = "TmpCxpFlujoVencidos.findByFechaVencimiento", query = "SELECT t FROM TmpCxpFlujoVencidos t WHERE t.fechaVencimiento = :fechaVencimiento"),
    @NamedQuery(name = "TmpCxpFlujoVencidos.findByDuedate", query = "SELECT t FROM TmpCxpFlujoVencidos t WHERE t.duedate = :duedate"),
    @NamedQuery(name = "TmpCxpFlujoVencidos.findByDiasVencidos", query = "SELECT t FROM TmpCxpFlujoVencidos t WHERE t.diasVencidos = :diasVencidos"),
    @NamedQuery(name = "TmpCxpFlujoVencidos.findByInvoice", query = "SELECT t FROM TmpCxpFlujoVencidos t WHERE t.invoice = :invoice"),
    @NamedQuery(name = "TmpCxpFlujoVencidos.findByTxt", query = "SELECT t FROM TmpCxpFlujoVencidos t WHERE t.txt = :txt"),
    @NamedQuery(name = "TmpCxpFlujoVencidos.findByDias15", query = "SELECT t FROM TmpCxpFlujoVencidos t WHERE t.dias15 = :dias15"),
    @NamedQuery(name = "TmpCxpFlujoVencidos.findByDias1530", query = "SELECT t FROM TmpCxpFlujoVencidos t WHERE t.dias1530 = :dias1530"),
    @NamedQuery(name = "TmpCxpFlujoVencidos.findByDias3060", query = "SELECT t FROM TmpCxpFlujoVencidos t WHERE t.dias3060 = :dias3060"),
    @NamedQuery(name = "TmpCxpFlujoVencidos.findByDias6090", query = "SELECT t FROM TmpCxpFlujoVencidos t WHERE t.dias6090 = :dias6090"),
    @NamedQuery(name = "TmpCxpFlujoVencidos.findByDias90120", query = "SELECT t FROM TmpCxpFlujoVencidos t WHERE t.dias90120 = :dias90120"),
    @NamedQuery(name = "TmpCxpFlujoVencidos.findByDias120", query = "SELECT t FROM TmpCxpFlujoVencidos t WHERE t.dias120 = :dias120")})
public class TmpCxpFlujoVencidos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
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
    @Temporal(TemporalType.DATE)
    private Date transdate;
    @Column(name = "PaymTermId")
    private Integer paymTermId;
    @Column(name = "FECHA_VENCIMIENTO")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;
    @Column(name = "DUEDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date duedate;
    @Column(name = "diasVencidos")
    private Integer diasVencidos;
    @Size(max = 20)
    @Column(name = "INVOICE")
    private String invoice;
    @Size(max = 60)
    @Column(name = "TXT")
    private String txt;
    @Column(name = "Dias15")
    private BigDecimal dias15;
    @Column(name = "Dias1530")
    private BigDecimal dias1530;
    @Column(name = "Dias3060")
    private BigDecimal dias3060;
    @Column(name = "Dias6090")
    private BigDecimal dias6090;
    @Column(name = "Dias90120")
    private BigDecimal dias90120;
    @Column(name = "Dias120")
    private BigDecimal dias120;

    @Column(name = "XVencer")
    private BigDecimal xVencer;

    public TmpCxpFlujoVencidos() {
    }

    public TmpCxpFlujoVencidos(Integer id) {
        this.id = id;
    }

    public TmpCxpFlujoVencidos(Integer id, String accountnum) {
        this.id = id;
        this.accountnum = accountnum;
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

    public Integer getPaymTermId() {
        return paymTermId;
    }

    public void setPaymTermId(Integer paymTermId) {
        this.paymTermId = paymTermId;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Date getDuedate() {
        return duedate;
    }

    public void setDuedate(Date duedate) {
        this.duedate = duedate;
    }

    public Integer getDiasVencidos() {
        return diasVencidos;
    }

    public void setDiasVencidos(Integer diasVencidos) {
        this.diasVencidos = diasVencidos;
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

    public BigDecimal getDias15() {
        return dias15;
    }

    public void setDias15(BigDecimal dias15) {
        this.dias15 = dias15;
    }

    public BigDecimal getDias1530() {
        return dias1530;
    }

    public void setDias1530(BigDecimal dias1530) {
        this.dias1530 = dias1530;
    }

    public BigDecimal getDias3060() {
        return dias3060;
    }

    public void setDias3060(BigDecimal dias3060) {
        this.dias3060 = dias3060;
    }

    public BigDecimal getDias6090() {
        return dias6090;
    }

    public void setDias6090(BigDecimal dias6090) {
        this.dias6090 = dias6090;
    }

    public BigDecimal getDias90120() {
        return dias90120;
    }

    public void setDias90120(BigDecimal dias90120) {
        this.dias90120 = dias90120;
    }

    public BigDecimal getDias120() {
        return dias120;
    }

    public void setDias120(BigDecimal dias120) {
        this.dias120 = dias120;
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
        if (!(object instanceof TmpCxpFlujoVencidos)) {
            return false;
        }
        TmpCxpFlujoVencidos other = (TmpCxpFlujoVencidos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.alphacell.model.financiero.TmpCxpFlujoVencidos[ id=" + id + " ]";
    }

    public BigDecimal getxVencer() {
        return xVencer;
    }

    public void setxVencer(BigDecimal xVencer) {
        this.xVencer = xVencer;
    }
}
