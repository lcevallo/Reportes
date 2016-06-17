package com.alphacell.model.cartera;

/**
 * Created by luis on 17/06/16.
 */

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
@Table(name = "LC_CXC_VISTA_CLIENTES")

@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "ClienteVista.findAll", query = "SELECT c FROM ClienteVista c"),
        @NamedQuery(name = "ClienteVista.findByAccountnum", query = "SELECT c FROM ClienteVista c WHERE c.accountnum = :accountnum"),
        @NamedQuery(name = "ClienteVista.findByNombreCliente", query = "SELECT c FROM ClienteVista c WHERE c.nombreCliente = :nombreCliente")})

public class ClienteVista implements Serializable  {

    private static final long serialVersionUID = 6562136513015955959L;

    @Id
    @Basic(optional = false)
    @Size(max = 20)
    @Column(name = "ACCOUNTNUM")
    private String accountnum;
    @Size(max = 100)
    @Column(name = "NOMBRECLIENTE")
    private String nombreCliente;


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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accountnum != null ? accountnum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cxcinfocliente)) {
            return false;
        }
        ClienteVista other = (ClienteVista) object;
        if ((this.accountnum == null && other.getAccountnum() != null) || (this.accountnum != null && !this.accountnum.equals(other.accountnum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.alphacell.model.cartera.ClienteVista[ accountnum=" + accountnum + " ]";
    }


}
