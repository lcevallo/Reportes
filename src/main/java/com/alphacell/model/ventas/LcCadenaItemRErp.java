/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphacell.model.ventas;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author luis.cevallos
 */
@Entity
@Table(name = "LC_CADENA_ITEM_R_ERP")
@XmlRootElement
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "LcCadenaItemRErp.sp_guardar_cadenaitem",procedureName = "dbo.LC_GUARDAR_EXCEL_VENTAS_CFG",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN,type = String.class,name ="cod_cadena" ),
                        @StoredProcedureParameter(mode = ParameterMode.IN,type = String.class,name ="marca" ),
                        @StoredProcedureParameter(mode = ParameterMode.IN,type = String.class,name ="modelo_unificado" ),
                        @StoredProcedureParameter(mode = ParameterMode.IN,type = String.class,name ="item_id" ),
                        @StoredProcedureParameter(mode = ParameterMode.IN,type = String.class,name ="descripcion_cadena" )
                }
        )
})


@NamedQueries({
    @NamedQuery(name = "LcCadenaItemRErp.findAll", query = "SELECT l FROM LcCadenaItemRErp l"),
    @NamedQuery(name = "LcCadenaItemRErp.findByLcCadenaItemRErpPk", query = "SELECT l FROM LcCadenaItemRErp l WHERE l.lcCadenaItemRErpPK.fkCodigoItem = :fkCodigoItem and l.lcCadenaItemRErpPK.fkCodigoCadena=:fkCodigoCadena and l.lcCadenaItemRErpPK.itemid = :itemid"),
    @NamedQuery(name = "LcCadenaItemRErp.findByFkCodigoItem", query = "SELECT l FROM LcCadenaItemRErp l WHERE l.lcCadenaItemRErpPK.fkCodigoItem = :fkCodigoItem"),
    @NamedQuery(name = "LcCadenaItemRErp.findByFkCodigoCadena", query = "SELECT l FROM LcCadenaItemRErp l WHERE l.lcCadenaItemRErpPK.fkCodigoCadena = :fkCodigoCadena"),
    @NamedQuery(name = "LcCadenaItemRErp.findByItemid", query = "SELECT l FROM LcCadenaItemRErp l WHERE l.lcCadenaItemRErpPK.itemid = :itemid"),
    @NamedQuery(name = "LcCadenaItemRErp.findByInventlocationid", query = "SELECT l FROM LcCadenaItemRErp l WHERE l.inventlocationid = :inventlocationid")})
public class LcCadenaItemRErp implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LcCadenaItemRErpPK lcCadenaItemRErpPK;
    @Size(max = 50)
    @Column(name = "INVENTLOCATIONID")
    private String inventlocationid;

    public LcCadenaItemRErp() {
    }

    public LcCadenaItemRErp(LcCadenaItemRErpPK lcCadenaItemRErpPK) {
        this.lcCadenaItemRErpPK = lcCadenaItemRErpPK;
    }

    public LcCadenaItemRErp(String fkCodigoItem, String fkCodigoCadena, String itemid) {
        this.lcCadenaItemRErpPK = new LcCadenaItemRErpPK(fkCodigoItem, fkCodigoCadena, itemid);
    }

    public LcCadenaItemRErpPK getLcCadenaItemRErpPK() {
        return lcCadenaItemRErpPK;
    }

    public void setLcCadenaItemRErpPK(LcCadenaItemRErpPK lcCadenaItemRErpPK) {
        this.lcCadenaItemRErpPK = lcCadenaItemRErpPK;
    }

    public String getInventlocationid() {
        return inventlocationid;
    }

    public void setInventlocationid(String inventlocationid) {
        this.inventlocationid = inventlocationid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lcCadenaItemRErpPK != null ? lcCadenaItemRErpPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LcCadenaItemRErp)) {
            return false;
        }
        LcCadenaItemRErp other = (LcCadenaItemRErp) object;
        if ((this.lcCadenaItemRErpPK == null && other.lcCadenaItemRErpPK != null) || (this.lcCadenaItemRErpPK != null && !this.lcCadenaItemRErpPK.equals(other.lcCadenaItemRErpPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.alphacell.model.ventas.LcCadenaItemRErp[ lcCadenaItemRErpPK=" + lcCadenaItemRErpPK + " ]";
    }
    
}
