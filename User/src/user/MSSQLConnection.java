/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Admin
 */
public class MSSQLConnection {
    
    public Connection getConnection() { 
        Connection conn = null;
        String uRL = "jdbc:sqlserver://;databaseName=QLKS";
        String userName = "sa";
        String password = "123";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(uRL, userName, password);
            System.out.println("Connect to MSSQL successfully");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Connect failed");
        }
        return conn;
    }
    
}

