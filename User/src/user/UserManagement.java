/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.sql.*;

/**
 *
 * @author MinhTam
 */
public class UserManagement {
    
    private Connection conn;
    
    public UserManagement(){
        conn = new MSSQLConnection().getConnection();
    }
    
    public void getalluser(){
        try{
            Statement stt = conn.createStatement();
            ResultSet result = stt.executeQuery("SELECT * from users");
            while(result.next()){
                System.out.printf("Username: %s - Password: %s - Fullname: %s\n",result.getString("username")
                ,result.getString("pass"),result.getString("fullname"));
            }
        }
        catch(SQLException err){
            err.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        UserManagement usermg = new UserManagement();
        usermg.getalluser();
    }
}
