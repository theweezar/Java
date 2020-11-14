/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Multichat.client;

import Support.Colors;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author hpmdu
 */
public class Client {
    
    private InetAddress host;
    private int port;

    public Client(InetAddress host, int port) {
        this.host = host;
        this.port = port;
    }
    
    public void run()throws IOException {
        Scanner sc = new Scanner(System.in);
        String name = "";
        while(name.trim().length() == 0){
            System.out.print("Nhập tên của bạn: ");
            name = sc.nextLine().trim();
        }
        System.out.println("=========================");
        System.out.println("| ~ Welcome to Server ~ |");
        System.out.println("=========================");
        Socket client = new Socket(host, port);
        ClientReadThread cRThread = new ClientReadThread(client);
        cRThread.start();
        ClientWriteThread cWThread = new ClientWriteThread(client, name);
        cWThread.start();
    }
    
    public static void main(String[] args) throws IOException{
        Client client = new Client(InetAddress.getLocalHost(), 1234);
        client.run();
    }
}
