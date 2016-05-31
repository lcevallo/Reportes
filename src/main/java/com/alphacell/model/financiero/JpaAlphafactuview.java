package com.alphacell.model.financiero;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author luis.cevallos
 */
@Entity
@Table(name = "JPA_ALPHAFACTUVIEW")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JpaAlphafactuview.findAll", query = "SELECT j FROM JpaAlphafactuview j"),
    @NamedQuery(name = "JpaAlphafactuview.findBySno", query = "SELECT j FROM JpaAlphafactuview j WHERE j.sno = :sno"),
    @NamedQuery(name = "JpaAlphafactuview.findByCodItem", query = "SELECT j FROM JpaAlphafactuview j WHERE j.codItem = :codItem"),
    @NamedQuery(name = "JpaAlphafactuview.findByDescripcion", query = "SELECT j FROM JpaAlphafactuview j WHERE j.descripcion = :descripcion"),
    @NamedQuery(name = "JpaAlphafactuview.findByFechaFac", query = "SELECT j FROM JpaAlphafactuview j WHERE j.fechaFac = :fechaFac"),
    @NamedQuery(name = "JpaAlphafactuview.findBetweenDates", query = "SELECT j FROM JpaAlphafactuview j WHERE j.fechaFac between :fechaIni AND :fechaFin"),
    @NamedQuery(name = "JpaAlphafactuview.findByNoDoc", query = "SELECT j FROM JpaAlphafactuview j WHERE j.noDoc = :noDoc"),
    @NamedQuery(name = "JpaAlphafactuview.findByOrdenVenta", query = "SELECT j FROM JpaAlphafactuview j WHERE j.ordenVenta = :ordenVenta"),
    @NamedQuery(name = "JpaAlphafactuview.findByCodCliente", query = "SELECT j FROM JpaAlphafactuview j WHERE j.codCliente = :codCliente"),
    @NamedQuery(name = "JpaAlphafactuview.findByNombCliente", query = "SELECT j FROM JpaAlphafactuview j WHERE j.nombCliente = :nombCliente"),
    @NamedQuery(name = "JpaAlphafactuview.findByQty", query = "SELECT j FROM JpaAlphafactuview j WHERE j.qty = :qty"),
    @NamedQuery(name = "JpaAlphafactuview.findByPrecioUnitario", query = "SELECT j FROM JpaAlphafactuview j WHERE j.precioUnitario = :precioUnitario"),
    @NamedQuery(name = "JpaAlphafactuview.findByTotalDscto", query = "SELECT j FROM JpaAlphafactuview j WHERE j.totalDscto = :totalDscto"),
    @NamedQuery(name = "JpaAlphafactuview.findByNeto", query = "SELECT j FROM JpaAlphafactuview j WHERE j.neto = :neto"),
    @NamedQuery(name = "JpaAlphafactuview.findByIva", query = "SELECT j FROM JpaAlphafactuview j WHERE j.iva = :iva"),
    @NamedQuery(name = "JpaAlphafactuview.findByTotal", query = "SELECT j FROM JpaAlphafactuview j WHERE j.total = :total"),
    @NamedQuery(name = "JpaAlphafactuview.findByVendedor", query = "SELECT j FROM JpaAlphafactuview j WHERE j.vendedor = :vendedor")})
public class JpaAlphafactuview implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id @Column(name = "SNO")
    private BigInteger sno;
    @Size(max = 20)
    @Column(name = "CodItem")
    private String codItem;
    @Size(max = 1000)
    @Column(name = "Descripcion")
    private String descripcion;
    @Column(name = "FechaFac")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFac;
    @Size(max = 20)
    @Column(name = "NoDoc")
    private String noDoc;
    @Size(max = 20)
    @Column(name = "OrdenVenta")
    private String ordenVenta;
    @Size(max = 20)
    @Column(name = "CodCliente")
    private String codCliente;
    @Size(max = 100)
    @Column(name = "NombCliente")
    private String nombCliente;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "QTY")
    private BigDecimal qty;
    @Column(name = "PrecioUnitario")
    private BigDecimal precioUnitario;
    @Column(name = "TotalDscto")
    private BigDecimal totalDscto;
    @Column(name = "Neto")
    private BigDecimal neto;
    @Column(name = "IVA")
    private BigDecimal iva;
    @Column(name = "Total")
    private BigDecimal total;
    @Size(max = 100)
    @Column(name = "Vendedor")
    private String vendedor;

    public JpaAlphafactuview() {
    }

    public BigInteger getSno() {
        return sno;
    }

    public void setSno(BigInteger sno) {
        this.sno = sno;
    }

    public String getCodItem() {
        return codItem;
    }

    public void setCodItem(String codItem) {
        this.codItem = codItem;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaFac() {
        return fechaFac;
    }

    public void setFechaFac(Date fechaFac) {
        this.fechaFac = fechaFac;
    }
 
    public String getNoDoc() {
        return noDoc;
    }

    public void setNoDoc(String noDoc) {
        this.noDoc = noDoc;
    }

    public String getOrdenVenta() {
        return ordenVenta;
    }

    public void setOrdenVenta(String ordenVenta) {
        this.ordenVenta = ordenVenta;
    }

    public String getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(String codCliente) {
        this.codCliente = codCliente;
    }

    public String getNombCliente() {
        return nombCliente;
    }

    public void setNombCliente(String nombCliente) {
        this.nombCliente = nombCliente;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getTotalDscto() {
        return totalDscto;
    }

    public void setTotalDscto(BigDecimal totalDscto) {
        this.totalDscto = totalDscto;
    }

    public BigDecimal getNeto() {
        return neto;
    }

    public void setNeto(BigDecimal neto) {
        this.neto = neto;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }
    
}
