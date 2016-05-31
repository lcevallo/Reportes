package com.alphacell.util.reporte;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by luis on 12/05/16.
 */
public class ConecctionMssqlServer {

    public static Connection getConn() {
        return conn;
    }

    public static void setConn(Connection conn) {
        ConecctionMssqlServer.conn = conn;
    }

    private static Connection conn;




    public static Connection Conectar()
    {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url=MyUtil.getDriverSqlserver();
            Properties props= new Properties();
            props.setProperty("user",MyUtil.getUSUARIO());
            props.setProperty("password",MyUtil.getCLAVE());
            conn= DriverManager.getConnection(url,props);
            return conn;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

}
