package com.alphacell.model.cartera;

/**
 * Created by admin on 02/04/2016.
 */

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 */
@Entity
@Table(name = "TABLA_CXCPIVOTE")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "TablaCxcpivote.findAll", query = "SELECT t FROM TablaCxcpivote t"),
        @NamedQuery(name = "TablaCxcpivote.findById", query = "SELECT t FROM TablaCxcpivote t WHERE t.id = :id"),
        @NamedQuery(name = "TablaCxcpivote.findByCliente", query = "SELECT t FROM TablaCxcpivote t WHERE t.cliente = :cliente"),
        @NamedQuery(name = "TablaCxcpivote.findByCodigoCliente", query = "SELECT t FROM TablaCxcpivote t WHERE t.codigoCliente = :codigoCliente"),
        @NamedQuery(name = "TablaCxcpivote.findByCreditmax", query = "SELECT t FROM TablaCxcpivote t WHERE t.creditmax = :creditmax"),
        @NamedQuery(name = "TablaCxcpivote.findByA", query = "SELECT t FROM TablaCxcpivote t WHERE t.a = :a"),
        @NamedQuery(name = "TablaCxcpivote.findByA1", query = "SELECT t FROM TablaCxcpivote t WHERE t.a1 = :a1"),
        @NamedQuery(name = "TablaCxcpivote.findByA2", query = "SELECT t FROM TablaCxcpivote t WHERE t.a2 = :a2"),
        @NamedQuery(name = "TablaCxcpivote.findByA3", query = "SELECT t FROM TablaCxcpivote t WHERE t.a3 = :a3"),
        @NamedQuery(name = "TablaCxcpivote.findByA4", query = "SELECT t FROM TablaCxcpivote t WHERE t.a4 = :a4"),
        @NamedQuery(name = "TablaCxcpivote.findByA5", query = "SELECT t FROM TablaCxcpivote t WHERE t.a5 = :a5"),
        @NamedQuery(name = "TablaCxcpivote.findByA6", query = "SELECT t FROM TablaCxcpivote t WHERE t.a6 = :a6"),
        @NamedQuery(name = "TablaCxcpivote.findByA7", query = "SELECT t FROM TablaCxcpivote t WHERE t.a7 = :a7")}
)
public class TablaCxcpivote implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 100)
    @Column(name = "NOMBRE_CLIENTE")
    private String cliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ACCOUNTNUM")
    private String codigoCliente;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREDITMAX")
    private BigDecimal creditmax;

    @Basic(optional = false)
    @Column(name = "UTILIZADO")
    private BigDecimal utilizado;

    @Column(name = "Saldo")
    private BigDecimal saldo;

    @Column(name = "1")
    private BigDecimal a;
    @Column(name = "2")
    private BigDecimal a1;
    @Column(name = "3")
    private BigDecimal a2;
    @Column(name = "4")
    private BigDecimal a3;
    @Column(name = "5")
    private BigDecimal a4;
    @Column(name = "6")
    private BigDecimal a5;
    @Column(name = "7")
    private BigDecimal a6;
    @Column(name = "8")
    private BigDecimal a7;
    @Column(name = "9")
    private BigDecimal a8;
    @Column(name = "10")
    private BigDecimal a9;

    @Transient
    private List<BigDecimal> listaValores;


    @Transient
    private BigDecimal sum0;

    @Transient
    private BigDecimal sum1;

    @Transient
    private BigDecimal sum2;

    @Transient
    private BigDecimal sum3;

    @Transient
    private BigDecimal sum4;

    @Transient
    private BigDecimal sum5;

    @Transient
    private BigDecimal sum6;

    @Transient
    private BigDecimal sum7;

    @Transient
    private BigDecimal sum8;

    @Transient
    private BigDecimal sum9;




    public TablaCxcpivote() {
        this.listaValores= new ArrayList<>();
    }

    public TablaCxcpivote(Integer id) {

        this.listaValores= new ArrayList<>();
        this.id = id;
    }

    public TablaCxcpivote(Integer id, String codigoCliente, BigDecimal creditmax) {

        this.listaValores= new ArrayList<>();
        this.id = id;
        this.codigoCliente = codigoCliente;
        this.creditmax = creditmax;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public BigDecimal getCreditmax() {
        return creditmax;
    }

    public void setCreditmax(BigDecimal creditmax) {
        this.creditmax = creditmax;
    }

    public BigDecimal getA() {
        return a;
    }

    public void setA(BigDecimal a) {
        this.a = a;
    }

    public BigDecimal getA1() {
        return a1;
    }

    public void setA1(BigDecimal a1) {
        this.a1 = a1;
    }

    public BigDecimal getA2() {
        return a2;
    }

    public void setA2(BigDecimal a2) {
        this.a2 = a2;
    }

    public BigDecimal getA3() {
        return a3;
    }

    public void setA3(BigDecimal a3) {
        this.a3 = a3;
    }

    public BigDecimal getA4() {
        return a4;
    }

    public void setA4(BigDecimal a4) {
        this.a4 = a4;
    }

    public BigDecimal getA5() {
        return a5;
    }

    public void setA5(BigDecimal a5) {
        this.a5 = a5;
    }

    public BigDecimal getA6() {
        return a6;
    }

    public void setA6(BigDecimal a6) {
        this.a6 = a6;
    }

    public BigDecimal getA7() {
        return a7;
    }

    public void setA7(BigDecimal a7) {
        this.a7 = a7;
    }

    public BigDecimal getA8() {
        return a8;
    }

    public void setA8(BigDecimal a8) {
        this.a8 = a8;
    }

    public BigDecimal getA9() {
        return a9;
    }

    public void setA9(BigDecimal a9) {
        this.a9 = a9;
    }


    public BigDecimal getUtilizado() {
        return utilizado;
    }

    public void setUtilizado(BigDecimal utilizado) {
        this.utilizado = utilizado;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public BigDecimal getSum0() {
        return this.a;
    }

    public void setSum0(BigDecimal sum0) {
        this.sum0 = this.a;
    }

    public BigDecimal getSum1() {
   /*
        List<BigDecimal> listaSumar=new ArrayList<>();
        listaSumar.add(this.a);
        listaSumar.add(this.a1);


        this.sum1=listaSumar.stream()
                .filter(c -> c != null)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
                */

        return this.sum1;
    }

    public void setSum1(BigDecimal sum1) {
        this.sum1 = sum1;
    }

    public BigDecimal getSum2() {


        return this.sum2;
    }

    public void setSum2(BigDecimal sum2) {
        this.sum2 = sum2;
    }

    public BigDecimal getSum3() {

        return this.sum3;
    }

    public void setSum3(BigDecimal sum3) {
        this.sum3 = sum3;
    }

    public BigDecimal getSum4() {
        return this.sum4;
    }

    public void setSum4(BigDecimal sum4) {
        this.sum4 = sum4;
    }

    public BigDecimal getSum5() {
        return this.sum5;
    }

    public void setSum5(BigDecimal sum5) {
        this.sum5 = sum5;
    }

    public BigDecimal getSum6() {
        return this.sum6;
    }

    public void setSum6(BigDecimal sum6) {
        this.sum6 = sum6;
    }

    public BigDecimal getSum7() {
         return this.sum7;
    }

    public void setSum7(BigDecimal sum7) {
        this.sum7 = sum7;
    }

    public BigDecimal getSum8() {
        return this.sum8;
    }

    public void setSum8(BigDecimal sum8) {
        this.sum8 = sum8;
    }

    public BigDecimal getSum9() {
        return this.sum9;
    }

    public void setSum9(BigDecimal sum9) {
        this.sum9 = sum9;
    }

    public List<BigDecimal> getListaValores() {

        this.listaValores.add(a);
        this.listaValores.add(a1);
        this.listaValores.add(a2);
        this.listaValores.add(a3);
        this.listaValores.add(a4);
        this.listaValores.add(a5);
        this.listaValores.add(a6);
        this.listaValores.add(a7);
        this.listaValores.add(a8);
        this.listaValores.add(a9);

        return listaValores;
    }

    public void setListaValores(List<BigDecimal> listaValores) {
        this.listaValores = listaValores;
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
        if (!(object instanceof TablaCxcpivote)) {
            return false;
        }
        TablaCxcpivote other = (TablaCxcpivote) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.TablaCxcpivote[ id=" + id + " ]";
    }



}
