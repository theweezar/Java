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
                System.out.printf("Username: %s - Password: %s \n",result.getString("username")
                ,result.getString("pass"));
            }
        }
        catch(SQLException err){
            err.printStackTrace();
        }
    }
    
    public void register(User newuser){
        try{
            String query = "INSERT INTO users (username,pass,fullname,pnumber,email,administrator) VALUES(?,?,?,?,?,?)";
            PreparedStatement stt = conn.prepareStatement(query);
            stt.setString(1, newuser.getUsername());
            stt.setString(2, newuser.getPassword());
            stt.setString(3, newuser.getFullname());
            stt.setString(4, newuser.getPhonenumber());
            stt.setString(5, newuser.getEmail());
            stt.setBoolean(6, newuser.isAdministrator());
            stt.execute();
        }
        catch(SQLException err){
            err.printStackTrace();
        }
    }
    
    public User validate(String username, String password){
        try{
//            Statement stt = conn.createStatement();
//            ResultSet result = stt.executeQuery("SELECT * FROM users WHERE username = '" + username + "' AND pass = '" + password + "'");
            String query = "SELECT * FROM users WHERE username = ? AND pass = ?";
            PreparedStatement stt = conn.prepareStatement(query);
            stt.setString(1, username);
            stt.setString(2, password);
            ResultSet result = stt.executeQuery();
            result.next();
            if (result.getRow() == 1){
                return new User(result.getString("username"),
                                result.getString("pass"),
                                result.getBoolean("administrator"),
                                result.getString("fullname"),
                                result.getString("pnumber"),
                                result.getString("email"));
            }
        }
        catch(SQLException err){
            err.printStackTrace();
        }
        return null;
    }
    
    public static void main(String[] args) {
        UserManagement usermg = new UserManagement();
//        usermg.getalluser();
//        usermg.register(new User("nhat","nhat","Nhat Cuong"));
        System.out.println(usermg.validate("admin", "admin").getFullname());
    }
}
