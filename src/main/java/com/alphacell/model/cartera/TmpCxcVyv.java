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
 * @author luis
 */
@Entity
@Table(name = "TMP_CXC_VYV")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TmpCxcVyv.findAll", query = "SELECT t FROM TmpCxcVyv t"),
    @NamedQuery(name = "TmpCxcVyv.findById", query = "SELECT t FROM TmpCxcVyv t WHERE t.id = :id"),
    @NamedQuery(name = "TmpCxcVyv.findByAccountnum", query = "SELECT t FROM TmpCxcVyv t WHERE t.accountnum = :accountnum"),
    @NamedQuery(name = "TmpCxcVyv.findByNombreCliente", query = "SELECT t FROM TmpCxcVyv t WHERE t.nombreCliente = :nombreCliente"),
    @NamedQuery(name = "TmpCxcVyv.findByDimension1", query = "SELECT t FROM TmpCxcVyv t WHERE t.dimension1 = :dimension1"),
    @NamedQuery(name = "TmpCxcVyv.findByMultivencimiento", query = "SELECT t FROM TmpCxcVyv t WHERE t.multivencimiento = :multivencimiento"),
    @NamedQuery(name = "TmpCxcVyv.findByColor", query = "SELECT t FROM TmpCxcVyv t WHERE t.color = :color"),
    @NamedQuery(name = "TmpCxcVyv.findByAmountcur", query = "SELECT t FROM TmpCxcVyv t WHERE t.amountcur = :amountcur"),
    @NamedQuery(name = "TmpCxcVyv.findByDebe", query = "SELECT t FROM TmpCxcVyv t WHERE t.debe = :debe"),
    @NamedQuery(name = "TmpCxcVyv.findByDocumentdate", query = "SELECT t FROM TmpCxcVyv t WHERE t.documentdate = :documentdate"),
    @NamedQuery(name = "TmpCxcVyv.findByTransdate", query = "SELECT t FROM TmpCxcVyv t WHERE t.transdate = :transdate"),
    @NamedQuery(name = "TmpCxcVyv.findByVoucher", query = "SELECT t FROM TmpCxcVyv t WHERE t.voucher = :voucher"),
    @NamedQuery(name = "TmpCxcVyv.findByInvoice", query = "SELECT t FROM TmpCxcVyv t WHERE t.invoice = :invoice"),
    @NamedQuery(name = "TmpCxcVyv.findByClosed", query = "SELECT t FROM TmpCxcVyv t WHERE t.closed = :closed"),
    @NamedQuery(name = "TmpCxcVyv.findByDuedate", query = "SELECT t FROM TmpCxcVyv t WHERE t.duedate = :duedate"),
    @NamedQuery(name = "TmpCxcVyv.findByDuedate2", query = "SELECT t FROM TmpCxcVyv t WHERE t.duedate2 = :duedate2"),
    @NamedQuery(name = "TmpCxcVyv.findByRecid", query = "SELECT t FROM TmpCxcVyv t WHERE t.recid = :recid"),
    @NamedQuery(name = "TmpCxcVyv.findByOffsetrecid", query = "SELECT t FROM TmpCxcVyv t WHERE t.offsetrecid = :offsetrecid"),
    @NamedQuery(name = "TmpCxcVyv.findBySalesid", query = "SELECT t FROM TmpCxcVyv t WHERE t.salesid = :salesid"),
    @NamedQuery(name = "TmpCxcVyv.findByRecidsales", query = "SELECT t FROM TmpCxcVyv t WHERE t.recidsales = :recidsales"),
    @NamedQuery(name = "TmpCxcVyv.findByDiasUsados", query = "SELECT t FROM TmpCxcVyv t WHERE t.diasUsados = :diasUsados"),
    @NamedQuery(name = "TmpCxcVyv.findByPagada", query = "SELECT t FROM TmpCxcVyv t WHERE t.pagada = :pagada"),
    @NamedQuery(name = "TmpCxcVyv.findByTotalVencido", query = "SELECT t FROM TmpCxcVyv t WHERE t.totalVencido = :totalVencido"),
    @NamedQuery(name = "TmpCxcVyv.findByDias1a30V", query = "SELECT t FROM TmpCxcVyv t WHERE t.dias1a30V = :dias1a30V"),
    @NamedQuery(name = "TmpCxcVyv.findByDias31a60V", query = "SELECT t FROM TmpCxcVyv t WHERE t.dias31a60V = :dias31a60V"),
    @NamedQuery(name = "TmpCxcVyv.findByDias61a90V", query = "SELECT t FROM TmpCxcVyv t WHERE t.dias61a90V = :dias61a90V"),
    @NamedQuery(name = "TmpCxcVyv.findByDias91a120V", query = "SELECT t FROM TmpCxcVyv t WHERE t.dias91a120V = :dias91a120V"),
    @NamedQuery(name = "TmpCxcVyv.findByDias121V", query = "SELECT t FROM TmpCxcVyv t WHERE t.dias121V = :dias121V"),
    @NamedQuery(name = "TmpCxcVyv.findByTotalXVencer", query = "SELECT t FROM TmpCxcVyv t WHERE t.totalXVencer = :totalXVencer"),
    @NamedQuery(name = "TmpCxcVyv.findByDias1a30XV", query = "SELECT t FROM TmpCxcVyv t WHERE t.dias1a30XV = :dias1a30XV"),
    @NamedQuery(name = "TmpCxcVyv.findByDias31a60XV", query = "SELECT t FROM TmpCxcVyv t WHERE t.dias31a60XV = :dias31a60XV"),
    @NamedQuery(name = "TmpCxcVyv.findByDias61a90XV", query = "SELECT t FROM TmpCxcVyv t WHERE t.dias61a90XV = :dias61a90XV"),
    @NamedQuery(name = "TmpCxcVyv.findByDias91a120XV", query = "SELECT t FROM TmpCxcVyv t WHERE t.dias91a120XV = :dias91a120XV"),
    @NamedQuery(name = "TmpCxcVyv.findByDias121XV", query = "SELECT t FROM TmpCxcVyv t WHERE t.dias121XV = :dias121XV")})
