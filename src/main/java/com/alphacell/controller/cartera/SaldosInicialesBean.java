package com.alphacell.controller.cartera;

import com.alphacell.model.cartera.Tmpcxcsaldosiniciales;
import com.alphacell.repository.SaldosInicialesRepository;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    private Map<String, List<Tmpcxcsaldosiniciales>> empleadosSubTable;
    private List<String> empleadosID;

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


        this.empleadosID = this.tblSaldosIniciales.stream()
                //.filter(distinctByKey(p -> p.getName())
                .map(tmpcxcsaldosiniciales -> tmpcxcsaldosiniciales.getAccountnum())
                .distinct()
                .collect(toList());


        this.empleadosSubTable = this.tblSaldosIniciales.stream()
                .collect(Collectors.groupingBy(si -> si.getAccountnum()));


        //   System.out.println(this.empleadosSubTable);

        //empleadosID.forEach((employee) -> System.out.print(employee + "; "));

    }

    public Map<String, List<Tmpcxcsaldosiniciales>> getEmpleadosSubTable() {
        return empleadosSubTable;
    }

    public void setEmpleadosSubTable(Map<String, List<Tmpcxcsaldosiniciales>> empleadosSubTable) {
        this.empleadosSubTable = empleadosSubTable;
    }

    public List<String> getEmpleadosID() {
        return empleadosID;
    }

    public void setEmpleadosID(List<String> empleadosID) {
        this.empleadosID = empleadosID;
    }
}
