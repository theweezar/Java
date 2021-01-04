/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author hpmdu
 */
public class TcpClient {
    
    private Socket client = null;
    private DataOutputStream out = null;
    private DataInputStream in = null;
    private Scanner scan = null;
    
    public TcpClient() throws IOException{
        this.client = new Socket("localhost", 1235);
        this.out = new DataOutputStream(this.client.getOutputStream());
        this.in = new DataInputStream(this.client.getInputStream());
        scan = new Scanner(System.in);
    }
    
    public DataOutputStream output() throws IOException {
        return new DataOutputStream(this.client.getOutputStream());
    }
    
    public DataInputStream input() throws IOException {
        return new DataInputStream(this.client.getInputStream());
    }
    
    public int getNumber(String title, boolean negative){
        int n = 0;
        while(true){
            System.out.print(title);
            try{
                n = scan.nextInt();
                if (!negative && n < 0) System.out.println("Nhập số nguyên dương");
                else return n;
            }
            catch(InputMismatchException e){
                scan.next();
                System.out.println("Nhập ký tự số");
            }
        }
    }
    
    public int menu(){
        String[] items = {
            "Đổi hệ số 10 thành hệ số 2, 8, 16",
            "Tổng các chữ số trong 1 số nguyên dương",
            "Phân tích thành các thừa số nguyên tố",
            "Các số nguyên tố nhỏ hơn n nguyên dương",
            "Liệt kê số thuận nghịch có 6 chữ số",
            "6,7,8. Nhập dãy a0 -> an.\n\tVà liệt kê các phần tử xuất hiện trong dãy đúng 1 lần.\n\tliệt kê các phần tử xuất hiện trong dãy đúng 2 lần.\n\tIn ra màn hình số lần xuất hiện của các phần tử.",
            "Đếm số từ trong 1 xâu ký tự",
            "Liệt kê các số nguyên tố có 5 chữ số, sao cho tổng của các chữ số đó bằng 1 số n cho trước",
            "Liệt kế số fibonaci nhỏ hơn n là số nguyên tố"
        };
        int opt = 0;
        System.out.println("------------------------------------");
        System.out.println("| Hoàng Phan Minh Đức - N17DCAT018 |");
        System.out.println("|               Menu               |");
        System.out.println("------------------------------------");
        for(int i = 0; i < items.length; i++){
            System.out.printf("%d. %s\n", i + 1, items[i]);
        }
        System.out.println("0. Thoát");
//        System.out.print("Chọn: ");
//        try{
//            opt = scan.nextInt();
//        }
//        catch(InputMismatchException e){
//            scan.next();
//            return -1;
//        }
//        return opt;
        return getNumber("Chọn: ", false);
    }
    
    public void bai_1() throws IOException {
        int n = getNumber("Nhập số n: ", false);
        out.writeInt(n);
        System.out.println(in.readUTF());
    }
    
    public void bai_2() throws IOException {
        int n = getNumber("Nhập số n: ", false);
        out.writeInt(n);
        System.out.printf("Tổng các chữ số của %d là %d\n", n, in.readInt());
    }
    
    public void bai_3() throws IOException {
        int n = getNumber("Nhập số n: ", false);
        out.writeInt(n);
        System.out.println(in.readUTF());
    }
    
    public void bai_4() throws IOException {
        int n = getNumber("Nhập số n: ", false);
        out.writeInt(n);
        System.out.println("Các snt nhỏ hơn n: " + in.readUTF());
    }
    
    public void bai_5() throws IOException {
        System.out.println(in.readUTF());
    }
    
    public void bai_678() throws IOException {
        // Nhập và gửi số lượng đến server
        int n = getNumber("Nhập số lượng phần tử: ", false);
        out.writeInt(n);
        // Sau đó nhập và gửi các phần tử đến server
        for(int i = 0; i < n; i++){
            int j = getNumber(String.format("Phần tử [%d]: ", i), true);
            out.writeInt(j);
        }
        System.out.println(in.readUTF());
    }
    
    public void bai_9() throws IOException {
        System.out.print("Nhập xâu ký tự: ");
        scan.next();
        out.writeUTF(scan.nextLine());
        System.out.println("Số ký tự: " + in.readInt());
    }
    
    public void bai_10() throws IOException {
        int n = getNumber("Nhập số nguyên dương n: ", false);
        out.writeInt(n);
        System.out.println("Kết quả: " + in.readUTF());
    }
    
    public void bai_11() throws IOException {
        
    }
    
    public void run()throws IOException{
        int opt = -1;
        while(opt != 0){
            opt = menu();
            switch(opt){
                case 1:
                    out.writeInt(opt);
                    bai_1();
                    break;
                case 2:
                    out.writeInt(opt);
                    bai_2();
                    break;
                case 3:
                    out.writeInt(opt);
                    bai_3();
                    break;
                case 4:
                    out.writeInt(opt);
                    bai_4();
                    break;
                case 5:
                    out.writeInt(opt);
                    bai_5();
                    break;
                case 6:
                    out.writeInt(opt);
                    bai_678();
                    break;
                case 7:
                    out.writeInt(opt);
                    bai_9();
                    break;
                case 8:
                    out.writeInt(opt);
                    bai_10();
                    break;
                case 9:
                    out.writeInt(opt);
                    bai_11();
                    break;
                case 0:
                    out.writeInt(opt);
                    System.out.println("Thoát");
                    scan.close();
                    out.close();
                    in.close();
                    break;
                default:
                    System.out.println("Nhập sai lựa chọn. Vui lòng nhập lại.");
                    break;
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        TcpClient client = new TcpClient();
        client.run();
    }
}
