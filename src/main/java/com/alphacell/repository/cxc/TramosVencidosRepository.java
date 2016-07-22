package com.alphacell.repository.cxc;

import com.alphacell.model.cartera.LcVistaTramosYaVencidosPivot;
import com.alphacell.model.cartera.TramosVencidosLC;

import javax.inject.Inject;
import javax.persistence.EntityManager;
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


    public List<TramosVencidosLC> cargarTablaFacturasVencidas()
    {
        List<TramosVencidosLC> listaEnviada= new ArrayList<TramosVencidosLC>();
        List<LcVistaTramosYaVencidosPivot> listaSQL=new ArrayList<LcVistaTramosYaVencidosPivot>();
        Map<String, List<LcVistaTramosYaVencidosPivot>> vencidosSubTable;


        try{

            listaSQL= manager.createNamedQuery("LcVistaTramosYaVencidosPivot.findAll",LcVistaTramosYaVencidosPivot.class).getResultList();

           /* vencidosSubTable=listaSQL.stream()
                        .collect(Collectors.groupingBy(si->si.getAccountnum()));
*/
            listaSQL.forEach(k->listaEnviada.add(new TramosVencidosLC(k.getAccountnum(),k)));

           return listaEnviada;
        }catch (SecurityException | IllegalStateException e) {
            e.printStackTrace();
        }
        return null;


    }


}
