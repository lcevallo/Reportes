/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphacell.model.financiero;

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
@Table(name = "TMPPIVOTCXP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tmppivotcxp.findAll", query = "SELECT t FROM Tmppivotcxp t"),
    @NamedQuery(name = "Tmppivotcxp.findByAccountnum", query = "SELECT t FROM Tmppivotcxp t WHERE t.accountnum = :accountnum"),
    @NamedQuery(name = "Tmppivotcxp.findByVencida", query = "SELECT t FROM Tmppivotcxp t WHERE t.vencida = :vencida"),
    @NamedQuery(name = "Tmppivotcxp.findByPrimeraSemana", query = "SELECT t FROM Tmppivotcxp t WHERE t.primeraSemana = :primeraSemana"),
    @NamedQuery(name = "Tmppivotcxp.findBySegundaSemana", query = "SELECT t FROM Tmppivotcxp t WHERE t.segundaSemana = :segundaSemana"),
    @NamedQuery(name = "Tmppivotcxp.findByTerceraSemana", query = "SELECT t FROM Tmppivotcxp t WHERE t.terceraSemana = :terceraSemana"),
    @NamedQuery(name = "Tmppivotcxp.findByCuartaSemana", query = "SELECT t FROM Tmppivotcxp t WHERE t.cuartaSemana = :cuartaSemana"),
    @NamedQuery(name = "Tmppivotcxp.findByQuintaSemana", query = "SELECT t FROM Tmppivotcxp t WHERE t.quintaSemana = :quintaSemana"),
    @NamedQuery(name = "Tmppivotcxp.findBySeptimaSemana", query = "SELECT t FROM Tmppivotcxp t WHERE t.septimaSemana = :septimaSemana"),
    @NamedQuery(name = "Tmppivotcxp.findByOctavaSemana", query = "SELECT t FROM Tmppivotcxp t WHERE t.octavaSemana = :octavaSemana"),
    @NamedQuery(name = "Tmppivotcxp.findByNovenaSemana", query = "SELECT t FROM Tmppivotcxp t WHERE t.novenaSemana = :novenaSemana"),
    @NamedQuery(name = "Tmppivotcxp.findByDecimaSemana", query = "SELECT t FROM Tmppivotcxp t WHERE t.decimaSemana = :decimaSemana"),
    @NamedQuery(name = "Tmppivotcxp.findByOnceavaSemana", query = "SELECT t FROM Tmppivotcxp t WHERE t.onceavaSemana = :onceavaSemana"),
    @NamedQuery(name = "Tmppivotcxp.findByDoceavaSemana", query = "SELECT t FROM Tmppivotcxp t WHERE t.doceavaSemana = :doceavaSemana"),
    @NamedQuery(name = "Tmppivotcxp.findByNombreCliente", query = "SELECT t FROM Tmppivotcxp t WHERE t.nombreCliente = :nombreCliente"),
    @NamedQuery(name = "Tmppivotcxp.findByTipo", query = "SELECT t FROM Tmppivotcxp t WHERE t.tipo = :tipo"),
    @NamedQuery(name = "Tmppivotcxp.findById", query = "SELECT t FROM Tmppivotcxp t WHERE t.id = :id")})
public class Tmppivotcxp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 20)
    @Column(name = "ACCOUNTNUM")
    private String accountnum;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VENCIDA")
    private BigDecimal vencida;
    @Column(name = "Primera_Semana")
    private BigDecimal primeraSemana;
    @Column(name = "Segunda_Semana")
    private BigDecimal segundaSemana;
    @Column(name = "Tercera_Semana")
    private BigDecimal terceraSemana;
    @Column(name = "Cuarta_Semana")
    private BigDecimal cuartaSemana;
    @Column(name = "Quinta_Semana")
    private BigDecimal quintaSemana;
    @Column(name = "Sexta_Semana")
    private BigDecimal sextaSemana;
    @Column(name = "Septima_Semana")
    private BigDecimal septimaSemana;
    @Column(name = "Octava_Semana")
    private BigDecimal octavaSemana;
    @Column(name = "Novena_Semana")
    private BigDecimal novenaSemana;
    @Column(name = "Decima_Semana")
    private BigDecimal decimaSemana;
    @Column(name = "Onceava_Semana")
    private BigDecimal onceavaSemana;
    @Column(name = "Doceava_Semana")
    private BigDecimal doceavaSemana;
    @Size(max = 100)
    @Column(name = "NOMBRE_CLIENTE")
    private String nombreCliente;
    @Size(max = 10)
    @Column(name = "TIPO")
    private String tipo;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;

    public Tmppivotcxp() {
    }

    public Tmppivotcxp(Integer id) {
        this.id = id;
    }

    public String getAccountnum() {
        return accountnum;
    }

    public void setAccountnum(String accountnum) {
        this.accountnum = accountnum;
    }

    public BigDecimal getVencida() {
        return vencida;
    }

    public void setVencida(BigDecimal vencida) {
        this.vencida = vencida;
    }

    public BigDecimal getPrimeraSemana() {
        return primeraSemana;
    }

    public void setPrimeraSemana(BigDecimal primeraSemana) {
        this.primeraSemana = primeraSemana;
    }

    public BigDecimal getSegundaSemana() {
        return segundaSemana;
    }

    public void setSegundaSemana(BigDecimal segundaSemana) {
        this.segundaSemana = segundaSemana;
    }

    public BigDecimal getTerceraSemana() {
        return terceraSemana;
    }

    public void setTerceraSemana(BigDecimal terceraSemana) {
        this.terceraSemana = terceraSemana;
    }

    public BigDecimal getCuartaSemana() {
        return cuartaSemana;
    }

    public void setCuartaSemana(BigDecimal cuartaSemana) {
        this.cuartaSemana = cuartaSemana;
    }

    public BigDecimal getQuintaSemana() {
        return quintaSemana;
    }

    public void setQuintaSemana(BigDecimal quintaSemana) {
        this.quintaSemana = quintaSemana;
    }

    public BigDecimal getSeptimaSemana() {
        return septimaSemana;
    }

    public void setSeptimaSemana(BigDecimal septimaSemana) {
        this.septimaSemana = septimaSemana;
    }

    public BigDecimal getOctavaSemana() {
        return octavaSemana;
    }

    public void setOctavaSemana(BigDecimal octavaSemana) {
        this.octavaSemana = octavaSemana;
    }

    public BigDecimal getNovenaSemana() {
        return novenaSemana;
    }

    public void setNovenaSemana(BigDecimal novenaSemana) {
        this.novenaSemana = novenaSemana;
    }

    public BigDecimal getDecimaSemana() {
        return decimaSemana;
    }

    public void setDecimaSemana(BigDecimal decimaSemana) {
        this.decimaSemana = decimaSemana;
    }

    public BigDecimal getOnceavaSemana() {
        return onceavaSemana;
    }

    public void setOnceavaSemana(BigDecimal onceavaSemana) {
        this.onceavaSemana = onceavaSemana;
    }

    public BigDecimal getDoceavaSemana() {
        return doceavaSemana;
    }

    public void setDoceavaSemana(BigDecimal doceavaSemana) {
        this.doceavaSemana = doceavaSemana;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        if (!(object instanceof Tmppivotcxp)) {
            return false;
        }
        Tmppivotcxp other = (Tmppivotcxp) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.alphacell.model.financiero.Tmppivotcxp[ id=" + id + " ]";
    }

    public BigDecimal getSextaSemana() {
        return sextaSemana;
    }

    public void setSextaSemana(BigDecimal sextaSemana) {
        this.sextaSemana = sextaSemana;
    }
}
