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
@Table(name = "LC_VISTA_CHEQUES_POSTFECHADOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LcVistaChequesPostfechados.findAll", query = "SELECT l FROM LcVistaChequesPostfechados l"),
    @NamedQuery(name = "LcVistaChequesPostfechados.findById", query = "SELECT l FROM LcVistaChequesPostfechados l WHERE l.id = :id"),
    @NamedQuery(name = "LcVistaChequesPostfechados.findByAccountnum", query = "SELECT l FROM LcVistaChequesPostfechados l WHERE l.accountnum = :accountnum"),
    @NamedQuery(name = "LcVistaChequesPostfechados.findByDimension1", query = "SELECT l FROM LcVistaChequesPostfechados l WHERE l.dimension1 = :dimension1"),
    @NamedQuery(name = "LcVistaChequesPostfechados.findByNombreCliente", query = "SELECT l FROM LcVistaChequesPostfechados l WHERE l.nombreCliente = :nombreCliente"),
    @NamedQuery(name = "LcVistaChequesPostfechados.findByInvoice", query = "SELECT l FROM LcVistaChequesPostfechados l WHERE l.invoice = :invoice"),
    @NamedQuery(name = "LcVistaChequesPostfechados.findByVoucher", query = "SELECT l FROM LcVistaChequesPostfechados l WHERE l.voucher = :voucher"),
    @NamedQuery(name = "LcVistaChequesPostfechados.findByTipo", query = "SELECT l FROM LcVistaChequesPostfechados l WHERE l.tipo = :tipo"),
    @NamedQuery(name = "LcVistaChequesPostfechados.findByDiasVencido", query = "SELECT l FROM LcVistaChequesPostfechados l WHERE l.diasVencido = :diasVencido"),
    @NamedQuery(name = "LcVistaChequesPostfechados.findByGrupodias", query = "SELECT l FROM LcVistaChequesPostfechados l WHERE l.grupodias = :grupodias"),
    @NamedQuery(name = "LcVistaChequesPostfechados.findByTransdate", query = "SELECT l FROM LcVistaChequesPostfechados l WHERE l.transdate = :transdate"),
    @NamedQuery(name = "LcVistaChequesPostfechados.findByDuedate", query = "SELECT l FROM LcVistaChequesPostfechados l WHERE l.duedate = :duedate"),
    @NamedQuery(name = "LcVistaChequesPostfechados.findByDuedateChequePost", query = "SELECT l FROM LcVistaChequesPostfechados l WHERE l.duedate = :duedate and l.tipo='CHEQUE POSTFECHADO'"),
    @NamedQuery(name = "LcVistaChequesPostfechados.findByDescripcion", query = "SELECT l FROM LcVistaChequesPostfechados l WHERE l.descripcion = :descripcion"),
    @NamedQuery(name = "LcVistaChequesPostfechados.findByDiffDate", query = "SELECT l FROM LcVistaChequesPostfechados l WHERE l.diffDate = :diffDate"),
    @NamedQuery(name = "LcVistaChequesPostfechados.findByAmountcur", query = "SELECT l FROM LcVistaChequesPostfechados l WHERE l.amountcur = :amountcur")})
public class LcVistaChequesPostfechados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "id")
    @Id
    private BigInteger id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ACCOUNTNUM")
    private String accountnum;
    @Size(max = 30)
    @Column(name = "DIMENSION1")
    private String dimension1;
    @Size(max = 100)
    @Column(name = "nombre_cliente")
    private String nombreCliente;
    @Size(max = 20)
    @Column(name = "INVOICE")
    private String invoice;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "VOUCHER")
    private String voucher;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 22)
    @Column(name = "TIPO")
    private String tipo;
    @Column(name = "dias_vencido")
    private Integer diasVencido;
    @Size(max = 18)
    @Column(name = "GRUPODIAS")
    private String grupodias;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRANSDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transdate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DUEDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date duedate;
    @Size(max = 60)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "DiffDate")
    private Integer diffDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "AMOUNTCUR")
    private BigDecimal amountcur;

    public LcVistaChequesPostfechados() {
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getAccountnum() {
        return accountnum;
    }

    public void setAccountnum(String accountnum) {
        this.accountnum = accountnum;
    }

    public String getDimension1() {
        return dimension1;
    }

    public void setDimension1(String dimension1) {
        this.dimension1 = dimension1;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getDiasVencido() {
        return diasVencido;
    }

    public void setDiasVencido(Integer diasVencido) {
        this.diasVencido = diasVencido;
    }

    public String getGrupodias() {
        return grupodias;
    }

    public void setGrupodias(String grupodias) {
        this.grupodias = grupodias;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getDiffDate() {
        return diffDate;
    }

    public void setDiffDate(Integer diffDate) {
        this.diffDate = diffDate;
    }

    public BigDecimal getAmountcur() {
        return amountcur;
    }

    public void setAmountcur(BigDecimal amountcur) {
        this.amountcur = amountcur;
    }
    
}
