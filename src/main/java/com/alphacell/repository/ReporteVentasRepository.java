package com.alphacell.repository;

import com.alphacell.model.financiero.TblReporteVentas;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by admin on 05/04/2016.
 */
public class ReporteVentasRepository implements Serializable {
    private static final long serialVersionUID = 7615912678901945056L;

    @Inject
    private EntityManager manager;

    public List<TblReporteVentas> obtenerReporteVentas(Date fechaInicial, Date fechaFinal)
    {

        List<TblReporteVentas> listaEnviar;

        try{

            Session session = manager.unwrap(Session.class);

            SQLQuery query = session.createSQLQuery("EXEC dbo.LC_ReporteVentas :fechaInicial, :fechaFinal").addEntity(TblReporteVentas.class);
            query.setDate("fechaInicial",fechaInicial);
            query.setDate("fechaFinal", fechaFinal);

            listaEnviar = query.list();

            return listaEnviar;

        }
        catch (SecurityException | IllegalStateException e) {

            e.printStackTrace();

        }
        return null;
    }


}
