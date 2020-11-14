/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Multichat.server;

import Support.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author hpmdu
 */
public class MyServer {
    
    private int port;
    public static ArrayList<Socket> userList;
    
    public MyServer(int port){
        this.port = port;
        userList = new ArrayList<>();
    }
    
    public static String serverSay(String msg){
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy|(HH:mm:ss)");
        return Colors.ANSI_RED+"Server["+ LocalDateTime.now().format(timeFormat) +"]: "+ msg + Colors.ANSI_BLACK;
    }
    
    public void run() throws IOException{
        System.out.println("=========== Chat Room ============");
        System.out.println("|Hoàng Phan Minh Đức - N17DCAT018|");
        System.out.println("==================================");
        ServerSocket server = new ServerSocket(port);
        // Luồng ghi tin nhắn của server bắt đầu chạy 
        ServerWriteThread writethread = new ServerWriteThread();
        writethread.start();
        while(true){
            // Mỗi khi có yêu cầu kết nối tới server của client thì server sẽ chấp thuận
            Socket connectingUser = server.accept();
            System.out.println("Đã kết nối với " + connectingUser);
//            new DataOutputStream(connectingUser.getOutputStream()).writeUTF(MyServer.serverSay("Welcome to chat room"));
            // 1 luồng đọc tin nhắn của client sẽ được bắt đầu mỗi khi kết nối
            ServerReadThread user = new ServerReadThread(connectingUser);
            MyServer.userList.add(connectingUser);
            user.start();
        }
//        server.close();
    }
    
    public static void main(String[] args) throws IOException {
        MyServer server = new MyServer(1234);
        server.run();
    }
    
}
