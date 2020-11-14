/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Multichat.server;

import Support.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * Luồng này là luồng phụ vì server dùng luồng này để gửi tin nhắn của riêng server đi sang tất cả các client
 * @author hpmdu
 */
public class ServerWriteThread extends Thread{
    
    @Override
    public void run(){
        DataOutputStream out = null;
        Scanner sc = new Scanner(System.in);
        while(true){
            // Chủ server sẽ nhập 1 đoạn tin nhắn
            String msg = sc.nextLine();
            // Tin nhắn trên sẽ được gửi đi qua tất cả các người dùng (client) đã kết nối
            if (msg.trim().length() != 0){
                for(Socket user: MyServer.userList){
                    try{
                        out = new DataOutputStream(user.getOutputStream());
                        out.writeUTF(MyServer.serverSay(msg));
                    }
                    catch(IOException e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
