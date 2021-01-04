/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Udp;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Base64;
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
        byte[] buf = new byte[1024*1024];
        DatagramPacket dPacket = new DatagramPacket(buf, buf.length);
        client.receive(dPacket);
        String strRec = new String(dPacket.getData(), 0, buf.length).trim();
        return strRec;
    }
    
    public int menu() throws IOException {
        String[] items = {
            "Nhập dữ liệu",
            "In danh sách",
        };
        int opt = 0;
        System.out.println("------------------------------------");
        System.out.println("| Hoàng Phan Minh Đức - N17DCAT018 |");
        System.out.println("|               Menu               |");
        System.out.println("------------------------------------");
        for(int i = 0; i < items.length; i++){
            System.out.printf("%d. %s\n", i + 1, items[i]);
        }
        System.out.print("Chọn (Nhập 0 để thoát): ");
        try{
            opt = scan.nextInt();
        }
        catch(InputMismatchException e){
            scan.next();
            return -1;
        }
        return opt;
    }
    
    public double getDouble(String title){
        double d = 0;
        while(true){
            try{
                System.out.print(title);
                d = scan.nextDouble();
                if (d < 0 || d > 10) System.out.println("Nhập điểm >= 0 hoặc <= 10");
                else return d;
            }
            catch(InputMismatchException e){
                System.out.println("Nhập sai ký tự");
                scan.next();
            }
        }
    }
    
    public void input() throws IOException {
        scan.nextLine();
        System.out.print("Nhập họ tên: ");
        String hoten = scan.nextLine();
        System.out.print("Nhập mã sv : ");
        String mssv = scan.nextLine();
        System.out.print("Nhập mã lớp: ");
        String malop = scan.nextLine();
        double d1 = getDouble("Nhập điểm bài tập 1: ");
        double d2 = getDouble("Nhập điểm bài tập 2: ");
        double d3 = getDouble("Nhập điểm bài tập 3: ");
        sendPacket(hoten.getBytes("UTF-8"));
        sendPacket(mssv.getBytes("UTF-8"));
        sendPacket(malop.getBytes("UTF-8"));
        sendPacket(String.format("%f;%f;%f", d1, d2, d3).getBytes());
    }
    
    public void run() throws IOException{
        int opt = -1;
        while(opt != 0){
            opt = menu();
            // Vì opt vẫn là int nên ta dùng nó trong switch case
            switch(opt){
                case 1:
                    sendPacket(String.format("%d", opt).getBytes());
                    input();
                    System.out.println(receiveData());
                    break;
                case 2:
                    System.out.println("Option 2");
                    sendPacket(String.format("%d", opt).getBytes());
                    break;
                case 0:
                    sendPacket(String.format("%d", opt).getBytes());
                    System.out.println("Thoát");
                    scan.close();
                    in.close();
                    client.close();
                    break;
                default:
                    System.out.println("Nhập sai lựa chọn. Vui lòng nhập lại.");
                    sendPacket("-1".getBytes());
                    break;
            }
            if (opt != 0) {
                String str = receiveData();
                System.out.println(str);
            }
        }
    }
    
    public static void main(String[] args) throws IOException{
        UdpClient client = new UdpClient();
        client.run();
    }
}
