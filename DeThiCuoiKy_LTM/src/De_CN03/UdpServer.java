/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package De_CN03;

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
        PacketProp p = receivePacket();
        System.out.println(p.getData());
    }
    
    public static void main(String[] args) throws IOException {
        UdpServer server = new UdpServer();
        server.run();
    }
}
