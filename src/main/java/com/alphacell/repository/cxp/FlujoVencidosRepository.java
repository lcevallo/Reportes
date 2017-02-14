/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphacell.repository.cxp;

import com.alphacell.model.financiero.CXPSumatoriaVencidas;
import com.alphacell.model.financiero.TmpCxpFlujoVencidos;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author luis
 */
public class FlujoVencidosRepository implements Serializable{

    private static final long serialVersionUID = -6595538692265901981L;

    @Inject
    private EntityManager manager;

    public List<TmpCxpFlujoVencidos> cargarTablaCXPFlujoVencidos()
    {
        List<TmpCxpFlujoVencidos> listaEnviada= new ArrayList<TmpCxpFlujoVencidos>();;

        try {


            StoredProcedureQuery query = this.manager.createStoredProcedureQuery("LC_CXP_FLUJO_X_TRAMOS",TmpCxpFlujoVencidos.class);

            if (query.execute()) {

                listaEnviada = query.getResultList();
            }


            return listaEnviada;

        } catch (SecurityException | IllegalStateException e) {
            e.printStackTrace();
        }
        return null;

    }

    public List<CXPSumatoriaVencidas> cargarResumenFlujoVencido()
    {
        List<CXPSumatoriaVencidas> listaEnviada= new ArrayList<CXPSumatoriaVencidas>();
        Map<String, List<TmpCxpFlujoVencidos>> resumenSubTable;

       List<TmpCxpFlujoVencidos> busqueda=new ArrayList<TmpCxpFlujoVencidos>();

        busqueda=this.cargarTablaCXPFlujoVencidos();


        resumenSubTable = busqueda.stream()
                .collect(Collectors.groupingBy(TmpCxpFlujoVencidos::getAccountnum));


        resumenSubTable.forEach((k,v)->listaEnviada.add(new CXPSumatoriaVencidas(k,v)));

        return listaEnviada;


    }
}
