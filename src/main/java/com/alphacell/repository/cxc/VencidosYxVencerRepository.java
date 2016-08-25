package com.alphacell.repository.cxc;

import com.alphacell.model.cartera.TmpCxcVyv;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by luis on 25/08/16.
 */
public class VencidosYxVencerRepository implements Serializable{


    private static final long serialVersionUID = -6595458714923249933L;


    @Inject
    private EntityManager manager;


    public List<TmpCxcVyv> findAll() {
        Query query = manager.createNamedQuery("TmpCxcVyv.findAll");

        return query.getResultList();
    }

    public List<TmpCxcVyv> cargarTablaCXCVencidosyXVencer()
    {
        List<TmpCxcVyv> listaEnviada= new ArrayList<TmpCxcVyv>();;

        try {


            StoredProcedureQuery query = this.manager.createStoredProcedureQuery("LC_CXC_VENCIDOS_X_VENCER",TmpCxcVyv.class);

            if (query.execute()) {

                listaEnviada = query.getResultList();
            }


            return listaEnviada;

        } catch (SecurityException | IllegalStateException e) {
            e.printStackTrace();
        }
        return null;

    }


}
