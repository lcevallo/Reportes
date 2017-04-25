/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphacell.model.ventas;

import com.alphacell.model.xls.LcCadenaItemsXLS;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author luis.cevallos
 */
@Entity
@Table(name = "LC_VISTA_EXCEL_VENTAS_INICIAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LcVistaExcelVentasInicial.findAll", query = "SELECT l FROM LcVistaExcelVentasInicial l"),
    @NamedQuery(name = "LcVistaExcelVentasInicial.findByRowN", query = "SELECT l FROM LcVistaExcelVentasInicial l WHERE l.rowN = :rowN"),
    @NamedQuery(name = "LcVistaExcelVentasInicial.findByFkCodigoCadena", query = "SELECT l FROM LcVistaExcelVentasInicial l WHERE l.fkCodigoCadena = :fkCodigoCadena"),
    @NamedQuery(name = "LcVistaExcelVentasInicial.findByInventlocationid", query = "SELECT l FROM LcVistaExcelVentasInicial l WHERE l.inventlocationid = :inventlocationid"),
    @NamedQuery(name = "LcVistaExcelVentasInicial.findByMarca", query = "SELECT l FROM LcVistaExcelVentasInicial l WHERE l.marca = :marca"),
    @NamedQuery(name = "LcVistaExcelVentasInicial.findByCodigoItem", query = "SELECT l FROM LcVistaExcelVentasInicial l WHERE l.codigoItem = :codigoItem"),
    @NamedQuery(name = "LcVistaExcelVentasInicial.findByItemid", query = "SELECT l FROM LcVistaExcelVentasInicial l WHERE l.itemid = :itemid"),
    @NamedQuery(name = "LcVistaExcelVentasInicial.findByName", query = "SELECT l FROM LcVistaExcelVentasInicial l WHERE l.name = :name")})
public class LcVistaExcelVentasInicial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "rowN")
    @Id
    private BigInteger rowN;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "fk_codigo_cadena")
    private String fkCodigoCadena;
    @Size(max = 10)
    @Column(name = "INVENTLOCATIONID")
    private String inventlocationid;
    @Size(max = 70)
    @Column(name = "marca")
    private String marca;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "codigo_item")
    private String codigoItem;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ITEMID")
    private String itemid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "NAME")
    private String name;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "descripcion_cadena")
    private String descripcionCadena;

    @Transient
    private LcCadenaItemsXLS lcCadenaItemsXLS;

    public LcVistaExcelVentasInicial() {
        this.lcCadenaItemsXLS= new LcCadenaItemsXLS();
    }

    public LcVistaExcelVentasInicial(String fkCodigoCadena, String marca, String codigoItem, String itemid,String descripcionCadena) {
        this.fkCodigoCadena = fkCodigoCadena;
        this.marca = marca;
        this.codigoItem = codigoItem;
        this.itemid = itemid;
        this.descripcionCadena = descripcionCadena;
        this.lcCadenaItemsXLS.setCodigo(this.getItemid());
        this.lcCadenaItemsXLS.setDescripcion(this.getName());

    }



    public BigInteger getRowN() {
        return rowN;
    }

    public void setRowN(BigInteger rowN) {
        this.rowN = rowN;


    }

    public String getFkCodigoCadena() {
        return fkCodigoCadena;
    }

    public void setFkCodigoCadena(String fkCodigoCadena) {
        this.fkCodigoCadena = fkCodigoCadena;
    }

    public String getInventlocationid() {
        return inventlocationid;
    }

    public void setInventlocationid(String inventlocationid) {
        this.inventlocationid = inventlocationid;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCodigoItem() {
        return codigoItem;
    }

    public void setCodigoItem(String codigoItem) {
        this.codigoItem = codigoItem;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescripcionCadena() {
        return descripcionCadena;
    }

    public void setDescripcionCadena(String descripcionCadena) {
        this.descripcionCadena = descripcionCadena;


    }

    public LcCadenaItemsXLS getLcCadenaItemsXLS() {
        return lcCadenaItemsXLS;
    }

    public void setLcCadenaItemsXLS(LcCadenaItemsXLS lcCadenaItemsXLS) {
        this.lcCadenaItemsXLS = lcCadenaItemsXLS;
    }


}