public class TmpCxcVyv implements Serializable {

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
    @Size(max = 30)
    @Column(name = "DIMENSION1")
    private String dimension1;
    @Size(max = 60)
    @Column(name = "MULTIVENCIMIENTO")
    private String multivencimiento;
    @Size(max = 2)
    @Column(name = "COLOR")
    private String color;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "AMOUNTCUR")
    private BigDecimal amountcur;
    @Column(name = "DEBE")
    private BigDecimal debe;
    @Column(name = "DOCUMENTDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date documentdate;
    @Column(name = "TRANSDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transdate;
    @Size(max = 20)
    @Column(name = "VOUCHER")
    private String voucher;
    @Size(max = 20)
    @Column(name = "INVOICE")
    private String invoice;
    @Column(name = "CLOSED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date closed;
    @Column(name = "DUEDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date duedate;
    @Column(name = "DUEDATE2")
    @Temporal(TemporalType.TIMESTAMP)
    private Date duedate2;
    @Column(name = "RECID")
    private BigInteger recid;
    @Column(name = "OFFSETRECID")
    private BigInteger offsetrecid;
    @Size(max = 20)
    @Column(name = "SALESID")
    private String salesid;
    @Column(name = "RECIDSALES")
    private BigInteger recidsales;
    @Column(name = "dias_usados")
    private Integer diasUsados;
    @Size(max = 7)
    @Column(name = "PAGADA")
    private String pagada;
    @Column(name = "TOTAL_VENCIDO")
    private BigDecimal totalVencido;
    @Column(name = "Dias1a30V")
    private BigDecimal dias1a30V;
    @Column(name = "Dias31a60V")
    private BigDecimal dias31a60V;
    @Column(name = "Dias61a90V")
    private BigDecimal dias61a90V;
    @Column(name = "Dias91a120V")
    private BigDecimal dias91a120V;
    @Column(name = "Dias121V")
    private BigDecimal dias121V;
    @Column(name = "TOTAL_X_VENCER")
    private BigDecimal totalXVencer;
    @Column(name = "Dias1a30XV")
    private BigDecimal dias1a30XV;
    @Column(name = "Dias31a60XV")
    private BigDecimal dias31a60XV;
    @Column(name = "Dias61a90XV")
    private BigDecimal dias61a90XV;
    @Column(name = "Dias91a120XV")
    private BigDecimal dias91a120XV;
    @Column(name = "Dias121XV")
    private BigDecimal dias121XV;

    public TmpCxcVyv() {
    }

    public TmpCxcVyv(Integer id) {
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

    public String getDimension1() {
        return dimension1;
    }

    public void setDimension1(String dimension1) {
        this.dimension1 = dimension1;
    }

    public String getMultivencimiento() {
        return multivencimiento;
    }

    public void setMultivencimiento(String multivencimiento) {
        this.multivencimiento = multivencimiento;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public BigDecimal getAmountcur() {
        return amountcur;
    }

    public void setAmountcur(BigDecimal amountcur) {
        this.amountcur = amountcur;
    }

    public BigDecimal getDebe() {
        return debe;
    }

    public void setDebe(BigDecimal debe) {
        this.debe = debe;
    }

    public Date getDocumentdate() {
        return documentdate;
    }

    public void setDocumentdate(Date documentdate) {
        this.documentdate = documentdate;
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

    public Date getClosed() {
        return closed;
    }

    public void setClosed(Date closed) {
        this.closed = closed;
    }

    public Date getDuedate() {
        return duedate;
    }

    public void setDuedate(Date duedate) {
        this.duedate = duedate;
    }

    public Date getDuedate2() {
        return duedate2;
    }

    public void setDuedate2(Date duedate2) {
        this.duedate2 = duedate2;
    }

    public BigInteger getRecid() {
        return recid;
    }

    public void setRecid(BigInteger recid) {
        this.recid = recid;
    }

    public BigInteger getOffsetrecid() {
        return offsetrecid;
    }

    public void setOffsetrecid(BigInteger offsetrecid) {
        this.offsetrecid = offsetrecid;
    }

    public String getSalesid() {
        return salesid;
    }

    public void setSalesid(String salesid) {
        this.salesid = salesid;
    }

    public BigInteger getRecidsales() {
        return recidsales;
    }

    public void setRecidsales(BigInteger recidsales) {
        this.recidsales = recidsales;
    }

    public Integer getDiasUsados() {
        return diasUsados;
    }

    public void setDiasUsados(Integer diasUsados) {
        this.diasUsados = diasUsados;
    }

    public String getPagada() {
        return pagada;
    }

    public void setPagada(String pagada) {
        this.pagada = pagada;
    }

    public BigDecimal getTotalVencido() {
        return totalVencido;
    }

    public void setTotalVencido(BigDecimal totalVencido) {
        this.totalVencido = totalVencido;
    }

    public BigDecimal getDias1a30V() {
        return dias1a30V;
    }

    public void setDias1a30V(BigDecimal dias1a30V) {
        this.dias1a30V = dias1a30V;
    }

    public BigDecimal getDias31a60V() {
        return dias31a60V;
    }

    public void setDias31a60V(BigDecimal dias31a60V) {
        this.dias31a60V = dias31a60V;
    }

    public BigDecimal getDias61a90V() {
        return dias61a90V;
    }

    public void setDias61a90V(BigDecimal dias61a90V) {
        this.dias61a90V = dias61a90V;
    }

    public BigDecimal getDias91a120V() {
        return dias91a120V;
    }

    public void setDias91a120V(BigDecimal dias91a120V) {
        this.dias91a120V = dias91a120V;
    }

    public BigDecimal getDias121V() {
        return dias121V;
    }

    public void setDias121V(BigDecimal dias121V) {
        this.dias121V = dias121V;
    }

    public BigDecimal getTotalXVencer() {
        return totalXVencer;
    }

    public void setTotalXVencer(BigDecimal totalXVencer) {
        this.totalXVencer = totalXVencer;
    }

    public BigDecimal getDias1a30XV() {
        return dias1a30XV;
    }

    public void setDias1a30XV(BigDecimal dias1a30XV) {
        this.dias1a30XV = dias1a30XV;
    }

    public BigDecimal getDias31a60XV() {
        return dias31a60XV;
    }

    public void setDias31a60XV(BigDecimal dias31a60XV) {
        this.dias31a60XV = dias31a60XV;
    }

    public BigDecimal getDias61a90XV() {
        return dias61a90XV;
    }

    public void setDias61a90XV(BigDecimal dias61a90XV) {
        this.dias61a90XV = dias61a90XV;
    }

    public BigDecimal getDias91a120XV() {
        return dias91a120XV;
    }

    public void setDias91a120XV(BigDecimal dias91a120XV) {
        this.dias91a120XV = dias91a120XV;
    }

    public BigDecimal getDias121XV() {
        return dias121XV;
    }

    public void setDias121XV(BigDecimal dias121XV) {
        this.dias121XV = dias121XV;
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
        if (!(object instanceof TmpCxcVyv)) {
            return false;
        }
        TmpCxcVyv other = (TmpCxcVyv) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.alphacell.model.cartera.TmpCxcVyv[ id=" + id + " ]";
    }
    
}
