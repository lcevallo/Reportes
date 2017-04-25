package com.alphacell.repository.ventas;


import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import com.alphacell.model.ventas.LcCadenaAlph;
import com.alphacell.model.ventas.LcCadenaItemRErp;
import com.alphacell.model.ventas.LcCadenaItemRErpPK;
import com.alphacell.model.ventas.LcCadenaItems;
import com.alphacell.model.ventas.LcCadenaItemsPK;
import com.alphacell.model.ventas.LcVistaExcelVentasInicial;
import com.alphacell.model.xls.LCExcelVentas1;
import com.alphacell.model.xls.LcCadenaItemsXLS;
import com.alphacell.services.NegocioException;
import com.alphacell.util.jpa.filter.CadenaItemFilter;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class ConfigRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
    private EntityManager manager;

	public List<LcVistaExcelVentasInicial> getByCadena(String cod_cadena)
    {
        List<LcVistaExcelVentasInicial> listaEnviada= new ArrayList<>();

        Query query= manager.createNamedQuery("LcVistaExcelVentasInicial.findByFkCodigoCadena")
                .setParameter("fkCodigoCadena",cod_cadena);


        listaEnviada= query.getResultList();

        listaEnviada.forEach(obj->{
            obj.getLcCadenaItemsXLS().setCodigo(obj.getItemid());
            obj.getLcCadenaItemsXLS().setDescripcion(obj.getName());
        });

        return listaEnviada;
    }
	
	public LcCadenaAlph guardaCadena(LcCadenaAlph lcCadenaAlph)
	{
		return manager.merge(lcCadenaAlph);
	}
	
    public LcCadenaItems guardaCadenaItems(LcCadenaItems lcCadenaItems)
    {
        return manager.merge(lcCadenaItems);
    }

    public LcCadenaItemRErp guardaRelacionCadenaItemERP(LcCadenaItemRErp lcCadenaItemRErp)
    {
        return manager.merge(lcCadenaItemRErp);
    }
    
   /*
    public List<LCExcelVentas1> getAllItemsERP()
	{
        List<LcCadenaItemsXLS> listaenviada=new ArrayList<LcCadenaItemsXLS>();
        Query query= manager.createQuery( "Select DISTINCT new com.alphacell.model.xls.LCExcelVentas1(v.itemid,v.name) from VistaLogiIndicemensualinventario v",LCExcelVentas1.class);
        return (List<LCExcelVentas1>) query.getResultList();
	}
*/
    public List<LcCadenaItemsXLS> getAllItemsERP()
    {

        List<LcCadenaItemsXLS> listaenviada=new ArrayList<LcCadenaItemsXLS>();

        Query query= manager.createQuery( "Select DISTINCT new com.alphacell.model.xls.LcCadenaItemsXLS(v.itemid,v.name) from VistaLogiIndicemensualinventario v",LcCadenaItemsXLS.class);

        //Query query = manager.createNamedQuery("VistaLogiIndicemensualinventario.itemsDistintos");

        return (List<LcCadenaItemsXLS>) query.getResultList();
    }
    
    
    public void removerCadenaItemRERP(LcCadenaItemRErp lcCadenaItemRErp) throws NegocioException
    {
      try{
          lcCadenaItemRErp= porItemsERPR(lcCadenaItemRErp.getLcCadenaItemRErpPK());
          manager.remove(lcCadenaItemRErp);
          manager.flush();
        }
        catch (PersistenceException e) {
            throw new NegocioException("No se ha podido eliminar la relacion !. ");
        }
    }

    private LcCadenaItemRErp porItemsERPR(LcVistaExcelVentasInicial lcVistaExcelVentasInicial)
    {
        return (LcCadenaItemRErp)manager.createNamedQuery("LcCadenaItemRErp.findByLcCadenaItemRErpPk",LcCadenaItemRErp.class)
                .setParameter("fkCodigoItem",lcVistaExcelVentasInicial.getCodigoItem())
                .setParameter("fkCodigoCadena",lcVistaExcelVentasInicial.getFkCodigoCadena())
                .setParameter("itemid",lcVistaExcelVentasInicial.getItemid())
                .getSingleResult();

    }

    private LcCadenaItemRErp porItemsERPR(LcCadenaItemRErpPK lcCadenaItemRErpPK) {
        return (LcCadenaItemRErp)manager.createNamedQuery("LcCadenaItemRErp.findByLcCadenaItemRErpPk",LcCadenaItemRErp.class)
                .setParameter("fkCodigoItem",lcCadenaItemRErpPK.getFkCodigoItem())
                .setParameter("fkCodigoCadena",lcCadenaItemRErpPK.getFkCodigoCadena())
                .setParameter("itemid",lcCadenaItemRErpPK.getItemid())
                .getSingleResult();
    }


    public void removerCadenaItem(LcCadenaItems lcCadenaItems) throws NegocioException {
        try {
            lcCadenaItems = porItemsPK(lcCadenaItems.getLcCadenaItemsPK());
            manager.remove(lcCadenaItems);
            manager.flush();
        } catch (PersistenceException e) {
            throw new NegocioException("No se ha podido eliminar el Item de la cadena!. ");
        }
    }
    
    public LcCadenaItems porItemsPK(LcCadenaItemsPK lcCadenaItemsPK){
	    return (LcCadenaItems)manager.createNamedQuery("LcCadenaItems.findByCodigoItem",LcCadenaItems.class)
                .setParameter("codigoItem",lcCadenaItemsPK.getCodigoItem())
                .setParameter("fkCodigoCadena",lcCadenaItemsPK.getFkCodigoCadena())
                .getSingleResult();
    }

    public LcCadenaAlph getByCadenaCodigo(String codigo)
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

    public Integer guardarSPExcelVentasR(LcVistaExcelVentasInicial lcVistaExcelVentasInicial) {

        try{
            StoredProcedureQuery query= manager.createNamedStoredProcedureQuery("LcCadenaItemRErp.sp_guardar_cadenaitem")
                    .setParameter("cod_cadena",lcVistaExcelVentasInicial.getFkCodigoCadena())
                    .setParameter("marca",lcVistaExcelVentasInicial.getMarca())
                    .setParameter("modelo_unificado",lcVistaExcelVentasInicial.getCodigoItem())
                    .setParameter("item_id",lcVistaExcelVentasInicial.getItemid())
                    .setParameter("descripcion_cadena",lcVistaExcelVentasInicial.getDescripcionCadena());

            if(query.execute()) {
                return (Integer) query.getSingleResult();
            }

        }catch (Exception ex){

            System.out.println(ex.getMessage());
        }
        return 0;

        }

    public void removerCadenaItemVistaInicial(LcVistaExcelVentasInicial lcVistaExcelVentasInicial) throws NegocioException {

        LcCadenaItemRErp lcCadenaItemRErp= this.porItemsERPR(lcVistaExcelVentasInicial);

        this.removerCadenaItemRERP(lcCadenaItemRErp);
    }


    public List<LcVistaExcelVentasInicial> filtrados(String codigoCadena, CadenaItemFilter cadenaItemFilter) {
        Session session = manager.unwrap(Session.class);

        Criteria criteria = session.createCriteria(LcVistaExcelVentasInicial.class);

        criteria.add(Restrictions.eq("LcVistaExcelVentasInicial.findByFkCodigoCadena", codigoCadena));


        if (StringUtils.isNotBlank(cadenaItemFilter.getCodigoItemCadena())) {
            criteria.add(Restrictions.ilike("codigoItem", cadenaItemFilter.getCodigoItemCadena(), MatchMode.ANYWHERE));
        }

        if (StringUtils.isNotBlank(cadenaItemFilter.getDescripcionItemCadena())) {
            criteria.add(Restrictions.like("descripcionCadena", "%"+cadenaItemFilter.getDescripcionItemCadena()+"%"));
        }

        if (StringUtils.isNotBlank(cadenaItemFilter.getCodigoAlpha())) {
            criteria.add(Restrictions.ilike("itemid", cadenaItemFilter.getCodigoAlpha(), MatchMode.ANYWHERE));
        }

        if (StringUtils.isNotBlank(cadenaItemFilter.getMarca())) {
            criteria.add(Restrictions.ilike("marca", cadenaItemFilter.getMarca(), MatchMode.ANYWHERE));
        }

        if (StringUtils.isNotBlank(cadenaItemFilter.getDescripcionAlpha())) {
            criteria.add(Restrictions.like("name", "%"+cadenaItemFilter.getDescripcionItemCadena()+"%"));
        }

        return criteria.addOrder(Order.asc("codigoItem")).list();
    }
}
