package com.alphacell.repository.cxc;

import com.alphacell.model.cartera.TmpCarteraTotalPvt;
import com.alphacell.model.cartera.TmpCarteraTotalXTramo1;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

/**
 * Created by luis.cevallos on 6/4/2016.
 */
public class CarteraTotalRepository implements Serializable {
    private static final long serialVersionUID = -2859506565088148418L;

    @Inject
    private EntityManager manager;


    public  List<TmpCarteraTotalPvt> cargarTablaCarteraPivote()
    {
        List<TmpCarteraTotalPvt> listaEnviada;

        try{

            Session session = manager.unwrap(Session.class);

            SQLQuery query = session.createSQLQuery("EXEC dbo.LC_CARTERA_TOTAL_X_TRAMO_3 ").addEntity(TmpCarteraTotalPvt.class);

            listaEnviada = query.list();

            return listaEnviada;

        }
        catch (SecurityException | IllegalStateException e)
        {
            e.printStackTrace();
        }
        return null;

    }


    public List<TmpCarteraTotalXTramo1> cargarInfoDialogoCartera(String codigo_cliente,String grupo_dias)
    {
        List<TmpCarteraTotalXTramo1> listaEnviada;
        try {

            Session session = manager.unwrap(Session.class);
            SQLQuery query = session.createSQLQuery("EXEC dbo.LC_CARTERA_TOTAL_X_TRAMO_3 :ACCOUNTNUM, :GRUPODIAS").addEntity(TmpCarteraTotalXTramo1.class);
            query.setString("ACCOUNTNUM", codigo_cliente);
            query.setString("GRUPODIAS", grupo_dias);

            listaEnviada = query.list();

            return listaEnviada;


        } catch (SecurityException | IllegalStateException e) {

                e.printStackTrace();

            }
            return null;

    }

}
