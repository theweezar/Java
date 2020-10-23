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
import java.util.Scanner;

/**
 *
 * @author hpmdu
 */
public class QLNVClient {
    
    private Socket client = null;
    private DataOutputStream out = null;
    private DataInputStream in = null;
    
    public QLNVClient() throws IOException{
        this.client = new Socket("localhost", 1234);
        this.out = new DataOutputStream(this.client.getOutputStream());
        this.in = new DataInputStream(this.client.getInputStream());
    }
    
    public void run(){
        
    }
    
    public static void main(String[] args) throws IOException{
        QLNVClient qlnv = new QLNVClient();
        qlnv.run();
        
    }
}
