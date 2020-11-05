/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bai_3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author hpmdu
 */
public class TcpClient {
    
    private Socket client = null;
    private DataOutputStream out = null;
    private DataInputStream in = null;
    
    public TcpClient() throws IOException{
        this.client = new Socket("localhost", 1234);
        this.out = new DataOutputStream(this.client.getOutputStream());
        this.in = new DataInputStream(this.client.getInputStream());
    }
    
    public void login() throws IOException{
        String username, password;
        boolean ok = false;
        Scanner scan = new Scanner(System.in);
        while(!ok){
            // client nhập username và password
            System.out.print("Username: ");
            username = scan.nextLine();
            System.out.print("Password: ");
            password = scan.nextLine();
            // Gửi username và password tới server để xử lý
            out.writeUTF(username);
            out.writeUTF(password);
            // Nhận phản hồi hợp lệ từ server
            ok = in.readBoolean();
            if (!ok) System.out.println("Dang nhap that bai");
        }
        System.out.println("Dang nhap thanh cong");
    }
    
    public void getPoint()throws IOException {
        String table = in.readUTF();
        System.out.println("Kết quả học tập");
        System.out.println(table);
    }
    
    public void run() throws IOException{
        login();
        getPoint();
    }
    
    public static void main(String[] args) throws IOException {
        TcpClient client = new TcpClient();
        client.run();
    }
}
