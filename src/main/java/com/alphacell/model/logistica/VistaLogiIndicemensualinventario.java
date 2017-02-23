package com.alphacell.model.logistica;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created by luis.cevallos on 13/2/2017.
 */
@Entity
@Table(name = "VISTA_LOGI_INDICEMENSUALINVENTARIO", schema = "dbo", catalog = "Produccion")

@NamedQuery(
        name="VistaLogiIndicemensualinventario.itemsDistintos",
        query = "SELECT  DISTINCT v.itemid,v.name from VistaLogiIndicemensualinventario v"
)
@NamedQueries({
        @NamedQuery(name = "VistaLogiIndicemensualinventario.findAll",query = "from VistaLogiIndicemensualinventario")
})

public class VistaLogiIndicemensualinventario implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long rownumber;
    private String itemid;
    private String inventlocationid;
    private BigDecimal sumaCantidad;
    private BigDecimal reservphysical;
    private String name;
    private BigDecimal physicalOnhand;
    private BigDecimal availPhysicalCalculated;
    private BigDecimal totalDisponible;

    private String color;

    @Basic
    @Id
    @Column(name = "rownumber", nullable = true)
    public Long getRownumber() {
        return rownumber;
    }

    public void setRownumber(Long rownumber) {
        this.rownumber = rownumber;
    }

    @Basic
    @Column(name = "ITEMID", nullable = false, length = 20)
    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    @Basic
    @Column(name = "INVENTLOCATIONID", nullable = true, length = 10)
    public String getInventlocationid() {
        return inventlocationid;
    }

    public void setInventlocationid(String inventlocationid) {
        this.inventlocationid = inventlocationid;
    }

    @Basic
    @Column(name = "suma_cantidad", nullable = true, precision = 16)
    public BigDecimal getSumaCantidad() {
        return sumaCantidad;
    }

    public void setSumaCantidad(BigDecimal sumaCantidad) {
        this.sumaCantidad = sumaCantidad;
    }

    @Basic
    @Column(name = "RESERVPHYSICAL", nullable = false, precision = 16)
    public BigDecimal getReservphysical() {
        return reservphysical;
    }

    public void setReservphysical(BigDecimal reservphysical) {
        this.reservphysical = reservphysical;
    }

    @Basic
    @Column(name = "NAME", nullable = false, length = 60)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Transient
    public Integer getDiaMes()
    {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.DAY_OF_MONTH);
    }
    @Transient
    public Integer getDiasDelMes()
    {
        Calendar c = Calendar.getInstance();
        return c.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    @Transient
    public String getColor() {
        return color;
    }

    public void setColor() {
       Double g=getIndice();

        if(g<= 2.0){
            this.color="colored-red";
        }
        if(g >= 3.0 && g<=10.0)
        {
            this.color= "colored-orange";
        }
    }

    @Transient
    public Double getIndice() {
        Calendar c = Calendar.getInstance();
        Integer monthMaxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        Integer dayOfMonth = c.get(Calendar.DAY_OF_MONTH);

       Double salida= (this.totalDisponible.doubleValue()/dayOfMonth)*monthMaxDays;
       return salida;
    }

    @Basic
    @Column(name = "physicalOnhand", nullable = true, precision = 16)
    public BigDecimal getPhysicalOnhand() {
        return physicalOnhand;
    }

    public void setPhysicalOnhand(BigDecimal physicalOnhand) {
        this.physicalOnhand = physicalOnhand;
    }

    @Basic
    @Column(name = "availPhysicalCalculated", nullable = true, precision = 16)
    public BigDecimal getAvailPhysicalCalculated() {
        return availPhysicalCalculated;
    }

    public void setAvailPhysicalCalculated(BigDecimal availPhysicalCalculated) {
        this.availPhysicalCalculated = availPhysicalCalculated;
    }

    @Basic
    @Column(name = "totalDisponible", nullable = true, precision = 16)
    public BigDecimal getTotalDisponible() {
        return totalDisponible;
    }

    public void setTotalDisponible(BigDecimal totalDisponible) {
        this.totalDisponible = totalDisponible;
        this.setColor();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VistaLogiIndicemensualinventario that = (VistaLogiIndicemensualinventario) o;

        if (rownumber != null ? !rownumber.equals(that.rownumber) : that.rownumber != null) return false;
        if (itemid != null ? !itemid.equals(that.itemid) : that.itemid != null) return false;
        if (inventlocationid != null ? !inventlocationid.equals(that.inventlocationid) : that.inventlocationid != null)
            return false;
        if (sumaCantidad != null ? !sumaCantidad.equals(that.sumaCantidad) : that.sumaCantidad != null) return false;
        if (reservphysical != null ? !reservphysical.equals(that.reservphysical) : that.reservphysical != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (physicalOnhand != null ? !physicalOnhand.equals(that.physicalOnhand) : that.physicalOnhand != null)
            return false;
        if (availPhysicalCalculated != null ? !availPhysicalCalculated.equals(that.availPhysicalCalculated) : that.availPhysicalCalculated != null)
            return false;
        if (totalDisponible != null ? !totalDisponible.equals(that.totalDisponible) : that.totalDisponible != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rownumber != null ? rownumber.hashCode() : 0;
        result = 31 * result + (itemid != null ? itemid.hashCode() : 0);
        result = 31 * result + (inventlocationid != null ? inventlocationid.hashCode() : 0);
        result = 31 * result + (sumaCantidad != null ? sumaCantidad.hashCode() : 0);
        result = 31 * result + (reservphysical != null ? reservphysical.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (physicalOnhand != null ? physicalOnhand.hashCode() : 0);
        result = 31 * result + (availPhysicalCalculated != null ? availPhysicalCalculated.hashCode() : 0);
        result = 31 * result + (totalDisponible != null ? totalDisponible.hashCode() : 0);
        return result;
    }
}
