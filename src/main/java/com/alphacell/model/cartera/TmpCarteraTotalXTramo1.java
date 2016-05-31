package com.alphacell.model.cartera;

/**
 * Created by luis.cevallos on 6/4/2016.
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
@Table(name = "TMP_CARTERA_TOTAL_X_TRAMO_1")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "TmpCarteraTotalXTramo1.findAll", query = "SELECT t FROM TmpCarteraTotalXTramo1 t"),
        @NamedQuery(name = "TmpCarteraTotalXTramo1.findById", query = "SELECT t FROM TmpCarteraTotalXTramo1 t WHERE t.id = :id"),
        @NamedQuery(name = "TmpCarteraTotalXTramo1.findByAccountnum", query = "SELECT t FROM TmpCarteraTotalXTramo1 t WHERE t.accountnum = :accountnum"),
        @NamedQuery(name = "TmpCarteraTotalXTramo1.findByNombreCliente", query = "SELECT t FROM TmpCarteraTotalXTramo1 t WHERE t.nombreCliente = :nombreCliente"),
        @NamedQuery(name = "TmpCarteraTotalXTramo1.findByInvoice", query = "SELECT t FROM TmpCarteraTotalXTramo1 t WHERE t.invoice = :invoice"),
        @NamedQuery(name = "TmpCarteraTotalXTramo1.findByTipo", query = "SELECT t FROM TmpCarteraTotalXTramo1 t WHERE t.tipo = :tipo"),
        @NamedQuery(name = "TmpCarteraTotalXTramo1.findByDiasVencido", query = "SELECT t FROM TmpCarteraTotalXTramo1 t WHERE t.diasVencido = :diasVencido"),
        @NamedQuery(name = "TmpCarteraTotalXTramo1.findByGrupodias", query = "SELECT t FROM TmpCarteraTotalXTramo1 t WHERE t.grupodias = :grupodias"),
        @NamedQuery(name = "TmpCarteraTotalXTramo1.findByTransdate", query = "SELECT t FROM TmpCarteraTotalXTramo1 t WHERE t.transdate = :transdate"),
        @NamedQuery(name = "TmpCarteraTotalXTramo1.findByDuedate", query = "SELECT t FROM TmpCarteraTotalXTramo1 t WHERE t.duedate = :duedate"),
        @NamedQuery(name = "TmpCarteraTotalXTramo1.findByDiffDate", query = "SELECT t FROM TmpCarteraTotalXTramo1 t WHERE t.diffDate = :diffDate"),
        @NamedQuery(name = "TmpCarteraTotalXTramo1.findByVoucher", query = "SELECT t FROM TmpCarteraTotalXTramo1 t WHERE t.voucher = :voucher"),
        @NamedQuery(name = "TmpCarteraTotalXTramo1.findByAmountcur", query = "SELECT t FROM TmpCarteraTotalXTramo1 t WHERE t.amountcur = :amountcur"),
        @NamedQuery(name = "TmpCarteraTotalXTramo1.findByOffsettransvoucher", query = "SELECT t FROM TmpCarteraTotalXTramo1 t WHERE t.offsettransvoucher = :offsettransvoucher"),
        @NamedQuery(name = "TmpCarteraTotalXTramo1.findByTransdate2", query = "SELECT t FROM TmpCarteraTotalXTramo1 t WHERE t.transdate2 = :transdate2"),
        @NamedQuery(name = "TmpCarteraTotalXTramo1.findByInvoice2", query = "SELECT t FROM TmpCarteraTotalXTramo1 t WHERE t.invoice2 = :invoice2"),
        @NamedQuery(name = "TmpCarteraTotalXTramo1.findByDuedate2", query = "SELECT t FROM TmpCarteraTotalXTramo1 t WHERE t.duedate2 = :duedate2"),
        @NamedQuery(name = "TmpCarteraTotalXTramo1.findByPayment", query = "SELECT t FROM TmpCarteraTotalXTramo1 t WHERE t.payment = :payment"),
        @NamedQuery(name = "TmpCarteraTotalXTramo1.findByPaymentsched", query = "SELECT t FROM TmpCarteraTotalXTramo1 t WHERE t.paymentsched = :paymentsched"),
        @NamedQuery(name = "TmpCarteraTotalXTramo1.findByPricegroupid", query = "SELECT t FROM TmpCarteraTotalXTramo1 t WHERE t.pricegroupid = :pricegroupid")})
public class TmpCarteraTotalXTramo1 implements Serializable {

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
    @Size(max = 20)
    @Column(name = "INVOICE")
    private String invoice;
    @Size(max = 22)
    @Column(name = "TIPO")
    private String tipo;
    @Column(name = "dias_vencido")
    private Integer diasVencido;
    @Size(max = 22)
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
    @Column(name = "DiffDate")
    private Integer diffDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "VOUCHER")
    private String voucher;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "AMOUNTCUR")
    private BigDecimal amountcur;
    @Size(max = 20)
    @Column(name = "OFFSETTRANSVOUCHER")
    private String offsettransvoucher;
    @Column(name = "TRANSDATE2")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transdate2;
    @Size(max = 20)
    @Column(name = "INVOICE2")
    private String invoice2;
    @Column(name = "DUEDATE2")
    @Temporal(TemporalType.TIMESTAMP)
    private Date duedate2;
    @Size(max = 10)
    @Column(name = "PAYMENT")
    private String payment;
    @Size(max = 30)
    @Column(name = "PAYMENTSCHED")
    private String paymentsched;
    @Size(max = 10)
    @Column(name = "PRICEGROUPID")
    private String pricegroupid;

    public TmpCarteraTotalXTramo1() {
    }

    public TmpCarteraTotalXTramo1(Integer id) {
        this.id = id;
    }

    public TmpCarteraTotalXTramo1(Integer id, String accountnum, Date transdate, Date duedate, String voucher, BigDecimal amountcur) {
        this.id = id;
        this.accountnum = accountnum;
        this.transdate = transdate;
        this.duedate = duedate;
        this.voucher = voucher;
        this.amountcur = amountcur;
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

    public Integer getDiffDate() {
        return diffDate;
    }

    public void setDiffDate(Integer diffDate) {
        this.diffDate = diffDate;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }

    public BigDecimal getAmountcur() {
        return amountcur;
    }

    public void setAmountcur(BigDecimal amountcur) {
        this.amountcur = amountcur;
    }

    public String getOffsettransvoucher() {
        return offsettransvoucher;
    }

    public void setOffsettransvoucher(String offsettransvoucher) {
        this.offsettransvoucher = offsettransvoucher;
    }

    public Date getTransdate2() {
        return transdate2;
    }

    public void setTransdate2(Date transdate2) {
        this.transdate2 = transdate2;
    }

    public String getInvoice2() {
        return invoice2;
    }

    public void setInvoice2(String invoice2) {
        this.invoice2 = invoice2;
    }

    public Date getDuedate2() {
        return duedate2;
    }

    public void setDuedate2(Date duedate2) {
        this.duedate2 = duedate2;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getPaymentsched() {
        return paymentsched;
    }

    public void setPaymentsched(String paymentsched) {
        this.paymentsched = paymentsched;
    }

    public String getPricegroupid() {
        return pricegroupid;
    }

    public void setPricegroupid(String pricegroupid) {
        this.pricegroupid = pricegroupid;
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
        if (!(object instanceof TmpCarteraTotalXTramo1)) {
            return false;
        }
        TmpCarteraTotalXTramo1 other = (TmpCarteraTotalXTramo1) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.TmpCarteraTotalXTramo1[ id=" + id + " ]";
    }

}