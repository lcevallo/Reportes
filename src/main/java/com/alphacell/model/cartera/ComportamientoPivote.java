/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphacell.model.cartera;

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
@Table(name = "ComportamientoPivote")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComportamientoPivote.findAll", query = "SELECT c FROM ComportamientoPivote c"),
    @NamedQuery(name = "ComportamientoPivote.findById", query = "SELECT c FROM ComportamientoPivote c WHERE c.id = :id"),
    @NamedQuery(name = "ComportamientoPivote.findByAccountnum", query = "SELECT c FROM ComportamientoPivote c WHERE c.accountnum = :accountnum"),
    @NamedQuery(name = "ComportamientoPivote.findByNombreCliente", query = "SELECT c FROM ComportamientoPivote c WHERE c.nombreCliente = :nombreCliente"),
    @NamedQuery(name = "ComportamientoPivote.findByInvoice", query = "SELECT c FROM ComportamientoPivote c WHERE c.invoice = :invoice"),
    @NamedQuery(name = "ComportamientoPivote.findByVoucher", query = "SELECT c FROM ComportamientoPivote c WHERE c.voucher = :voucher"),
    @NamedQuery(name = "ComportamientoPivote.findByAmountcur", query = "SELECT c FROM ComportamientoPivote c WHERE c.amountcur = :amountcur"),
    @NamedQuery(name = "ComportamientoPivote.findByDebe", query = "SELECT c FROM ComportamientoPivote c WHERE c.debe = :debe"),
    @NamedQuery(name = "ComportamientoPivote.findByPagada", query = "SELECT c FROM ComportamientoPivote c WHERE c.pagada = :pagada"),
    @NamedQuery(name = "ComportamientoPivote.findByTransdate", query = "SELECT c FROM ComportamientoPivote c WHERE c.transdate = :transdate"),
    @NamedQuery(name = "ComportamientoPivote.findByFechas30P", query = "SELECT c FROM ComportamientoPivote c WHERE c.fechas30P = :fechas30P"),
    @NamedQuery(name = "ComportamientoPivote.findByFechas30C", query = "SELECT c FROM ComportamientoPivote c WHERE c.fechas30C = :fechas30C"),
    @NamedQuery(name = "ComportamientoPivote.findByFechas30DT", query = "SELECT c FROM ComportamientoPivote c WHERE c.fechas30DT = :fechas30DT"),
    @NamedQuery(name = "ComportamientoPivote.findByFechas45P", query = "SELECT c FROM ComportamientoPivote c WHERE c.fechas45P = :fechas45P"),
    @NamedQuery(name = "ComportamientoPivote.findByFechas45C", query = "SELECT c FROM ComportamientoPivote c WHERE c.fechas45C = :fechas45C"),
    @NamedQuery(name = "ComportamientoPivote.findByFechas45DT", query = "SELECT c FROM ComportamientoPivote c WHERE c.fechas45DT = :fechas45DT"),
    @NamedQuery(name = "ComportamientoPivote.findByFechas60P", query = "SELECT c FROM ComportamientoPivote c WHERE c.fechas60P = :fechas60P"),
    @NamedQuery(name = "ComportamientoPivote.findByFechas60C", query = "SELECT c FROM ComportamientoPivote c WHERE c.fechas60C = :fechas60C"),
    @NamedQuery(name = "ComportamientoPivote.findByFechas60DT", query = "SELECT c FROM ComportamientoPivote c WHERE c.fechas60DT = :fechas60DT"),
    @NamedQuery(name = "ComportamientoPivote.findByFechas90P", query = "SELECT c FROM ComportamientoPivote c WHERE c.fechas90P = :fechas90P"),
    @NamedQuery(name = "ComportamientoPivote.findByFechas90C", query = "SELECT c FROM ComportamientoPivote c WHERE c.fechas90C = :fechas90C"),
    @NamedQuery(name = "ComportamientoPivote.findByFechas90DT", query = "SELECT c FROM ComportamientoPivote c WHERE c.fechas90DT = :fechas90DT"),
    @NamedQuery(name = "ComportamientoPivote.findByFechas120P", query = "SELECT c FROM ComportamientoPivote c WHERE c.fechas120P = :fechas120P"),
    @NamedQuery(name = "ComportamientoPivote.findByFechas120C", query = "SELECT c FROM ComportamientoPivote c WHERE c.fechas120C = :fechas120C"),
    @NamedQuery(name = "ComportamientoPivote.findByFechas120DT", query = "SELECT c FROM ComportamientoPivote c WHERE c.fechas120DT = :fechas120DT"),
    @NamedQuery(name = "ComportamientoPivote.findByFechas150P", query = "SELECT c FROM ComportamientoPivote c WHERE c.fechas150P = :fechas150P"),
    @NamedQuery(name = "ComportamientoPivote.findByFechas150C", query = "SELECT c FROM ComportamientoPivote c WHERE c.fechas150C = :fechas150C"),
    @NamedQuery(name = "ComportamientoPivote.findByFechas150DT", query = "SELECT c FROM ComportamientoPivote c WHERE c.fechas150DT = :fechas150DT")})
