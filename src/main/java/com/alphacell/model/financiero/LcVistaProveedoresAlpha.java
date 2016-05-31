/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphacell.model.financiero;

import java.io.Serializable;
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
@Table(name = "LC_VISTA_PROVEEDORES_ALPHA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LcVistaProveedoresAlpha.findAll", query = "SELECT l FROM LcVistaProveedoresAlpha l"),
    @NamedQuery(name = "LcVistaProveedoresAlpha.findByName", query = "SELECT l FROM LcVistaProveedoresAlpha l WHERE l.name = :name"),
    @NamedQuery(name = "LcVistaProveedoresAlpha.findByAccountnum", query = "SELECT l FROM LcVistaProveedoresAlpha l WHERE l.accountnum = :accountnum"),
    @NamedQuery(name = "LcVistaProveedoresAlpha.findByVendGroup", query = "SELECT l FROM LcVistaProveedoresAlpha l WHERE l.vendGroup = :vendGroup"),
    @NamedQuery(name ="LcVistaProveedoresAlpha.findByPosibleName", query = "SELECT l FROM LcVistaProveedoresAlpha l where l.name LIKE :name")})

public class LcVistaProveedoresAlpha implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 100)
    @Column(name = "NAME")
    private String name;
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ACCOUNTNUM")
    @Id
    private String accountnum;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "VendGroup")
    private String vendGroup;

    public LcVistaProveedoresAlpha() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountnum() {
        return accountnum;
    }

    public void setAccountnum(String accountnum) {
        this.accountnum = accountnum;
    }

    public String getVendGroup() {
        return vendGroup;
    }

    public void setVendGroup(String vendGroup) {
        this.vendGroup = vendGroup;
    }
    
}
