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
@Table(name = "TABLAFLUJO")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Tablaflujo.findAll", query = "SELECT t FROM Tablaflujo t"),
        @NamedQuery(name = "Tablaflujo.findById", query = "SELECT t FROM Tablaflujo t WHERE t.id = :id"),
        @NamedQuery(name = "Tablaflujo.findByVoucher", query = "SELECT t FROM Tablaflujo t WHERE t.voucher = :voucher"),
        @NamedQuery(name = "Tablaflujo.findByAccountnum", query = "SELECT t FROM Tablaflujo t WHERE t.accountnum = :accountnum"),
        @NamedQuery(name = "Tablaflujo.findByNombreCliente", query = "SELECT t FROM Tablaflujo t WHERE t.nombreCliente = :nombreCliente"),
        @NamedQuery(name = "Tablaflujo.findByTipo", query = "SELECT t FROM Tablaflujo t WHERE t.tipo = :tipo"),
        @NamedQuery(name = "Tablaflujo.findByRango", query = "SELECT t FROM Tablaflujo t WHERE t.rango = :rango"),
        @NamedQuery(name = "Tablaflujo.findByNumsemana", query = "SELECT t FROM Tablaflujo t WHERE t.numsemana = :numsemana"),
        @NamedQuery(name = "Tablaflujo.findBySemana", query = "SELECT t FROM Tablaflujo t WHERE t.semana = :semana"),
        @NamedQuery(name = "Tablaflujo.findByUseCashDisc", query = "SELECT t FROM Tablaflujo t WHERE t.useCashDisc = :useCashDisc"),
        @NamedQuery(name = "Tablaflujo.findByDataAreaId", query = "SELECT t FROM Tablaflujo t WHERE t.dataAreaId = :dataAreaId"),
        @NamedQuery(name = "Tablaflujo.findByTransDate", query = "SELECT t FROM Tablaflujo t WHERE t.transDate = :transDate"),
        @NamedQuery(name = "Tablaflujo.findByDueDate", query = "SELECT t FROM Tablaflujo t WHERE t.dueDate = :dueDate"),
        @NamedQuery(name = "Tablaflujo.findByBankDiscNoticeDeadline", query = "SELECT t FROM Tablaflujo t WHERE t.bankDiscNoticeDeadline = :bankDiscNoticeDeadline"),
        @NamedQuery(name = "Tablaflujo.findByInvoice", query = "SELECT t FROM Tablaflujo t WHERE t.invoice = :invoice"),
        @NamedQuery(name = "Tablaflujo.findByAmountCur", query = "SELECT t FROM Tablaflujo t WHERE t.amountCur = :amountCur"),
        @NamedQuery(name = "Tablaflujo.findByCurrencyCode", query = "SELECT t FROM Tablaflujo t WHERE t.currencyCode = :currencyCode")})
public class Tablaflujo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 20)
    @Column(name = "Voucher")
    private String voucher;
    @Size(max = 20)
    @Column(name = "ACCOUNTNUM")
    private String accountnum;
    @Size(max = 100)
    @Column(name = "NOMBRE_CLIENTE")
    private String nombreCliente;
    @Size(max = 22)
    @Column(name = "TIPO")
    private String tipo;
    @Size(max = 21)
    @Column(name = "RANGO")
    private String rango;
    @Column(name = "NUMSEMANA")
    private Integer numsemana;
    @Size(max = 14)
    @Column(name = "SEMANA")
    private String semana;
    @Column(name = "UseCashDisc")
    private Integer useCashDisc;
    @Size(max = 4)
    @Column(name = "dataAreaId")
    private String dataAreaId;
    @Column(name = "TransDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transDate;
    @Column(name = "DueDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dueDate;
    @Column(name = "BankDiscNoticeDeadline")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bankDiscNoticeDeadline;
    @Size(max = 20)
    @Column(name = "Invoice")
    private String invoice;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "AmountCur")
    private BigDecimal amountCur;
    @Size(max = 3)
    @Column(name = "CurrencyCode")
    private String currencyCode;

    public Tablaflujo() {
    }

    public Tablaflujo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public Integer getUseCashDisc() {
        return useCashDisc;
    }

    public void setUseCashDisc(Integer useCashDisc) {
        this.useCashDisc = useCashDisc;
    }

    public String getDataAreaId() {
        return dataAreaId;
    }

    public void setDataAreaId(String dataAreaId) {
        this.dataAreaId = dataAreaId;
    }

    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getBankDiscNoticeDeadline() {
        return bankDiscNoticeDeadline;
    }

    public void setBankDiscNoticeDeadline(Date bankDiscNoticeDeadline) {
        this.bankDiscNoticeDeadline = bankDiscNoticeDeadline;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public BigDecimal getAmountCur() {
        return amountCur;
    }

    public void setAmountCur(BigDecimal amountCur) {
        this.amountCur = amountCur;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
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
        if (!(object instanceof Tablaflujo)) {
            return false;
        }
        Tablaflujo other = (Tablaflujo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Tablaflujo[ id=" + id + " ]";
    }

}