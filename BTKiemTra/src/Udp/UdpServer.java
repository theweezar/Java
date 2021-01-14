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
                    
                    break;
                case 2:
                    System.out.println("Option 2");
                    
                    break;
                case 3:
                    System.out.println("Option 3");
                    
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
