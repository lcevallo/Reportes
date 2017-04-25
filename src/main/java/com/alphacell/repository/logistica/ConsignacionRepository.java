package com.alphacell.repository.logistica;

import com.alphacell.model.logistica.LcVistaListaInventarios;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

/**
 * Created by luis.cevallos on 15/3/2017.
 */
public class ConsignacionRepository implements Serializable{
    private static final long serialVersionUID = -3677805194266986337L;

    @Inject
    private EntityManager manager;

    public List<LcVistaListaInventarios> buscarInventarios()
    {
        return manager.createNamedQuery("LcVistaListaInventarios.findAll",LcVistaListaInventarios.class).getResultList();
    }

}
