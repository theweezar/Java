/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCPSocketFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author hpmdu
 */
public class MyServer {
    
    public static String[] remove(int index, String[] arr){
        for(int i = index; i < arr.length - 1; i++){
            arr[i] = arr[i+1];
        }
        return arr;
    }
    
    public static void main(String[] args) throws IOException {
        MySocket server = new MySocket(1234);
        server.getAccept();
        String fName = server.getInput().readUTF();
        
        File f = new File(fName);
        if (!f.exists()) {
            System.out.println("File not found");
            
            f.createNewFile();
            
        }
        else {
            Scanner scan = new Scanner(f);
            String plain = "";
            while(scan.hasNextLine()){
                plain = plain + scan.nextLine() + " ";
            }
            String[] arr = plain.split(" ");
            int max = arr[0].length();
            for(int i = 0; i < arr.length; i++){
                if (arr[i].length() > max) max = arr[i].length();
            }
            String sendText = "";
            for(int i = 0; i < arr.length; i++){
                if (arr[i].length() == max){
                    sendText += arr[i] + "\n";
                }
            }
            server.getOutput().writeUTF(sendText);
        }
    }
}
