/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bai2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author daiph
 */
public class Client2 {
     
    public static void menu( ){
        // ----------------------- MENU -------------------------
        System.out.println("**************************************************");
        System.out.println("                      MENU                        ");
        for( String item : menus){
            System.out.println("    "+item);
        }
        System.out.println("   >>>>>>>>>>> Nhan phim 0 de thoat <<<<<<<<<<<   ");
        System.out.println("                                                  ");
        System.out.println("               Phan Đại N17DCAT013                ");
        System.out.println("**************************************************");
        System.out.print(" Yêu cầu của bạn là : ");
      // ------------------------------------------------------
    
    }
    
    public static Socket client; 
    public static DataInputStream dis;
    public static DataOutputStream dos;
        public static int socau = 3; 
        public static String menus[] = new String[socau];
        public static String data[] = new String[socau];
           
    public static void Option1() throws  IOException{
        Scanner sc = new Scanner(System.in);
        int limit = Integer.parseInt( data[0] );
        dos.writeInt(limit);
        System.out.println("Tổng các số nguyên tố từ một số n = "+limit+" là "+dis.readInt());
    }
    public static void Option2() throws IOException{
        dos.writeUTF(data[1].replaceAll("\""," "));
        System.out.println("Chuỗi sau khi đã chuẩn hóa là : "+dis.readUTF());
    }
    public static void Option3() throws IOException{
//        System.out.println(data[2].trim().replaceAll("\""," ").trim());
        dos.writeUTF(data[2].replaceAll("\"","").trim());
        System.out.println("Ma trận tăng dần là : \n"+dis.readUTF());
    }
   
    public static void pause(){
        System.out.println("Bấm phím bất kì để tiếp tục..");
        new Scanner(System.in).nextLine();
    }
    /**
     * @param args the command line arguments
     */
    public static void run() throws IOException {
        // Khởi tạo Menu
        Scanner sc = new Scanner(System.in);
        boolean check = false;
        File file = null;
            
        do{
            client = new Socket("localhost",1002);
            dis = new DataInputStream( client.getInputStream() );
            dos = new DataOutputStream( client.getOutputStream() );
            System.out.print("Nhập đường dẫn với tên File (dấu \\ thay bằng \\\\ ): ");
            String url = sc.nextLine();
            // Kiểm tra file có tồn tại không 
            if(url.trim().equalsIgnoreCase("exit")){
                System.exit(0);
            }
            dos.writeInt(-1);
            dos.writeUTF(url);
            check = dis.readBoolean();
            if(!check) System.out.println("File không tồn tại !!");
            // D:\\TODO\\BAITAP.txt -> return
            // D:\\TODO\\Java\\BAITAP.txt 
        }while( !check );
        
        // Phân tách file thành menu và data
        String str = dis.readUTF();       
        
        String all[] = str.split(",");
            // For Menu 
            menus[0]=all[0]; 
            menus[1]=all[1];
            menus[2]=all[2];
            // For Data
            data[0]=all[3]; 
            data[1]=all[4];
            data[2]=all[5];
        // Khởi tạo Client
        sc = new Scanner(System.in);
        boolean again = true; //Mỗi khi lựa chọn thì sẽ bật
        // Loop
            int option = 0;
            // Gửi yêu cầu
                do{
                    // Mỗi khi quay lại với again = true thì sẽ vẽ lại menu 
                    if(again){
                    client = new Socket("localhost",1002);
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
                            
                        
                        default:
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
