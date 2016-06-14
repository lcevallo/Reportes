package com.alphacell.controller.cartera;

import com.alphacell.model.cartera.Tmpcxcsaldosiniciales;
import com.alphacell.repository.SaldosInicialesRepository;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by luis on 13/06/16.
 */
@Named
@ViewScoped
public class SaldosInicialesBean implements Serializable {

    private static final long serialVersionUID = 2624324863598507528L;

    private List<Tmpcxcsaldosiniciales> tblSaldosIniciales;
    private List<Tmpcxcsaldosiniciales> tblSaldosInicialesFiltered;
    private Tmpcxcsaldosiniciales recordTmpcxcsaldosiniciales;

    @Inject
    private SaldosInicialesRepository saldosInicialesRepository;

    public List<Tmpcxcsaldosiniciales> getTblSaldosIniciales() {
        return tblSaldosIniciales;
    }

    public void setTblSaldosIniciales(List<Tmpcxcsaldosiniciales> tblSaldosIniciales) {
        this.tblSaldosIniciales = tblSaldosIniciales;
    }

    public List<Tmpcxcsaldosiniciales> getTblSaldosInicialesFiltered() {
        return tblSaldosInicialesFiltered;
    }

    public void setTblSaldosInicialesFiltered(List<Tmpcxcsaldosiniciales> tblSaldosInicialesFiltered) {
        this.tblSaldosInicialesFiltered = tblSaldosInicialesFiltered;
    }

    public Tmpcxcsaldosiniciales getRecordTmpcxcsaldosiniciales() {
        return recordTmpcxcsaldosiniciales;
    }

    public void setRecordTmpcxcsaldosiniciales(Tmpcxcsaldosiniciales recordTmpcxcsaldosiniciales) {
        this.recordTmpcxcsaldosiniciales = recordTmpcxcsaldosiniciales;
    }

    @PostConstruct
    public void inicializar() {

        this.tblSaldosIniciales = saldosInicialesRepository.cargarTablaSaldosIniciales();


        List<String> empleadosID = this.tblSaldosIniciales.stream()
                //.filter(distinctByKey(p -> p.getName())
                .map(tmpcxcsaldosiniciales -> tmpcxcsaldosiniciales.getAccountnum())
                .distinct()
                .collect(toList());

        //empleadosID.forEach((employee) -> System.out.print(employee + "; "));

    }

}
