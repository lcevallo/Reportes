package com.alphacell.repository;

import com.alphacell.model.cartera.Cxcinfocliente;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

/**
 * Created by luis on 02/06/16.
 */
public class InfoClienteRepository implements Serializable {

    private static final long serialVersionUID = -2960495031275694100L;

    @Inject
    private EntityManager manager;


    public List<Cxcinfocliente> buscarInformacionCliente() {
        List<Cxcinfocliente> listaEnviar;

        try {

            Session session = manager.unwrap(Session.class);
            SQLQuery query = session.createSQLQuery("EXEC dbo.LC_CXC_INFO_CLIENTE").addEntity(Cxcinfocliente.class);

            listaEnviar = query.list();
            return listaEnviar;

        } catch (SecurityException | IllegalStateException e) {

            e.printStackTrace();

        }
        return null;


    }

}
