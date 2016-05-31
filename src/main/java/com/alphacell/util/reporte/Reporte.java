package com.alphacell.util.reporte;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRXlsExporter;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by luis on 12/05/16.
 * Esta clase me sirve para crear reportes en pdf y en excel
 */
public class Reporte {

    Connection conn;

    public Reporte() {

    }


    public void ExportarPDF(HashMap<String, Object> parametros, String path, String nombre_reporte)
            throws JRException, IOException
    {
        this.conn=ConecctionMssqlServer.Conectar();

        File jasper= new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(path));
        JasperPrint jasperPrint= JasperFillManager.fillReport(jasper.getPath(),parametros,conn);
        HttpServletResponse response= (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-diposition","attachment; filename="+nombre_reporte+".pdf");

        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint,stream);

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();

    }

    public void verPDF(HashMap<String, Object> parametros, String path, String nombre_reporte)
            throws JRException, IOException{

        this.conn=ConecctionMssqlServer.Conectar();
        File jasper= new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(path));

        byte[] bytes= JasperRunManager.runReportToPdf(jasper.getPath(),parametros,conn);
        HttpServletResponse response=(HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);

        ServletOutputStream outputStream= response.getOutputStream();
        outputStream.write(bytes,0,bytes.length);

        outputStream.flush();
        outputStream.close();

        FacesContext.getCurrentInstance().responseComplete();

    }


    public void exportarXLS(HashMap<String, Object> parametros, String path, String nombre_reporte)
        throws JRException, IOException{

        this.conn=ConecctionMssqlServer.Conectar();

//        parametros.put(JRParameter.REPORT_LOCALE, new Locale("es_EC","Ecuador"));
        parametros.put(JRParameter.REPORT_LOCALE, Locale.GERMANY);


        File jasper= new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(path));
        JasperPrint jasperPrint= JasperFillManager.fillReport(jasper.getPath(),parametros,conn);

        HttpServletResponse response= (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition","attachment; filename="+nombre_reporte+".xls");

        ServletOutputStream stream = response.getOutputStream();
        JRXlsExporter exporter= new JRXlsExporter();


        exporter.setParameter(JRExporterParameter.JASPER_PRINT,jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,stream);
        exporter.exportReport();

        stream.flush();
        stream.close();

        FacesContext.getCurrentInstance().responseComplete();

    }
}
