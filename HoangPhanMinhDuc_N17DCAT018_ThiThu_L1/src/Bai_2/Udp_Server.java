/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bai_2;

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
public class Udp_Server {

    private DatagramSocket server = null;
    
    public Udp_Server() throws IOException {
        server = new DatagramSocket(1234);
        System.out.println("Server đã sẵn sàng");
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
    
    public void copyFile(File source, File dest) throws FileNotFoundException, IOException{
        InputStream is = null;
        OutputStream os = null;
        is = new FileInputStream(source);
        os = new FileOutputStream(dest);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }
        System.out.println("Copy thành công");
        // Đọc ma trận từ file
        Scanner scan = new Scanner(source);
        String plain = ""; // Đọc nguyên ma trận vào 1 đoạn test
        int arrLen = 0; // Số ký tự của 1 dòng/hàng trong ma trận
        while(scan.hasNextLine()){
            plain = plain + scan.nextLine().trim() + " ";
            // Vòng lặp đầu tiên khi có hàng thứ nhất, ta sẽ split ra và tìm số ký tự của 1 dòng đó rồi gán vào arrLen
            if (arrLen == 0) arrLen = plain.trim().split(" ").length;
        }
        // Split nguyên chuỗi đó vào mảng chuỗi
        String[] pArr = plain.split(" ");
        // Khởi tạo mảng số có độ dài bằng mảng chuỗi
        int[] arr = new int[pArr.length];
        // Biển đổi ký tự dạng chuỗi thành ký tự dạng số nguyên
        for(int i = 0; i < arr.length; i++){
            arr[i] = Integer.parseInt(pArr[i]);
        }
        // In ra màn hình ma trận có sẵn
        for(int i = 0; i < arr.length; i++){
            System.out.printf("%d ", arr[i]);
            if ((i + 1) % arrLen == 0) System.out.println();
        }
        // Sắp xếp tăng dần
        for(int i = 0; i < arr.length; i++){
            for(int j = i; j < arr.length; j++){
                if (arr[i] > arr[j]){
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
        // In ra màn hình kết quả
        String ketqua = "";
        System.out.println("Kết quả:");
        for(int i = 0; i < arr.length; i++){
//            System.out.printf("%d ", arr[i]);
            ketqua += arr[i] + " ";
            if ((i + 1) % arrLen == 0) {
//                System.out.println();
                ketqua += "\n";
            }
        }
        System.out.println(ketqua);
        os.write(ketqua.getBytes());
        is.close();
        os.close();   
    }
    
    public void run() throws IOException{
        // Nhận packet từ client và chuyển packet thành String
        byte[] buf1 = new byte[256];
        byte[] buf2 = new byte[256];
        DatagramPacket packet1 = new DatagramPacket(buf1, buf1.length);
        DatagramPacket packet2 = new DatagramPacket(buf2, buf2.length);
        server.receive(packet1);
        server.receive(packet2);
        String source = new String(packet1.getData(), 0, buf1.length).trim();
        String dest = new String(packet2.getData(), 0, buf2.length).trim();
//        System.out.println("Nguồn: " + source);
//        System.out.println("Đích: " + dest);
        // Xử lý string data
        copyFile(new File(source), new File(dest));
//        File f_to = new File(link_to);
//        f_to.createNewFile();
        // Gửi lại cho client
//        if (exist) sendPacket("Đã copy".getBytes(), packet.getAddress(), packet.getPort());
//        else sendPacket("Đường dẫn không tồn tại".getBytes(), packet.getAddress(), packet.getPort());
    }
    
    
    public static void main(String[] args) throws IOException{
        Udp_Server server = new Udp_Server();
        server.run();
    }
}
