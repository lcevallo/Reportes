package com.alphacell.repository;

import com.alphacell.model.cartera.JpaAlphaLimitecredito;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

/**
 * Created by luis.cevallos on 1/4/2016.
 */
public class LimiteCreditoRepository implements Serializable {
    private static final long serialVersionUID = 853576701334369622L;

    @Inject
    private EntityManager manager;


    public List<JpaAlphaLimitecredito> ObtenerTodos()
    {

        Query query = manager.createNamedQuery("JpaAlphaLimitecredito.findAll");

        return (List<JpaAlphaLimitecredito>) query.getResultList();
    }


}
