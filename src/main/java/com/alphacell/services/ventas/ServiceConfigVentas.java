package com.alphacell.services.ventas;

import java.io.Serializable;

import javax.inject.Inject;

import com.alphacell.model.ventas.LcCadenaAlph;
import com.alphacell.model.ventas.LcCadenaItemRErp;
import com.alphacell.model.ventas.LcCadenaItems;
import com.alphacell.model.ventas.LcVistaExcelVentasInicial;
import com.alphacell.repository.ventas.ConfigRepository;
import com.alphacell.services.NegocioException;
import com.alphacell.util.jpa.Transacional;

public class ServiceConfigVentas implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
    private ConfigRepository configRepository;

    @Transacional
    public LcCadenaAlph salvarCadena(LcCadenaAlph lcCadenaAlph) {
       return  configRepository.guardaCadena(lcCadenaAlph);
    }
    
    @Transacional
    public LcCadenaItems salvarCadenaItem(LcCadenaItems lcCadenaItems) {
       return  configRepository.guardaCadenaItems(lcCadenaItems);
    }
    
    @Transacional
    public LcCadenaItemRErp  salvarLcCadenaItemRERP(LcCadenaItemRErp lcCadenaItemRErp)
    {
       return configRepository.guardaRelacionCadenaItemERP(lcCadenaItemRErp);
    }
    
    @Transacional
    public void removerItemCadenaItemVistaInicial(LcVistaExcelVentasInicial lcVistaExcelVentasInicial) throws NegocioException
    {
        configRepository.removerCadenaItemVistaInicial(lcVistaExcelVentasInicial);
    }

    @Transacional
    public void removerItemCadena(LcCadenaItems cadenaItemsSelected) throws NegocioException {
        configRepository.removerCadenaItem(cadenaItemsSelected);
    }

    @Transacional
    public Integer guardarSPExcelVentas(LcVistaExcelVentasInicial lcVistaExcelVentasInicial)
    {
      return  configRepository.guardarSPExcelVentasR(lcVistaExcelVentasInicial);
    }

   
}
