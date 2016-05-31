package com.alphacell.repository;

import com.alphacell.model.financiero.Tmpcxptramo;
import com.alphacell.model.financiero.Tmppivotcxp;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * Created by luis.cevallos on 26/4/2016.
 */
public class ReporteCxPxTramosRepository implements Serializable{
    private static final long serialVersionUID = -9036523848932022026L;

    @Inject
    private EntityManager manager;

    public List<Tmppivotcxp> buscarTramosXSemana(Date fechaInicial)
    {
        LocalDate localDate = fechaInicial.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year  = localDate.getYear();
        int month = localDate.getMonthValue();
        int day   = localDate.getDayOfMonth();

        List<Tmppivotcxp> listaEnviada;

        try{

            Session session = manager.unwrap(Session.class);
            SQLQuery query = session.createSQLQuery("EXEC dbo.LC_CXP_REPORTE_TRAMOS :dia, :mes, :anio").addEntity(Tmppivotcxp.class);
            query.setInteger("dia",day );
            query.setInteger("mes", month);
            query.setInteger("anio", year);

            listaEnviada = query.list();

            return listaEnviada;

        }
        catch (SecurityException | IllegalStateException e)
        {
            e.printStackTrace();
        }
        return null;


    }

    public List<Tmpcxptramo> buscarDetalleTramosXSemana(Date fechaInicial, String accountNum, int semana)
    {
        LocalDate localDate = fechaInicial.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year  = localDate.getYear();
        int month = localDate.getMonthValue();
        int day   = localDate.getDayOfMonth();

        List<Tmpcxptramo> listaEnviada;

        try{

        Session session = manager.unwrap(Session.class);
        SQLQuery query = session.createSQLQuery("EXEC dbo.LC_CXP_REPORTE_DETALLE_TRAMOS :dia, :mes, :anio, :accountnum, :num_semana").addEntity(Tmpcxptramo.class);
        query.setInteger("dia",day );
        query.setInteger("mes", month);
        query.setInteger("anio", year);
        query.setString("accountnum",accountNum);
        query.setInteger("num_semana",semana);

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
