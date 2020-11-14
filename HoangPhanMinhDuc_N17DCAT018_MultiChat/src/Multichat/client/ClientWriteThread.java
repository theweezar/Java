/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Multichat.client;

import Multichat.server.MyServer;
import Support.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Luồng này là luồng của duy nhất 1 client dùng để gửi tin nhắn lên cho server
 * @author hpmdu
 */
public class ClientWriteThread extends Thread {
    
    private Socket client;
    private String name;

    public ClientWriteThread(Socket client, String name) {
        this.client = client;
        this.name = name;
    }
    
    @Override
    public void run(){
        DataOutputStream out = null;
        Scanner sc = new Scanner(System.in);
        try{
            out = new DataOutputStream(client.getOutputStream());
            while(true){
                String msg = sc.nextLine();
                DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy|(HH:mm:ss)");
                if (msg.trim().length() == 0){
                    // Do nothing
                    
                }
                else out.writeUTF(Colors.ANSI_GREEN + name + "[" + LocalDateTime.now().format(timeFormat) + "]: " + Colors.ANSI_BLUE + msg + Colors.ANSI_BLACK);
            }
        }
        catch(IOException e1){
            try{
                out.close();
                client.close();
            }
            catch(IOException e2){
                e2.printStackTrace();
            }
        }
    }
}
