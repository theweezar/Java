/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Multichat.client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Luồng này là luồng nhận tất cả tin nhắn đến từ những client khác hoặc đến từ chính server và in ra màn hình của người nhận
 * @author hpmdu
 */
public class ClientReadThread extends Thread{
    
    private Socket client;
    
    public ClientReadThread(Socket client){
        this.client = client;
    }
    
    @Override
    public void run(){
        DataInputStream in = null;
        try{
            in = new DataInputStream(client.getInputStream());
            while(true){
                String msg = in.readUTF();
                System.out.println(msg);
            }
        }
        catch(IOException e){
            try{
                in.close();
                client.close();
            }
            catch(IOException e1){
                e1.printStackTrace();
            }
        }
    }
}
