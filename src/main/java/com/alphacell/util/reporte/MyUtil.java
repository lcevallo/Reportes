/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphacell.util.reporte;

/**
 *
 * @author luis
 */
public class MyUtil {

    static final String USUARIO="sa";
    static final String CLAVE="...Alpha.2015";


    static final String DRIVER_SQLSERVER="jdbc:sqlserver://192.168.10.21\\MSSQLSERVER:1433;databaseName=Produccion";

    public static String getUSUARIO() {
        return USUARIO;
    }

    public static String getCLAVE() {
        return CLAVE;
    }

    public static String getDriverSqlserver() {
        return DRIVER_SQLSERVER;
    }
}
