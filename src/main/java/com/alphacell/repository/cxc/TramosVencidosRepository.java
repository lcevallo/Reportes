package com.alphacell.repository.cxc;

import com.alphacell.model.cartera.LcTramosYaVencidosPivot;
import com.alphacell.model.cartera.LcVistaTramosYaVencidosPivot;
import com.alphacell.model.cartera.TramosVencidosLC;
import com.alphacell.model.cartera.TramosVencidosLC_bu;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by luis on 19/07/16.
 */
public class TramosVencidosRepository implements Serializable {

    private static final long serialVersionUID = 2103765733773442305L;

    @Inject
    private EntityManager manager;


    public List<TramosVencidosLC_bu> cargarTablaFacturasVencidasVista()
    {
        List<TramosVencidosLC_bu> listaEnviada= new ArrayList<TramosVencidosLC_bu>();
        List<LcVistaTramosYaVencidosPivot> listaSQL=new ArrayList<LcVistaTramosYaVencidosPivot>();
        Map<String, List<LcVistaTramosYaVencidosPivot>> vencidosSubTable;


        try{

            listaSQL= manager.createNamedQuery("LcVistaTramosYaVencidosPivot.findAll",LcVistaTramosYaVencidosPivot.class).getResultList();

           /* vencidosSubTable=listaSQL.stream()
                        .collect(Collectors.groupingBy(si->si.getAccountnum()));
*/
            listaSQL.forEach(k->listaEnviada.add(new TramosVencidosLC_bu(k.getAccountnum(),k)));

           return listaEnviada;
        }catch (SecurityException | IllegalStateException e) {
            e.printStackTrace();
        }
        return null;


    }


    public List<TramosVencidosLC> cargarTablasFacturasVencidas()
    {
        List<TramosVencidosLC> listaEnviada= new ArrayList<TramosVencidosLC>();
        List<LcTramosYaVencidosPivot> tmpTramosVencidos= new ArrayList<LcTramosYaVencidosPivot>();
        Map<String,List<LcTramosYaVencidosPivot>> vencidosSubtable = null;


        try{

            StoredProcedureQuery query = this.manager.createStoredProcedureQuery("LC_TRAMOS_YA_VENCIDOS",LcTramosYaVencidosPivot.class);


            if (query.execute())
            {

                tmpTramosVencidos = query.getResultList();

/*
                List<String> ids = tmpTramosVencidos.stream().map(sc -> sc.getAccountnum()).distinct().collect(Collectors.toList());

                List<LcTramosYaVencidosPivot> finalTmpTramosVencidos = tmpTramosVencidos;

                ids.stream().forEach(
                        x->{
                            TramosVencidosLC tramosVencidosLC = new TramosVencidosLC(x,finalTmpTramosVencidos
                                    .stream()
                                    .filter(a -> a.getAccountnum().equals(x))
                                    .collect(Collectors.toList()));

                            listaEnviada.add(tramosVencidosLC);
                        }
                );
                */

                vencidosSubtable=tmpTramosVencidos.stream()
                        .collect(Collectors.groupingBy(si->si.getAccountnum()));

                vencidosSubtable.forEach((k,v)->listaEnviada.add(new TramosVencidosLC(k,v)));


            }


          return listaEnviada;

        }
        catch (SecurityException | IllegalStateException e) {
            e.printStackTrace();
        }
        return null;


    }


}