public class ComportamientoPivote implements Serializable {

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
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "AMOUNTCUR")
    private BigDecimal amountcur;
    @Column(name = "DEBE")
    private BigDecimal debe;
    @Size(max = 7)
    @Column(name = "PAGADA")
    private String pagada;
    @Column(name = "TRANSDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transdate;
    @Column(name = "fechas30P")
    @Temporal(TemporalType.DATE)
    private Date fechas30P;
    @Column(name = "fechas30C")
    @Temporal(TemporalType.DATE)
    private Date fechas30C;
    @Column(name = "fechas30DT")
    private Integer fechas30DT;
    @Column(name = "fechas45P")
    @Temporal(TemporalType.DATE)
    private Date fechas45P;
    @Column(name = "fechas45C")
    @Temporal(TemporalType.DATE)
    private Date fechas45C;
    @Column(name = "fechas45DT")
    private Integer fechas45DT;
    @Column(name = "fechas60P")
    @Temporal(TemporalType.DATE)
    private Date fechas60P;
    @Column(name = "fechas60C")
    @Temporal(TemporalType.DATE)
    private Date fechas60C;
    @Column(name = "fechas60DT")
    private Integer fechas60DT;
    @Column(name = "fechas90P")
    @Temporal(TemporalType.DATE)
    private Date fechas90P;
    @Column(name = "fechas90C")
    @Temporal(TemporalType.DATE)
    private Date fechas90C;
    @Column(name = "fechas90DT")
    private Integer fechas90DT;
    @Column(name = "fechas120P")
    @Temporal(TemporalType.DATE)
    private Date fechas120P;
    @Column(name = "fechas120C")
    @Temporal(TemporalType.DATE)
    private Date fechas120C;
    @Column(name = "fechas120DT")
    private Integer fechas120DT;
    @Column(name = "fechas150P")
    @Temporal(TemporalType.DATE)
    private Date fechas150P;
    @Column(name = "fechas150C")
    @Temporal(TemporalType.DATE)
    private Date fechas150C;
    @Column(name = "fechas150DT")
    private Integer fechas150DT;

    public ComportamientoPivote() {
    }

    public ComportamientoPivote(Integer id) {
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

    public String getPagada() {
        return pagada;
    }

    public void setPagada(String pagada) {
        this.pagada = pagada;
    }

    public Date getTransdate() {
        return transdate;
    }

    public void setTransdate(Date transdate) {
        this.transdate = transdate;
    }

    public Date getFechas30P() {
        return fechas30P;
    }

    public void setFechas30P(Date fechas30P) {
        this.fechas30P = fechas30P;
    }

    public Date getFechas30C() {
        return fechas30C;
    }

    public void setFechas30C(Date fechas30C) {
        this.fechas30C = fechas30C;
    }

    public Integer getFechas30DT() {
        return fechas30DT;
    }

    public void setFechas30DT(Integer fechas30DT) {
        this.fechas30DT = fechas30DT;
    }

    public Date getFechas45P() {
        return fechas45P;
    }

    public void setFechas45P(Date fechas45P) {
        this.fechas45P = fechas45P;
    }

    public Date getFechas45C() {
        return fechas45C;
    }

    public void setFechas45C(Date fechas45C) {
        this.fechas45C = fechas45C;
    }

    public Integer getFechas45DT() {
        return fechas45DT;
    }

    public void setFechas45DT(Integer fechas45DT) {
        this.fechas45DT = fechas45DT;
    }

    public Date getFechas60P() {
        return fechas60P;
    }

    public void setFechas60P(Date fechas60P) {
        this.fechas60P = fechas60P;
    }

    public Date getFechas60C() {
        return fechas60C;
    }

    public void setFechas60C(Date fechas60C) {
        this.fechas60C = fechas60C;
    }

    public Integer getFechas60DT() {
        return fechas60DT;
    }

    public void setFechas60DT(Integer fechas60DT) {
        this.fechas60DT = fechas60DT;
    }

    public Date getFechas90P() {
        return fechas90P;
    }

    public void setFechas90P(Date fechas90P) {
        this.fechas90P = fechas90P;
    }

    public Date getFechas90C() {
        return fechas90C;
    }

    public void setFechas90C(Date fechas90C) {
        this.fechas90C = fechas90C;
    }

    public Integer getFechas90DT() {
        return fechas90DT;
    }

    public void setFechas90DT(Integer fechas90DT) {
        this.fechas90DT = fechas90DT;
    }

    public Date getFechas120P() {
        return fechas120P;
    }

    public void setFechas120P(Date fechas120P) {
        this.fechas120P = fechas120P;
    }

    public Date getFechas120C() {
        return fechas120C;
    }

    public void setFechas120C(Date fechas120C) {
        this.fechas120C = fechas120C;
    }

    public Integer getFechas120DT() {
        return fechas120DT;
    }

    public void setFechas120DT(Integer fechas120DT) {
        this.fechas120DT = fechas120DT;
    }

    public Date getFechas150P() {
        return fechas150P;
    }

    public void setFechas150P(Date fechas150P) {
        this.fechas150P = fechas150P;
    }

    public Date getFechas150C() {
        return fechas150C;
    }

    public void setFechas150C(Date fechas150C) {
        this.fechas150C = fechas150C;
    }

    public Integer getFechas150DT() {
        return fechas150DT;
    }

    public void setFechas150DT(Integer fechas150DT) {
        this.fechas150DT = fechas150DT;
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
        if (!(object instanceof ComportamientoPivote)) {
            return false;
        }
        ComportamientoPivote other = (ComportamientoPivote) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.alphacell.model.cartera.ComportamientoPivote[ id=" + id + " ]";
    }
    
}
