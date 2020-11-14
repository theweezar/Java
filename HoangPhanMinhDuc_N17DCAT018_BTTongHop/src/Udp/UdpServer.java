/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Udp;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;

/**
 *
 * @author hpmdu
 */
public class UdpServer {
    
    private DatagramSocket server = null;
    public String duongdi = "";
    
    public UdpServer() throws IOException {
        server = new DatagramSocket(1235);
        System.out.println("Kết nối server UDP thành công");
    }
    
    public DatagramPacket newPacket(){
        byte[] buf = new byte[256];
        DatagramPacket i = new DatagramPacket(buf, buf.length);
        return i;
    }
    
    public void sendPacket(byte[] buf, InetAddress address, int port) throws IOException {
        DatagramPacket toClient = new DatagramPacket(buf, buf.length, address, port);
        server.send(toClient);
    }
    
    public PacketProp receivePacket() throws IOException {
        byte[] buf = new byte[256];
        DatagramPacket dPacket = new DatagramPacket(buf, buf.length);
        server.receive(dPacket);
        String strRec = new String(dPacket.getData(), 0, buf.length).trim();
        PacketProp p = new PacketProp(strRec, dPacket.getAddress(), dPacket.getPort());
        return p;
    }
    
    public boolean validateLogin() throws IOException{
        boolean ok = false;
        String username = receivePacket().getData();
        String password = receivePacket().getData();
        Connection conn = new MssqlConnection().getConnection();
        try{
            String sql = "SELECT * FROM USERS WHERE USERNAME = ? AND PASSWORD = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                ok = true;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return ok;
    }
    
    public boolean userNameExist(String username, String password) throws IOException {
        boolean exist = false;
        Connection conn = new MssqlConnection().getConnection();
        try{
            String sql = "SELECT * FROM USERS WHERE USERNAME = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                exist = true;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return exist;
    }
    
    public void insert(String username, String password) throws IOException {
        Connection conn = new MssqlConnection().getConnection();
        try{
            System.out.printf("Username: %s\nPassword: %s", username, password);
            String sql = "INSERT INTO USERS VALUES(?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.execute();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public int min(int x, int y, int z) {
        int past = 0;
        if (x < y) {
            past = (x < z) ? x : z; 
        }
        else{
            past = (y < z) ? y : z; 
        }
        duongdi += String.format("%d ", past);
        return past;
    }  
    
    public int minCost(int cost[][], int m, int n) { 
        if (n < 0 || m < 0) 
            return Integer.MAX_VALUE; 
        else if (m == 0 && n == 0) 
            return cost[m][n]; 
        else
            return cost[m][n] +  
                min(minCost(cost, m-1, n-1), 
                     minCost(cost, m-1, n),  
                     minCost(cost, m, n-1) ); 
    } 
    
    public String analizeFile(File f) throws FileNotFoundException{
        Scanner readFile = new Scanner(f);
        String data = "";
        while(readFile.hasNextLine()){
            data += readFile.nextLine() + "\n";
        }
        System.out.println(data.trim());
        data = data.trim();
        int hang = data.split("\n").length;
        int cot = data.split("\n")[0].split("\\s+").length;
        System.out.printf("\nHàng: %d, cột: %d\n", hang, cot);
        int[][] cost = new int[hang][cot];
        String[] strHang = data.split("\n");
        System.out.println();
        for(int i = 0; i < cost.length; i++){
            String[] strCot = strHang[i].split("\\s+");
            for(int j = 0; j < cost[i].length; j++){
                cost[i][j] = Integer.parseInt(strCot[j].trim());
                System.out.printf("%d_", cost[i][j]);
            }
        }
        
        int tong = minCost(cost, hang - 1, cot - 1);
        String kq = String.format("Tổng: %d", tong);
        return kq;
    }
    
    public void run() throws IOException{
        int opt = -1;
        while(opt != 0){
            PacketProp p = receivePacket();
            try{
                opt = Integer.parseInt(p.getData());
            }
            catch(NumberFormatException e){
                opt = -1;
            }
            switch(opt){
                case 1:
                    if (validateLogin()){
                        // Làm gì đó ...
                        sendPacket("1".getBytes(), p.getAddress(), p.getPort());
                        // Nhận đường link file
                        while(true){
                            PacketProp fPacket = receivePacket();
                            File f = new File(fPacket.getData());
                            if (!f.exists()) sendPacket("0".getBytes(), fPacket.getAddress(), fPacket.getPort());
                            else{
                                sendPacket("Đã nhận được file. Tiến hành phân tích".getBytes(), fPacket.getAddress(), fPacket.getPort());
                                String kq = analizeFile(f);
                                sendPacket(kq.getBytes(), fPacket.getAddress(), fPacket.getPort());
                            }
                        }
                    }
                    else sendPacket("Đăng nhập không thành công, nhập lại Username và Password".getBytes(), p.getAddress(), p.getPort());
                    break;
                case 2:
                    String username = receivePacket().getData().trim();
                    String password = receivePacket().getData().trim();
                    if (userNameExist(username, password)){
                        sendPacket("Username đã tồn tại, vui lòng nhập lại".getBytes(), p.getAddress(), p.getPort());
                    }
                    else {
                        insert(username, password);
                        sendPacket("Thành công".getBytes(), p.getAddress(), p.getPort());
                    }
                    break;
                case 0:
                    System.out.println("Thoát");
                    server.close();
                    break;
                default:
                    System.out.println("Nhập sai lựa chọn. Vui lòng nhập lại.");
                    break;
            }
            if (opt != 0) sendPacket("".getBytes(), p.getAddress(), p.getPort());
        }
    }
    
    public static void main(String[] args) throws IOException {
        UdpServer server = new UdpServer();
        server.run();
    }
}
