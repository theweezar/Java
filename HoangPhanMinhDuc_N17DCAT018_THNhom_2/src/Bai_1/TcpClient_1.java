/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bai_1;

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
public class TcpClient_1 {
    
    private Socket client;
    private DataInputStream in;
    private DataOutputStream out;
    private Scanner sc;
    
    public TcpClient_1() throws IOException{
        client = new Socket("localhost",1234);
        in = new DataInputStream( client.getInputStream());
        out = new DataOutputStream( client.getOutputStream());
        sc = new Scanner(System.in);
    }
    
    public void menu(){
        // ----------------------- MENU -------------------------
        System.out.println("Hoàng Phan Minh Đức - N17DCAT018");
        System.out.println("*                      MENU                      *");
        System.out.println("*  1. Tính tổng S=1+3+5+7+...+(2n+1)             *");
        System.out.println("*  2. Tính tổng S=1*2+2*3+...+n*(n+1)            *");
        System.out.println("*  3. Tính tổng S=1-2+3-4+(2n+1)                 *");
        System.out.println("*  4. Tính tổng 2 số a va b                      *");
        System.out.println("*  5. Tính lập phương 2 số a và b                *");
        System.out.println("*  6. Tính bình phương 2 số a và b               *");
        System.out.println("*  7. Tính tích S1=1*2*3*...*n và S2=1+2+...+n   *");
        System.out.print(" Yêu cầu của bạn là : ");
      // ------------------------------------------------------
    }
    
    public int nhapSoNguyen(String title, boolean smallerThanZero){
        int n = 0;
        while(true){
            System.out.print(title);
            try{
                n = sc.nextInt();
                if (smallerThanZero) return n;
                else if (n >= 0) {
                    return n;
                }
                else System.out.println("Nhập số nguyên dương lớn hơn hoặc bằng 0");
            }
            catch(InputMismatchException e){
                sc.next();
                System.out.println("Nhập sai ký tự");
            }
        }
    }
    
    public void yeuCau1237(int option) throws IOException{
        int n = nhapSoNguyen("Nhập số nguyên dương n: ", false);
        out.writeInt(n);
        int kq = in.readInt();
        System.out.println(">>>>>>>>>>>>>>>>>>>>\n");
        if (option == 1) System.out.println("S=1+3+5+7+...+(2n+1) là " + kq);
        else if (option == 2) System.out.println("S=1*2+2*3+...+n*(n+1) là " + kq);
        else if (option == 3) System.out.println("S=1-2+3-4+(2n+1) là " + kq);
        else if (option == 7) System.out.println("Tích S1=1*2*3*...*n và S2=1+2+...+n là " + kq);
        System.out.println("\n<<<<<<<<<<<<<<<<<<<<\n");
    }
    
    public void yeuCau456(int option) throws IOException{
        int a = nhapSoNguyen("Nhập số nguyên dương a: ", true);
        int b = nhapSoNguyen("Nhập số nguyên dương b: ", true);
        out.writeInt(a);
        out.writeInt(b);
        int kq = in.readInt();
        System.out.println(">>>>>>>>>>>>>>>>>>>>\n");
        if (option == 4) System.out.printf("%d + %d = %d\n",a,b,kq);
        else if (option == 5) System.out.printf("(%d + %d) ^ 3 = %d",a,b,kq);
        else if (option == 6) System.out.printf("(%d + %d) ^ 2 = %d",a,b,kq);
        System.out.println("\n<<<<<<<<<<<<<<<<<<<<\n");
    }
    
    public void run_1() throws IOException{
        boolean again = true;
        int option = 0;
        do{
            // Mỗi khi quay lại với again = true thì sẽ vẽ lại menu 
            if(again){
                menu();
                again = false;
            }
            // Còn nếu again = false thì lúc đó đang lỗi 
            try {
                option = 0;
                option = sc.nextInt();
                switch(option){
                    case 1: 
                        out.writeInt(option);
                        yeuCau1237(1);
                        again = true;
                        break;

                    case 2:
                        out.writeInt(option);
                        yeuCau1237(2);
                        again = true;
                        break;

                    case 3:
                        out.writeInt(option);
                        yeuCau1237(3);
                        again = true;
                        break;

                    case 4:
                        out.writeInt(option);
                        yeuCau456(4);
                        again = true;
                        break;

                    case 5:
                        out.writeInt(option);
                        yeuCau456(5);
                        again = true;
                        break;

                    case 6:
                        out.writeInt(option);
                        yeuCau456(6);
                        again = true;
                        break;

                    case 7:
                        out.writeInt(option);
                        yeuCau1237(7);
                        again = true;
                        break;

                    default:
                        throw new Exception();
                    // Đóng
                    case 0:
                        out.writeInt(option);
                        client.close();
                        return;
                }
            } 
            catch (Exception e) {
                    System.out.println("Lựa chọn của bạn không hợp lệ");
                    sc.nextLine();
                System.out.print(" Yêu cầu của bạn là : ");
            }
        } while(true);
    }
    
    public static void main(String[] args) throws IOException{
        TcpClient_1 client = new TcpClient_1();
        client.run_1();
    }
}
