/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bai_1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author hpmdu
 */
public class TcpClient {
    private Socket client = null;
    private DataOutputStream out = null;
    private DataInputStream in = null;
    private Scanner scan = null;
    
    public TcpClient() throws IOException{
//        this.client = new Socket("localhost", 1235);
//        this.out = new DataOutputStream(this.client.getOutputStream());
//        this.in = new DataInputStream(this.client.getInputStream());
//        scan = new Scanner(System.in);
    }
    
    public void run() throws IOException {
        
    }
    
    public void bai_1_demkytu(String input){
        // Phần đọc từ client - in.read
        System.out.println("Chuỗi nhập vào: " + input);
        String output = "";
        String[] arr = input.trim().split("\\s+");
        output += String.format("Chuỗi vừa nhập có %d từ\n", arr.length);
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr.length; j++){
                if (arr[i].compareTo(arr[j]) > 0){
                    String tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
        int count = 1;
        for(int i = 1; i < arr.length; i++){
            if (arr[i - 1].equals(arr[i])){
                count++;
            }
            else{
                output += String.format(("%s xuất hiện %d lần\n"), arr[i - 1], count);
                count = 1;
            }   
        }
        if (arr[arr.length - 1].equals(arr[arr.length - 2])) 
            output += String.format(("%s xuất hiện %d lần\n"), arr[arr.length - 1], count);
        else output += String.format(("%s xuất hiện 1 lần\n"), arr[arr.length - 1]);
        // Phần gửi ra cho client - out.write
        System.out.println(output);
    }
    
    public static void main(String[] args) throws IOException {
        TcpClient client = new TcpClient();
//        client.run();
        client.bai_1_demkytu("Hoang    Phan Phan Hoang  Minh  Hoang   Duc     @@");
    }
}
