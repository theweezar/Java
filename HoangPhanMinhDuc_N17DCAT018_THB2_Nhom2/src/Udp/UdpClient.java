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
    
    public void option1() throws IOException {
        scan.nextLine();
        String source, dest;
        System.out.print("Nhập đường link nguồn: "); // D:\\MATRANMANG.txt
        source = scan.nextLine().trim();
        System.out.print("Nhập đường link đích: "); // D:\\udp_copy\\MATRANMANG.txt
        dest = scan.nextLine().trim();
        sendPacket(source.getBytes());
        sendPacket(dest.getBytes());
        System.out.println(receiveData());
    }
    
    public int getNumber(String title, boolean smallerThanZero){
        int a = 0;
        while(true){
            try{
                System.out.print(title);
                a = scan.nextInt();
                if (smallerThanZero) return a;
                else{
                    if (a >= 0) return a;
                    else System.out.println("Nhập số nguyên dương");
                }
            }
            catch(InputMismatchException e){
                System.out.println("Nhập số nguyên");
                scan.next();
            }
        }
    }
    
    public void option2() throws IOException {
        // m là hàng, n là cột
        int m, n;
        // Nếu đúng điều kiện thì gắn vào chuỗi s để gửi qua server
        m = getNumber("Nhập số hàng m: ", false);
        n = getNumber("Nhập số cột n: ", false);
        String s = String.format("%d,%d;", m, n);
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                int tmp = getNumber(String.format("Nhập ma trận vị trí [%d][%d]: ", i, j), true);
                s += tmp + " ";
            }
        }
        sendPacket(s.getBytes());
        System.out.println(receiveData());
    }
    
    public int menu() throws IOException {
        String[] items = {
            "Di chuyển file",
            "Tìm 2 số lớn nhất và vị trí của chúng trong ma trận, tìm và tính tổng các số nguyên tố trong ma trận đó"
        };
        int opt = 0;
        System.out.println("------------------------------------");
        System.out.println("| Hoàng Phan Minh Đức - N17DCAT018 |");
        System.out.println("|               Menu               |");
        System.out.println("------------------------------------");
        for(int i = 0; i < items.length; i++){
            System.out.printf("%d. %s\n", i + 1, items[i]);
        }
        System.out.print("Chọn: ");
        try{
            opt = scan.nextInt();
        }
        catch(InputMismatchException e){
            scan.next();
            return -1;
        }
        return opt;
    }
    
    public void run() throws IOException{
        int opt = -1;
        while(opt != 0){
            opt = menu();
            // Vì opt vẫn là int nên ta dùng nó trong switch case
            switch(opt){
                case 1:
//                    System.out.println("Option 1");
                    sendPacket(String.format("%d", opt).getBytes());
                    option1();
                    break;
                case 2:
//                    System.out.println("Option 2");
                    sendPacket(String.format("%d", opt).getBytes());
                    option2();
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
                    sendPacket(String.format("%d", opt).getBytes());
                    break;
            }
            if (opt != 0){
                String rcvStr = receiveData();
                System.out.println(rcvStr);
            }
        }
    }
    
    public static void main(String[] args) throws IOException{
        UdpClient client = new UdpClient();
        client.run();
    }
}
