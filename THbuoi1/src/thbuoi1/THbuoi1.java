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
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author daiph
 */
public class THbuoi1 {

    public static void menu(){
        System.out.println("**************************************************");
        System.out.println("                      MENU                        ");
        System.out.println("                   1. Bai 1                       ");
        System.out.println("                   2. Bai 2                       ");
        System.out.println("   >>>>>>>>>>> Nhan phim 0 de thoat <<<<<<<<<<<   ");
        System.out.println("                                                  ");
        System.out.println("               Phan Đại N17DCAT013                ");
        System.out.println("**************************************************");
        System.out.print(" Yêu cầu của bạn là : ");
    }
    /**
     * @param args the command line arguments
     */
    public static Socket client; 
    public static DataInputStream dis;
    public static DataOutputStream dos;
    
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean check = false;
        int luachon = 0;
        int chay = 0;
        do{
            client = new Socket("localhost",1000);
            dis = new DataInputStream( client.getInputStream() );
            dos = new DataOutputStream( client.getOutputStream() );
            menu();
            try {
                luachon = sc.nextInt();
                switch(luachon){
                    case 1: 
                        dos.writeInt(luachon);
                        new Client1().run();
                        break;
                    case 2:
                        dos.writeInt(luachon);
                        new Client2().run();
                        break;
                    case 0:
                        dos.writeInt(luachon);
                        client.close();
                        System.exit(0);
                    default:
                        dos.writeInt(-1);
                        throw new Exception();
                        
                }
            } catch (Exception e) {
                System.out.println("Sai kiểu dữ liệu ! Mời nhập lại yêu cầu ");
                sc.nextLine();
                System.out.println("Yêu cầu của bạn là: ");
            }
            
        }while(luachon != 0);
    }
    
}
