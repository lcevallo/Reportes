package com.alphacell.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.jpa.spi.StoredProcedureQueryParameterRegistration;

import com.alphacell.model.financiero.LcTblCxp;
import com.alphacell.model.financiero.LcVistaProveedoresAlpha;
import com.alphacell.util.jpa.filter.FlujoVencidoFilter;

/**
 * Created by luis.cevallos on 20/4/2016.
 */
public class CuentasxPagarRepository implements Serializable {


    private static final long serialVersionUID = 4215536602181286978L;

    @Inject
    private EntityManager manager;

    public List<LcVistaProveedoresAlpha> findByNombre(String nombre)
    {
        Query query = manager.createNamedQuery("LcVistaProveedoresAlpha.findByPosibleName");
        query.setParameter("name", "%"+nombre+"%");

        return query.getResultList();
    }
    public LcVistaProveedoresAlpha findByCodigoCliente(String codigo)
    {
        Query query = manager.createNamedQuery("LcVistaProveedoresAlpha.findByAccountnum");
        query.setParameter("accountnum", codigo);
        return (LcVistaProveedoresAlpha) query.getSingleResult();
    }

    public List<LcTblCxp> obtenerDataTableFechas(Date fechaInicial,Date fechaFinal){

        List<LcTblCxp> listaEnviada;

        try{

            Session session = manager.unwrap(Session.class);

            SQLQuery query = session.createSQLQuery("EXEC dbo.LC_CXP_REPORTE1 :fechaInicial, :fechaFinal").addEntity(LcTblCxp.class);
            query.setDate("fechaInicial",fechaInicial );
            query.setDate("fechaFinal", fechaFinal);

            listaEnviada = query.list();
            return listaEnviada;

        }
        catch (SecurityException | IllegalStateException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public List<LcTblCxp> cargarTablaSaldosPorPagar(FlujoVencidoFilter flujoVencidoFilter)
    {
        List<LcTblCxp> listaEnviada= new ArrayList<LcTblCxp>();

        try {
            StoredProcedureQuery query = this.manager.createStoredProcedureQuery("LC_CXP_SALDOS_X_PAGAR",LcTblCxp.class);

            query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(2, Date.class, ParameterMode.IN);

            ( (StoredProcedureQueryParameterRegistration) query.getParameter(1)).enablePassingNulls( true );

            query.setParameter(1, flujoVencidoFilter.getCodProveedor());
            query.setParameter(2, flujoVencidoFilter.getFechaDocumento());

            if (query.execute()) {
                listaEnviada = query.getResultList();
            }
            return listaEnviada;
        } catch (SecurityException | IllegalStateException e) {
            e.printStackTrace();
        }
        return null;
    }

}
