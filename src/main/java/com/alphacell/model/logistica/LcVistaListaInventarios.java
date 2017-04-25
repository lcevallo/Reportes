package com.alphacell.model.logistica;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by luis.cevallos on 15/3/2017.
 */
@Entity
@Table(name = "LC_VISTA_LISTA_INVENTARIOS", schema = "dbo", catalog = "Produccion")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "LcVistaListaInventarios.findAll", query = "SELECT l FROM LcVistaListaInventarios l"),
        @NamedQuery(name = "LcVistaListaInventarios.findByInventlocationid", query = "SELECT l FROM LcVistaListaInventarios l WHERE l.inventlocationid = :inventlocationid"),
        @NamedQuery(name = "LcVistaListaInventarios.findByName", query = "SELECT l FROM LcVistaListaInventarios l WHERE l.name = :name")})
public class LcVistaListaInventarios implements Serializable {
    private static final long serialVersionUID = -9142038120573732910L;
    private String inventlocationid;
    private String name;

    @Basic
    @Column(name = "INVENTLOCATIONID", nullable = false, length = 10)
    @Id
    public String getInventlocationid() {
        return inventlocationid;
    }

    public void setInventlocationid(String inventlocationid) {
        this.inventlocationid = inventlocationid;
    }

    @Basic
    @Column(name = "NAME", nullable = false, length = 60)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LcVistaListaInventarios that = (LcVistaListaInventarios) o;

        if (inventlocationid != null ? !inventlocationid.equals(that.inventlocationid) : that.inventlocationid != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = inventlocationid != null ? inventlocationid.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {

        // return "com.alphacell.model.ventas.LcCadenaAlph[ codigoCadena=" + codigoCadena + " ]";
        return String.format("LcVistaListaInventarios[%s,%s]",inventlocationid,name);
    }
}
