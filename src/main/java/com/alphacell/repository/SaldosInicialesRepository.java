package com.alphacell.repository;

import com.alphacell.model.cartera.Tmpcxcsaldosiniciales;
import com.alphacell.model.cartera.Tmpcxcsidiariocompensaciondetalle;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

/**
 * Created by luis on 13/06/16.
 */
public class SaldosInicialesRepository implements Serializable {

    private static final long serialVersionUID = -4653675513631025482L;

    @Inject
    private EntityManager manager;

    public List<Tmpcxcsaldosiniciales> cargarTablaSaldosIniciales() {

        List<Tmpcxcsaldosiniciales> listaEnviada;

        try {

            Session session = manager.unwrap(Session.class);

            SQLQuery query = session.createSQLQuery("EXEC dbo.LC_CXC_SALDOS_INICIALES ").addEntity(Tmpcxcsaldosiniciales.class);
            listaEnviada = query.list();
            return listaEnviada;

        } catch (SecurityException | IllegalStateException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Tmpcxcsidiariocompensaciondetalle> cargarTablaSaldosInicialesDetalle(String factura) {

        List<Tmpcxcsidiariocompensaciondetalle> listaEnviada;

        try {

            Session session = manager.unwrap(Session.class);

            SQLQuery query = session.createSQLQuery("EXEC dbo.LC_CXC_SALDOS_INICIALES :factura ").addEntity(Tmpcxcsidiariocompensaciondetalle.class);
            query.setString("factura", factura);
            listaEnviada = query.list();
            return listaEnviada;

        } catch (SecurityException | IllegalStateException e) {
            e.printStackTrace();
        }
        return null;
    }

}
