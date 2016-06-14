/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@Table(name = "TblReporteVentas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblReporteVentas.findAll", query = "SELECT t FROM TblReporteVentas t"),
    @NamedQuery(name = "TblReporteVentas.findById", query = "SELECT t FROM TblReporteVentas t WHERE t.id = :id"),
    @NamedQuery(name = "TblReporteVentas.findByNoDoc", query = "SELECT t FROM TblReporteVentas t WHERE t.noDoc = :noDoc"),
    @NamedQuery(name = "TblReporteVentas.findByFechaDoc", query = "SELECT t FROM TblReporteVentas t WHERE t.fechaDoc = :fechaDoc"),
    @NamedQuery(name = "TblReporteVentas.findByCodCliente", query = "SELECT t FROM TblReporteVentas t WHERE t.codCliente = :codCliente"),
    @NamedQuery(name = "TblReporteVentas.findByNombCliente", query = "SELECT t FROM TblReporteVentas t WHERE t.nombCliente = :nombCliente"),
    @NamedQuery(name = "TblReporteVentas.findByCodItem", query = "SELECT t FROM TblReporteVentas t WHERE t.codItem = :codItem"),
    @NamedQuery(name = "TblReporteVentas.findByDiario", query = "SELECT t FROM TblReporteVentas t WHERE t.diario = :diario"),
    @NamedQuery(name = "TblReporteVentas.findByDescripcion", query = "SELECT t FROM TblReporteVentas t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "TblReporteVentas.findByCantidad", query = "SELECT t FROM TblReporteVentas t WHERE t.cantidad = :cantidad"),
    @NamedQuery(name = "TblReporteVentas.findByPrecioUnitario", query = "SELECT t FROM TblReporteVentas t WHERE t.precioUnitario = :precioUnitario"),
    @NamedQuery(name = "TblReporteVentas.findByTotalDescuento", query = "SELECT t FROM TblReporteVentas t WHERE t.totalDescuento = :totalDescuento"),
    @NamedQuery(name = "TblReporteVentas.findByIva", query = "SELECT t FROM TblReporteVentas t WHERE t.iva = :iva"),
    @NamedQuery(name = "TblReporteVentas.findByNeto", query = "SELECT t FROM TblReporteVentas t WHERE t.neto = :neto"),
    @NamedQuery(name = "TblReporteVentas.findByCosto", query = "SELECT t FROM TblReporteVentas t WHERE t.costo = :costo"),
    @NamedQuery(name = "TblReporteVentas.findByExternalItemId", query = "SELECT t FROM TblReporteVentas t WHERE t.externalItemId = :externalItemId"),
    @NamedQuery(name = "TblReporteVentas.findByCodigoBodega", query = "SELECT t FROM TblReporteVentas t WHERE t.codigoBodega = :codigoBodega"),
    @NamedQuery(name = "TblReporteVentas.findByVendedor", query = "SELECT t FROM TblReporteVentas t WHERE t.vendedor = :vendedor")})
public class TblReporteVentas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NoDoc")
    private String noDoc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FechaDoc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDoc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CodCliente")
    private String codCliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NombCliente")
    private String nombCliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CodItem")
    private String codItem;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Diario")
    private String diario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "Descripcion")
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "Cantidad")
    private BigDecimal cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PrecioUnitario")
    private BigDecimal precioUnitario;
    @Column(name = "TotalDescuento")
    private BigDecimal totalDescuento;
    @Column(name = "IVA")
    private BigDecimal iva;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Neto")
    private BigDecimal neto;
    @Column(name = "costo")
    private BigDecimal costo;
    @Size(max = 20)
    @Column(name = "ExternalItemId")
    private String externalItemId;
    @Size(max = 20)
    @Column(name = "codigoBodega")
    private String codigoBodega;
    @Size(max = 100)
    @Column(name = "vendedor")
    private String vendedor;

    public TblReporteVentas() {
    }

    public TblReporteVentas(Integer id) {
        this.id = id;
    }

    public TblReporteVentas(Integer id, String noDoc, Date fechaDoc, String codCliente, String nombCliente, String codItem, String diario, String descripcion, BigDecimal cantidad, BigDecimal precioUnitario, BigDecimal neto) {
        this.id = id;
        this.noDoc = noDoc;
        this.fechaDoc = fechaDoc;
        this.codCliente = codCliente;
        this.nombCliente = nombCliente;
        this.codItem = codItem;
        this.diario = diario;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.neto = neto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoDoc() {
        return noDoc;
    }

    public void setNoDoc(String noDoc) {
        this.noDoc = noDoc;
    }

    public Date getFechaDoc() {
        return fechaDoc;
    }

    public void setFechaDoc(Date fechaDoc) {
        this.fechaDoc = fechaDoc;
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

    public String getCodItem() {
        return codItem;
    }

    public void setCodItem(String codItem) {
        this.codItem = codItem;
    }

    public String getDiario() {
        return diario;
    }

    public void setDiario(String diario) {
        this.diario = diario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getTotalDescuento() {
        return totalDescuento;
    }

    public void setTotalDescuento(BigDecimal totalDescuento) {
        this.totalDescuento = totalDescuento;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public BigDecimal getNeto() {
        return neto;
    }

    public void setNeto(BigDecimal neto) {
        this.neto = neto;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public String getExternalItemId() {
        return externalItemId;
    }

    public void setExternalItemId(String externalItemId) {
        this.externalItemId = externalItemId;
    }

    public String getCodigoBodega() {
        return codigoBodega;
    }

    public void setCodigoBodega(String codigoBodega) {
        this.codigoBodega = codigoBodega;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
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
        if (!(object instanceof TblReporteVentas)) {
            return false;
        }
        TblReporteVentas other = (TblReporteVentas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.alphacell.model.financiero.TblReporteVentas[ id=" + id + " ]";
    }
    
}
