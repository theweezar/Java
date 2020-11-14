/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bai1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author daiph
 */
public class Server1 {
    public static ServerSocket server;
    public static Socket client;
    public static DataInputStream dis;
    public static DataOutputStream dos; 
     public static String id = "";
    
    public static void Yeucau1() throws IOException{
       int n = dis.readInt(), tong = 0;
       for( int i = 0; i <= n ; i++)
           tong += (2*i+1);
       dos.writeInt(tong);
       System.out.println("Đã gửi kết quả của yêu cầu 1 cho Client!");
       client.close();
       server.close();
    }
    public static void Yeucau2() throws  IOException{
       int n = dis.readInt(), tong = 0;
       for( int i = 1; i <= n ; i++)
           tong += i*(i+1);
       dos.writeInt(tong);
       System.out.println("Đã gửi kết quả của yêu cầu 2 cho Client!");
       client.close();
       server.close();
    }
    public static void Yeucau3() throws  IOException{
       int n = dis.readInt(), tong = 0;
       for( int i = 0; i <= n ; i++)
           if( i%2 == 0)
               tong -= i;
           else 
               tong += i;
       dos.writeInt(tong);
       System.out.println("Đã gửi kết quả của yêu cầu 3 cho Client!");
       client.close();
       server.close();
    }
    public static void Yeucau4() throws  IOException{
        int a = dis.readInt(),
            b = dis.readInt();
        dos.writeInt(a+b);
        System.out.println("Đã gửi kết quả của yêu cầu 4 cho Client!");
       client.close();
       server.close();
    }
    public static void Yeucau5() throws  IOException{
        int a = dis.readInt(),
            b = dis.readInt();
        dos.writeDouble(Math.pow((double)(a+b), 3.0));
        System.out.println("Đã gửi kết quả của yêu cầu 5 cho Client!");
        client.close();
        server.close();
    }
    public static void Yeucau6() throws  IOException{
        int a = dis.readInt(),
            b = dis.readInt();
        dos.writeDouble(Math.pow((double)(a+b), 2.0));
        System.out.println("Đã gửi kết quả của yêu cầu 6 cho Client!");
        client.close();
        server.close();
    }
    public static void Yeucau7() throws  IOException{
        int n = dis.readInt(), s1= 1, s2= 0;
       for( int i = 1; i <= n ; i++)
       {
           s1*=i;
           s2+=i;
       }
       dos.writeInt(s1);
       dos.writeInt(s2);
       System.out.println("Đã gửi kết quả của yêu cầu 7 cho Client!");
       client.close();
       server.close();
    }
    public static void run() throws IOException {
        System.out.println("Server 1 đã kết nối .. ");
        System.out.println("Server 1 đang nhận yêu cầu ");
        int option = 0;
        // Nơi nhận yêu cầu 
        do{
            server = new ServerSocket(1001);
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
                case 4:
                    Yeucau4();
                    break;
                case 5: 
                    Yeucau5();
                    break;
                case 6:
                    Yeucau6();
                    break;
                case 7:
                    Yeucau7();
                    break;
                case 0:
                    client.close();
                    server.close();
                    return;
                default:
                    System.out.println(" Server nhận yêu cầu khác ");
                    break;
            }

        }while(option != 0);
                    // Kết thúc
                    
               
        
    }
}
