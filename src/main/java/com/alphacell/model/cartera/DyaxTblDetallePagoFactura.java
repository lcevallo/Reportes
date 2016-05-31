package com.alphacell.model.cartera;

/**
 * Created by luis.cevallos on 11/4/2016.
 */
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
@Table(name = "DYAX_TBL_DETALLE_PAGO_FACTURA")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "DyaxTblDetallePagoFactura.findAll", query = "SELECT d FROM DyaxTblDetallePagoFactura d"),
        @NamedQuery(name = "DyaxTblDetallePagoFactura.findById", query = "SELECT d FROM DyaxTblDetallePagoFactura d WHERE d.id = :id"),
        @NamedQuery(name = "DyaxTblDetallePagoFactura.findByAccountnum", query = "SELECT d FROM DyaxTblDetallePagoFactura d WHERE d.accountnum = :accountnum"),
        @NamedQuery(name = "DyaxTblDetallePagoFactura.findByNombreCliente", query = "SELECT d FROM DyaxTblDetallePagoFactura d WHERE d.nombreCliente = :nombreCliente"),
        @NamedQuery(name = "DyaxTblDetallePagoFactura.findByInvoice", query = "SELECT d FROM DyaxTblDetallePagoFactura d WHERE d.invoice = :invoice"),
        @NamedQuery(name = "DyaxTblDetallePagoFactura.findByVoucher", query = "SELECT d FROM DyaxTblDetallePagoFactura d WHERE d.voucher = :voucher"),
        @NamedQuery(name = "DyaxTblDetallePagoFactura.findByOffsetTransVoucher", query = "SELECT d FROM DyaxTblDetallePagoFactura d WHERE d.offsetTransVoucher = :offsetTransVoucher"),
        @NamedQuery(name = "DyaxTblDetallePagoFactura.findByTxt", query = "SELECT d FROM DyaxTblDetallePagoFactura d WHERE d.txt = :txt"),
        @NamedQuery(name = "DyaxTblDetallePagoFactura.findByAmountcurcredit", query = "SELECT d FROM DyaxTblDetallePagoFactura d WHERE d.amountcurcredit = :amountcurcredit"),
        @NamedQuery(name = "DyaxTblDetallePagoFactura.findByPaymreference", query = "SELECT d FROM DyaxTblDetallePagoFactura d WHERE d.paymreference = :paymreference"),
        @NamedQuery(name = "DyaxTblDetallePagoFactura.findByAcknowledgementdate", query = "SELECT d FROM DyaxTblDetallePagoFactura d WHERE d.acknowledgementdate = :acknowledgementdate"),
        @NamedQuery(name = "DyaxTblDetallePagoFactura.findByPaymmode", query = "SELECT d FROM DyaxTblDetallePagoFactura d WHERE d.paymmode = :paymmode"),
        @NamedQuery(name = "DyaxTblDetallePagoFactura.findByTransdate", query = "SELECT d FROM DyaxTblDetallePagoFactura d WHERE d.transdate = :transdate")})
public class DyaxTblDetallePagoFactura implements Serializable {

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
    @Size(max = 20)
    @Column(name = "INVOICE")
    private String invoice;
    @Size(max = 20)
    @Column(name = "VOUCHER")
    private String voucher;
    @Size(max = 20)
    @Column(name = "OffsetTransVoucher")
    private String offsetTransVoucher;
    @Size(max = 60)
    @Column(name = "TXT")
    private String txt;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "AMOUNTCURCREDIT")
    private BigDecimal amountcurcredit;
    @Size(max = 20)
    @Column(name = "PAYMREFERENCE")
    private String paymreference;

    @Column(name = "ACKNOWLEDGEMENTDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date acknowledgementdate;
    @Size(max = 10)
    @Column(name = "PAYMMODE")
    private String paymmode;
    @Column(name = "TRANSDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transdate;

    public DyaxTblDetallePagoFactura() {
    }

    public DyaxTblDetallePagoFactura(Integer id) {
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

    public String getOffsetTransVoucher() {
        return offsetTransVoucher;
    }

    public void setOffsetTransVoucher(String offsetTransVoucher) {
        this.offsetTransVoucher = offsetTransVoucher;
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

    public String getPaymreference() {
        return paymreference;
    }

    public void setPaymreference(String paymreference) {
        this.paymreference = paymreference;
    }

    public Date getAcknowledgementdate() {
        return acknowledgementdate;
    }

    public void setAcknowledgementdate(Date acknowledgementdate) {
        this.acknowledgementdate = acknowledgementdate;
    }

    public String getPaymmode() {
        return paymmode;
    }

    public void setPaymmode(String paymmode) {
        this.paymmode = paymmode;
    }

    public Date getTransdate() {
        return transdate;
    }

    public void setTransdate(Date transdate) {
        this.transdate = transdate;
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
        if (!(object instanceof DyaxTblDetallePagoFactura)) {
            return false;
        }
        DyaxTblDetallePagoFactura other = (DyaxTblDetallePagoFactura) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.cartera.DyaxTblDetallePagoFactura[ id=" + id + " ]";
    }

}
