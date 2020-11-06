/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Udp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

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
    
    public void option1() throws IOException{
        PacketProp source = receivePacket();
        PacketProp dest = receivePacket();
        String kq = moveFile(new File(source.getData()), new File(dest.getData()));
        sendPacket(kq.getBytes(), source.getAddress(), source.getPort());
    }
    
    public String moveFile(File source, File dest) throws IOException{
        String kq = "";
        if (!source.exists()){
            kq = "File nguồn không tồn tại";
        }
        else if (dest.exists()){
            kq = "File đích đã tồn tại";
        }
        else{
            InputStream is = null;
            OutputStream os = null;
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            kq = "Di chuyển thành công";
            is.close();
            os.close();
        }
        return kq;
    }
    
    public int[] sapxepgiamdan(int[] arr_t){
        for(int i = 0; i < arr_t.length; i++){
            for(int j = i; j < arr_t.length; j++){
                if (arr_t[i] < arr_t[j]){
                    int tmp = arr_t[i];
                    arr_t[i] = arr_t[j];
                    arr_t[j] = tmp;
                }
            }
        }
        return arr_t;
    }
    
    public static boolean isSNT(int s){
        for(int i = 2; i <= (int)Math.sqrt(s); i++){
            if (s % i == 0) return false;
        }
        return true;
    }
    
    public void option2() throws IOException{
        // Nhận data
        PacketProp pdata = receivePacket();
        // Tách data ra 2 phần, 1 phần là số hàng cột, 1 phần là tổ hợp số nguyên
        String[] s1  = pdata.getData().trim().split(";");
        System.out.println(pdata.getData());
        // m là hàng, n là cột
        int m, n;
        // Tách ra để gán cho m và n
        String[] s2 = s1[0].split(",");
        m = Integer.parseInt(s2[0]);
        n = Integer.parseInt(s2[1]);
        // Tách tổ hợp số ra để vào trong mảng số nguyên
        String[] s3 = s1[1].trim().split(" ");
        int[] arr = new int[s3.length];
        for(int i = 0; i < s3.length; i++){
            arr[i] = Integer.parseInt(s3[i]);
        }
        // Sắp xếp ma trận nhỏ dần
        int[] arr_t = sapxepgiamdan(arr);
        // Tìm 2 số lớn nhất và vị trí của chúng (3,3,2,2,2,1) lớn nhất là 3,2 vị trí 1,2,3,4,5
        int max1 = arr_t[0];
        int max2 = 0;
        System.out.println("Sau khi sắp xếp: ");
        for(int i = 1; i < arr_t.length; i++){
            if (arr_t[i] != max1) {
                max2 = arr_t[i];
                break;
            }
        }
        System.out.printf("max1: %d, max2: %d\n", max1, max2);
        // Phân tích vị trí của 2 số đó
        for(int i = 0; i < s3.length; i++){
            arr[i] = Integer.parseInt(s3[i]);
        }
        System.out.println("Mảng arr:");
        for(int i = 0; i < arr.length; i++){
            if (i % n == 0) System.out.println();
            System.out.printf("%d ", arr[i]);
        }
        System.out.println();
        String p1 = "";
        for(int i = 0; i < arr.length; i++){
            int hang = i / n;
            int cot = i % n;
            if (arr[i] == max1) p1 += String.format("[%d][%d] ", hang, cot);
        }
        String p2 = "";
        for(int i = 0; i < arr.length; i++){
            int hang = i / n;
            int cot = i % n;
            if (arr[i] == max2) p2 += String.format("[%d][%d] ", hang, cot);
        }
        String k1 = String.format("Vị trí max1: %d là %s\nVị trí max2: %d là %s\n", max1, p1, max2, p2);
//        System.out.println(k1);
        // Tìm số nguyên tố
        String snt = "";
        int tong = 0;
        for(int i = 0; i < arr.length; i++){
            if (isSNT(Math.abs(arr[i])) && Math.abs(arr[i]) != 1 && arr[i] != 0){
                tong += arr[i];
                snt += arr[i] + " ";
            }
        }
        k1 += "\nTập hợp các số nguyên tố: " + snt.trim() + "\nTổng các số nguyên tố: " + tong; 
        System.out.println(k1);
        sendPacket(k1.getBytes(), pdata.getAddress(), pdata.getPort());
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
                    System.out.println("Bài 1");
                    option1();
                    sendPacket("Server đã nhận option 1".getBytes(), p.getAddress(), p.getPort());
                    break;
                case 2:
                    System.out.println("Bài 2");
                    option2();
                    sendPacket("Server đã nhận option 2".getBytes(), p.getAddress(), p.getPort());
                    break;
                case 0:
                    System.out.println("Thoát");
                    server.close();
                    break;
                default:
                    System.out.println("Nhập sai lựa chọn. Vui lòng nhập lại.");
                    sendPacket("Server nhận sai lựa chọn".getBytes(), p.getAddress(), p.getPort());
                    break;
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        UdpServer server = new UdpServer();
        server.run();
    }
}
