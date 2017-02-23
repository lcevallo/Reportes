package com.alphacell.repository.ventas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.jpa.spi.StoredProcedureQueryParameterRegistration;

import com.alphacell.model.ventas.LcCadenaAlph;
import com.alphacell.model.ventas.LcCadenaItems;
import com.alphacell.model.ventas.LcCadenaItemsPK;
import com.alphacell.model.xls.LcCadenaItemsXLS;
import com.alphacell.services.NegocioException;

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

    public List<LcCadenaItemsXLS> getAllItemsERP()
	{

        List<LcCadenaItemsXLS> listaenviada=new ArrayList<LcCadenaItemsXLS>();

        Query query= manager.createQuery( "Select DISTINCT new com.alphacell.model.xls.LcCadenaItemsXLS(v.itemid,v.name) from VistaLogiIndicemensualinventario v",LcCadenaItemsXLS.class);

        //Query query = manager.createNamedQuery("VistaLogiIndicemensualinventario.itemsDistintos");

       return (List<LcCadenaItemsXLS>) query.getResultList();
	}

    public void remover(LcCadenaItems lcCadenaItems) throws NegocioException {
        try {
            lcCadenaItems = porItemsPK(lcCadenaItems.getLcCadenaItemsPK());
            manager.remove(lcCadenaItems);
            manager.flush();
        } catch (PersistenceException e) {
            throw new NegocioException("Se elimino el Item de la cadena!. ");
        }
    }

    public LcCadenaItems porItemsPK(LcCadenaItemsPK lcCadenaItemsPK){
	    return (LcCadenaItems)manager.createNativeQuery("LcCadenaItems.findByCodigoItem",LcCadenaItems.class)
                .setParameter("codigoItem",lcCadenaItemsPK.getCodigoItem())
                .getSingleResult();
    }

    public LcCadenaItems guardarCadenaItem(LcCadenaItems lcCadenaItems) {
    
        StoredProcedureQuery query= manager.createStoredProcedureQuery("LcCadenaItems.sp_guardar_cadenaitem")
                .setParameter("codigo_cadena",lcCadenaItems.getLcCadenaItemsPK().getCodigoItem())
                .setParameter("descripcion_cadena",lcCadenaItems.getDescripcionCadena())
                .setParameter("codigo_item_alph",lcCadenaItems.getFkCodigoAlph())
                .setParameter("descripcion_item_alph",lcCadenaItems.getDescripcionAlph());


        ((StoredProcedureQueryParameterRegistration) query.getParameter("codigo_item_alph")).enablePassingNulls(true);
        ((StoredProcedureQueryParameterRegistration) query.getParameter("descripcion_item_alph")).enablePassingNulls(true);

        return (LcCadenaItems) query.getSingleResult();

    }
}
