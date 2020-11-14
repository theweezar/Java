/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bai1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author daiph
 */
public class Client1 {
     
    public static void menu( ){
        // ----------------------- MENU -------------------------
        System.out.println("**************************************************");
        System.out.println("*                      MENU                      *");
        System.out.println("*  1. Tính tổng S=1+3+5+7+...+(2n+1)             *");
        System.out.println("*  2. Tính tổng S=1*2+2*3+...+n*(n+1)            *");
        System.out.println("*  3. Tính tổng S=1-2+3-4+...+(2n+1)             *");
        System.out.println("*  4. Tính tổng 2 số a va b                      *");
        System.out.println("*  5. Tính lập phương 2 số a và b                *");
        System.out.println("*  6. Tính bình phương 2 số a và b               *");
        System.out.println("*  7. Tính tích S1=1*2*3*...*n và S2=1+2+...+n   *");
        System.out.println("*  >>>>>>>>>>> Nhan phim 0 de thoat <<<<<<<<<<<  *");
        System.out.println("*                                                *");
        System.out.println("*              Phan Đại N17DCAT013               *");
        System.out.println("**************************************************");
        System.out.print(" Yêu cầu của bạn là : ");
      // ------------------------------------------------------
    
    }
    
    public static Socket client; 
    public static DataInputStream dis;
    public static DataOutputStream dos;
        public static String user = "";
       
           
    public static void Option1() throws  IOException{
        Scanner sc = new Scanner(System.in);
        boolean check = false;
        int n = 0;
        do{
            try {
               System.out.print("Nhập n : ");
               n = sc.nextInt();
               if( n < 0 ) check = false;
               else check = true;
            } catch (Exception e) {
                System.out.println("Sai kiểu dữ liệu! Mời nhập lại n ( n > 0 ) ");
                sc.nextLine();
            }
        }while(!check);
        dos.writeInt(n);
        int tong = dis.readInt();
        System.out.println("Tổng S=1+3+5+7+...+(2n+1)  với n = "+n+" là "+tong);
    }
    public static void Option2() throws IOException{
      Scanner sc = new Scanner(System.in);
        boolean check = false;
        int n = 0;
        do{
            try {
               System.out.print("Nhập n : ");
               n = sc.nextInt();
               if( n < 0 ) check = false;
               else check = true;
            } catch (Exception e) {
                System.out.println("Sai kiểu dữ liệu! Mời nhập lại n ( n > 0 ) ");
                sc.nextLine();
            }
        }while(!check);
        dos.writeInt(n);
        int tong = dis.readInt();
        System.out.println("Tổng S=1*2+2*3+...+n*(n+1) với n = "+n+" là "+tong);
    }
    public static void Option3() throws IOException{
       Scanner sc = new Scanner(System.in);
        boolean check = false;
        int n = 0;
        do{
            try {
               System.out.print("Nhập n : ");
               n = sc.nextInt();
               if( n < 0 ) check = false;
               else check = true;
            } catch (Exception e) {
                System.out.println("Sai kiểu dữ liệu! Mời nhập lại n ( n > 0 ) ");
                sc.nextLine();
            }
        }while(!check);
        dos.writeInt(n);
        int tong = dis.readInt();
        System.out.println("Tổng S=1-2+3-4+...+(2n+1) với n = "+n+" là "+tong);
    }
    public static void Option4() throws IOException{
      Scanner sc = new Scanner(System.in);
        boolean check = false;
        int a=0 ,b = 0;
        do{
            try {
               System.out.print("Nhập a và b <Enter để xác nhận từng số> : ");
               a = sc.nextInt();
               b = sc.nextInt();
               check = true;
            } catch (Exception e) {
                System.out.println("Sai kiểu dữ liệu! Mời nhập lại a,b (int) ");
                sc.nextLine();
            }
        }while(!check);
        dos.writeInt(a);
        dos.writeInt(b);
        int tong = dis.readInt();
        System.out.println("Tổng S= a + b với a = "+a+", b = "+b+" là "+tong);
    }
    public static void Option5() throws IOException{
       Scanner sc = new Scanner(System.in);
        boolean check = false;
        int a=0 ,b = 0;
        do{
            try {
               System.out.print("Nhập a và b <Enter để xác nhận từng số> : ");
               a = sc.nextInt();
               b = sc.nextInt();
               check = true;
            } catch (Exception e) {
                System.out.println("Sai kiểu dữ liệu! Mời nhập lại a,b (int) ");
                sc.nextLine();
            }
        }while(!check);
        dos.writeInt(a);
        dos.writeInt(b);
        double tong = dis.readDouble();
        System.out.println("Tổng lập phương 2 số a = "+a+" và b = "+b+" là "+tong);
    }
    public static void Option6() throws IOException{
      Scanner sc = new Scanner(System.in);
        boolean check = false;
        int a=0 ,b = 0;
        do{
            try {
               System.out.print("Nhập a và b <Enter để xác nhận từng số> : ");
               a = sc.nextInt();
               b = sc.nextInt();
               check = true;
            } catch (Exception e) {
                System.out.println("Sai kiểu dữ liệu! Mời nhập lại a,b (int) ");
                sc.nextLine();
            }
        }while(!check);
        dos.writeInt(a);
        dos.writeInt(b);
        double tong = dis.readDouble();
        System.out.println("Tổng bình phương 2 số a = "+a+" và b = "+b+" là "+tong);
    }
    public static void Option7() throws IOException{
      Scanner sc = new Scanner(System.in);
        boolean check = false;
        int n = 0;
        do{
            try {
               System.out.print("Nhập n : ");
               n = sc.nextInt();
               if( n < 0 ) check = false;
               else check = true;
            } catch (Exception e) {
                System.out.println("Sai kiểu dữ liệu! Mời nhập lại n ( n > 0 ) ");
                sc.nextLine();
            }
        }while(!check);
        dos.writeInt(n);
        int s1 = dis.readInt(),
            s2 = dis.readInt();
        System.out.println("Tích S1=1*2*3*...*n và S2=1+2+...+n với n = "+n+" là S1 = "+s1 + " ; S2 ="+s2);
    }
    
