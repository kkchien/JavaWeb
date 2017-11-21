/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.sqlite.SQLiteConfig;

/**
 *
 * @author Administrator
 */
public class DatabaseUtil {
    private static Connection connection = null;
    public static Connection connectSQL(String databaseUrl, String databaseName, String username, String password) {
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            String dbURL = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=java_web";
            String dbURL = "jdbc:sqlserver:" + databaseUrl + ";databaseName=" + databaseName;
//            String username = "sa";
//            String password = "1234$";
            connection = DriverManager.getConnection(dbURL, username, password);
            if (connection != null) {
                DatabaseMetaData dm = (DatabaseMetaData) connection.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());
                System.out.println("Connected");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return connection;

    }

    public static Connection connectSqlite(String databaseUrl, String databaseName, String username, String password) {
        try {
            Class.forName("org.sqlite.JDBC");
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            connection = DriverManager.getConnection("jdbc:sqlite:" + databaseUrl + databaseName + ".db", config.toProperties());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        }
        return connection;
    }

    public static void main(String[] args) {
        connectSQL("//localhost\\SQLEXPRESS", "baithi", "sa", "1234$");
    }
}
