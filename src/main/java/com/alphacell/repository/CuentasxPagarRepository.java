package com.alphacell.repository;

import com.alphacell.model.financiero.LcTblCxp;
import com.alphacell.model.financiero.LcVistaProveedoresAlpha;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
}
