package com.alphacell.repository;

import com.alphacell.model.financiero.JpaAlphafactuview;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by luis.cevallos on 1/4/2016.
 */
public class VentasRepository implements Serializable {
    private static final long serialVersionUID = 8166960703592889956L;

    @Inject
    private EntityManager manager;

    public List<JpaAlphafactuview> obtenerVentasPorFecha(Date fechaInicial, Date fechaFinal)
    {
        Query query = manager.createNamedQuery("JpaAlphafactuview.findBetweenDates");
        query.setParameter("fechaIni",fechaInicial);
        query.setParameter("fechaFin",fechaFinal);

        return (List<JpaAlphafactuview>)query.getResultList();
    }

    public List<JpaAlphafactuview> ObtenerTodos()
    {
       return manager.createQuery("from JpaAlphafactuview",JpaAlphafactuview.class).getResultList();

    }
}
