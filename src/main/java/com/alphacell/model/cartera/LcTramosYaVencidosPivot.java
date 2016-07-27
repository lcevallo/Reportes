package com.alphacell.model.cartera;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by luis on 26/07/16.
 */
@Entity
@Table(name = "LC_TRAMOS_YA_VENCIDOS_PIVOT")

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


    private BigDecimal dias15B;
    private BigDecimal dias30B;
    private BigDecimal dias45B;
    private BigDecimal dias60B;
    private BigDecimal dias90B;
    private BigDecimal dias120B;
    private BigDecimal masDe120DiasB;

    private String dias15F;
    private String dias30F;
    private String dias45F;
    private String dias60F;
    private String dias90F;
    private String dias120F;
    private String masDe120DiasF;

    public LcTramosYaVencidosPivot(int id, String accountnum, String nombreCliente, String dias15, String dias30, String dias45, String dias60, String dias90, String dias120, String masDe120Dias) {
        this.id = id;
        this.accountnum = accountnum;
        this.nombreCliente = nombreCliente;
        this.dias15 = dias15;
        this.dias30 = dias30;
        this.dias45 = dias45;
        this.dias60 = dias60;
        this.dias90 = dias90;
        this.dias120 = dias120;
        this.masDe120Dias = masDe120Dias;


        this.dias15B= this.obtenerValorDecimal(dias15);
        this.dias30B= this.obtenerValorDecimal(dias30);
        this.dias45B= this.obtenerValorDecimal(dias45);
        this.dias60B= this.obtenerValorDecimal(dias60);
        this.dias90B= this.obtenerValorDecimal(dias90);
        this.dias120B= this.obtenerValorDecimal(dias120);
        this.masDe120DiasB= this.obtenerValorDecimal(masDe120Dias);

        this.dias15F=this.obtenerValorString(dias15);
        this.dias30F=this.obtenerValorString(dias30);
        this.dias45F=this.obtenerValorString(dias45);
        this.dias60F=this.obtenerValorString(dias60);
        this.dias90F=this.obtenerValorString(dias90);
        this.dias120F=this.obtenerValorString(dias120);
        this.masDe120DiasF=this.obtenerValorString(masDe120Dias);

    }

    public LcTramosYaVencidosPivot() {
    }


    @Id
    @Column(name = "ID", nullable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Column(name = "ACCOUNTNUM", nullable = false, length = 20)
    public String getAccountnum() {
        return accountnum;
    }

    public void setAccountnum(String accountnum) {
        this.accountnum = accountnum;
    }


    @Column(name = "nombre_cliente", nullable = true, length = 100)
    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }


    @Column(name = "dias15", nullable = true, length = 300)
    public String getDias15() {
        return dias15;

    }

    public void setDias15(String dias15) {
        this.dias15B= this.obtenerValorDecimal(dias15);
        this.dias15F=this.obtenerValorString(dias15);
        this.dias15 = dias15;
    }


    @Column(name = "dias30", nullable = true, length = 300)
    public String getDias30() {
        return dias30;

    }

    public void setDias30(String dias30) {
        this.dias30B= this.obtenerValorDecimal(dias30);
        this.dias30F=this.obtenerValorString(dias30);
        this.dias30 = dias30;
    }


    @Column(name = "dias45", nullable = true, length = 300)
    public String getDias45() {
        return dias45;
    }

    public void setDias45(String dias45) {
        this.dias45B= this.obtenerValorDecimal(dias45);
        this.dias45F=this.obtenerValorString(dias45);
        this.dias45 = dias45;
    }


    @Column(name = "dias60", nullable = true, length = 300)
    public String getDias60() {
        return dias60;

    }

    public void setDias60(String dias60) {
        this.dias60B= this.obtenerValorDecimal(dias60);
        this.dias60F=this.obtenerValorString(dias60);
        this.dias60 = dias60;
    }


    @Column(name = "dias90", nullable = true, length = 300)
    public String getDias90() {
        return dias90;

    }

    public void setDias90(String dias90) {
        this.dias90B= this.obtenerValorDecimal(dias90);
        this.dias90F=this.obtenerValorString(dias90);
        this.dias90 = dias90;
    }


    @Column(name = "dias120", nullable = true, length = 300)
    public String getDias120() {
        return dias120;

    }

    public void setDias120(String dias120) {
        this.dias120B= this.obtenerValorDecimal(dias120);
        this.dias120F=this.obtenerValorString(dias120);
        this.dias120 = dias120;
    }


    @Column(name = "Mas_de_120_dias", nullable = true, length = 300)
    public String getMasDe120Dias() {
        return masDe120Dias;

    }

    public void setMasDe120Dias(String masDe120Dias) {
        this.masDe120DiasB= this.obtenerValorDecimal(masDe120Dias);
        this.masDe120DiasF=this.obtenerValorString(masDe120Dias);
        this.masDe120Dias = masDe120Dias;
    }


    @Transient
    public BigDecimal getDias15B() {
        return dias15B;
    }


    public void setDias15B(BigDecimal dias15B) {
        this.dias15B = dias15B;
    }

    @Transient
    public BigDecimal getDias30B() {

        return dias30B;
    }

    public void setDias30B(BigDecimal dias30B) {
        this.dias30B = dias30B;
    }

    @Transient
    public BigDecimal getDias45B() {

        return dias45B;
    }

    public void setDias45B(BigDecimal dias45B) {
        this.dias45B = dias45B;
    }

    @Transient
    public BigDecimal getDias60B() {

        return dias60B;
    }

    public void setDias60B(BigDecimal dias60B) {
        this.dias60B = dias60B;
    }

    @Transient
    public BigDecimal getDias90B() {
        return dias90B;
    }

    public void setDias90B(BigDecimal dias90B) {
        this.dias90B = dias90B;
    }

    @Transient
    public BigDecimal getDias120B() {

        return dias120B;
    }

    public void setDias120B(BigDecimal dias120B) {
        this.dias120B = dias120B;
    }

    @Transient
    public BigDecimal getMasDe120DiasB() {

        return masDe120DiasB;
    }

    public void setMasDe120DiasB(BigDecimal masDe120DiasB) {
        this.masDe120DiasB = masDe120DiasB;
    }

    @Transient
    public String getDias15F() {

        return dias15F;
    }

    public void setDias15F(String dias15F) {
        this.dias15F = dias15F;
    }

    @Transient
    public String getDias30F() {
        return dias30F;
    }

    public void setDias30F(String dias30F) {
        this.dias30F = dias30F;
    }

    @Transient
    public String getDias45F() {
        return dias45F;
    }

    public void setDias45F(String dias45F) {
        this.dias45F = dias45F;
    }

    @Transient
    public String getDias60F() {

        return dias60F;
    }

    public void setDias60F(String dias60F) {
        this.dias60F = dias60F;
    }

    @Transient
    public String getDias90F() {

        return dias90F;
    }

    public void setDias90F(String dias90F) {
        this.dias90F = dias90F;
    }

    @Transient
    public String getDias120F() {

        return dias120F;
    }

    public void setDias120F(String dias120F) {
        this.dias120F = dias120F;
    }

    @Transient
    public String getMasDe120DiasF() {

        return masDe120DiasF;
    }

    public void setMasDe120DiasF(String masDe120DiasF) {
        this.masDe120DiasF = masDe120DiasF;
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


    public BigDecimal obtenerValorDecimal(String dato)
    {
        if(dato!=null)
        {
            int posicion = dato.lastIndexOf("||");
            return new BigDecimal(Double.parseDouble(dato.substring(posicion+2,dato.length())));
        }
        else
            return null;

    }

    public String obtenerValorString(String dato)
    {
        if(dato!=null)
            return  dato.substring(0,dato.lastIndexOf("||"));
        else
            return null;
    }



}
