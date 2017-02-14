package com.alphacell.repository.logistica;

import com.alphacell.model.logistica.VistaLogiIndicemensualinventario;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class IndiceInventarioRepository implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;


	public List<VistaLogiIndicemensualinventario> buscarIndicesInventario(String tipo)
    {
        List<VistaLogiIndicemensualinventario> listaEnviada= new ArrayList<VistaLogiIndicemensualinventario>();


        if(tipo.equals("Todos"))
        {
            Query query= manager.createNamedQuery("VistaLogiIndicemensualinventario.findAll");
            listaEnviada = query.getResultList();

            return listaEnviada;

        }

        //TODO: Tengo que hacer un combobox con los tipos de elemento a buscar como por ejemplo Televisores, Celulares, etc
        return null;

    }

}