    public static void pause(){
        System.out.println("Bấm phím bất kì để tiếp tục..");
        new Scanner(System.in).nextLine();
    }
    /**
     * @param args the command line arguments
     */
    public static void run() throws IOException {
        // Khởi tạo Client 
        Scanner sc = new Scanner(System.in);
        boolean again = true; //Mỗi khi lựa chọn thì sẽ bật
        // Loop
            int option = 0;
            // Gửi yêu cầu
                do{
                    // Mỗi khi quay lại với again = true thì sẽ vẽ lại menu 
                    if(again){
                    client = new Socket("localhost",1001);
                    dis = new DataInputStream( client.getInputStream() );
                    dos = new DataOutputStream( client.getOutputStream() );
                    sc = new Scanner(System.in);
                    menu();
                    again = false;
                    }
                    // Còn nếu again = false thì lúc đó đang lỗi 
                    try {
                    option = 0;
                    option = sc.nextInt();
                    switch(option){
                        case 1: 
                            dos.writeInt(option);
                            Option1();
                            pause();
                            again = true;
                            break;
                            
                        case 2:
                            dos.writeInt(option);
                            Option2();
                            pause();
                            again = true;
                            break;
                        
                        case 3:
                            dos.writeInt(option);
                            Option3();
                            pause();
                            again = true;
                            break;
                            
                        case 4:
                            dos.writeInt(option);
                            Option4();
                            pause();
                            again = true;
                            break;
                            
                        case 5:
                            dos.writeInt(option);
                            Option5();
                            pause();
                            again = true;
                            break;
                            
                        case 6:
                            dos.writeInt(option);
                            Option6();
                            pause();
                            again = true;
                            break;
                        
                        case 7:
                            dos.writeInt(option);
                            Option7();
                            pause();
                            again = true;
                            break;
                        
                        default:
                            dos.writeInt(option);
                            throw new Exception();
                        // Đóng
                        case 0:
                            dos.writeInt(option);
                            client.close();
                            return;
                        }
                    } catch (Exception e) {
                            System.out.println("Lựa chọn của bạn không hợp lệ");
                            sc.nextLine();
                        System.out.print(" Yêu cầu của bạn là : ");
                        
                    }
                }while(true); 
    }
}
