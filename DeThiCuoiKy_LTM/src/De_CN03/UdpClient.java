/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package De_CN03;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author hpmdu
 */
public class UdpClient {
    
    private DatagramSocket client = null;
    private DataInputStream in = null;
    private Scanner scan = null;
    private InetAddress address = null;
    int port = 1235;

    public UdpClient()throws IOException{
        client = new DatagramSocket();
        in = new DataInputStream(System.in);
        // Ở đây dùng scanner thay vì DataInputStream cũng được, vì nếu dùng Scanner để nextLine() thì chuỗi đó
        // Vẫn có thể dùng hàm getBytes() để chuyển qua server
        scan = new Scanner(System.in);
        address = InetAddress.getByName("localhost");
    }
    
    public void sendPacket(byte[] buf) throws UnknownHostException, IOException{
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
        client.send(packet);
    }
    
    public String receiveData() throws IOException {
        byte[] buf = new byte[256];
        DatagramPacket dPacket = new DatagramPacket(buf, buf.length);
        client.receive(dPacket);
        String strRec = new String(dPacket.getData(), 0, buf.length).trim();
        return strRec;
    }
    
    public String doc_file_byte(String path) throws IOException {
        System.out.println("Đọc file bằng byte");
        BufferedInputStream buff_in = new BufferedInputStream(new FileInputStream(new File(path)));
        byte[] b = new byte[1024 * 8];
        while(buff_in.read(b) != -1){
//            System.out.println(new String(b));
        }
        System.out.println(new String(b));
        return new String(b);
    }
    
    public String doc_file_scanner(String path) throws IOException {
        System.out.println("Đọc file bằng Scanner");
        Scanner scan_file = new Scanner(new File(path));
        String data = "";
        while(scan_file.hasNextLine()){
            data += scan_file.nextLine();
        }
        System.out.println(data);
        return data;
    }
    
    public void run() throws IOException{
//        doc_file_byte("D:\\input.txt");
        String data_file = doc_file_scanner("D:\\input.txt");
        sendPacket(data_file.getBytes());
    }
    
    public static void main(String[] args) throws IOException{
        UdpClient client = new UdpClient();
        client.run();
    }
}
