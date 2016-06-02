/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@Table(name = "CXCINFOCLIENTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cxcinfocliente.findAll", query = "SELECT c FROM Cxcinfocliente c"),
    @NamedQuery(name = "Cxcinfocliente.findById", query = "SELECT c FROM Cxcinfocliente c WHERE c.id = :id"),
    @NamedQuery(name = "Cxcinfocliente.findByAccountnum", query = "SELECT c FROM Cxcinfocliente c WHERE c.accountnum = :accountnum"),
    @NamedQuery(name = "Cxcinfocliente.findByNombreCliente", query = "SELECT c FROM Cxcinfocliente c WHERE c.nombreCliente = :nombreCliente"),
    @NamedQuery(name = "Cxcinfocliente.findByCreditmax", query = "SELECT c FROM Cxcinfocliente c WHERE c.creditmax = :creditmax"),
    @NamedQuery(name = "Cxcinfocliente.findByVatnum", query = "SELECT c FROM Cxcinfocliente c WHERE c.vatnum = :vatnum"),
    @NamedQuery(name = "Cxcinfocliente.findByPaymSched", query = "SELECT c FROM Cxcinfocliente c WHERE c.paymSched = :paymSched"),
    @NamedQuery(name = "Cxcinfocliente.findByPaymMode", query = "SELECT c FROM Cxcinfocliente c WHERE c.paymMode = :paymMode"),
    @NamedQuery(name = "Cxcinfocliente.findByAddress", query = "SELECT c FROM Cxcinfocliente c WHERE c.address = :address"),
    @NamedQuery(name = "Cxcinfocliente.findByStreetnumber", query = "SELECT c FROM Cxcinfocliente c WHERE c.streetnumber = :streetnumber"),
    @NamedQuery(name = "Cxcinfocliente.findByStreet", query = "SELECT c FROM Cxcinfocliente c WHERE c.street = :street"),
    @NamedQuery(name = "Cxcinfocliente.findByCity", query = "SELECT c FROM Cxcinfocliente c WHERE c.city = :city"),
    @NamedQuery(name = "Cxcinfocliente.findByCanal", query = "SELECT c FROM Cxcinfocliente c WHERE c.canal = :canal"),
    @NamedQuery(name = "Cxcinfocliente.findByCounty", query = "SELECT c FROM Cxcinfocliente c WHERE c.county = :county"),
    @NamedQuery(name = "Cxcinfocliente.findByState", query = "SELECT c FROM Cxcinfocliente c WHERE c.state = :state"),
    @NamedQuery(name = "Cxcinfocliente.findByVendedor", query = "SELECT c FROM Cxcinfocliente c WHERE c.vendedor = :vendedor"),
    @NamedQuery(name = "Cxcinfocliente.findByMail", query = "SELECT c FROM Cxcinfocliente c WHERE c.mail = :mail"),
    @NamedQuery(name = "Cxcinfocliente.findByTelefono", query = "SELECT c FROM Cxcinfocliente c WHERE c.telefono = :telefono")})
public class Cxcinfocliente implements Serializable {

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
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CREDITMAX")
    private BigDecimal creditmax;
    @Size(max = 20)
    @Column(name = "VATNUM")
    private String vatnum;
    @Size(max = 30)
    @Column(name = "PaymSched")
    private String paymSched;
    @Size(max = 10)
    @Column(name = "PaymMode")
    private String paymMode;
    @Size(max = 250)
    @Column(name = "ADDRESS")
    private String address;
    @Size(max = 20)
    @Column(name = "STREETNUMBER")
    private String streetnumber;
    @Size(max = 250)
    @Column(name = "STREET")
    private String street;
    @Size(max = 30)
    @Column(name = "CITY")
    private String city;
    @Size(max = 30)
    @Column(name = "CANAL")
    private String canal;
    @Size(max = 10)
    @Column(name = "COUNTY")
    private String county;
    @Size(max = 10)
    @Column(name = "STATE")
    private String state;
    @Size(max = 100)
    @Column(name = "VENDEDOR")
    private String vendedor;
    @Size(max = 255)
    @Column(name = "MAIL")
    private String mail;
    @Size(max = 255)
    @Column(name = "TELEFONO")
    private String telefono;

    public Cxcinfocliente() {
    }

    public Cxcinfocliente(Integer id) {
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

    public BigDecimal getCreditmax() {
        return creditmax;
    }

    public void setCreditmax(BigDecimal creditmax) {
        this.creditmax = creditmax;
    }

    public String getVatnum() {
        return vatnum;
    }

    public void setVatnum(String vatnum) {
        this.vatnum = vatnum;
    }

    public String getPaymSched() {
        return paymSched;
    }

    public void setPaymSched(String paymSched) {
        this.paymSched = paymSched;
    }

    public String getPaymMode() {
        return paymMode;
    }

    public void setPaymMode(String paymMode) {
        this.paymMode = paymMode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStreetnumber() {
        return streetnumber;
    }

    public void setStreetnumber(String streetnumber) {
        this.streetnumber = streetnumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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
        if (!(object instanceof Cxcinfocliente)) {
            return false;
        }
        Cxcinfocliente other = (Cxcinfocliente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.alphacell.model.cartera.Cxcinfocliente[ id=" + id + " ]";
    }
    
}
