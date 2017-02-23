package com.alphacell.services.ventas;

import java.io.Serializable;

import javax.inject.Inject;

import com.alphacell.model.ventas.LcCadenaAlph;
import com.alphacell.model.ventas.LcCadenaItems;
import com.alphacell.services.NegocioException;
import com.alphacell.repository.ventas.ConfigRepository;
import com.alphacell.util.jpa.Transacional;

public class ServiceConfigVentas implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
    private ConfigRepository configRepository;

    @Transacional
    public void salvarCadena(LcCadenaAlph lcCadenaAlph) {
        configRepository.guardaCadena(lcCadenaAlph);
    }

    @Transacional
    public void removerItemCadena(LcCadenaItems cadenaItemsSelected) throws NegocioException {
        configRepository.remover(cadenaItemsSelected);
    }

    @Transacional
    public LcCadenaItems guardar(LcCadenaItems lcCadenaItems)
    {
       return configRepository.guardarCadenaItem(lcCadenaItems);
    }
}
