package com.alphacell.repository.cxc;

import com.alphacell.model.cartera.LcVistaChequesPostfechados;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by luis on 23/09/16.
 */
public class CXCChequesPostfechadosRepository implements Serializable {

    private static final long serialVersionUID = 4707351623635149250L;

    @Inject
    private EntityManager manager;

    public List<LcVistaChequesPostfechados> obtenerPorFecha(Date fechaBusqueda)
    {
        Query query = manager.createNamedQuery("LcVistaChequesPostfechados.findByDuedateChequePost")
                .setParameter("duedate",fechaBusqueda)
                .setParameter("tipo","CHEQUE POSTFECHADO");

        return (List<LcVistaChequesPostfechados>) query.getResultList();
    }


    public List<LcVistaChequesPostfechados> obtenerEntreFechas(Date fechaInicial, Date fechaFinal)
    {
            Query query= manager.createQuery("SELECT l FROM LcVistaChequesPostfechados l WHERE l.tipo='CHEQUE POSTFECHADO' and l.duedate between :fechaini and :fechafin")
                    .setParameter("fechaini",fechaInicial)
                    .setParameter("fechafin",fechaFinal);

                    return  (List<LcVistaChequesPostfechados>) query.getResultList();

    }

}
