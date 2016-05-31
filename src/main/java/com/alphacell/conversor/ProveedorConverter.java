/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphacell.conversor;

import com.alphacell.model.financiero.LcVistaProveedoresAlpha;
import com.alphacell.repository.CuentasxPagarRepository;
import com.alphacell.util.cdi.CDIServiceLocator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 * @author luis.cevallos
 */
@FacesConverter(forClass = LcVistaProveedoresAlpha.class)
public class ProveedorConverter implements Converter {
   // @Inject
    CuentasxPagarRepository cuentasxPagarRepository;
    public ProveedorConverter() {
        cuentasxPagarRepository = CDIServiceLocator.getBean(CuentasxPagarRepository.class);
    }


    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            Object obj = cuentasxPagarRepository.findByCodigoCliente(value);
            return obj;

        } catch (Exception e) {
            e.printStackTrace();
            throw new ConverterException(new FacesMessage(String.format("No se puede convertir %s a un Proveedor", value)), e);

        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if (!(value instanceof LcVistaProveedoresAlpha)) {
            return null;
        }
        String s = String.valueOf(((LcVistaProveedoresAlpha) value).getAccountnum());
        return s;
    }
}
