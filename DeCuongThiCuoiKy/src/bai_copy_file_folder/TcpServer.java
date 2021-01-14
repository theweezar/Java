/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bai_copy_file_folder;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
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
    
    public void download() throws IOException {
        // Luồng đọc byte được gửi từ client
        BufferedInputStream buff_in = new BufferedInputStream(this.client.getInputStream());
        // Luồng ghi byte vào file từ server xuống máy local
        BufferedOutputStream buff_out = new BufferedOutputStream(this.client.getOutputStream());
        byte[] b = new byte[1024 * 8];
        while(buff_in.read(b) != -1){
            buff_out.write(b);
            buff_out.flush();
        }
    }
    
    public static void main(String[] args) throws IOException {
        TcpServer server = new TcpServer();
        server.download();
    }
}
