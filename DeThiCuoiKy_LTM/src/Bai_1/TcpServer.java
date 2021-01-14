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

/**
 *
 * @author hpmdu
 */
public class TcpServer {
    private ServerSocket server = null;
    private Socket client = null;
    private DataOutputStream out = null;
    private DataInputStream in = null;
    
    public TcpServer() throws IOException {
        this.server = new ServerSocket(1235);
        this.client = this.server.accept();
        System.out.println("Kết nối server TCP thành công");
        this.out = new DataOutputStream(this.client.getOutputStream());
        this.in = new DataInputStream(this.client.getInputStream());
    }
    
    public void run() throws IOException {
        
    }
    
    public static void main(String[] args) throws IOException {
        TcpServer server = new TcpServer();
        server.run();
    }
}
