/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bai_2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author hpmdu
 */
public class TcpServer_2 {
    
    private ServerSocket server = null;
    private Socket client = null;
    private DataOutputStream out = null;
    private DataInputStream in = null;
    private int[] arr;
    private int limit;
    private String chStr;
    
    public TcpServer_2() throws IOException {
        server = new ServerSocket(1235);
        client = server.accept();
        in = new DataInputStream( client.getInputStream() );
        out = new DataOutputStream( client.getOutputStream() );
    }
    
    public void timvadocfile() throws IOException {
        while(true){
            String source = in.readUTF().trim();
            File f = new File(source);
            if (f.exists()){
                // Tạo 1 chuỗi hiển thị
                String plain = "";
                Scanner sc = new Scanner(f);
                // Đọc file 
                while(sc.hasNextLine()){
                    plain += sc.nextLine();
                }
                // Phân tách kết quả đọc được và biến đổi cho dễ nhìn
                String[] strArr = plain.split(",");
                plain = "";
                for(int i = 0; i < 3; i++){
                    String tmp = strArr[i].replace("-", " ");
                    plain += tmp + "\n";
                }
                // Đọc số giới hạn để tính tổng số nguyên tố
                limit = Integer.parseInt(strArr[3]);
//                System.out.println("Giới hạn tính tổng số nguyên tố: " + limit);
                // Đọc chuỗi để chuẩn hóa;
                chStr = strArr[4].trim();
//                System.out.println(chStr);
                // Đọc chuỗi số cuối cùng
                strArr[strArr.length - 1] = strArr[strArr.length - 1].trim().replace('"', ' ');
                String[] intArr = strArr[strArr.length - 1].trim().split(" ");
                // Tạo mảng số nguyên và in ra server để coi
                arr = new int[intArr.length];
                for(int i = 0; i < arr.length; i++){
                    arr[i] = Integer.parseInt(intArr[i].trim());
//                    System.out.println(arr[i]);
                }
                out.writeUTF("1_Đã tìm được file. Dữ liệu:\n"+plain);
                break;
            }
            else {
                out.writeUTF("0_Không tìm được file");
            }
        }
    }
    
    public static boolean isSNT(int s){
        for(int i = 2; i <= (int)Math.sqrt(s); i++){
            if (s % i == 0) return false;
        }
        return true;
    }
    
    public int tinhtongsonguyento(){
        int tong = 0;
        for(int i = 2; i < 30; i++){
            if (isSNT(i)) tong += i;
        }
        return tong;
    }
    
    public String chuanhoa(){
        return chStr.replaceAll("\\s+"," ");
    }
    
    public String sapxeptangdan(){
        String kq = "";
        for(int i = 0; i < arr.length; i++){
            for(int j = i; j < arr.length; j++){
                if (arr[i] > arr[j]){
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
        for(int i = 0; i < arr.length; i++) kq += arr[i] + " ";
        return kq;
    }
    
    public void xulyyeucau() throws IOException {
        while(true){
            String o = "";
            o = in.readUTF();
            if (o.equals("1")) out.writeUTF("Tổng số nguyên tố bé hơn 30 là " + tinhtongsonguyento());
            else if (o.equals("2")) out.writeUTF(chuanhoa());
            else if (o.equals("3")) out.writeUTF(sapxeptangdan());
            else if (o.equals("0")) {
                close();
                break;
            }
            else{
                out.writeUTF("Nhập sai yêu cầu");
            }
        }
    }
    
    public void close() throws IOException{
        out.close();
        in.close();;
    }
    
    public void run_2() throws IOException {
        timvadocfile();
        xulyyeucau();
        return;
    }
    
    public static void main(String[] args) throws IOException {
        TcpServer_2 server = new TcpServer_2();
        server.run_2();
    }
}
