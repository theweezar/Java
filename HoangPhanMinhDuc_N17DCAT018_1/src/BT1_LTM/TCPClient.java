/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BT1_LTM;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {
     public static void menu(){
        System.out.println("MỜI BẠN NHẬP");
        System.out.println("Câu 1: Phuong trinh bac 3");
        System.out.println("Câu 2:");
        System.out.println("Ấn 0 để thoát");
    }
     public static int PORT = 1234;
    public static Socket client; 
    public static DataInputStream inp;
    public static DataOutputStream out;
    
    public static File file;
    
    
    public static void pause(){
        System.out.println("Ấn tiếp tục...");
        new Scanner(System.in).nextLine();
    }
     public static void Cau1() throws IOException{
        double a, b, c, d;
        a = b = c = d = 0.0;
        Scanner sc = new Scanner(System.in);
        boolean check = false;
        
        do{
        check = false;
        try {
            System.out.println("Nhap a: ");
            a = sc.nextDouble();
            check = true;
            } catch (Exception e) {
                System.out.println("Sai kiểu dữ liệu !! Mời bạn nhập lại");
                sc.nextLine();
            }
        }while(!check);
        
        do{
        check = false;
        try {
            System.out.println("Nhap b: ");
            b = sc.nextDouble();
            check = true;
            } catch (Exception e) {
                System.out.println("Sai kiểu dữ liệu !! Mời bạn nhập lại");
                sc.nextLine();
            }
        }while(!check);
        
        do{
        check = false;
        try {
            System.out.println("Nhap c: ");
            c = sc.nextDouble();
            check = true;
            } catch (Exception e) {
                System.out.println("Sai kiểu dữ liệu !! Mời bạn nhập lại");
                sc.nextLine();
            }
        }while(!check);
        
        do{
        check = false;
        try {
            System.out.println("Nhap d: ");
            d = sc.nextDouble();
            check = true;
            } catch (Exception e) {
                System.out.println("Sai kiểu dữ liệu !! Mời bạn nhập lại");
                sc.nextLine();
            }
        }while(!check);
        
        out.writeDouble(a);
        
        out.writeDouble(b);
        
        out.writeDouble(c);
        
        out.writeDouble(d);
        
    }
     
     public static void Cau2 () throws IOException{
         
     }
         public static void main(String[] args) throws IOException {

            Scanner sc= new Scanner(System.in);
            
            int option = 0; 
            boolean again = true;
            String temp = "";
        do{
            client = new Socket("localhost",PORT);
            inp = new DataInputStream( client.getInputStream() ); 
            out = new DataOutputStream( client.getOutputStream() );
            try {
                if ( again ) {
                    menu();
                    again = false;
                }
                System.out.print("Mời bạn nhập lựa chọn : ");
                
                option = sc.nextInt();
                
                switch(option){
                    case 1: 
                        out.writeInt(1);
                        Cau1();
                        pause();
                        again = true;
                        break;
                        
                    case 2: 
                        out.writeInt(2);
                        Cau2();
                        pause();
                        again = true;
                        break;

                        
                    case 0 : // 0 to exit
                        out.writeInt(0);
                        client.close();
                        System.exit(0);
                        
                    default : throw new Exception(); 
                }
            } catch (Exception e) {
                System.out.println("Lựa chọn của bạn không hợp lệ");   
                sc.nextLine();
            }
        }while(true);
        
    }
    
}