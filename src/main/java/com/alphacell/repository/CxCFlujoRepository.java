package com.alphacell.repository;

import com.alphacell.model.Semanatemp;
import com.alphacell.model.cartera.TablaCxcpivote;
import com.alphacell.model.financiero.Tablaflujo;
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
 * Created by luis.cevallos on 1/4/2016.
 */
public class CxCFlujoRepository  implements Serializable{
    private static final long serialVersionUID = -5478156680901935822L;

    @Inject
    private EntityManager manager;


    public List<Semanatemp> buscar8Semanas(Date fechaInicial)
    {
        LocalDate localDate = fechaInicial.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year  = localDate.getYear();
        int month = localDate.getMonthValue();
        int day   = localDate.getDayOfMonth();

        List<Semanatemp> listaEnviada;
        try{

            Session session = manager.unwrap(Session.class);

            SQLQuery query = session.createSQLQuery("EXEC dbo.LC_FlujoCaja :dia, :mes, :anio, :semanas").addEntity(Semanatemp.class);
            query.setInteger("dia",day );
            query.setInteger("mes", month);
            query.setInteger("anio", year);
            query.setInteger("semanas",1);

            listaEnviada = query.list();

            return listaEnviada;

        }
        catch (SecurityException | IllegalStateException e)
        {
            e.printStackTrace();
        }
        return null;


    }


    public List<Tablaflujo> buscarFlujoSemanas(Date fechaInicial,String codigo_cliente,Integer Semana)
    {

        LocalDate localDate = fechaInicial.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year  = localDate.getYear();
        int month = localDate.getMonthValue();
        int day   = localDate.getDayOfMonth();


        List<Tablaflujo> listaEnviada;

        try {

            Session session = manager.unwrap(Session.class);

            SQLQuery query = session.createSQLQuery("EXEC dbo.LC_FlujoCaja :dia, :mes, :anio, :accountnum, :numsemana").addEntity(Tablaflujo.class);
            query.setInteger("dia",day );
            query.setInteger("mes", month);
            query.setInteger("anio", year);
            query.setString("accountnum", codigo_cliente);
            query.setInteger("numsemana", Semana);

            listaEnviada = query.list();

            return listaEnviada;

        } catch (SecurityException | IllegalStateException e) {

            e.printStackTrace();

        }
        return null;
    }

    public List<TablaCxcpivote> buscarPivoteFlujo(Date fechaInicial)
    {

        LocalDate localDate = fechaInicial.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year  = localDate.getYear();
        int month = localDate.getMonthValue();
        int day   = localDate.getDayOfMonth();


        List<TablaCxcpivote> listaPivoteada;

        try {

            Session session = manager.unwrap(Session.class);

            SQLQuery query = session.createSQLQuery("EXEC dbo.LC_FlujoCaja :dia, :mes, :anio").addEntity(TablaCxcpivote.class);
            query.setInteger("dia",day );
            query.setInteger("mes", month);
            query.setInteger("anio", year);


            listaPivoteada = query.list();

            return listaPivoteada;

        } catch (SecurityException | IllegalStateException e) {

            e.printStackTrace();

        }
        return null;
    }






}
