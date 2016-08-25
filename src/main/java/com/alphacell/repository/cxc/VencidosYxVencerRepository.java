package com.alphacell.repository.cxc;

import com.alphacell.model.cartera.TmpCxcVyv;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
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

}
