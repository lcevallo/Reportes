package com.alphacell.model.cartera;

/**
 * Created by luis.cevallos on 4/4/2016.
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
@Table(name = "HISTORIALPROTESTO")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Historialprotesto.findAll", query = "SELECT h FROM Historialprotesto h"),
        @NamedQuery(name = "Historialprotesto.findById", query = "SELECT h FROM Historialprotesto h WHERE h.id = :id"),
        @NamedQuery(name = "Historialprotesto.findByTransdate", query = "SELECT h FROM Historialprotesto h WHERE h.transdate = :transdate"),
        @NamedQuery(name = "Historialprotesto.findByOrderMonth", query = "SELECT h FROM Historialprotesto h WHERE h.orderMonth = :orderMonth"),
        @NamedQuery(name = "Historialprotesto.findByAccountnum", query = "SELECT h FROM Historialprotesto h WHERE h.accountnum = :accountnum"),
        @NamedQuery(name = "Historialprotesto.findByNombreCliente", query = "SELECT h FROM Historialprotesto h WHERE h.nombreCliente = :nombreCliente"),
        @NamedQuery(name = "Historialprotesto.findByAmountCurDebit", query = "SELECT h FROM Historialprotesto h WHERE h.amountCurDebit = :amountCurDebit"),
        @NamedQuery(name = "Historialprotesto.findByPostingProfile", query = "SELECT h FROM Historialprotesto h WHERE h.postingProfile = :postingProfile"),
        @NamedQuery(name = "Historialprotesto.findByVoucher", query = "SELECT h FROM Historialprotesto h WHERE h.voucher = :voucher")})
public class Historialprotesto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRANSDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transdate;
    @Column(name = "OrderMonth")
    private Integer orderMonth;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ACCOUNTNUM")
    private String accountnum;
    @Size(max = 100)
    @Column(name = "NOMBRE_CLIENTE")
    private String nombreCliente;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "AmountCurDebit")
    private BigDecimal amountCurDebit;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "PostingProfile")
    private String postingProfile;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "VOUCHER")
    private String voucher;

    public Historialprotesto() {
    }

    public Historialprotesto(Integer id) {
        this.id = id;
    }

    public Historialprotesto(Integer id, Date transdate, String accountnum, BigDecimal amountCurDebit, String postingProfile, String voucher) {
        this.id = id;
        this.transdate = transdate;
        this.accountnum = accountnum;
        this.amountCurDebit = amountCurDebit;
        this.postingProfile = postingProfile;
        this.voucher = voucher;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTransdate() {
        return transdate;
    }

    public void setTransdate(Date transdate) {
        this.transdate = transdate;
    }

    public Integer getOrderMonth() {
        return orderMonth;
    }

    public void setOrderMonth(Integer orderMonth) {
        this.orderMonth = orderMonth;
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

    public BigDecimal getAmountCurDebit() {
        return amountCurDebit;
    }

    public void setAmountCurDebit(BigDecimal amountCurDebit) {
        this.amountCurDebit = amountCurDebit;
    }

    public String getPostingProfile() {
        return postingProfile;
    }

    public void setPostingProfile(String postingProfile) {
        this.postingProfile = postingProfile;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
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
        if (!(object instanceof Historialprotesto)) {
            return false;
        }
        Historialprotesto other = (Historialprotesto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Historialprotesto[ id=" + id + " ]";
    }

}
