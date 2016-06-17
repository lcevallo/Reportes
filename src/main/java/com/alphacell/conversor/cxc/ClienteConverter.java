package com.alphacell.conversor.cxc;

import com.alphacell.model.cartera.ClienteVista;
import com.alphacell.repository.cxc.SaldosInicialesRepository;
import com.alphacell.util.cdi.CDIServiceLocator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 * Created by luis on 17/06/16.
 */

@FacesConverter(forClass = ClienteVista.class)
public class ClienteConverter implements Converter {

    // @Inject
    SaldosInicialesRepository saldosInicialesRepository;


    public ClienteConverter() {
        this.saldosInicialesRepository = CDIServiceLocator.getBean(SaldosInicialesRepository.class);
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            Object obj = saldosInicialesRepository.findByCodigoCliente(value);
            return obj;

        } catch (Exception e) {
            e.printStackTrace();
            throw new ConverterException(new FacesMessage(String.format("No se puede convertir %s a un Cliente", value)), e);

        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if (!(value instanceof ClienteVista)) {
            return null;
        }
        String s = String.valueOf(((ClienteVista) value).getAccountnum());
        return s;
    }


}
