package com.alphacell.model.cartera;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by luis on 26/07/16.
 */
public class TramosVencidosLC {
    private String name;
    private String accountNum;
    private BigDecimal sumatoria15dias;
    private BigDecimal sumatoria30dias;
    private BigDecimal sumatoria45dias;
    private BigDecimal sumatoria60dias;
    private BigDecimal sumatoria90dias;
    private BigDecimal sumatoria120dias;
    private BigDecimal sumatoriaM120dias;
    private BigDecimal sumatoriaTotal;

    private List<LcTramosYaVencidosPivot> listaLcTramosYaVencidosPivotList;


    public TramosVencidosLC(String accountNum,List<LcTramosYaVencidosPivot> listaLcTramosYaVencidosPivotList) {
        this.accountNum = accountNum;
        this.name=listaLcTramosYaVencidosPivotList.get(0).getNombreCliente();
        this.listaLcTramosYaVencidosPivotList = listaLcTramosYaVencidosPivotList;

        this.sumatoria15dias= this.listaLcTramosYaVencidosPivotList
                .stream()
                .filter(Objects::nonNull)
                .filter(c->c.getDias15B()!=null)
                .map(LcTramosYaVencidosPivot::getDias15B)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.sumatoria30dias= this.listaLcTramosYaVencidosPivotList
                .stream()
                .filter(Objects::nonNull)
                .filter(c->c.getDias30B()!=null)
                .map(LcTramosYaVencidosPivot::getDias30B)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.sumatoria45dias= this.listaLcTramosYaVencidosPivotList
                .stream()
                .filter(Objects::nonNull)
                .filter(c->c.getDias45B()!=null)
                .map(LcTramosYaVencidosPivot::getDias45B)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.sumatoria60dias= this.listaLcTramosYaVencidosPivotList
                .stream()
                .filter(Objects::nonNull)
                .filter(c->c.getDias60B()!=null)
                .map(LcTramosYaVencidosPivot::getDias60B)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.sumatoria90dias= this.listaLcTramosYaVencidosPivotList
                .stream()
                .filter(Objects::nonNull)
                .filter(c->c.getDias90B()!=null)
                .map(LcTramosYaVencidosPivot::getDias90B)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.sumatoria120dias= this.listaLcTramosYaVencidosPivotList
                .stream()
                .filter(Objects::nonNull)
                .filter(c->c.getDias120B()!=null)
                .map(LcTramosYaVencidosPivot::getDias120B)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.sumatoriaM120dias= this.listaLcTramosYaVencidosPivotList
                .stream()
                .filter(Objects::nonNull)
                .filter(c->c.getMasDe120DiasB()!=null)
                .map(LcTramosYaVencidosPivot::getMasDe120DiasB)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        List<BigDecimal> bigDecimalValues= new ArrayList<>();
        bigDecimalValues.add(this.sumatoria15dias);
        bigDecimalValues.add(this.sumatoria30dias);
        bigDecimalValues.add(this.sumatoria45dias);
        bigDecimalValues.add(this.sumatoria60dias);
        bigDecimalValues.add(this.sumatoria90dias);
        bigDecimalValues.add(this.sumatoria120dias);
        bigDecimalValues.add(this.sumatoriaM120dias);


        this.sumatoriaTotal=bigDecimalValues.stream().reduce(
                BigDecimal.ZERO, BigDecimal::add);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public List<LcTramosYaVencidosPivot> getListaLcTramosYaVencidosPivotList() {
        return listaLcTramosYaVencidosPivotList;
    }

    public void setListaLcTramosYaVencidosPivotList(List<LcTramosYaVencidosPivot> listaLcTramosYaVencidosPivotList) {
        this.listaLcTramosYaVencidosPivotList = listaLcTramosYaVencidosPivotList;
    }

    public BigDecimal getSumatoria15dias() {
        return sumatoria15dias;
    }

    public void setSumatoria15dias(BigDecimal sumatoria15dias) {


        this.sumatoria15dias = sumatoria15dias;
    }

    public BigDecimal getSumatoria30dias() {
        return sumatoria30dias;
    }

    public void setSumatoria30dias(BigDecimal sumatoria30dias) {
        this.sumatoria30dias = sumatoria30dias;
    }

    public BigDecimal getSumatoria45dias() {
        return sumatoria45dias;
    }

    public void setSumatoria45dias(BigDecimal sumatoria45dias) {
        this.sumatoria45dias = sumatoria45dias;
    }

    public BigDecimal getSumatoria60dias() {
        return sumatoria60dias;
    }

    public void setSumatoria60dias(BigDecimal sumatoria60dias) {
        this.sumatoria60dias = sumatoria60dias;
    }

    public BigDecimal getSumatoria90dias() {
        return sumatoria90dias;
    }

    public void setSumatoria90dias(BigDecimal sumatoria90dias) {
        this.sumatoria90dias = sumatoria90dias;
    }

    public BigDecimal getSumatoria120dias() {
        return sumatoria120dias;
    }

    public void setSumatoria120dias(BigDecimal sumatoria120dias) {
        this.sumatoria120dias = sumatoria120dias;
    }

    public BigDecimal getSumatoriaM120dias() {
        return sumatoriaM120dias;
    }

    public void setSumatoriaM120dias(BigDecimal sumatoriaM120dias) {
        this.sumatoriaM120dias = sumatoriaM120dias;
    }

    public BigDecimal getSumatoriaTotal() {
        return sumatoriaTotal;
    }

    public void setSumatoriaTotal(BigDecimal sumatoriaTotal) {
        this.sumatoriaTotal = sumatoriaTotal;
    }
}
