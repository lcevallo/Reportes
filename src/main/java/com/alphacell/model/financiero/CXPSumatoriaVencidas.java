package com.alphacell.model.financiero;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by luis on 22/09/16.
 */
public class CXPSumatoriaVencidas {

    private String accountnum;
    private String nombreCliente;
    private List<TmpCxpFlujoVencidos> base;
    private BigDecimal sum15dias;
    private BigDecimal sum1530dias;
    private BigDecimal sum3060dias;
    private BigDecimal sum6090dias;
    private BigDecimal sum90120dias;
    private BigDecimal sum120dias;
    private BigDecimal sumPorVencer;
    private BigDecimal Total;


    public CXPSumatoriaVencidas(String accountnum, List<TmpCxpFlujoVencidos> base) {

        List<BigDecimal> listSumas= new ArrayList<BigDecimal>();
        this.nombreCliente=(base.isEmpty()?null:base.get(0).getNombreCliente());
        this.base = base;
        this.accountnum = accountnum;

        this.sum15dias=this.SumarxFechas(this.base,"15");
        this.sum1530dias=this.SumarxFechas(this.base,"15-30");
        this.sum3060dias=this.SumarxFechas(this.base,"30-60");
        this.sum6090dias=this.SumarxFechas(this.base,"60-90");
        this.sum90120dias=this.SumarxFechas(this.base,"90-120");
        this.sum120dias=this.SumarxFechas(this.base,"120");
        this.sumPorVencer=this.SumarxFechas(this.base,"xVencer");
        listSumas.add(this.sum15dias);
        listSumas.add(this.sum1530dias);
        listSumas.add(this.sum3060dias);
        listSumas.add(this.sum6090dias);
        listSumas.add(this.sum90120dias);
        listSumas.add(this.sum120dias);
        listSumas.add(this.sumPorVencer);

        this.Total=listSumas.stream()
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);



    }

    public BigDecimal SumarxFechas(List<TmpCxpFlujoVencidos> listaCxpFlujoVencido,String intervalo)
    {
        BigDecimal invoiceValue=null;

        switch (intervalo)
        {
            case "15":
                invoiceValue = listaCxpFlujoVencido.stream().filter(Objects::nonNull).reduce(BigDecimal.ZERO, (bd, item) -> bd.add(item.getDias15()), BigDecimal::add);
                break;
            case "15-30":
                invoiceValue = listaCxpFlujoVencido.stream().filter(Objects::nonNull).reduce(BigDecimal.ZERO, (bd, item) -> bd.add(item.getDias1530()), BigDecimal::add);
                break;
            case "30-60":
                invoiceValue = listaCxpFlujoVencido.stream().filter(Objects::nonNull).reduce(BigDecimal.ZERO, (bd, item) -> bd.add(item.getDias3060()), BigDecimal::add);
                break;
            case "60-90":
                invoiceValue = listaCxpFlujoVencido.stream().filter(Objects::nonNull).reduce(BigDecimal.ZERO, (bd, item) -> bd.add(item.getDias6090()), BigDecimal::add);
                break;
            case "90-120":
                invoiceValue = listaCxpFlujoVencido.stream().filter(Objects::nonNull).reduce(BigDecimal.ZERO, (bd, item) -> bd.add(item.getDias90120()), BigDecimal::add);
                break;
            case "120":
                invoiceValue = listaCxpFlujoVencido.stream().filter(Objects::nonNull).reduce(BigDecimal.ZERO, (bd, item) -> bd.add(item.getDias120()), BigDecimal::add);
                break;
            case "xVencer":
                invoiceValue = listaCxpFlujoVencido.stream().filter(Objects::nonNull).reduce(BigDecimal.ZERO, (bd, item) -> bd.add(item.getxVencer()), BigDecimal::add);
                break;

        }

        return invoiceValue;
    }

    public String getAccountnum() {
        return accountnum;
    }

    public void setAccountnum(String accountnum) {
        this.accountnum = accountnum;
    }

    public List<TmpCxpFlujoVencidos> getBase() {
        return base;
    }

    public void setBase(List<TmpCxpFlujoVencidos> base) {
        this.base = base;
    }

    public BigDecimal getSum15dias() {
        return sum15dias;
    }

    public void setSum15dias(BigDecimal sum15dias) {
        this.sum15dias = sum15dias;
    }

    public BigDecimal getSum1530dias() {
        return sum1530dias;
    }

    public void setSum1530dias(BigDecimal sum1530dias) {
        this.sum1530dias = sum1530dias;
    }

    public BigDecimal getSum3060dias() {
        return sum3060dias;
    }

    public void setSum3060dias(BigDecimal sum3060dias) {
        this.sum3060dias = sum3060dias;
    }

    public BigDecimal getSum6090dias() {
        return sum6090dias;
    }

    public void setSum6090dias(BigDecimal sum6090dias) {
        this.sum6090dias = sum6090dias;
    }

    public BigDecimal getSum90120dias() {
        return sum90120dias;
    }

    public void setSum90120dias(BigDecimal sum90120dias) {
        this.sum90120dias = sum90120dias;
    }

    public BigDecimal getSum120dias() {
        return sum120dias;
    }

    public void setSum120dias(BigDecimal sum120dias) {
        this.sum120dias = sum120dias;
    }

    public BigDecimal getSumPorVencer() {
        return sumPorVencer;
    }

    public void setSumPorVencer(BigDecimal sumPorVencer) {
        this.sumPorVencer = sumPorVencer;
    }

    public BigDecimal getTotal() {
        return Total;
    }

    public void setTotal(BigDecimal total) {
        Total = total;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
}
