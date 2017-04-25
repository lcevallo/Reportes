package com.alphacell.controller.logistica;

import com.alphacell.model.logistica.LcVistaListaInventarios;
import com.alphacell.repository.logistica.ConsignacionRepository;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by luis.cevallos on 15/3/2017.
 */
@Named
@ViewScoped
public class ConsignacionBean implements Serializable {

    private static final long serialVersionUID = -1979590051760182039L;

    private List<LcVistaListaInventarios> cmbInventarios;
    private LcVistaListaInventarios inventarioSelected;

    @Inject
    private ConsignacionRepository consignacionRepository;

    @PostConstruct
    public void init()
    {
        this.cmbInventarios= new ArrayList<>();
        this.inventarioSelected= new LcVistaListaInventarios();
        this.cmbInventarios= this.consignacionRepository.buscarInventarios();

    }





    public List<LcVistaListaInventarios> getCmbInventarios() {
        return cmbInventarios;
    }

    public void setCmbInventarios(List<LcVistaListaInventarios> cmbInventarios) {
        this.cmbInventarios = cmbInventarios;
    }

    public LcVistaListaInventarios getInventarioSelected() {
        return inventarioSelected;
    }

    public void setInventarioSelected(LcVistaListaInventarios inventarioSelected) {
        this.inventarioSelected = inventarioSelected;
    }
}
