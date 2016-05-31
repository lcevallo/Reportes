package com.alphacell.model.cartera;

/**
 * Created by luis.cevallos on 6/4/2016.
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
@Table(name = "TMP_CARTERA_TOTAL_PVT")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "TmpCarteraTotalPvt.findAll", query = "SELECT t FROM TmpCarteraTotalPvt t"),
        @NamedQuery(name = "TmpCarteraTotalPvt.findById", query = "SELECT t FROM TmpCarteraTotalPvt t WHERE t.id = :id"),
        @NamedQuery(name = "TmpCarteraTotalPvt.findByAccountnum", query = "SELECT t FROM TmpCarteraTotalPvt t WHERE t.accountnum = :accountnum"),
        @NamedQuery(name = "TmpCarteraTotalPvt.findByNombreCliente", query = "SELECT t FROM TmpCarteraTotalPvt t WHERE t.nombreCliente = :nombreCliente"),
        @NamedQuery(name = "TmpCarteraTotalPvt.findByYaVencidos", query = "SELECT t FROM TmpCarteraTotalPvt t WHERE t.yaVencidos = :yaVencidos"),
        @NamedQuery(name = "TmpCarteraTotalPvt.findByDias", query = "SELECT t FROM TmpCarteraTotalPvt t WHERE t.dias = :dias"),
        @NamedQuery(name = "TmpCarteraTotalPvt.findByDias1", query = "SELECT t FROM TmpCarteraTotalPvt t WHERE t.dias1 = :dias1"),
        @NamedQuery(name = "TmpCarteraTotalPvt.findByDias2", query = "SELECT t FROM TmpCarteraTotalPvt t WHERE t.dias2 = :dias2"),
        @NamedQuery(name = "TmpCarteraTotalPvt.findByDias3", query = "SELECT t FROM TmpCarteraTotalPvt t WHERE t.dias3 = :dias3"),
        @NamedQuery(name = "TmpCarteraTotalPvt.findByDias4", query = "SELECT t FROM TmpCarteraTotalPvt t WHERE t.dias4 = :dias4"),
        @NamedQuery(name = "TmpCarteraTotalPvt.findByDias5", query = "SELECT t FROM TmpCarteraTotalPvt t WHERE t.dias5 = :dias5"),
        @NamedQuery(name = "TmpCarteraTotalPvt.findByMayoresa120dias", query = "SELECT t FROM TmpCarteraTotalPvt t WHERE t.mayoresa120dias = :mayoresa120dias")})
public class TmpCarteraTotalPvt implements Serializable {

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
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "YA VENCIDOS")
    private BigDecimal yaVencidos;
    @Column(name = "15 dias")
    private BigDecimal dias;
    @Column(name = "30 dias")
    private BigDecimal dias1;
    @Column(name = "45 dias")
    private BigDecimal dias2;
    @Column(name = "60 dias")
    private BigDecimal dias3;
    @Column(name = "90 dias")
    private BigDecimal dias4;
    @Column(name = "120 dias")
    private BigDecimal dias5;
    @Column(name = "Mayores a 120 dias")
    private BigDecimal mayoresa120dias;

    public TmpCarteraTotalPvt() {
    }

    public TmpCarteraTotalPvt(Integer id) {
        this.id = id;
    }

    public TmpCarteraTotalPvt(Integer id, String accountnum) {
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

    public BigDecimal getYaVencidos() {
        return yaVencidos;
    }

    public void setYaVencidos(BigDecimal yaVencidos) {
        this.yaVencidos = yaVencidos;
    }

    public BigDecimal getDias() {
        return dias;
    }

    public void setDias(BigDecimal dias) {
        this.dias = dias;
    }

    public BigDecimal getDias1() {
        return dias1;
    }

    public void setDias1(BigDecimal dias1) {
        this.dias1 = dias1;
    }

    public BigDecimal getDias2() {
        return dias2;
    }

    public void setDias2(BigDecimal dias2) {
        this.dias2 = dias2;
    }

    public BigDecimal getDias3() {
        return dias3;
    }

    public void setDias3(BigDecimal dias3) {
        this.dias3 = dias3;
    }

    public BigDecimal getDias4() {
        return dias4;
    }

    public void setDias4(BigDecimal dias4) {
        this.dias4 = dias4;
    }

    public BigDecimal getDias5() {
        return dias5;
    }

    public void setDias5(BigDecimal dias5) {
        this.dias5 = dias5;
    }

    public BigDecimal getMayoresa120dias() {
        return mayoresa120dias;
    }

    public void setMayoresa120dias(BigDecimal mayoresa120dias) {
        this.mayoresa120dias = mayoresa120dias;
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
        if (!(object instanceof TmpCarteraTotalPvt)) {
            return false;
        }
        TmpCarteraTotalPvt other = (TmpCarteraTotalPvt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.TmpCarteraTotalPvt[ id=" + id + " ]";
    }

}
