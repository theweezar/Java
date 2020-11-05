/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bai_2;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author hpmdu
 */
public class Udp_Client {
    
    private DatagramSocket client = null;
    private DataInputStream in = null;
    int port = 1234;

    public Udp_Client()throws IOException{
        client = new DatagramSocket();
        in = new DataInputStream(System.in);
    }
    
    public byte[] getData() throws IOException{
        // Nhập chuỗi rồi sau đó chuyển thành bytes
        String s;
        s = in.readLine();
        return s.getBytes();
    }
    
    public DatagramPacket newPacket(){
        byte[] buf = new byte[256];
        DatagramPacket i = new DatagramPacket(buf, buf.length);
        return i;
    }
    
    public void sendPacket(byte[] buf) throws UnknownHostException, IOException{
        InetAddress address = InetAddress.getByName("localhost");
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
        client.send(packet);
    }
    
    public void run() throws UnknownHostException, IOException{
        // Nhập 1 đoạn chuỗi và biến đoạn chuỗi đó thành byte[]. Sau đó gửi cho server
        System.out.print("Nhập đường link nguồn: "); // D:\\MATRANMANG.txt
        byte[] link_from = getData();
        System.out.print("Nhập đường link đích: "); // D:\\udp_copy\\MATRANMANG.txt
        byte[] link_to = getData();
        sendPacket(link_from);
        sendPacket(link_to);
        // Nhận dữ liệu từ server
        byte[] buf = new byte[256];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
//        client.receive(packet);
//        String data = new String(packet.getData(), 0, buf.length);
//        System.out.println(data);
    }
    
    public static void main(String[] args) throws UnknownHostException, IOException{
        Udp_Client client = new Udp_Client();
        client.run();
    }
    
    
}
