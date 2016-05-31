package com.alphacell.model.cartera;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author luis.cevallos
 */
@Entity
@Table(name = "JPA_ALPHA_LIMITECREDITO")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "JpaAlphaLimitecredito.findAll", query = "SELECT j FROM JpaAlphaLimitecredito j"),
        @NamedQuery(name = "JpaAlphaLimitecredito.findByAccountnum", query = "SELECT j FROM JpaAlphaLimitecredito j WHERE j.accountnum = :accountnum"),
        @NamedQuery(name = "JpaAlphaLimitecredito.findByNombreCliente", query = "SELECT j FROM JpaAlphaLimitecredito j WHERE j.nombreCliente = :nombreCliente"),
        @NamedQuery(name = "JpaAlphaLimitecredito.findByUtilizado", query = "SELECT j FROM JpaAlphaLimitecredito j WHERE j.utilizado = :utilizado"),
        @NamedQuery(name = "JpaAlphaLimitecredito.findByCredito", query = "SELECT j FROM JpaAlphaLimitecredito j WHERE j.credito = :credito")})
public class JpaAlphaLimitecredito implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ACCOUNTNUM")
    @Id
    private String accountnum;
    @Size(max = 100)
    @Column(name = "NOMBRE_CLIENTE")
    private String nombreCliente;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "UTILIZADO")
    private BigDecimal utilizado;
    @Column(name = "CREDITO")
    private BigDecimal credito;

    public JpaAlphaLimitecredito() {
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

    public BigDecimal getUtilizado() {
        return utilizado;
    }

    public void setUtilizado(BigDecimal utilizado) {
        this.utilizado = utilizado;
    }

    public BigDecimal getCredito() {
        return credito;
    }

    public void setCredito(BigDecimal credito) {
        this.credito = credito;
    }

}
