/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bai_3;
import java.sql.*;

/**
 *
 * @author hpmdu
 */
public class MssqlConnection {
    
    public Connection getConnection(){
        Connection conn = null;
        String uRL = "jdbc:sqlserver://127.0.0.1:1433;databaseName=LTM";
        String userName = "sa";
        String password = "123";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(uRL, userName, password);
            System.out.println("Ket noi CSDL thanh cong");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Khong ket noi duoc voi CSDL");
        }
        return conn;
    }
    
    public static void main(String[] args) {
        new MssqlConnection().getConnection(); 
    }
}
