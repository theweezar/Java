/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chuong2_Tong2soab_UDP;
import com.sun.org.apache.bcel.internal.util.ByteSequence;
import java.io.*;
import java.net.*;
/**
 *
 * @author ASUS
 */
public class Client_UDP {
    public static void main(String[] args) throws SocketException {
        //tao moi socket client
        DatagramSocket clientsocket = new DatagramSocket();
        System.out.println("kết nối server");
        // nhập yêu cầu người dùng
        DataInputStream in = new DataInputStream(System.in);
        int a,b,tong;
        try
        {
            System.out.println("nhập số a: ");
            a = Integer.parseInt(in.readLine());
            System.out.println("nhập số b: ");
            b = Integer.parseInt(in.readLine());
            // khai bao mang byte chứa dữ liệu gửi đi
            byte out[];
            byte out1[];
            // chuyển kiểu dữ liệu int -> string
            String S1 = String.valueOf(a);
            String S2 = String.valueOf(b);
            // chuyển kiểu dữ liệu string -> byte và dduawd vào mảng đã khai báo
            out = S1.getBytes();
            out1 = S2.getBytes();
            //lấy kích thước cua mảng
            int leng1 = out.length;
            int leng2 = out1.length;
            // địa chỉ máy chủ
            InetAddress address = InetAddress.getByName("localhost");
            //số port
            int port = 1024;
            // tạo gói dữ liệu đi
            DatagramPacket guisoa = new DatagramPacket(out, leng1, address, port);
            DatagramPacket guisob = new DatagramPacket(out1, leng2, address, port);
            //gửi gói lên server
            clientsocket.send(guisoa);
            clientsocket.send(guisob);
            // tạo gói để nhận dữ liệu về
            byte inserver[];
            inserver = new byte[256];
            // kích thước mảng nhận dữ liệu về
            int leng3 = inserver.length;
            //tạo gói nhận dữ liệu về
            DatagramPacket tuser = new DatagramPacket(inserver, leng3);
            // nhận gói trả về
            clientsocket.receive(tuser);
            // khai báo biến để chuyển từ kiểu byte sang kiểu string
           String data ;
           data = (new String(tuser.getData(),0, tuser.getLength())).trim();
            System.out.println("kết quả: "+ data);
            clientsocket.close();
        }
        catch(IOException e)
        {}
    }
}
