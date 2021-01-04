/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

/**
 *
 * @author hpmdu
 */
public class UdpServer {
    
    private DatagramSocket server = null;
    
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
    
    public void insert(String hoten, String mssv, String malop, double[] diem, double dgk){
        Connection conn = new MssqlConnection().getConnection();
        try{
            String sql = "INSERT INTO BANGDIEM VALUES(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setNString(1, hoten);
            ps.setNString(2, mssv);
            ps.setNString(3, malop);
            ps.setDouble(4, diem[0]);
            ps.setDouble(5, diem[1]);
            ps.setDouble(6, diem[2]);
            ps.setDouble(7, dgk);
            ps.execute();
        }
        catch(SQLException e){
            System.out.println("Có lỗi xảy ra khi lưu dữ liệu");
            e.printStackTrace();
        }
    }
    
    public String select(){
        Connection conn = new MssqlConnection().getConnection();
        String t = "IDDiem\tHoTen\t\tMaSV\t\tIDLop\t\tDBT1\t\tDBT2\t\tDBT3\t\tDGK\n";
        try{
            String sql = "SELECT * FROM BANGDIEM";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                t += String.format("%d\t%s\t%s\t%s\t\t%.2f\t\t%.2f\t\t%.2f\t\t%.2f\n", 
                        rs.getInt("IDDiem"),
                        rs.getNString("HoVaTen"),
                        rs.getNString("MaSV"),
                        rs.getNString("IDLop"),
                        rs.getDouble("DBT1"),
                        rs.getDouble("DBT2"),
                        rs.getDouble("DBT3"),
                        rs.getDouble("DGK") );
            }
        }
        catch(SQLException e){
            System.out.println("Có lỗi xảy ra khi lấy dữ liệu");
            e.printStackTrace();
        }
        return t;
    }
    
    public String calcPoint() throws IOException {
        String hoten = receivePacket().getData();
        String mssv = receivePacket().getData();
        String malop = receivePacket().getData();
        String[] bangDiem = receivePacket().getData().split(";");
        double[] diem = new double[3];
        for(int i = 0; i < bangDiem.length; i++){
            diem[i] = Double.parseDouble(bangDiem[i].trim());
        }
        double dgk = (diem[0] + diem[1] + diem[2]) / 3;
        
        // Phần lưu database ở khúc này
        insert(hoten, mssv, malop, diem, dgk);
        // Kết thúc phần lưu database
        
        return String.format("%s %.2f", hoten, dgk);
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
                    System.out.println("Option 1");
                    String kq = calcPoint();
                    sendPacket(kq.getBytes("UTF-8"), p.getAddress(), p.getPort());
                    break;
                case 2:
                    System.out.println("Option 2");
                    sendPacket(select().getBytes("UTF-8"), p.getAddress(), p.getPort());
                    break;
                case 0:
                    System.out.println("Thoát");
                    server.close();
                    break;
                default:
                    System.out.println("Nhập sai lựa chọn. Vui lòng nhập lại.");
                    break;
            }
            if (opt != 0) sendPacket("Server UDP đã nhận yêu cầu".getBytes(), p.getAddress(), p.getPort());
        }
    }
    
    public static void main(String[] args) throws IOException {
        UdpServer server = new UdpServer();
        server.run();
    }
}
