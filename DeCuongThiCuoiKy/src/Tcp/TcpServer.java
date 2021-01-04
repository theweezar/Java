/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author hpmdu
 */
public class TcpServer {
    
    private ServerSocket server = null;
    private Socket client = null;
    private DataOutputStream out = null;
    private DataInputStream in = null;
    
    public TcpServer() throws IOException {
        this.server = new ServerSocket(1235);
        this.client = this.server.accept();
        System.out.println("Kết nối server TCP thành công");
        this.out = new DataOutputStream(this.client.getOutputStream());
        this.in = new DataInputStream(this.client.getInputStream());
    }
    
    public DataOutputStream output() throws IOException {
        return new DataOutputStream(this.client.getOutputStream());
    }
    
    public DataInputStream input() throws IOException {
        return new DataInputStream(this.client.getInputStream());
    }
    
    public String string_reverse(String inp){
        String re = "";
        for(int i = inp.length() - 1; i >= 0; i--){
            re += inp.charAt(i);
        }
        return re;
    }
    
    public void bai_1() throws IOException {
        int n = in.readInt();
        String cs2 = "";
        int tmp = n;
        // Hệ cơ số 2
        while(tmp != 0){
            cs2 += tmp % 2;
            tmp = tmp / 2;
        }
        // Hệ cơ số 8
        tmp = n;
        String cs8 = "";
        while(tmp != 0){
            cs8 += tmp % 8;
            tmp = tmp / 8;
        }
        // Hệ cơ số 16
        tmp = n;
        String cs16 = "";
        String[] h16 = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"}; 
        while(tmp != 0){
            cs16 += h16[tmp % 16];
            tmp = tmp / 16;
        }
        String rs = String.format("Số n = %d\nHệ cơ số 2 : %s\nHệ cơ số 8 : %s\nHệ cơ số 16: %s\n",
                        n, string_reverse(cs2), string_reverse(cs8), string_reverse(cs16));
        out.writeUTF(rs);
    }
    
    public void bai_2() throws IOException {
        int n = in.readInt();
//        System.out.printf("Bài 2 - n = %d\n", n);
        int t = 0;
        while(n != 0){
            t += n % 10;
            n = n / 10;
        }
//        System.out.printf("Bài 2 - t = %d\n", t);
        out.writeInt(t);
    }
    
    public void bai_3() throws IOException {
        int n = in.readInt();
        int dem;
        String kq = "";
        for(int i = 2; i <= n; i++){
            dem = 0;
            while(n % i == 0){
                ++dem;
                n /= i;
            }
            if (dem != 0){
                if(dem > 1) kq += String.format("%d^%d", i, dem);
                else kq += String.format("%d", i);
                if(n > i){
                    kq += " * ";
                }
            }
        }
        out.writeUTF(kq);
    }
    
    public boolean isSNT(int s){
        for(int i = 2; i <= (int)Math.sqrt(s); i++){
            if (s % i == 0) return false;
        }
        return true;
    }
    
    public void bai_4() throws IOException {
        int n = in.readInt();
        String kq = "";
        for(int i = 2; i < n; i++){
            if (isSNT(i)) kq += i + ", ";
        }
        out.writeUTF(kq);
    }
    
    public void bai_5() throws IOException {
        String kq = "";
        for(int i = 100; i <= 999; i++){
            kq += i + string_reverse(String.format("%d", i)) + "\n";
        }
        out.writeUTF(kq);
    }
    
    public void bai_678() throws IOException {
        // Nhận số lượng từ client
        int n = in.readInt();
        // Khởi tạo mảng theo số lượng
        int[] arr = new int[n];
        // Nhận các phần tử từ client
        for(int i = 0; i < n; i++){
            arr[i] = in.readInt();
            System.out.printf("%d ", arr[i]);
        }
        // 6. Liệt kê các phần tử xuất hiện đúng 1 lần
        String kq1 = "Bài 6: ";
        int dem;
        for(int i = 0; i < n; i++){
            dem = 0;
            for(int j = 0; j < n; j++){
                if (arr[i] == arr[j] && i != j) dem++;
            }
            if (dem == 0) kq1 += arr[i] + " ";
        }
        // Sắp xếp phần tử từ bé tới lớn lại để dùng cho 2 bài
        for(int i = 0; i < n; i++){
            for(int j = i; j < n; j++){
                if (arr[i] > arr[j]){
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
        // 7. Liệt kê các phần tử xuất hiện đúng 2 lần, ko hơn
        String kq2 = "Bài 7: ";
        dem = 1;
        for(int i = 1; i < n; i++){
            if (arr[i - 1] == arr[i]) dem++;
            else if (dem == 2) {
                kq2 += arr[i - 1] + " ";
                dem = 1;
            }
        }
        // 8. In ra màn hình số lần phần tử xuất hiện
        String kq3 = "Bài 8:\n";
        dem = 1;
        for(int i = 1; i < n; i++){
            if (arr[i - 1] == arr[i]) dem++;
            else{
                kq3 += String.format("Số %d xuất hiện %d lần\n", arr[i - 1], dem);
                dem = 1;
            }
        }
        if (arr[n - 1] != arr[n - 2]) kq3 += String.format("Số %d xuất hiện 1 lần\n", arr[n - 1]);
        out.writeUTF(String.format(("%s\n%s\n%s"), kq1, kq2, kq3));
    }
    
    public void bai_9() throws IOException {
        String str = in.readUTF().trim();
        // Cách 1: dùng vòng lặp đếm khoảng trắng
        int dem = 1;
        for(int i = 0; i < str.length() - 1; i++){
            if (str.charAt(i) == ' ' && str.charAt(i + 1) != ' ') dem++;
        }
        if (dem != 1) dem++;
        out.writeInt(dem);
        // Cách 2: dùng split rồi đếm số lượng phần tử trong mảng
//        if (str.length() == 0) out.writeInt(0);
//        else if (str.split("\\s+") == null) out.writeInt(1);
//        else out.writeInt(str.split("\\s+").length + 1);
    }
    
    public void bai_10() throws IOException {
        int n = in.readInt();
        String kq = "";
        int t, x;
        int start = 10000, end = 99999;
        for(int i = start; i <= end; i++){
            if (isSNT(i)){
                x = i;
                t = 0;
                while(x != 0){
                    t += x % 10;
                    x = x / 10;
                }
                if (t == n) kq += i + "\n";
            }
        }
        out.writeUTF(kq);
    }
    
    public int fibonaci(int n){
        if (n == 0 || n == 1) return n;
        else{
            return fibonaci(n - 1) + fibonaci(n - 2);
        }
    }
    
    public void bai_11() throws IOException {
        int n = in.readInt();
        
        
    }
    
    public void run() throws IOException {
        int opt = -1;
        while(opt != 0){
            opt = in.readInt();
            switch(opt){
                case 1:
                    bai_1();
                    break;
                case 2:
                    bai_2();
                    break;
                case 3:
                    bai_3();
                    break;
                case 4:
                    bai_4();
                    break;
                case 5:
                    bai_5();
                    break;
                case 6:
                    bai_678();
                    break;
                case 7:
                    bai_9();
                    break;
                case 8:
                    bai_10();
                    break;
                case 9:
                    bai_11();
                    break;
                case 0:
                    out.close();
                    in.close();
                    break;
                default:
                    break;
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        TcpServer server = new TcpServer();
        server.run();
    }
}
