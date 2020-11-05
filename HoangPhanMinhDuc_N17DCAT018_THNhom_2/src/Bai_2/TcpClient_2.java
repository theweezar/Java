/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bai_2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author hpmdu
 */
public class TcpClient_2 {
    
    private Socket client;
    private DataInputStream in;
    private DataOutputStream out;
    private Scanner sc;
    private String menu;
    
    public TcpClient_2() throws IOException{
        client = new Socket("localhost",1235);
        in = new DataInputStream( client.getInputStream());
        out = new DataOutputStream( client.getOutputStream());
        sc = new Scanner(System.in);
    }
    
    public void timvadocfile() throws IOException{
        while(true){
            System.out.print("Nhập đường link nguồn: "); // D:\\BAITAP.txt
            out.writeUTF(sc.nextLine());
            String[] rs = in.readUTF().split("_",2);
            if (rs[0].trim().equals("0")){
                System.out.println(rs[1]);
            }
            else if (rs[0].trim().equals("1")){
                menu = rs[1];
                break;
            }
        }
    }
    
    public void thuchiencacyeucau()throws IOException{
        String o = "";
        while(true){
            System.out.println(menu);
            System.out.println("Hoàng Phan Minh Đức - N17DCAT018");
            System.out.println("===> Nhập 0 để thoát <===");
            System.out.print("Nhập yêu cầu: ");
            o = sc.nextLine().trim();
            out.writeUTF(o);
            if (!o.equals("0")) {
                System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>\n");
                System.out.println(in.readUTF());
                System.out.println("\n<<<<<<<<<<<<<<<<<<<<<<\n");
            }
            else {
                close();
                break;
            }
        }
    }
    
    public void close() throws IOException{
        out.close();
        in.close();;
        sc.close();
    }
    
    public void run_2()throws IOException{
        timvadocfile();
        thuchiencacyeucau();
        close();
        return;
    }
    
    public static void main(String[] args) throws IOException{
        TcpClient_2 client = new TcpClient_2();
        client.run_2();
    }
}
