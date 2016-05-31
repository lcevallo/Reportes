package com.alphacell.model.cartera;

/**
 * Created by luis.cevallos on 4/4/2016.
 */
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
@Table(name = "TMP_PVT_HISTORIAL_PROTESTO_AFTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TmpPvtHistorialProtestoAfter.findAll", query = "SELECT t FROM TmpPvtHistorialProtestoAfter t"),
    @NamedQuery(name = "TmpPvtHistorialProtestoAfter.findById", query = "SELECT t FROM TmpPvtHistorialProtestoAfter t WHERE t.id = :id"),
    @NamedQuery(name = "TmpPvtHistorialProtestoAfter.findByAccountnum", query = "SELECT t FROM TmpPvtHistorialProtestoAfter t WHERE t.accountnum = :accountnum"),
    @NamedQuery(name = "TmpPvtHistorialProtestoAfter.findByNombreCliente", query = "SELECT t FROM TmpPvtHistorialProtestoAfter t WHERE t.nombreCliente = :nombreCliente"),
    @NamedQuery(name = "TmpPvtHistorialProtestoAfter.findByA", query = "SELECT t FROM TmpPvtHistorialProtestoAfter t WHERE t.a = :a"),
    @NamedQuery(name = "TmpPvtHistorialProtestoAfter.findByA1", query = "SELECT t FROM TmpPvtHistorialProtestoAfter t WHERE t.a1 = :a1"),
    @NamedQuery(name = "TmpPvtHistorialProtestoAfter.findByA2", query = "SELECT t FROM TmpPvtHistorialProtestoAfter t WHERE t.a2 = :a2"),
    @NamedQuery(name = "TmpPvtHistorialProtestoAfter.findByA3", query = "SELECT t FROM TmpPvtHistorialProtestoAfter t WHERE t.a3 = :a3"),
    @NamedQuery(name = "TmpPvtHistorialProtestoAfter.findByA4", query = "SELECT t FROM TmpPvtHistorialProtestoAfter t WHERE t.a4 = :a4"),
    @NamedQuery(name = "TmpPvtHistorialProtestoAfter.findByA5", query = "SELECT t FROM TmpPvtHistorialProtestoAfter t WHERE t.a5 = :a5"),
    @NamedQuery(name = "TmpPvtHistorialProtestoAfter.findByA6", query = "SELECT t FROM TmpPvtHistorialProtestoAfter t WHERE t.a6 = :a6"),
    @NamedQuery(name = "TmpPvtHistorialProtestoAfter.findByA7", query = "SELECT t FROM TmpPvtHistorialProtestoAfter t WHERE t.a7 = :a7"),
    @NamedQuery(name = "TmpPvtHistorialProtestoAfter.findByA8", query = "SELECT t FROM TmpPvtHistorialProtestoAfter t WHERE t.a8 = :a8"),
    @NamedQuery(name = "TmpPvtHistorialProtestoAfter.findByA9", query = "SELECT t FROM TmpPvtHistorialProtestoAfter t WHERE t.a9 = :a9"),
    @NamedQuery(name = "TmpPvtHistorialProtestoAfter.findByA10", query = "SELECT t FROM TmpPvtHistorialProtestoAfter t WHERE t.a10 = :a10"),
    @NamedQuery(name = "TmpPvtHistorialProtestoAfter.findByA11", query = "SELECT t FROM TmpPvtHistorialProtestoAfter t WHERE t.a11 = :a11")})
public class TmpPvtHistorialProtestoAfter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ACCOUNTNUM")
    private String accountnum;
    @Size(max = 100)
    @Column(name = "NOMBRE_CLIENTE")
    private String nombreCliente;
    @Column(name = "1")
    private Integer a;
    @Column(name = "2")
    private Integer a1;
    @Column(name = "3")
    private Integer a2;
    @Column(name = "4")
    private Integer a3;
    @Column(name = "5")
    private Integer a4;
    @Column(name = "6")
    private Integer a5;
    @Column(name = "7")
    private Integer a6;
    @Column(name = "8")
    private Integer a7;
    @Column(name = "9")
    private Integer a8;
    @Column(name = "10")
    private Integer a9;
    @Column(name = "11")
    private Integer a10;
    @Column(name = "12")
    private Integer a11;

    public TmpPvtHistorialProtestoAfter() {
    }

    public TmpPvtHistorialProtestoAfter(Integer id) {
        this.id = id;
    }

    public TmpPvtHistorialProtestoAfter(Integer id, String accountnum) {
        this.id = id;
        this.accountnum = accountnum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Integer getA() {
        return a;
    }

    public void setA(Integer a) {
        this.a = a;
    }

    public Integer getA1() {
        return a1;
    }

    public void setA1(Integer a1) {
        this.a1 = a1;
    }

    public Integer getA2() {
        return a2;
    }

    public void setA2(Integer a2) {
        this.a2 = a2;
    }

    public Integer getA3() {
        return a3;
    }

    public void setA3(Integer a3) {
        this.a3 = a3;
    }

    public Integer getA4() {
        return a4;
    }

    public void setA4(Integer a4) {
        this.a4 = a4;
    }

    public Integer getA5() {
        return a5;
    }

    public void setA5(Integer a5) {
        this.a5 = a5;
    }

    public Integer getA6() {
        return a6;
    }

    public void setA6(Integer a6) {
        this.a6 = a6;
    }

    public Integer getA7() {
        return a7;
    }

    public void setA7(Integer a7) {
        this.a7 = a7;
    }

    public Integer getA8() {
        return a8;
    }

    public void setA8(Integer a8) {
        this.a8 = a8;
    }

    public Integer getA9() {
        return a9;
    }

    public void setA9(Integer a9) {
        this.a9 = a9;
    }

    public Integer getA10() {
        return a10;
    }

    public void setA10(Integer a10) {
        this.a10 = a10;
    }

    public Integer getA11() {
        return a11;
    }

    public void setA11(Integer a11) {
        this.a11 = a11;
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
        if (!(object instanceof TmpPvtHistorialProtestoAfter)) {
            return false;
        }
        TmpPvtHistorialProtestoAfter other = (TmpPvtHistorialProtestoAfter) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.TmpPvtHistorialProtestoAfter[ id=" + id + " ]";
    }

}
