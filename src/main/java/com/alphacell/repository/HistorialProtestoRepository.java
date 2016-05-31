package com.alphacell.repository;

import com.alphacell.model.cartera.Historialprotesto;
import com.alphacell.model.cartera.TmpPvtHistorialProtestoAfter;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

/**
 * Created by luis.cevallos on 4/4/2016.
 */
public class HistorialProtestoRepository implements Serializable{
    private static final long serialVersionUID = 4218787311060045400L;

    @Inject
    private EntityManager manager;

    public List<Historialprotesto> buscarHistorialProtesto(String codigo_cliente,Integer mes)
    {
        List<Historialprotesto> listaEnviar;

        try {

            Session session = manager.unwrap(Session.class);
            SQLQuery query = session.createSQLQuery("EXEC dbo.LC_HISTORIAL_PROTESTO :ACCOUNTNUM, :mes ").addEntity(Historialprotesto.class);
            query.setString("ACCOUNTNUM",codigo_cliente );
            query.setInteger("mes", mes);

            listaEnviar = query.list();
            return listaEnviar;

        } catch (SecurityException | IllegalStateException e) {

            e.printStackTrace();

        }
        return null;


    }

    public List<TmpPvtHistorialProtestoAfter> buscarPivoteHistorialProtesto()
    {
        List<TmpPvtHistorialProtestoAfter> listaPivoteada;
        try {

            Session session = manager.unwrap(Session.class);

            SQLQuery query = session.createSQLQuery("EXEC dbo.LC_HISTORIAL_PROTESTO").addEntity(TmpPvtHistorialProtestoAfter.class);


            listaPivoteada = query.list();

            return listaPivoteada;

        } catch (SecurityException | IllegalStateException e) {

            e.printStackTrace();

        }
        return null;


    }

}
