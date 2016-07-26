package com.alphacell.model.cartera;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by luis on 26/07/16.
 */
@Entity
@Table(name = "LC_TRAMOS_YA_VENCIDOS_PIVOT", schema = "dbo", catalog = "Produccion")

@XmlRootElement
@NamedQueries({
        @NamedQuery(name ="LcTramosYaVencidosPivot.findAll",query="Select c from LcTramosYaVencidosPivot c")
})

public class LcTramosYaVencidosPivot implements Serializable{
    private static final long serialVersionUID = 8744304119052332990L;
    private int id;
    private String accountnum;
    private String nombreCliente;
    private String dias15;
    private String dias30;
    private String dias45;
    private String dias60;
    private String dias90;
    private String dias120;
    private String masDe120Dias;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ACCOUNTNUM", nullable = false, length = 20)
    public String getAccountnum() {
        return accountnum;
    }

    public void setAccountnum(String accountnum) {
        this.accountnum = accountnum;
    }

    @Basic
    @Column(name = "nombre_cliente", nullable = true, length = 100)
    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    @Basic
    @Column(name = "dias15", nullable = true, length = 300)
    public String getDias15() {
        return dias15;
    }

    public void setDias15(String dias15) {
        this.dias15 = dias15;
    }

    @Basic
    @Column(name = "dias30", nullable = true, length = 300)
    public String getDias30() {
        return dias30;
    }

    public void setDias30(String dias30) {
        this.dias30 = dias30;
    }

    @Basic
    @Column(name = "dias45", nullable = true, length = 300)
    public String getDias45() {
        return dias45;
    }

    public void setDias45(String dias45) {
        this.dias45 = dias45;
    }

    @Basic
    @Column(name = "dias60", nullable = true, length = 300)
    public String getDias60() {
        return dias60;
    }

    public void setDias60(String dias60) {
        this.dias60 = dias60;
    }

    @Basic
    @Column(name = "dias90", nullable = true, length = 300)
    public String getDias90() {
        return dias90;
    }

    public void setDias90(String dias90) {
        this.dias90 = dias90;
    }

    @Basic
    @Column(name = "dias120", nullable = true, length = 300)
    public String getDias120() {
        return dias120;
    }

    public void setDias120(String dias120) {
        this.dias120 = dias120;
    }

    @Basic
    @Column(name = "Mas_de_120_dias", nullable = true, length = 300)
    public String getMasDe120Dias() {
        return masDe120Dias;
    }

    public void setMasDe120Dias(String masDe120Dias) {
        this.masDe120Dias = masDe120Dias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LcTramosYaVencidosPivot that = (LcTramosYaVencidosPivot) o;

        if (id != that.id) return false;
        if (accountnum != null ? !accountnum.equals(that.accountnum) : that.accountnum != null) return false;
        if (nombreCliente != null ? !nombreCliente.equals(that.nombreCliente) : that.nombreCliente != null)
            return false;
        if (dias15 != null ? !dias15.equals(that.dias15) : that.dias15 != null) return false;
        if (dias30 != null ? !dias30.equals(that.dias30) : that.dias30 != null) return false;
        if (dias45 != null ? !dias45.equals(that.dias45) : that.dias45 != null) return false;
        if (dias60 != null ? !dias60.equals(that.dias60) : that.dias60 != null) return false;
        if (dias90 != null ? !dias90.equals(that.dias90) : that.dias90 != null) return false;
        if (dias120 != null ? !dias120.equals(that.dias120) : that.dias120 != null) return false;
        if (masDe120Dias != null ? !masDe120Dias.equals(that.masDe120Dias) : that.masDe120Dias != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (accountnum != null ? accountnum.hashCode() : 0);
        result = 31 * result + (nombreCliente != null ? nombreCliente.hashCode() : 0);
        result = 31 * result + (dias15 != null ? dias15.hashCode() : 0);
        result = 31 * result + (dias30 != null ? dias30.hashCode() : 0);
        result = 31 * result + (dias45 != null ? dias45.hashCode() : 0);
        result = 31 * result + (dias60 != null ? dias60.hashCode() : 0);
        result = 31 * result + (dias90 != null ? dias90.hashCode() : 0);
        result = 31 * result + (dias120 != null ? dias120.hashCode() : 0);
        result = 31 * result + (masDe120Dias != null ? masDe120Dias.hashCode() : 0);
        return result;
    }
    
     @Override
    public String toString() {
        return "Entities.LcTramosYaVencidosPivot[ id=" + id + " ]";
    }
}
