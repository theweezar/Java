/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bai_1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hpmdu
 */
public class TcpServer {
    
    private ServerSocket server = null;
    private Socket client = null;
    private DataOutputStream out = null;
    private DataInputStream in = null;
    
    public TcpServer() throws IOException{
        this.server = new ServerSocket(1234);
        this.client = this.server.accept();
        System.out.println("Ket noi server thanh cong");
        this.out = new DataOutputStream(this.client.getOutputStream());
        this.in = new DataInputStream(this.client.getInputStream());
    }
    
    public int calculate(int a){
        int tong = 0;
        while(a != 0){
            tong += a % 10;
            a = a / 10;
        }
        return tong;
    }
    
    public void run() throws IOException{
        int a = 0;
        while(a != -1){
            // Đọc số a bên int được truyền từ client
            a = in.readInt();
            // Nếu a khác -1 thì tính toán, nếu a bằng -1 thì tiến hành đóng server
            if (a != -1){
                out.writeInt(calculate(a));
            }
        }
        out.close();
        in.close();
        client.close();
        server.close();
    }
    
    public static void main(String[] args) throws IOException{
        TcpServer server = new TcpServer();
        server.run();
    }
}
