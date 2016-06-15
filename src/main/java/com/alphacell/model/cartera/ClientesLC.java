package com.alphacell.model.cartera;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * Created by luis on 15/06/16.
 */
public class ClientesLC {
    private String name;
    private String accountNum;
    private List<Tmpcxcsaldosiniciales> tmpcxcsaldosinicialesList;
    private BigDecimal sumatoriaDeuda;
    private BigDecimal sumatoriaPagado;


    public ClientesLC(String accountNum, List<Tmpcxcsaldosiniciales> tmpcxcsaldosinicialesList) {
        this.accountNum = accountNum;
        this.tmpcxcsaldosinicialesList = tmpcxcsaldosinicialesList;
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

    public List<Tmpcxcsaldosiniciales> getTmpcxcsaldosinicialesList() {
        return tmpcxcsaldosinicialesList;
    }

    public void setTmpcxcsaldosinicialesList(List<Tmpcxcsaldosiniciales> tmpcxcsaldosinicialesList) {
        this.tmpcxcsaldosinicialesList = tmpcxcsaldosinicialesList;
    }

    public BigDecimal getSumatoriaDeuda() {

        this.sumatoriaDeuda= this.tmpcxcsaldosinicialesList
                .stream()
                .filter(Objects::nonNull)
                .filter(c->c.getAmountcur()!=null)
                .map(Tmpcxcsaldosiniciales::getAmountcur)
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        return sumatoriaDeuda;
    }

    public void setSumatoriaDeuda(BigDecimal sumatoriaDeuda) {
        this.sumatoriaDeuda = sumatoriaDeuda;
    }

    public BigDecimal getSumatoriaPagado() {

        this.sumatoriaPagado= this.tmpcxcsaldosinicialesList
                .stream()
                .filter(Objects::nonNull)
                .filter(c->c.getValorpagado()!=null)
                .map(Tmpcxcsaldosiniciales::getValorpagado)
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        return sumatoriaPagado;
    }

    public void setSumatoriaPagado(BigDecimal sumatoriaPagado) {
        this.sumatoriaPagado = sumatoriaPagado;
    }
}
