/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KetNoiDB;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author hpmdu
 */
public class QLNVServer {
    
    private ServerSocket server = null;
    private Socket client = null;
    private DataOutputStream out = null;
    private DataInputStream in = null;
    
    
    public QLNVServer() throws IOException{
        this.server = new ServerSocket(1234);
        this.client = this.server.accept();
        this.out = new DataOutputStream(this.client.getOutputStream());
        this.in = new DataInputStream(this.client.getInputStream());
    }
    
    public void run(){
        int a;
        
    }
    
    public static void main(String[] args) throws IOException{
        QLNVServer s = new QLNVServer();
        s.run();
    }
}
