package com.alphacell.repository.cxc;

import com.alphacell.model.cartera.ClienteVista;
import com.alphacell.model.cartera.ClientesLC;
import com.alphacell.model.cartera.Tmpcxcsaldosiniciales;
import com.alphacell.model.cartera.Tmpcxcsidiariocompensaciondetalle;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by luis on 13/06/16.
 */
public class SaldosInicialesRepository implements Serializable {

    private static final long serialVersionUID = -4653675513631025482L;

    @Inject
    private EntityManager manager;


    public List<ClientesLC> cargarTablaSaldosIniciales(String clientes,  Integer abierto, BigDecimal facturaOpen, BigDecimal facturaClose, String condicionOPen, String condicionClose){

        List<ClientesLC> listaEnviada;
        List<Tmpcxcsaldosiniciales> tmpcxcsaldosinicialesList;
        Map<String, List<Tmpcxcsaldosiniciales>> empleadosSubTable;


        try {

            Session session = manager.unwrap(Session.class);

            StringBuilder queryStrB = new StringBuilder("EXEC dbo.LC_CXC_SALDOS_INICIALES null ");


            queryStrB.append(clientes!=null?",:conjuntoID":",null").append(abierto!=null?",:abierto":",null").append(facturaOpen!=null?",:facturaOPEN":",null")
                      .append(facturaClose!=null?",:facturaCLOSE":",null").append(condicionOPen!=null?",:condicionFacturaOPEN":",null")
                       .append(condicionClose!=null?",:condicionFacturaCLOSE":",null");



                SQLQuery query = session.createSQLQuery(queryStrB.toString()).addEntity(Tmpcxcsaldosiniciales.class);

                if(clientes!=null) {
                    query.setString("conjuntoID", clientes);
                }

                if (abierto!=null)
                    query.setInteger("abierto",abierto);


                if (facturaOpen!=null)
                    query.setBigDecimal("facturaOPEN",facturaOpen);

                if (facturaClose!=null)
                    query.setBigDecimal("facturaCLOSE",facturaClose);

                if (condicionOPen!=null)
                    query.setString("condicionFacturaOPEN",condicionOPen);

                if (condicionClose!=null)
                    query.setString("condicionFacturaCLOSE",condicionClose);

                tmpcxcsaldosinicialesList = query.list();

            //}

            listaEnviada  = new ArrayList<ClientesLC>();

            empleadosSubTable = tmpcxcsaldosinicialesList.stream()
                    .collect(Collectors.groupingBy(Tmpcxcsaldosiniciales::getAccountnum));


            empleadosSubTable.forEach((k,v)->listaEnviada.add(new ClientesLC(k,v)));

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

    public List<ClienteVista> obtenerClientes()
    {
        return manager.createNamedQuery("ClienteVista.findAll",ClienteVista.class).getResultList();

    }


    public ClienteVista findByCodigoCliente(String codigo)
    {
        Query query = manager.createNamedQuery("ClienteVista.findByAccountnum");
        query.setParameter("accountnum", codigo);
        return (ClienteVista) query.getSingleResult();
    }

}
