/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package JaVaChieuThu3;
package Tcp;
import java.util.*;
import java.io.*;
import java.net.*;
/**
 *
 * @author ASUS
 */
public class server_tong2so_tcp {
    public static void main(String[] args) throws IOException {
        // tao socket server va port cho server
        ServerSocket server = new ServerSocket(1234);
        
        System.out.println("Server đã sẵn sàng");
        // Server chap nhan ket noi voi client
        Socket client = server.accept();
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        DataInputStream dis = new DataInputStream(client.getInputStream());
        System.out.println("client đã kết nối đến server");
        int soa =0;
        int sob =0; int tong2so =0;
        soa = dis.readInt();
       sob = dis.readInt();
       tong2so = soa + sob;
       dos.writeInt(tong2so);
       server.close();
       client.close();
    }
        
}
