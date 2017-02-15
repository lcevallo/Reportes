package com.alphacell.repository.ventas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.alphacell.model.ventas.LcCadenaAlph;

public class ConfigRepository implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
    private EntityManager manager;
	
	public LcCadenaAlph guardaCadena(LcCadenaAlph lcCadenaAlph)
	{
		return manager.merge(lcCadenaAlph);
	}
	
	public LcCadenaAlph getByCodigo(String codigo)
	{
		LcCadenaAlph lcCadenaAlph= manager.find(LcCadenaAlph.class,codigo);
		return lcCadenaAlph;
	}

	public List<LcCadenaAlph> getAllCadenas()
    {
        List<LcCadenaAlph> listaenviada=new ArrayList<LcCadenaAlph>();
        Query query= manager.createNamedQuery("LcCadenaAlph.findAll");

        listaenviada=query.getResultList();

        return listaenviada;

    }

}
