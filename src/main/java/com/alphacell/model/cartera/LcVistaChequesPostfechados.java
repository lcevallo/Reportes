package com.alphacell.model.cartera;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by luis on 27/09/16.
 */
@Entity
@Table(name = "LC_VISTA_CHEQUES_POSTFECHADOS", schema = "dbo", catalog = "Produccion")
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
        @NamedQuery(name = "LcVistaChequesPostfechados.findByDuedateChequePost", query = "SELECT l FROM LcVistaChequesPostfechados l WHERE l.duedate = :duedate and l.tipo=:tipo"),
        @NamedQuery(name = "LcVistaChequesPostfechados.findByDescripcion", query = "SELECT l FROM LcVistaChequesPostfechados l WHERE l.descripcion = :descripcion"),
        @NamedQuery(name = "LcVistaChequesPostfechados.findByDiffDate", query = "SELECT l FROM LcVistaChequesPostfechados l WHERE l.diffDate = :diffDate"),
        @NamedQuery(name = "LcVistaChequesPostfechados.findByAmountcur", query = "SELECT l FROM LcVistaChequesPostfechados l WHERE l.amountcur = :amountcur")})

public class LcVistaChequesPostfechados implements Serializable{
    private static final long serialVersionUID = -6779003517190287843L;


    private Long id;
    private String accountnum;
    private String dimension1;
    private String nombreCliente;
    private String invoice;
    private String voucher;
    private String tipo;
    private Integer diasVencido;
    private String grupodias;
    private Date transdate;
    private Date duedate;
    private String descripcion;
    private Integer diffDate;
    private BigDecimal amountcur;

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ACCOUNTNUM", nullable = false, length = 20)
    public String getAccountnum() {
        return accountnum;
    }

    public void setAccountnum(String accountnum) {
        this.accountnum = accountnum;
    }

    @Basic
    @Column(name = "DIMENSION1", nullable = true, length = 30)
    public String getDimension1() {
        return dimension1;
    }

    public void setDimension1(String dimension1) {
        this.dimension1 = dimension1;
    }

    @Basic
    @Column(name = "nombre_cliente", nullable = true, length = 100)
    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    @Basic
    @Column(name = "INVOICE", nullable = true, length = 20)
    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    @Basic
    @Column(name = "VOUCHER", nullable = false, length = 20)
    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }

    @Basic
    @Column(name = "TIPO", nullable = false, length = 22)
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Basic
    @Column(name = "dias_vencido", nullable = true)
    public Integer getDiasVencido() {
        return diasVencido;
    }

    public void setDiasVencido(Integer diasVencido) {
        this.diasVencido = diasVencido;
    }

    @Basic
    @Column(name = "GRUPODIAS", nullable = true, length = 18)
    public String getGrupodias() {
        return grupodias;
    }

    public void setGrupodias(String grupodias) {
        this.grupodias = grupodias;
    }

    @Basic
    @Column(name = "TRANSDATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getTransdate() {
        return transdate;
    }

    public void setTransdate(Date transdate) {
        this.transdate = transdate;
    }

    @Basic
    @Column(name = "DUEDATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDuedate() {
        return duedate;
    }

    public void setDuedate(Date duedate) {
        this.duedate = duedate;
    }

    @Basic
    @Column(name = "DESCRIPCION", nullable = true, length = 60)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Basic
    @Column(name = "DiffDate", nullable = true)
    public Integer getDiffDate() {
        return diffDate;
    }

    public void setDiffDate(Integer diffDate) {
        this.diffDate = diffDate;
    }

    @Basic
    @Column(name = "AMOUNTCUR", nullable = false, precision = 16)
    public BigDecimal getAmountcur() {
        return amountcur;
    }

    public void setAmountcur(BigDecimal amountcur) {
        this.amountcur = amountcur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LcVistaChequesPostfechados that = (LcVistaChequesPostfechados) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (accountnum != null ? !accountnum.equals(that.accountnum) : that.accountnum != null) return false;
        if (dimension1 != null ? !dimension1.equals(that.dimension1) : that.dimension1 != null) return false;
        if (nombreCliente != null ? !nombreCliente.equals(that.nombreCliente) : that.nombreCliente != null)
            return false;
        if (invoice != null ? !invoice.equals(that.invoice) : that.invoice != null) return false;
        if (voucher != null ? !voucher.equals(that.voucher) : that.voucher != null) return false;
        if (tipo != null ? !tipo.equals(that.tipo) : that.tipo != null) return false;
        if (diasVencido != null ? !diasVencido.equals(that.diasVencido) : that.diasVencido != null) return false;
        if (grupodias != null ? !grupodias.equals(that.grupodias) : that.grupodias != null) return false;
        if (transdate != null ? !transdate.equals(that.transdate) : that.transdate != null) return false;
        if (duedate != null ? !duedate.equals(that.duedate) : that.duedate != null) return false;
        if (descripcion != null ? !descripcion.equals(that.descripcion) : that.descripcion != null) return false;
        if (diffDate != null ? !diffDate.equals(that.diffDate) : that.diffDate != null) return false;
        if (amountcur != null ? !amountcur.equals(that.amountcur) : that.amountcur != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (accountnum != null ? accountnum.hashCode() : 0);
        result = 31 * result + (dimension1 != null ? dimension1.hashCode() : 0);
        result = 31 * result + (nombreCliente != null ? nombreCliente.hashCode() : 0);
        result = 31 * result + (invoice != null ? invoice.hashCode() : 0);
        result = 31 * result + (voucher != null ? voucher.hashCode() : 0);
        result = 31 * result + (tipo != null ? tipo.hashCode() : 0);
        result = 31 * result + (diasVencido != null ? diasVencido.hashCode() : 0);
        result = 31 * result + (grupodias != null ? grupodias.hashCode() : 0);
        result = 31 * result + (transdate != null ? transdate.hashCode() : 0);
        result = 31 * result + (duedate != null ? duedate.hashCode() : 0);
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        result = 31 * result + (diffDate != null ? diffDate.hashCode() : 0);
        result = 31 * result + (amountcur != null ? amountcur.hashCode() : 0);
        return result;
    }
}
