package com.alphacell.controller.financiero;

import com.alphacell.model.Semanatemp;
import com.alphacell.model.cartera.TablaCxcpivote;
import com.alphacell.model.financiero.FechasCorte;
import com.alphacell.repository.CxCFlujoRepository;
import com.alphacell.util.ManejoFechas;
import com.alphacell.util.jsf.FormatoExcelPoi;
import com.alphacell.util.reporte.Reporte;
import com.lowagie.text.*;
import net.sf.jasperreports.engine.JRException;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.stream.IntStream;


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

    private TablaCxcpivote tablaCxcpivoteSelected;
    private Date fechaInicialBusqueda;
    private int  NumeroSemana;
    private Date fechaFinalBusqueda;
    private Reporte rpt;
    private HashMap<String, Object> parametros;

    @Inject
    private CxCFlujoRepository cxCFlujoRepository;


    @PostConstruct
    public void init()
    {

        this.columnasShow=new ArrayList<>();
        IntStream.rangeClosed(1,10).forEach(n->this.columnasShow.add(false));

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


    public void generarNumeroSemana(){
        this.NumeroSemana= ManejoFechas.getNumeroSemana(this.fechaInicialBusqueda);
        this.semanaInicialList =ManejoFechas.FechasExtremas(this.fechaInicialBusqueda);
        Iterator i = this.semanaInicialList.iterator();
        while (i.hasNext())
        {
            Date fecha2 = (Date) i.next();
        }
    }

    public void abrirDialogo()
    {

/*
        Map<String,Object> opciones=new HashMap<>();
        opciones.put("modal",true);
        opciones.put("resizable", false);
        opciones.put("contentHeight", 500);


        RequestContext.getCurrentInstance().update(
                Arrays.asList("fmrcxc-fechascortes:tablecxc-fechascortes"));

        RequestContext.getCurrentInstance().openDialog("/dialogo/financiero/cxcFlujoFechasCortes",opciones,null);
        //  RequestContext.getCurrentInstance().openDialog("/WEB-INF/dialog/finaciero/cxcFlujoFechasCortes",opciones,null);

        */

        this.diasCorteList=ManejoFechas.FechasCortes(this.semanaInicialList.stream().findFirst().get(),this.NumeroSemana,10);

        RequestContext.getCurrentInstance().update(
                Arrays.asList("cxcFechaCorteDialog","tablecxc-fechascortes"));


        RequestContext.getCurrentInstance().execute("PF('cxcFechaCorteDialogWidget').show();");


    }


    public Boolean mostrarColumna(Integer columna)
    {

       Integer numeroSemana= this.diasCorteList.get(columna).getNumeroSemana();

       return this.diasCorteListSelected.stream().anyMatch(c->c.getNumeroSemana().equals(numeroSemana));

    }


    public void changeValueShowColumnSelected(SelectEvent event)
    {

        FechasCorte fechasCorteSelected;
        fechasCorteSelected = (FechasCorte) event.getObject();
        int indice= this.diasCorteList.indexOf(fechasCorteSelected);

        if(indice!=-1)
        this.columnasShow.set(indice,true);
    }


    public void changeValueShowColumnUnSelected(SelectEvent event) {

        FechasCorte fechasCorteSelected;
        fechasCorteSelected = (FechasCorte) event.getObject();
        int indice= this.diasCorteList.indexOf(fechasCorteSelected);

        if(indice!=-1)
            this.columnasShow.set(indice,false);

    }

    public void cargarTablaFlujo()
    {
        this.semanatempList=cxCFlujoRepository.buscar8Semanas(this.semanaInicialList.get(0));
        this.tablaCxcpivoteList=cxCFlujoRepository.buscarPivoteFlujo(this.semanaInicialList.get(0));

        this.tablaCxcpivoteList.stream().filter(z->z!=null)
                .forEach(x->{
           for(int i=0;i<this.diasCorteListSelected.size();i++)
           {
               BigDecimal valor = null;
               Integer numCol = this.diasCorteListSelected.get(i).getNoColumna();

               if(i==0) {

                   valor = this.salidaSumatoriaEntre(x.getListaValores(), 0, numCol);

               }
               else {

                   valor = this.salidaSumatoriaEntre(x.getListaValores(), this.diasCorteListSelected.get(i-1).getNoColumna(), numCol);
               }


               switch (numCol)
               {
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

        });
    }

    public BigDecimal salidaSumatoriaEntre(List<BigDecimal> lista,Integer limiteInf,Integer limiteSup )
    {
      return lista.stream().skip(limiteInf).limit( (limiteSup-limiteInf)+1).filter(c -> c != null).reduce(BigDecimal.ZERO,BigDecimal::add);

    }

    public void exportarExcelFlujoCxCFinanciero(){

        rpt= new Reporte();
        parametros= new HashMap<String, Object>();

        LocalDate localDate = this.semanaInicialList.get(0).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year  = localDate.getYear();
        int month = localDate.getMonthValue();
        int day   = localDate.getDayOfMonth();


        parametros.put("dia",day);
        parametros.put("mes",month);
        parametros.put("anio",year);


        String path="/reportes/CxCFlujoFinanciero.jasper";


        try{

            rpt.exportarXLS(parametros,path,"CxCFlujoFinanciero");
        } catch (JRException e) {
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error",e.getMessage()));
        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error",e.getMessage()));
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

        HashSet omitirColumnas = new HashSet();

        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow header = sheet.getRow(0);

        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        for(int i=0; i < header.getPhysicalNumberOfCells();i++) {
            HSSFCell cell = header.getCell(i);

            switch (cell.toString())
            {

                case "Codigo Cliente":
                    omitirColumnas.add(new Integer(i));

                    break;

                case "Cliente":
                    omitirColumnas.add(new Integer(i));
                    break;


                case "Credito":
                    if(cell.getRowIndex()>0)
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    break;

                case "Utilizado":
                    if(cell.getRowIndex()>0)
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    break;

                case "Saldo":
                    if(cell.getRowIndex()>0)
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    break;

                case "1era semana":
                    if(cell.getRowIndex()==0)
                        cell.setCellValue("Semana: "+this.diasCorteList.get(0).getNumeroSemana()+" - "+this.getFechaFormateada(this.diasCorteList.get(0).getFechaCorte()));
                    else
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);

                    break;

                case "1er Grupo":
                    if(cell.getRowIndex()==0)
                        cell.setCellValue("Σ Semana: "+this.diasCorteList.get(0).getNumeroSemana());
                    if(cell.getRowIndex()>0)
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    break;

                case "2da semana":
                    if(cell.getRowIndex()==0)
                        cell.setCellValue("Semana: "+this.diasCorteList.get(1).getNumeroSemana()+" - "+this.getFechaFormateada(this.diasCorteList.get(1).getFechaCorte()));
                    else
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    break;


                case "2do Grupo":
                    if(cell.getRowIndex()==0)
                        cell.setCellValue("Σ Semana: "+this.diasCorteList.get(1).getNumeroSemana());
                    if(cell.getRowIndex()>0)
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    break;

                case "3era semana":
                    if(cell.getRowIndex()==0)
                        cell.setCellValue("Semana: "+this.diasCorteList.get(2).getNumeroSemana()+" - "+this.getFechaFormateada(this.diasCorteList.get(2).getFechaCorte()));
                    else
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    break;


                case "3er Grupo":
                    if(cell.getRowIndex()==0)
                        cell.setCellValue("Σ Semana: "+this.diasCorteList.get(2).getNumeroSemana());
                    if(cell.getRowIndex()>0)
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    break;

                case "4ta semana":
                    if(cell.getRowIndex()==0)
                        cell.setCellValue("Semana: "+this.diasCorteList.get(3).getNumeroSemana()+" - "+this.getFechaFormateada(this.diasCorteList.get(3).getFechaCorte()));
                    else
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    break;


                case "4to Grupo":
                    if(cell.getRowIndex()==0)
                        cell.setCellValue("Σ Semana: "+this.diasCorteList.get(3).getNumeroSemana());
                    if(cell.getRowIndex()>0)
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                     break;


                case "5ta semana":
                    if(cell.getRowIndex()==0)
                        cell.setCellValue("Semana: "+this.diasCorteList.get(4).getNumeroSemana()+" - "+this.getFechaFormateada(this.diasCorteList.get(4).getFechaCorte()));
                    else
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    break;


                case "5to Grupo":
                    if(cell.getRowIndex()==0)
                        cell.setCellValue("Σ Semana: "+this.diasCorteList.get(4).getNumeroSemana());
                    if(cell.getRowIndex()>0)
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    break;

                case "6ta semana":

                    if(cell.getRowIndex()==0)
                        cell.setCellValue("Semana: "+this.diasCorteList.get(5).getNumeroSemana()+" - "+this.getFechaFormateada(this.diasCorteList.get(5).getFechaCorte()));
                    else
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    break;

                case "6to Grupo":
                    if(cell.getRowIndex()==0)
                        cell.setCellValue("Σ Semana: "+this.diasCorteList.get(5).getNumeroSemana());
                    if(cell.getRowIndex()>0)
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    break;

                case "7ma semana":

                    if(cell.getRowIndex()==0)
                        cell.setCellValue("Semana: "+this.diasCorteList.get(6).getNumeroSemana()+" - "+this.getFechaFormateada(this.diasCorteList.get(6).getFechaCorte()));
                    else
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    break;

                case "7mo Grupo":
                    if(cell.getRowIndex()==0)
                        cell.setCellValue("Σ Semana: "+this.diasCorteList.get(6).getNumeroSemana());
                    if(cell.getRowIndex()>0)
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    break;

                case "8ava semana":
                    if(cell.getRowIndex()==0)
                        cell.setCellValue("Semana: "+this.diasCorteList.get(7).getNumeroSemana()+" - "+this.getFechaFormateada(this.diasCorteList.get(7).getFechaCorte()));
                    else
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    break;

                case "8avo Grupo":
                    if(cell.getRowIndex()==0)
                        cell.setCellValue("Σ Semana: "+this.diasCorteList.get(7).getNumeroSemana());
                    if(cell.getRowIndex()>0)
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    break;

                case "9na semana":
                    if(cell.getRowIndex()==0)
                        cell.setCellValue("Semana: "+this.diasCorteList.get(8).getNumeroSemana()+" - "+this.getFechaFormateada(this.diasCorteList.get(8).getFechaCorte()));
                    else
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    break;
                case "9no Grupo":
                    if(cell.getRowIndex()==0)
                        cell.setCellValue("Σ Semana: "+this.diasCorteList.get(8).getNumeroSemana());
                    if(cell.getRowIndex()>0)
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    break;

                case "10ma semana":
                    if(cell.getRowIndex()==0)
                        cell.setCellValue("Semana: "+this.diasCorteList.get(9).getNumeroSemana()+" - "+this.getFechaFormateada(this.diasCorteList.get(9).getFechaCorte()));
                    else
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    break;

                case "10mo Grupo":
                    if(cell.getRowIndex()==0)
                        cell.setCellValue("Σ Semana: "+this.diasCorteList.get(9).getNumeroSemana());
                    if(cell.getRowIndex()>0)
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    break;

            }

            FormatoExcelPoi.formatearArchivoExcel(document,omitirColumnas);


        }
    }

    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.A4);

        InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/resources/images/primefaces.jpg");
        byte[] logoBytes =  IOUtils.toByteArray(stream);
        Image image = Image.getInstance(logoBytes);
        image.scalePercent(50);
        pdf.add(image);
    }


    public String getFechaFormateada(Date fechaFormatear)
    {


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate localDate = fechaFormatear.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        int year  = localDate.getYear();
        int month = localDate.getMonthValue();
        int day   = localDate.getDayOfMonth();
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


}
