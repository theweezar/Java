/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCPSocketFile;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author hpmdu
 */
public class MyClient {
    public static void main(String[] args) throws IOException {
        MySocket client = new MySocket(1234);
        client.createClient("localhost");
        Scanner scan = new Scanner(System.in);
        String fName = "";
        boolean ok = false;
        while(true){
            System.out.print("Nhap ten file: ");
            fName = scan.nextLine();
            if (fName.trim().length() != 0 && !fName.matches("(.*)[\\\\,/,:,*,?,\",<,>,|](.*)(?=.txt)")){
                client.getOutput().writeUTF("txtFolder\\" + fName);
                ok = true;
                break;
            }
            else System.out.println("Vui long khong bo trong hoac ghi ki tu dac biet");
        }
        
        if (ok) System.out.println(client.getInput().readUTF());
    }
}
