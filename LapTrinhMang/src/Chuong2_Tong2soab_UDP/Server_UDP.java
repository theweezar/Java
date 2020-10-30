/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chuong2_Tong2soab_UDP;
import java.io.*;
import java.net.*;

/**
 *
 * @author ASUS
 */
public class Server_UDP {
    public static void main(String[] args) throws SocketException, IOException {
        //tao kết nối
        DatagramSocket serverSocket = new DatagramSocket(1024);
        System.out.println("server đã sẵn sàng");
        // tạo mảng chứa dữ liệu gửi lên từ client
        byte infromclient [];
        infromclient = new byte[256];
        byte infromclient1 [];
        infromclient1 = new byte[256];
        //lấy kích thước mảng
        int leng1 = infromclient.length;
        int leng2 = infromclient1.length;
        // tạo gói nhận dữ liệu
        DatagramPacket fromclient = new DatagramPacket(infromclient, leng1);
         DatagramPacket fromclient1 = new DatagramPacket(infromclient1, leng2);
         // nhận goi về
         serverSocket.receive(fromclient);
         serverSocket.receive(fromclient1);
         // tạo biến data kiểu string để lấy dữ liệu
         String data1, data2;
         //lấy dữu liệu về biến data
         data1 = (new String(fromclient.getData(),0,infromclient.length)).trim();
          data2 = (new String(fromclient1.getData(),0,infromclient1.length)).trim();
        // chuyển dữ liệu về kiểu string -> int
        int a,b,tong;
        a = Integer.parseInt(data1);
        b = Integer.parseInt(data2);
        // xử lý yêu cầu
        tong = a+b;
        //chuyển dữ liệu từ int về sting
        data1 = String.valueOf(tong);
        byte outtoclient[];
        outtoclient = data1.getBytes();
        leng1 = outtoclient.length;
       InetAddress add = fromclient.getAddress();
       int port = fromclient.getPort();
       DatagramPacket toclient = new DatagramPacket(outtoclient,leng1,add,port);
       serverSocket.send(toclient);
       serverSocket.close();
    }
}
