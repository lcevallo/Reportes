package com.alphacell.controller.financiero;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.alphacell.model.financiero.LcTblCxp;
import com.alphacell.repository.CuentasxPagarRepository;
import com.alphacell.util.jpa.filter.FlujoVencidoFilter;
import com.alphacell.util.jsf.FormatoExcelPoi;


@Named
@ViewScoped
public class SaldosxPagarBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<LcTblCxp>  tableLcTblCxp;
	private FlujoVencidoFilter flujoVencidoFilter;


	@Inject
	private CuentasxPagarRepository cuentasxPagarRepository;


	@PostConstruct
	public void init() {
		flujoVencidoFilter= new FlujoVencidoFilter();
		tableLcTblCxp=new ArrayList<>();
	}

	public void postProcessXLS(Object document) {

		HashSet omitirColumnas = new HashSet();
		//add elements to HashSet object
		omitirColumnas.add(new Integer("0"));
		omitirColumnas.add(new Integer("1"));
		omitirColumnas.add(new Integer("2"));
		omitirColumnas.add(new Integer("3"));
		omitirColumnas.add(new Integer("4"));
		omitirColumnas.add(new Integer("5"));
		omitirColumnas.add(new Integer("6"));
		omitirColumnas.add(new Integer("7"));

		FormatoExcelPoi.formatearArchivoExcel(document,omitirColumnas,0);

	}

	public void buscarSaldosxPagar()
	{
		this.tableLcTblCxp= this.cuentasxPagarRepository.cargarTablaSaldosPorPagar(this.flujoVencidoFilter);
	}

	public List<LcTblCxp> getTableLcTblCxp() {
		return tableLcTblCxp;
	}

	public void setTableLcTblCxp(List<LcTblCxp> tableLcTblCxp) {
		this.tableLcTblCxp = tableLcTblCxp;
	}

	public FlujoVencidoFilter getFlujoVencidoFilter() {
		return flujoVencidoFilter;
	}

	public void setFlujoVencidoFilter(FlujoVencidoFilter flujoVencidoFilter) {
		this.flujoVencidoFilter = flujoVencidoFilter;
	}
}
