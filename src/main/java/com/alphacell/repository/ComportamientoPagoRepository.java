package com.alphacell.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.alphacell.model.cartera.ComportamientoPivote;
import com.alphacell.model.cartera.Tmpcxccomportamientodetalle;
import com.alphacell.model.cartera.Tmpcxccomportamientooffsetrecid;

/**
 * Created by luis.cevallos on 11/4/2016.
 */
public class ComportamientoPagoRepository  implements Serializable{
    private static final long serialVersionUID = 7059098240376275823L;

    @Inject
    private EntityManager manager;

    public List<ComportamientoPivote> obtenerPivoteFacturas()
    {

        List<ComportamientoPivote> listaEnviar;
        try {

            Session session = manager.unwrap(Session.class);
            SQLQuery query = session.createSQLQuery("EXEC dbo.LC_COMPORTAMIENTOPAGO ").addEntity(ComportamientoPivote.class);

            listaEnviar = query.list();
            return listaEnviar;

        } catch (SecurityException | IllegalStateException e) {

            e.printStackTrace();

        }
        return null;
    }


    public List<Tmpcxccomportamientodetalle> obtenerDetalleFacturasClientes(String Factura, String grupo, Integer opcion, Map Fechas)
    {

        List<Tmpcxccomportamientodetalle> listaEnviar;
        try {


            Session session = manager.unwrap(Session.class);

            SQLQuery query = session.createSQLQuery("EXEC dbo.LC_CXC_COMPORTAMIENTOPAGO_DETALLE :INVOICE,:GRUPO,:opcion,:fechas30P,:fechas45P,:fechas60P,:fechas90P,:fechas120P,:fechas150P").addEntity(Tmpcxccomportamientodetalle.class);
            query.setString("INVOICE", Factura);
            query.setString("GRUPO", grupo);
            query.setInteger("opcion", opcion);
            query.setDate("fechas30P", (Date)Fechas.get("fecha30P"));
            query.setDate("fechas45P", (Date)Fechas.get("fecha45P"));
            query.setDate("fechas60P", (Date)Fechas.get("fecha60P"));
            query.setDate("fechas90P", (Date)Fechas.get("fecha90P"));
            query.setDate("fechas120P", (Date)Fechas.get("fecha120P"));
            query.setDate("fechas150P", (Date)Fechas.get("fecha150P"));


            listaEnviar = query.list();
            return listaEnviar;

        } catch (SecurityException | IllegalStateException e) {

            e.printStackTrace();

        }
        return null;

    }

    public List<Tmpcxccomportamientooffsetrecid> obtenerDetalleFacturasClientesCobradas(String Factura, String grupo, Integer opcion, Map Fechas)
    {

        List<Tmpcxccomportamientooffsetrecid> listaEnviar;
        try {


            Session session = manager.unwrap(Session.class);
            SQLQuery query = session.createSQLQuery("EXEC dbo.LC_CXC_COMPORTAMIENTOPAGO_DETALLE :INVOICE,:GRUPO,:opcion,:fechas30P,:fechas45P,:fechas60P,:fechas90P,:fechas120P,:fechas150P").addEntity(Tmpcxccomportamientooffsetrecid.class);
            query.setString("INVOICE", Factura);
            query.setString("GRUPO", grupo);
            query.setInteger("opcion", opcion);
            query.setDate("fechas30P", (Date)Fechas.get("fecha30P"));
            query.setDate("fechas45P", (Date)Fechas.get("fecha45P"));
            query.setDate("fechas60P", (Date)Fechas.get("fecha60P"));
            query.setDate("fechas90P", (Date)Fechas.get("fecha90P"));
            query.setDate("fechas120P", (Date)Fechas.get("fecha120P"));
            query.setDate("fechas150P", (Date)Fechas.get("fecha150P"));


            listaEnviar = query.list();
            return listaEnviar;

        } catch (SecurityException | IllegalStateException e) {

            e.printStackTrace();

        }
        return null;

    }



}
