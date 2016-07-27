package com.alphacell.model.cartera;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Created by luis on 19/07/16.
 */
public class TramosVencidosLC_bu {

    private String name;
    private String accountNum;
    private LcVistaTramosYaVencidosPivot recordTablaPivoteada;


    private Map<String,BigDecimal> _15Dias=new HashMap<String,BigDecimal>();
    private Map<String,Double> _15DiasP=new HashMap<String,Double>();
    private Map<String,BigDecimal> _30Dias=new HashMap<String,BigDecimal>();
    private Map<String,BigDecimal> _45Dias=new HashMap<String,BigDecimal>();
    private Map<String,BigDecimal> _60Dias=new HashMap<String,BigDecimal>();
    private Map<String,BigDecimal> _90Dias=new HashMap<String,BigDecimal>();
    private Map<String,BigDecimal> _120Dias=new HashMap<String,BigDecimal>();
    private Map<String,BigDecimal> M_120Dias=new HashMap<String,BigDecimal>();
    private Map<String,BigDecimal> sumatorias=new HashMap<String,BigDecimal>();





    public TramosVencidosLC_bu(String accountNum,LcVistaTramosYaVencidosPivot tablapivoteada) {
        this.recordTablaPivoteada = tablapivoteada;
        this.accountNum=this.recordTablaPivoteada.getAccountnum();
        this.name=accountNum;
        String[] ColNanme={"15 dias","30 dias","45 dias","60 dias","90 dias","120 dias","Mayor a 120 dias"};

    try {

      if (this.recordTablaPivoteada.getDias15()!=null)
        this._15Dias= Stream.of(this.recordTablaPivoteada.getDias15()).map(w->w.split(",")).flatMap(Arrays::stream).collect(Collectors.toList())
                .stream()
                .collect(
                        Collectors.toMap(x -> {
                            int posicion = x.lastIndexOf("||");
                            return x.substring(0,posicion);
                        }, x->{

                            int posicion = x.lastIndexOf("||");
                            return  new BigDecimal(Double.parseDouble(x.substring(posicion+2,x.length())));
                        })
                );

        if (this.recordTablaPivoteada.getDias30()!=null)
        this._30Dias= Stream.of(this.recordTablaPivoteada.getDias30()).map(w->w.split(",")).flatMap(Arrays::stream).collect(Collectors.toList())
                .stream()
                .collect(
                        Collectors.toMap(x -> {
                            int posicion = x.lastIndexOf("||");
                            return x.substring(0,posicion);
                        }, x->{

                            int posicion = x.lastIndexOf("||");
                            return  new BigDecimal(Double.parseDouble(x.substring(posicion+2,x.length())));
                        })
                );

        if (this.recordTablaPivoteada.getDias45()!=null)
        this._45Dias= Stream.of(this.recordTablaPivoteada.getDias45()).map(w->w.split(",")).flatMap(Arrays::stream).collect(Collectors.toList())
                .stream()
                .collect(
                        Collectors.toMap(x -> {
                            int posicion = x.lastIndexOf("||");
                            return x.substring(0,posicion);
                        }, x->{

                            int posicion = x.lastIndexOf("||");
                            return  new BigDecimal(Double.parseDouble(x.substring(posicion+2,x.length())));
                        })
                );

        if (this.recordTablaPivoteada.getDias60()!=null)
        this._60Dias= Stream.of(this.recordTablaPivoteada.getDias60()).map(w->w.split(",")).flatMap(Arrays::stream).collect(Collectors.toList())
                .stream()
                .collect(
                        Collectors.toMap(x -> {
                            int posicion = x.lastIndexOf("||");
                            return x.substring(0,posicion);
                        }, x->{

                            int posicion = x.lastIndexOf("||");
                            return  new BigDecimal(Double.parseDouble(x.substring(posicion+2,x.length())));
                        })
                );

        if (this.recordTablaPivoteada.getDias90()!=null)
        this._90Dias= Stream.of(this.recordTablaPivoteada.getDias90()).map(w->w.split(",")).flatMap(Arrays::stream).collect(Collectors.toList())
                .stream()
                .collect(
                        Collectors.toMap(x -> {
                            int posicion = x.lastIndexOf("||");
                            return x.substring(0,posicion);
                        }, x->{

                            int posicion = x.lastIndexOf("||");
                            return  new BigDecimal(Double.parseDouble(x.substring(posicion+2,x.length())));
                        })
                );

        if (this.recordTablaPivoteada.getDias120()!=null)
        this._120Dias= Stream.of(this.recordTablaPivoteada.getDias120()).map(w->w.split(",")).flatMap(Arrays::stream).collect(Collectors.toList())
                .stream()
                .collect(
                        Collectors.toMap(x -> {
                            int posicion = x.lastIndexOf("||");
                            return x.substring(0,posicion);
                        }, x->{

                            int posicion = x.lastIndexOf("||");
                            return  new BigDecimal(Double.parseDouble(x.substring(posicion+2,x.length())));
                        })
                );

        if (this.recordTablaPivoteada.getMayoresDe120Dias()!=null)
        this.M_120Dias= Stream.of(this.recordTablaPivoteada.getMayoresDe120Dias()).map(w->w.split(",")).flatMap(Arrays::stream).collect(Collectors.toList())
                .stream()
                .collect(
                        Collectors.toMap(x -> {
                            int posicion = x.lastIndexOf("||");
                            return x.substring(0,posicion);
                        }, x->{

                            int posicion = x.lastIndexOf("||");
                            return  new BigDecimal(Double.parseDouble(x.substring(posicion+2,x.length())));
                        })
                );
/*
        List<Integer> longitud= new ArrayList<Integer>();

        longitud.add(this._15Dias.size());
        longitud.add(this._30Dias.size());
        longitud.add(this._45Dias.size());
        longitud.add(this._60Dias.size());
        longitud.add(this._90Dias.size());
        longitud.add(this._120Dias.size());
        longitud.add(this.M_120Dias.size());

        int max = longitud.stream().collect(Collectors.summarizingInt(Integer::intValue)).getMax();

        Object[][] dataTabla={
                {(this._15Dias.entrySet().stream().map(e->e.getKey()).collect(Collectors.toList())).stream().toArray(String[]::new)},
                {(this._30Dias.entrySet().stream().map(e->e.getKey()).collect(Collectors.toList())).stream().toArray(String[]::new)},
                {(this._45Dias.entrySet().stream().map(e->e.getKey()).collect(Collectors.toList())).stream().toArray(String[]::new)},
                {(this._60Dias.entrySet().stream().map(e->e.getKey()).collect(Collectors.toList())).stream().toArray(String[]::new)},
                {(this._90Dias.entrySet().stream().map(e->e.getKey()).collect(Collectors.toList())).stream().toArray(String[]::new)},
                {(this._120Dias.entrySet().stream().map(e->e.getKey()).collect(Collectors.toList())).stream().toArray(String[]::new)},
                {(this.M_120Dias.entrySet().stream().map(e->e.getKey()).collect(Collectors.toList())).stream().toArray(String[]::new)}

        };

        System.out.println(dataTabla);
        System.out.println(dataTabla[1][2]);
        */


    }catch (Exception e){
            e.printStackTrace();

    }

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

    public Map<String, BigDecimal> get_15Dias() {
        return _15Dias;
    }

    public void set_15Dias(Map<String, BigDecimal> _15Dias) {
        this._15Dias = _15Dias;
    }

    public Map<String, BigDecimal> get_30Dias() {
        return _30Dias;
    }

    public void set_30Dias(Map<String, BigDecimal> _30Dias) {
        this._30Dias = _30Dias;
    }

    public Map<String, BigDecimal> get_45Dias() {
        return _45Dias;
    }

    public void set_45Dias(Map<String, BigDecimal> _45Dias) {
        this._45Dias = _45Dias;
    }

    public Map<String, BigDecimal> get_60Dias() {
        return _60Dias;
    }

    public void set_60Dias(Map<String, BigDecimal> _60Dias) {
        this._60Dias = _60Dias;
    }

    public Map<String, BigDecimal> get_90Dias() {
        return _90Dias;
    }

    public void set_90Dias(Map<String, BigDecimal> _90Dias) {
        this._90Dias = _90Dias;
    }

    public Map<String, BigDecimal> get_120Dias() {
        return _120Dias;
    }

    public void set_120Dias(Map<String, BigDecimal> _120Dias) {
        this._120Dias = _120Dias;
    }

    public Map<String, BigDecimal> getM_120Dias() {
        return M_120Dias;
    }

    public void setM_120Dias(Map<String, BigDecimal> m_120Dias) {
        M_120Dias = m_120Dias;
    }

    public Map<String, BigDecimal> getSumatorias() {
        return sumatorias;
    }

    public void setSumatorias(Map<String, BigDecimal> sumatorias) {
        this.sumatorias = sumatorias;
    }


}
