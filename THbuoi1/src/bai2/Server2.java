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
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author daiph
 */
public class Server2 {
    public static ServerSocket server;
    public static Socket client;
    public static DataInputStream dis;
    public static DataOutputStream dos; 
     public static String id = "";
    public static boolean laSonguyento(int n) {
        // so nguyen n < 2 khong phai la so nguyen to
        if (n < 2) {
            return false;
        }
        // check so nguyen to khi n >= 2
        int can2 = (int) Math.sqrt(n);
        for (int i = 2; i <= can2; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
    public static String chuanhoa( String str){
        str=str.trim();
        str=str.replaceAll("\\s+", " ");
        
        String temp[]=str.split(" ");
        str="";
        for(int i=0;i<temp.length;i++){
            str += String.valueOf(temp[i].charAt(0)).toUpperCase()+temp[i].substring(1);
            if(i<temp.length-1) str+=" ";
        }
         
        System.out.println(str);
        return str;
    }
    public static void Yeucau1() throws  IOException{
       int n = dis.readInt(), tong = 0;
       // Xu li so nguyen to
       for( int i = 2; i <= n; i++){
           if(laSonguyento(i)) tong += i;
       }
       dos.writeInt(tong);
       System.out.println("Đã gửi kết quả của yêu cầu 1 cho Client!");
       client.close();
       server.close();
    }
    public static void Yeucau2() throws  IOException{
       dos.writeUTF( chuanhoa(dis.readUTF()) );
       System.out.println("Đã gửi kết quả của yêu cầu 2 cho Client!");
       client.close();
       server.close();
    }
    public static void Yeucau3() throws  IOException{
       String matran[] = dis.readUTF().split(" ");
       int n = Integer.parseInt(matran[0]) , m = Integer.parseInt(matran[1]);
       int mang[] = new int[n*m];
       for( int i = 0; i < n*m; i++){
           mang[i] = Integer.parseInt( matran[i+2] );
       }
       String str = ""; int temp = 0;
       for(int i = 0; i < n*m-1; i++){
           for(int j=i; j < n*m; j++){
               if(mang[i] > mang[j]){
                   temp = mang[i];
                   mang[i] = mang[j];
                   mang[j] = temp;
               }
           }
       }
       
       for(int i = 0; i < n; i++){
           for(int j=0; j < m; j++){
               str += String.valueOf(mang[i*m + j]) + " ";
           }
           str = str.trim() + "\n";
      }
        
       dos.writeUTF(str.substring(0,str.length()-1));
       client.close();
       server.close();
    }
    public static void Xulifile() throws IOException{
        String url = dis.readUTF();
        boolean check = false;
        File file = new File(url.trim());

            if( !file.exists() ){
                System.out.println("File không tồn tại !");
            }else 
                check = true;
            
        dos.writeBoolean(check);
        String str = "";
        if(check)
        {
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()){
                str = sc.nextLine();
            }
            dos.writeUTF(str);
        }
        client.close();
        server.close();
        
    }
   
    public static void run() throws IOException{
        System.out.println("Server 2 đã kết nối .. ");
        System.out.println("Server 2 đang nhận yêu cầu ");
        int option = 0;
        // Nơi nhận yêu cầu 
        do{
            server = new ServerSocket(1002);
            client = server.accept();
            dis = new DataInputStream( client.getInputStream() );
            dos = new DataOutputStream( client.getOutputStream() );
            option = 0;
            
            option = dis.readInt();
            System.out.println("Nhận yêu cầu "+option);

            // Trả về yêu cầu 
            switch( option ){
                case 1: 
                    Yeucau1();
                    break;
                case 2:
                    Yeucau2();
                    break;
                case 3: 
                    Yeucau3();
                    break;
                case 0:
                    client.close();
                    server.close();
                    return;
                default:
                    Xulifile();
                    break;
            }

        }while(option != 0);
                    // Kết thúc
                    
               
        
    }
}
