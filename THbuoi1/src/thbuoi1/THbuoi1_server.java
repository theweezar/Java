/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thbuoi1;

import bai1.*;
import bai2.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author daiph
 */
public class THbuoi1_server {
    public static ServerSocket server;
    public static Socket client;
    public static DataInputStream dis;
    public static DataOutputStream dos; 
    public static void main(String[] args) throws IOException {
         System.out.println("SERVER TỔNG ĐÃ KẾT NỐI .. ");
        System.out.println("SERVER TỔNG ĐANG NHẬN YÊU CẦU ");
        int option = 0;
        // Nơi nhận yêu cầu 
        do{
            server = new ServerSocket(1000);
            client = server.accept();
            dis = new DataInputStream( client.getInputStream() );
            dos = new DataOutputStream( client.getOutputStream() );
            option = 0;
            
            option = dis.readInt();
            System.out.println("Nhận yêu cầu "+option);

            // Trả về yêu cầu 
            switch( option ){
                case 1: 
                    new Server1().run();
                    
                    client.close();
                    server.close();
                    break;
                case 2:
                    new Server2().run();
                    
                    client.close();
                    server.close();
                    break;
                
                case 0:
                    client.close();
                    server.close();
                    System.exit(0);
                default:
                    System.out.println("Không tìm thấy server !Mời bạn nhập lại từ Client");
                    break;
            }

        }while(option != 0);
    }
}
