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
 * @author luis
 */
@Entity
@Table(name = "tmp_cxp_flujo_vencidos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TmpCxpFlujoVencidos.findAll", query = "SELECT t FROM TmpCxpFlujoVencidos t"),
    @NamedQuery(name = "TmpCxpFlujoVencidos.findById", query = "SELECT t FROM TmpCxpFlujoVencidos t WHERE t.id = :id"),
    @NamedQuery(name = "TmpCxpFlujoVencidos.findByAccountnum", query = "SELECT t FROM TmpCxpFlujoVencidos t WHERE t.accountnum = :accountnum"),
    @NamedQuery(name = "TmpCxpFlujoVencidos.findByName", query = "SELECT t FROM TmpCxpFlujoVencidos t WHERE t.name = :name"),
    @NamedQuery(name = "TmpCxpFlujoVencidos.findByVendGroup", query = "SELECT t FROM TmpCxpFlujoVencidos t WHERE t.vendGroup = :vendGroup"),
    @NamedQuery(name = "TmpCxpFlujoVencidos.findByPaymsched", query = "SELECT t FROM TmpCxpFlujoVencidos t WHERE t.paymsched = :paymsched"),
    @NamedQuery(name = "TmpCxpFlujoVencidos.findByPaymid", query = "SELECT t FROM TmpCxpFlujoVencidos t WHERE t.paymid = :paymid"),
    @NamedQuery(name = "TmpCxpFlujoVencidos.findByNumcuenta", query = "SELECT t FROM TmpCxpFlujoVencidos t WHERE t.numcuenta = :numcuenta"),
    @NamedQuery(name = "TmpCxpFlujoVencidos.findByBankaccount", query = "SELECT t FROM TmpCxpFlujoVencidos t WHERE t.bankaccount = :bankaccount"),
    @NamedQuery(name = "TmpCxpFlujoVencidos.findByCodigobanco", query = "SELECT t FROM TmpCxpFlujoVencidos t WHERE t.codigobanco = :codigobanco"),
    @NamedQuery(name = "TmpCxpFlujoVencidos.findByTipoidentificacion", query = "SELECT t FROM TmpCxpFlujoVencidos t WHERE t.tipoidentificacion = :tipoidentificacion"),
    @NamedQuery(name = "TmpCxpFlujoVencidos.findByFormapago", query = "SELECT t FROM TmpCxpFlujoVencidos t WHERE t.formapago = :formapago"),
    @NamedQuery(name = "TmpCxpFlujoVencidos.findByNumbanco", query = "SELECT t FROM TmpCxpFlujoVencidos t WHERE t.numbanco = :numbanco"),
    @NamedQuery(name = "TmpCxpFlujoVencidos.findByRuc", query = "SELECT t FROM TmpCxpFlujoVencidos t WHERE t.ruc = :ruc"),
    @NamedQuery(name = "TmpCxpFlujoVencidos.findByAmountcur", query = "SELECT t FROM TmpCxpFlujoVencidos t WHERE t.amountcur = :amountcur"),
    @NamedQuery(name = "TmpCxpFlujoVencidos.findByAmountmst", query = "SELECT t FROM TmpCxpFlujoVencidos t WHERE t.amountmst = :amountmst"),
    @NamedQuery(name = "TmpCxpFlujoVencidos.findByTransdate", query = "SELECT t FROM TmpCxpFlujoVencidos t WHERE t.transdate = :transdate"),
    @NamedQuery(name = "TmpCxpFlujoVencidos.findByDuedate", query = "SELECT t FROM TmpCxpFlujoVencidos t WHERE t.duedate = :duedate"),
    @NamedQuery(name = "TmpCxpFlujoVencidos.findByPaymTermId", query = "SELECT t FROM TmpCxpFlujoVencidos t WHERE t.paymTermId = :paymTermId"),
    @NamedQuery(name = "TmpCxpFlujoVencidos.findByInvoice", query = "SELECT t FROM TmpCxpFlujoVencidos t WHERE t.invoice = :invoice"),
    @NamedQuery(name = "TmpCxpFlujoVencidos.findByTxt", query = "SELECT t FROM TmpCxpFlujoVencidos t WHERE t.txt = :txt"),
    @NamedQuery(name = "TmpCxpFlujoVencidos.findByFechaVencimiento", query = "SELECT t FROM TmpCxpFlujoVencidos t WHERE t.fechaVencimiento = :fechaVencimiento"),
    @NamedQuery(name = "TmpCxpFlujoVencidos.findByDiasVencidos", query = "SELECT t FROM TmpCxpFlujoVencidos t WHERE t.diasVencidos = :diasVencidos"),
    @NamedQuery(name = "TmpCxpFlujoVencidos.findByDias15", query = "SELECT t FROM TmpCxpFlujoVencidos t WHERE t.dias15 = :dias15"),
    @NamedQuery(name = "TmpCxpFlujoVencidos.findByDias1530", query = "SELECT t FROM TmpCxpFlujoVencidos t WHERE t.dias1530 = :dias1530"),
    @NamedQuery(name = "TmpCxpFlujoVencidos.findByDias3060", query = "SELECT t FROM TmpCxpFlujoVencidos t WHERE t.dias3060 = :dias3060"),
    @NamedQuery(name = "TmpCxpFlujoVencidos.findByDias6090", query = "SELECT t FROM TmpCxpFlujoVencidos t WHERE t.dias6090 = :dias6090"),
    @NamedQuery(name = "TmpCxpFlujoVencidos.findByDias90120", query = "SELECT t FROM TmpCxpFlujoVencidos t WHERE t.dias90120 = :dias90120"),
    @NamedQuery(name = "TmpCxpFlujoVencidos.findByDias120", query = "SELECT t FROM TmpCxpFlujoVencidos t WHERE t.dias120 = :dias120"),
    @NamedQuery(name = "TmpCxpFlujoVencidos.findByXVencer", query = "SELECT t FROM TmpCxpFlujoVencidos t WHERE t.xVencer = :xVencer")})
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
    @Column(name = "NAME")
    private String name;
    @Size(max = 10)
    @Column(name = "VendGroup")
    private String vendGroup;
    @Size(max = 30)
    @Column(name = "PAYMSCHED")
    private String paymsched;
    @Size(max = 200)
    @Column(name = "PAYMID")
    private String paymid;
    @Size(max = 35)
    @Column(name = "NUMCUENTA")
    private String numcuenta;
    @Size(max = 10)
    @Column(name = "BANKACCOUNT")
    private String bankaccount;
    @Size(max = 2)
    @Column(name = "CODIGOBANCO")
    private String codigobanco;
    @Size(max = 1)
    @Column(name = "TIPOIDENTIFICACION")
    private String tipoidentificacion;
    @Size(max = 3)
    @Column(name = "FORMAPAGO")
    private String formapago;
    @Column(name = "NUMBANCO")
    private Integer numbanco;
    @Size(max = 20)
    @Column(name = "RUC")
    private String ruc;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "AMOUNTCUR")
    private BigDecimal amountcur;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AMOUNTMST")
    private BigDecimal amountmst;
    @Column(name = "TRANSDATE")
    @Temporal(TemporalType.DATE)
    private Date transdate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DUEDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date duedate;
    @Column(name = "PaymTermId")
    private Integer paymTermId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "INVOICE")
    private String invoice;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "TXT")
    private String txt;
    @Column(name = "FECHA_VENCIMIENTO")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;
    @Column(name = "diasVencidos")
    private Integer diasVencidos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Dias15")
    private BigDecimal dias15;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Dias1530")
    private BigDecimal dias1530;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Dias3060")
    private BigDecimal dias3060;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Dias6090")
    private BigDecimal dias6090;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Dias90120")
    private BigDecimal dias90120;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Dias120")
    private BigDecimal dias120;
    @Basic(optional = false)
    @NotNull
    @Column(name = "XVencer")
    private BigDecimal xVencer;

    public TmpCxpFlujoVencidos() {
    }

    public TmpCxpFlujoVencidos(Integer id) {
        this.id = id;
    }

    public TmpCxpFlujoVencidos(Integer id, String accountnum, BigDecimal amountcur, BigDecimal amountmst, Date duedate, String invoice, String txt, BigDecimal dias15, BigDecimal dias1530, BigDecimal dias3060, BigDecimal dias6090, BigDecimal dias90120, BigDecimal dias120, BigDecimal xVencer) {
        this.id = id;
        this.accountnum = accountnum;
        this.amountcur = amountcur;
        this.amountmst = amountmst;
        this.duedate = duedate;
        this.invoice = invoice;
        this.txt = txt;
        this.dias15 = dias15;
        this.dias1530 = dias1530;
        this.dias3060 = dias3060;
        this.dias6090 = dias6090;
        this.dias90120 = dias90120;
        this.dias120 = dias120;
        this.xVencer = xVencer;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVendGroup() {
        return vendGroup;
    }

    public void setVendGroup(String vendGroup) {
        this.vendGroup = vendGroup;
    }

    public String getPaymsched() {
        return paymsched;
    }

    public void setPaymsched(String paymsched) {
        this.paymsched = paymsched;
    }

    public String getPaymid() {
        return paymid;
    }

    public void setPaymid(String paymid) {
        this.paymid = paymid;
    }

    public String getNumcuenta() {
        return numcuenta;
    }

    public void setNumcuenta(String numcuenta) {
        this.numcuenta = numcuenta;
    }

    public String getBankaccount() {
        return bankaccount;
    }

    public void setBankaccount(String bankaccount) {
        this.bankaccount = bankaccount;
    }

    public String getCodigobanco() {
        return codigobanco;
    }

    public void setCodigobanco(String codigobanco) {
        this.codigobanco = codigobanco;
    }

    public String getTipoidentificacion() {
        return tipoidentificacion;
    }

    public void setTipoidentificacion(String tipoidentificacion) {
        this.tipoidentificacion = tipoidentificacion;
    }

    public String getFormapago() {
        return formapago;
    }

    public void setFormapago(String formapago) {
        this.formapago = formapago;
    }

    public Integer getNumbanco() {
        return numbanco;
    }

    public void setNumbanco(Integer numbanco) {
        this.numbanco = numbanco;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
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

    public Integer getPaymTermId() {
        return paymTermId;
    }

    public void setPaymTermId(Integer paymTermId) {
        this.paymTermId = paymTermId;
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

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Integer getDiasVencidos() {
        return diasVencidos;
    }

    public void setDiasVencidos(Integer diasVencidos) {
        this.diasVencidos = diasVencidos;
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

    public BigDecimal getXVencer() {
        return xVencer;
    }

    public void setXVencer(BigDecimal xVencer) {
        this.xVencer = xVencer;
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
    
}
