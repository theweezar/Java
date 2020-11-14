/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Multichat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Đây là luồng xử lý chính của server vì nó có nhiệm vụ phải truyền tin nhắn giữa các client
 * nhiệm vụ phụ của nó là đọc tất cả tin nhắn của client và upload lên màn hình của admin
 * @author hpmdu
 */
public class ServerReadThread extends Thread {

    private Socket user;
    
    public ServerReadThread(Socket user){
        // user ở đây nghĩa là user vừa mới kết nối tới server
        this.user = user;
    }
    
    @Override
    public void run(){
        DataInputStream in = null;
        try{
            in = new DataInputStream(user.getInputStream());
            while(true){
                // Đọc tin nhắn của 1 trong những user đã kết nối tới server
                String msg = in.readUTF();
                // Phần này truyền tất cả các tin nhắn từ client này qua các client khác 
                for(Socket ConnectedUser : MyServer.userList){
                    // Để cho người dùng không gửi lại cho chính mình
                    if (user.getPort() != ConnectedUser.getPort()){
                        DataOutputStream out = new DataOutputStream(ConnectedUser.getOutputStream());
                        out.writeUTF(msg);
                    }
                }
                System.out.println(msg);
            }
        }
        catch(IOException e_1){
            try{
                in.close();
                user.close();
            }
            catch(IOException e_2){
                e_2.printStackTrace();
            }
        }
    }
}
