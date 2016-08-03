package com.alphacell.controller.cartera;

import com.alphacell.model.cartera.LcTramosYaVencidosPivot;
import com.alphacell.model.cartera.TramosVencidosLC;
import com.alphacell.model.cartera.TramosVencidosLC_bu;
import com.alphacell.repository.cxc.TramosVencidosRepository;
import com.alphacell.util.reporte.Reporte;
import net.sf.jasperreports.engine.JRException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by luis on 19/07/16.
 */

@Named
@ViewScoped
public class TramosVencidoBean implements Serializable {


    private static final long serialVersionUID = -2845092518664112368L;

    private  List<TramosVencidosLC> tablaTramosVencidos;
    private  List<TramosVencidosLC> tablaTramosVencidosOriginal;
    private  List<TramosVencidosLC> tablaTramosVencidosFiltered;
    private String filtroValue;
    private HashMap<String, Object> parametros;
    private Reporte rpt;

    private Map<String, List<LcTramosYaVencidosPivot>> tablaTramosVencidosMap;




    @Inject
    private TramosVencidosRepository tramosVencidosRepository;


    @PostConstruct
    public void init()
    {
        this.tablaTramosVencidos = tramosVencidosRepository.cargarTablasFacturasVencidas();
        this.tablaTramosVencidosOriginal=this.tablaTramosVencidos;

    }

    public List<TramosVencidosLC> getTablaTramosVencidos() {
        return tablaTramosVencidos;
    }

    public void setTablaTramosVencidos(List<TramosVencidosLC> tablaTramosVencidos) {
        this.tablaTramosVencidos = tablaTramosVencidos;
    }


    public Map<String, List<LcTramosYaVencidosPivot>> getTablaTramosVencidosMap() {
        return tablaTramosVencidosMap;
    }

    public void setTablaTramosVencidosMap(Map<String, List<LcTramosYaVencidosPivot>> tablaTramosVencidosMap) {
        this.tablaTramosVencidosMap = tablaTramosVencidosMap;
    }

    public List<TramosVencidosLC> getTablaTramosVencidosFiltered() {
        return tablaTramosVencidosFiltered;
    }

    public void setTablaTramosVencidosFiltered(List<TramosVencidosLC> tablaTramosVencidosFiltered) {
        this.tablaTramosVencidosFiltered = tablaTramosVencidosFiltered;
    }

    public void filtrarTabla()
    {

        if (filtroValue.length()>0)
            this.tablaTramosVencidos=this.tablaTramosVencidos.stream().filter(p->p.getName().contains(this.filtroValue)).collect(Collectors.toList());
        else
            this.tablaTramosVencidos=this.tablaTramosVencidosOriginal;

    }

    public String getFiltroValue() {
        return filtroValue;
    }

    public void setFiltroValue(String filtroValue) {
        this.filtroValue = filtroValue;
    }

    public void exportarExcelTramosVencidos(){
        rpt= new Reporte();
        parametros= new HashMap<String, Object>();


        String path="/reportes/TramosVencidos.jasper";

        try{

            rpt.exportarXLS(parametros,path,"TramosVencidos");
        } catch (JRException e) {
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error",e.getMessage()));
        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error",e.getMessage()));
        }

    }

    public Reporte getRpt() {
        return rpt;
    }

    public void setRpt(Reporte rpt) {
        this.rpt = rpt;
    }
}
