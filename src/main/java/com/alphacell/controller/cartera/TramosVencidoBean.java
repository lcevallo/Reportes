package com.alphacell.controller.cartera;

import com.alphacell.model.cartera.TramosVencidosLC;
import com.alphacell.repository.cxc.TramosVencidosRepository;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by luis on 19/07/16.
 */

@Named
@ViewScoped
public class TramosVencidoBean implements Serializable {


    private static final long serialVersionUID = -2845092518664112368L;

    private  List<TramosVencidosLC> tablaTramosVencidos;


    @Inject
    private TramosVencidosRepository tramosVencidosRepository;


    @PostConstruct
    public void init()
    {
        this.tablaTramosVencidos = tramosVencidosRepository.cargarTablaFacturasVencidas();

    }

    public List<TramosVencidosLC> getTablaTramosVencidos() {
        return tablaTramosVencidos;
    }

    public void setTablaTramosVencidos(List<TramosVencidosLC> tablaTramosVencidos) {
        this.tablaTramosVencidos = tablaTramosVencidos;
    }
}
