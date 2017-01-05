package com.alphacell.controller.financiero;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import com.alphacell.model.Semanatemp;
import com.alphacell.model.cartera.TablaCxcpivote;
import com.alphacell.model.financiero.FechasCorte;
import com.alphacell.repository.CxCFlujoRepository;
import com.alphacell.util.ManejoFechas;
import com.alphacell.util.jsf.FormatoExcelPoi;
import com.alphacell.util.reporte.Reporte;
import com.alphacell.util.streams.ConverterStream;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;

import net.sf.jasperreports.engine.JRException;

/**
 * Created by luis.cevallos on 30/3/2016.
 */
@Named
@ViewScoped
public class CxCFlujoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Date> semanaInicialList;
	private List<FechasCorte> diasCorteList;
	private List<FechasCorte> diasCorteListSelected;

	private List<Boolean> columnasShow;

	private List<Semanatemp> semanatempList;

	private List<TablaCxcpivote> tablaCxcpivoteList;
	private List<TablaCxcpivote> tablaCxcpivoteListFiltered;

	private List<BigDecimal> SumaColumnas;
	private List<BigDecimal> SumaFilas;

	private TablaCxcpivote tablaCxcpivoteSelected;
	private Date fechaInicialBusqueda;
	private int NumeroSemana;
	private Date fechaFinalBusqueda;
	private Reporte rpt;
	private HashMap<String, Object> parametros;

	@Inject
	private CxCFlujoRepository cxCFlujoRepository;

	@PostConstruct
	public void init() {

		this.columnasShow = new ArrayList<>();
		this.diasCorteListSelected = new ArrayList<>();
        this.SumaColumnas= new ArrayList<>();
        this.SumaFilas= new ArrayList<>();

		IntStream.rangeClosed(1, 10).forEach(n -> this.columnasShow.add(false));

	}

	public Date getFechaInicialBusqueda() {
		return fechaInicialBusqueda;
	}

	public void setFechaInicialBusqueda(Date fechaInicialBusqueda) {
		this.fechaInicialBusqueda = fechaInicialBusqueda;
	}

	public Date getFechaFinalBusqueda() {
		return fechaFinalBusqueda;
	}

	public void setFechaFinalBusqueda(Date fechaFinalBusqueda) {
		this.fechaFinalBusqueda = fechaFinalBusqueda;
	}

	public int getNumeroSemana() {
		return NumeroSemana;
	}

	public void setNumeroSemana(int numeroSemana) {
		NumeroSemana = numeroSemana;
	}

	public void generarNumeroSemana() {
		this.NumeroSemana = ManejoFechas.getNumeroSemana(this.fechaInicialBusqueda);
		this.semanaInicialList = ManejoFechas.FechasExtremas(this.fechaInicialBusqueda);
		Iterator i = this.semanaInicialList.iterator();
		while (i.hasNext()) {
			Date fecha2 = (Date) i.next();
		}
	}

	public void abrirDialogo() {

		/*
		 * Map<String,Object> opciones=new HashMap<>();
		 * opciones.put("modal",true); opciones.put("resizable", false);
		 * opciones.put("contentHeight", 500);
		 * 
		 * 
		 * RequestContext.getCurrentInstance().update(
		 * Arrays.asList("fmrcxc-fechascortes:tablecxc-fechascortes"));
		 * 
		 * RequestContext.getCurrentInstance().openDialog(
		 * "/dialogo/financiero/cxcFlujoFechasCortes",opciones,null); //
		 * RequestContext.getCurrentInstance().openDialog(
		 * "/WEB-INF/dialog/finaciero/cxcFlujoFechasCortes",opciones,null);
		 * 
		 */

		this.diasCorteList = ManejoFechas.FechasCortes(this.semanaInicialList.stream().findFirst().get(),
				this.NumeroSemana, 10);

		RequestContext.getCurrentInstance().update(Arrays.asList("cxcFechaCorteDialog", "tablecxc-fechascortes"));

		RequestContext.getCurrentInstance().execute("PF('cxcFechaCorteDialogWidget').show();");

	}

	public Boolean mostrarColumna(Integer columna) {

		Integer numeroSemana = this.diasCorteList.get(columna).getNumeroSemana();

		return this.diasCorteListSelected.stream().anyMatch(c -> c.getNumeroSemana().equals(numeroSemana));

	}

	public void changeValueShowColumnSelected(SelectEvent event) {

		FechasCorte fechasCorteSelected;
		fechasCorteSelected = (FechasCorte) event.getObject();
		int indice = this.diasCorteList.indexOf(fechasCorteSelected);

		if (indice != -1)
			this.columnasShow.set(indice, true);
	}

	public void changeValueShowColumnUnSelected(UnselectEvent event) {

		FechasCorte fechasCorteSelected;
		fechasCorteSelected = (FechasCorte) event.getObject();
		int indice = this.diasCorteList.indexOf(fechasCorteSelected);

		if (indice != -1)
			this.columnasShow.set(indice, false);

	}

	public void cargarTablaFlujo() {
		this.semanatempList = cxCFlujoRepository.buscar8Semanas(this.semanaInicialList.get(0));
		this.tablaCxcpivoteList = cxCFlujoRepository.buscarPivoteFlujo(this.semanaInicialList.get(0));

		this.tablaCxcpivoteList.stream().filter(z -> z != null).forEach(x -> {
			List<BigDecimal> listaEnviar = new ArrayList<BigDecimal>();
			listaEnviar.add(x.getA());
			listaEnviar.add(x.getA1());
			listaEnviar.add(x.getA2());
			listaEnviar.add(x.getA3());
			listaEnviar.add(x.getA4());
			listaEnviar.add(x.getA5());
			listaEnviar.add(x.getA6());
			listaEnviar.add(x.getA7());
			listaEnviar.add(x.getA8());
			listaEnviar.add(x.getA9());

			for (int i = 0; i < this.diasCorteListSelected.size(); i++) {
				BigDecimal valor = null;
				Integer numCol = this.diasCorteListSelected.get(i).getNoColumna();

				if (i == 0) {

					valor = this.salidaSumatoriaEntre(listaEnviar, 0, numCol);

				} else {

					valor = this.salidaSumatoriaEntre(listaEnviar, this.diasCorteListSelected.get(i - 1).getNoColumna(),
							numCol);
				}

				switch (numCol) {
				case 0:
					x.setSum0(valor);
					break;
				case 1:
					x.setSum1(valor);
					break;
				case 2:
					x.setSum2(valor);
					break;
				case 3:
					x.setSum3(valor);
					break;
				case 4:
					x.setSum4(valor);
					break;
				case 5:
					x.setSum5(valor);
					break;
				case 6:
					x.setSum6(valor);
				case 7:
					x.setSum7(valor);
					break;
				case 8:
					x.setSum8(valor);
					break;
				case 9:
					x.setSum9(valor);
					break;

				}

			}

		});// fin del foreach

		// 10 veces
		this.SumaColumnas.add(this.tablaCxcpivoteList.stream().filter(Objects::nonNull).map(x -> x.getA())
                .filter(Objects::nonNull)
				.reduce(BigDecimal.ZERO, BigDecimal::add));

		this.SumaColumnas.add(this.tablaCxcpivoteList.stream().filter(Objects::nonNull).map(x -> x.getA1())
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add));

		this.SumaColumnas.add(this.tablaCxcpivoteList.stream().filter(Objects::nonNull).map(x -> x.getA2())
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add));

		this.SumaColumnas.add(this.tablaCxcpivoteList.stream().filter(Objects::nonNull).map(x -> x.getA3())
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add));

		this.SumaColumnas.add(this.tablaCxcpivoteList.stream().filter(Objects::nonNull).map(x -> x.getA4())
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add));

		this.SumaColumnas.add(this.tablaCxcpivoteList.stream().filter(Objects::nonNull).map(x -> x.getA5())
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add));

		this.SumaColumnas.add(this.tablaCxcpivoteList.stream().filter(Objects::nonNull).map(x -> x.getA6())
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add));

		this.SumaColumnas.add(this.tablaCxcpivoteList.stream().filter(Objects::nonNull).map(x -> x.getA7())
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add));

		this.SumaColumnas.add(this.tablaCxcpivoteList.stream().filter(Objects::nonNull).map(x -> x.getA8())
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add));

		this.SumaColumnas.add(this.tablaCxcpivoteList.stream().filter(Objects::nonNull).map(x -> x.getA9())
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add));

		// FIN DE LAS 10 veces

		this.tablaCxcpivoteList.forEach(y -> {

			List<BigDecimal> tempLista = new ArrayList<BigDecimal>();
			tempLista.add(y.getA());
			tempLista.add(y.getA1());
			tempLista.add(y.getA2());
			tempLista.add(y.getA3());
			tempLista.add(y.getA4());
			tempLista.add(y.getA5());
			tempLista.add(y.getA6());
			tempLista.add(y.getA7());
			tempLista.add(y.getA8());
			tempLista.add(y.getA9());

			BigDecimal sumOfBigDecimals = tempLista.stream()
					.filter(Objects::nonNull)
					.reduce(BigDecimal.ZERO, BigDecimal::add);
			this.SumaFilas.add(sumOfBigDecimals);

		});

	}

	public BigDecimal salidaSumatoriaEntre(List<BigDecimal> lista, Integer limiteInf, Integer limiteSup) {
		return lista.stream().skip(limiteInf).limit((limiteSup - limiteInf) + 1).filter(c -> c != null)
				.reduce(BigDecimal.ZERO, BigDecimal::add);

	}

	public void exportarExcelFlujoCxCFinanciero() {

		rpt = new Reporte();
		parametros = new HashMap<String, Object>();

		LocalDate localDate = this.semanaInicialList.get(0).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int year = localDate.getYear();
		int month = localDate.getMonthValue();
		int day = localDate.getDayOfMonth();

		parametros.put("dia", day);
		parametros.put("mes", month);
		parametros.put("anio", year);

		String path = "/reportes/CxCFlujoFinanciero.jasper";

		try {

			rpt.exportarXLS(parametros, path, "CxCFlujoFinanciero");
		} catch (JRException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", e.getMessage()));
		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", e.getMessage()));
		}

	}

	public void posProcessarXls(Object documento) {
		HSSFWorkbook planilha = (HSSFWorkbook) documento;
		HSSFSheet folha = planilha.getSheetAt(0);
		HSSFRow cabecalho = folha.getRow(0);
		HSSFCellStyle estiloCelula = planilha.createCellStyle();
		Font fonteCabecalho = planilha.createFont();

		fonteCabecalho.setColor(IndexedColors.WHITE.getIndex());
		fonteCabecalho.setBold(true);

		fonteCabecalho.setFontHeightInPoints((short) 16);

		estiloCelula.setFont(fonteCabecalho);
		estiloCelula.setFillForegroundColor(IndexedColors.BLACK.getIndex());
		estiloCelula.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		for (int i = 0; i < cabecalho.getPhysicalNumberOfCells(); i++) {
			cabecalho.getCell(i).setCellStyle(estiloCelula);
		}
	}

	public void postProcessXLS2(Object document) {

		HSSFWorkbook wb = (HSSFWorkbook) document;
		HSSFSheet sheet = wb.getSheetAt(0);

		HSSFRow header = sheet.getRow(0);

		HSSFCellStyle cellStyle = wb.createCellStyle();
		HSSFDataFormat hssfDataFormat = wb.createDataFormat();
		cellStyle.setDataFormat(hssfDataFormat.getFormat("#.##0,000"));

		if (this.diasCorteList == null || this.diasCorteList.size() == 0) {
			this.diasCorteList = ManejoFechas.FechasCortes(this.semanaInicialList.stream().findFirst().get(),
					this.NumeroSemana, 10);
		}

		// int rows=sheet.getLastRowNum();
		// sheet.shiftRows(0,sheet.getLastRowNum(), 1);

		for (int i = 0; i < header.getPhysicalNumberOfCells(); i++) {
			HSSFCell cell = header.getCell(i);

			switch (cell.toString()) {

			case "Credito":
				if (cell.getRowIndex() > 0) {

					// cell.setCellType(Cell.CELL_TYPE_NUMERIC);

				}
				break;

			case "Utilizado":
				// if(cell.getRowIndex()>0)
				// cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				break;

			case "Saldo":
				// if(cell.getRowIndex()>0)
				// cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				break;

			case "1era semana":
				if (cell.getRowIndex() == 0)
					cell.setCellValue("Semana: " + this.diasCorteList.get(0).getNumeroSemana() + " - "
							+ this.getFechaFormateada(this.diasCorteList.get(0).getFechaCorte()));

				break;

			case "1er Grupo":
				if (cell.getRowIndex() == 0)
					cell.setCellValue("Total Semana: " + this.diasCorteList.get(0).getNumeroSemana());

				break;

			case "2da semana":
				if (cell.getRowIndex() == 0)
					cell.setCellValue("Semana: " + this.diasCorteList.get(1).getNumeroSemana() + " - "
							+ this.getFechaFormateada(this.diasCorteList.get(1).getFechaCorte()));

				break;

			case "2do Grupo":
				if (cell.getRowIndex() == 0)
					cell.setCellValue("Total Semana: " + this.diasCorteList.get(1).getNumeroSemana());

				break;

			case "3era semana":
				if (cell.getRowIndex() == 0)
					cell.setCellValue("Semana: " + this.diasCorteList.get(2).getNumeroSemana() + " - "
							+ this.getFechaFormateada(this.diasCorteList.get(2).getFechaCorte()));

				break;

			case "3er Grupo":
				if (cell.getRowIndex() == 0)
					cell.setCellValue("Total Semana: " + this.diasCorteList.get(2).getNumeroSemana());

				break;

			case "4ta semana":
				if (cell.getRowIndex() == 0)
					cell.setCellValue("Semana: " + this.diasCorteList.get(3).getNumeroSemana() + " - "
							+ this.getFechaFormateada(this.diasCorteList.get(3).getFechaCorte()));

				break;

			case "4to Grupo":
				if (cell.getRowIndex() == 0)
					cell.setCellValue("Total Semana: " + this.diasCorteList.get(3).getNumeroSemana());
				break;

			case "5ta semana":
				if (cell.getRowIndex() == 0)
					cell.setCellValue("Semana: " + this.diasCorteList.get(4).getNumeroSemana() + " - "
							+ this.getFechaFormateada(this.diasCorteList.get(4).getFechaCorte()));

				break;

			case "5to Grupo":
				if (cell.getRowIndex() == 0)
					cell.setCellValue("Total Semana: " + this.diasCorteList.get(4).getNumeroSemana());

				break;

			case "6ta semana":

				if (cell.getRowIndex() == 0)
					cell.setCellValue("Semana: " + this.diasCorteList.get(5).getNumeroSemana() + " - "
							+ this.getFechaFormateada(this.diasCorteList.get(5).getFechaCorte()));

				break;

			case "6to Grupo":
				if (cell.getRowIndex() == 0)
					cell.setCellValue("Total Semana: " + this.diasCorteList.get(5).getNumeroSemana());

				break;

			case "7ma semana":

				if (cell.getRowIndex() == 0)
					cell.setCellValue("Semana: " + this.diasCorteList.get(6).getNumeroSemana() + " - "
							+ this.getFechaFormateada(this.diasCorteList.get(6).getFechaCorte()));

				break;

			case "7mo Grupo":
				if (cell.getRowIndex() == 0)
					cell.setCellValue("Total Semana: " + this.diasCorteList.get(6).getNumeroSemana());

				break;

			case "8ava semana":
				if (cell.getRowIndex() == 0)
					cell.setCellValue("Semana: " + this.diasCorteList.get(7).getNumeroSemana() + " - "
							+ this.getFechaFormateada(this.diasCorteList.get(7).getFechaCorte()));

				break;

			case "8avo Grupo":
				if (cell.getRowIndex() == 0)
					cell.setCellValue("Total Semana: " + this.diasCorteList.get(7).getNumeroSemana());

				break;

			case "9na semana":
				if (cell.getRowIndex() == 0)
					cell.setCellValue("Semana: " + this.diasCorteList.get(8).getNumeroSemana() + " - "
							+ this.getFechaFormateada(this.diasCorteList.get(8).getFechaCorte()));

				break;
			case "9no Grupo":
				if (cell.getRowIndex() == 0)
					cell.setCellValue("Total Semana: " + this.diasCorteList.get(8).getNumeroSemana());

				break;

			case "10ma semana":
				if (cell.getRowIndex() == 0)
					cell.setCellValue("Semana: " + this.diasCorteList.get(9).getNumeroSemana() + " - "
							+ this.getFechaFormateada(this.diasCorteList.get(9).getFechaCorte()));
				break;

			case "10mo Grupo":
				if (cell.getRowIndex() == 0)
					cell.setCellValue("Total Semana: " + this.diasCorteList.get(9).getNumeroSemana());

				break;

			}

		}
		sheet.createRow(1);
		// Aqui ya agregue una linea en la segunda posicion del excel ahora voy
		// a iterar por las dos filas que son el encabezado
		// Get iterator to all the rows in current sheet

		HSSFRow header0 = sheet.getRow(0);
		Map<Integer, String> cabecera1 = new HashMap<>();
		Map<Integer, String> cabecera2 = new HashMap<>();

		Stream<Cell> cellStream = ConverterStream.asStream(header0.cellIterator());

		cellStream.forEach(cell -> {
			String valor = cell.toString();
			if (cell.toString().contains("-")) {
				List<String> initials = Arrays.stream(valor.split(" ")).collect(Collectors.toList());
				String valor2 = initials.stream().filter(x -> initials.indexOf(x) > 2).collect(Collectors.joining());

				cabecera2.put(cell.getColumnIndex(), initials.get(0) + " " + initials.get(1));
				cabecera1.put(cell.getColumnIndex(), "S." + valor2);
				cell.setCellValue(initials.get(0) + " " + initials.get(1));

			} else {
				cabecera1.put(cell.getColumnIndex(), valor);
				cell.setCellValue("");
				cell.setCellType(XSSFCell.CELL_TYPE_BLANK);
			}
		});

		HSSFRow header2 = sheet.createRow(1);
		cabecera1.forEach((k, v) -> {
			header2.createCell(k).setCellType(XSSFCell.CELL_TYPE_STRING);
			header2.getCell(k).setCellValue(v);
		}

		);

		this.postDescarga(document);
	}

	public void postDescarga(Object document) {
		HashSet omitirColumnas = new HashSet<Integer>();
		omitirColumnas.add(new Integer(0));
		omitirColumnas.add(new Integer(1));
		omitirColumnas.add(new Integer(2));
		FormatoExcelPoi.formatearArchivoExcel(document, omitirColumnas, 1);
	}

	public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
		Document pdf = (Document) document;
		pdf.open();
		pdf.setPageSize(PageSize.A4);

		InputStream stream = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext())
				.getResourceAsStream("/resources/images/primefaces.jpg");
		byte[] logoBytes = IOUtils.toByteArray(stream);
		Image image = Image.getInstance(logoBytes);
		image.scalePercent(50);
		pdf.add(image);
	}

	public String getFechaFormateada(Date fechaFormatear) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		LocalDate localDate = fechaFormatear.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		int year = localDate.getYear();
		int month = localDate.getMonthValue();
		int day = localDate.getDayOfMonth();
		LocalDateTime xmas = LocalDateTime.of(year, month, day, 0, 0);
		String formattedDateTime = localDate.format(formatter);

		return formattedDateTime;

	}

	public List<Date> getSemanaInicialList() {
		return semanaInicialList;
	}

	public void setSemanaInicialList(List<Date> semanaInicialList) {
		this.semanaInicialList = semanaInicialList;
	}

	public List<TablaCxcpivote> getTablaCxcpivoteList() {
		return tablaCxcpivoteList;
	}

	public void setTablaCxcpivoteList(List<TablaCxcpivote> tablaCxcpivoteList) {
		this.tablaCxcpivoteList = tablaCxcpivoteList;
	}

	public TablaCxcpivote getTablaCxcpivoteSelected() {
		return tablaCxcpivoteSelected;
	}

	public void setTablaCxcpivoteSelected(TablaCxcpivote tablaCxcpivoteSelected) {
		this.tablaCxcpivoteSelected = tablaCxcpivoteSelected;
	}

	public List<TablaCxcpivote> getTablaCxcpivoteListFiltered() {
		return tablaCxcpivoteListFiltered;
	}

	public void setTablaCxcpivoteListFiltered(List<TablaCxcpivote> tablaCxcpivoteListFiltered) {
		this.tablaCxcpivoteListFiltered = tablaCxcpivoteListFiltered;
	}

	public Reporte getRpt() {
		return rpt;
	}

	public void setRpt(Reporte rpt) {
		this.rpt = rpt;
	}

	public HashMap<String, Object> getParametros() {
		return parametros;
	}

	public void setParametros(HashMap<String, Object> parametros) {
		this.parametros = parametros;
	}

	public List<Semanatemp> getSemanatempList() {
		return semanatempList;
	}

	public void setSemanatempList(List<Semanatemp> semanatempList) {
		this.semanatempList = semanatempList;
	}

	public List<FechasCorte> getDiasCorteList() {
		return diasCorteList;
	}

	public void setDiasCorteList(List<FechasCorte> diasCorteList) {
		this.diasCorteList = diasCorteList;
	}

	public List<FechasCorte> getDiasCorteListSelected() {
		return diasCorteListSelected;
	}

	public void setDiasCorteListSelected(List<FechasCorte> diasCorteListSelected) {
		this.diasCorteListSelected = diasCorteListSelected;
	}

	public List<Boolean> getColumnasShow() {
		return columnasShow;
	}

	public void setColumnasShow(List<Boolean> columnasShow) {
		this.columnasShow = columnasShow;
	}

    public List<BigDecimal> getSumaColumnas() {
        return SumaColumnas;
    }

    public void setSumaColumnas(List<BigDecimal> sumaColumnas) {
        SumaColumnas = sumaColumnas;
    }

    public List<BigDecimal> getSumaFilas() {
        return SumaFilas;
    }

    public void setSumaFilas(List<BigDecimal> sumaFilas) {
        SumaFilas = sumaFilas;
    }
}