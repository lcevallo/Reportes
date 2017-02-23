/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphacell.repository.cxp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;

import com.alphacell.model.financiero.CXPSumatoriaVencidas;
import com.alphacell.model.financiero.TmpCxpFlujoVencidos;

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
        List<TmpCxpFlujoVencidos> listaEnviada= new ArrayList<TmpCxpFlujoVencidos>();

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
    /*

    @SuppressWarnings("unchecked")
    public List<TmpCxpFlujoVencidos> filtrados(FlujoVencidoFilter flujoVencidoFilter)
    {
        Session session= manager.unwrap(Session.class);
        Criteria criteria=session.createCriteria(TmpCxpFlujoVencidos.class);

        if(StringUtils.isNotBlank(flujoVencidoFilter.getCodProveedor()))
        {
            switch (flujoVencidoFilter.getCodProveedor()){
                case "Todos":
                    return this.cargarTablaCXPFlujoVencidos();
                default:
                    criteria.add(Restrictions.eq("accountnum",flujoVencidoFilter.getCodProveedor()));
            }

        }

        if(flujoVencidoFilter.getFechaDocumento()!=null)
            criteria.add(Restrictions.eq("transdate",flujoVencidoFilter.getFechaDocumento()));

        return criteria.addOrder(Order.desc("transdate")).list();

    }
*/

}
