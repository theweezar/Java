/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCPSocketFile;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author hpmdu
 */
public class MySocket {
    
    private Integer PORT = null;
    private DataOutputStream out = null;
    private DataInputStream in = null;
    
    public MySocket(Integer PORT){
        this.PORT = PORT;
    }
    
    public ServerSocket getServer(){
        try{
            return new ServerSocket(PORT);
        }
        catch(IOException e){
            e.printStackTrace();
            System.out.println("ERROR");
            return null;
        }
    }
    
    public void createClient(String path){
        try{
            Socket client = new Socket(path,PORT);
            out = new DataOutputStream(client.getOutputStream());
            in  = new DataInputStream(client.getInputStream());
        }
        catch(UnknownHostException e1){
            e1.printStackTrace();
        }
        catch(IOException e2){
            e2.printStackTrace();
        }
    }
    
    public void getAccept(){
        try{
            Socket client = getServer().accept();
            out = new DataOutputStream(client.getOutputStream());
            in  = new DataInputStream(client.getInputStream());
            System.out.println("Server is running.....");
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public DataOutputStream getOutput(){
        return this.out;
    }
    
    public DataInputStream getInput(){
        return this.in;
    }
}
