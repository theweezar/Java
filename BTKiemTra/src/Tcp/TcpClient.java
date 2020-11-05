/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.InputMismatchException;
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
        this.client = new Socket("localhost", 1234);
        this.out = new DataOutputStream(this.client.getOutputStream());
        this.in = new DataInputStream(this.client.getInputStream());
        scan = new Scanner(System.in);
    }
    
    public int menu(){
        String[] items = {
            "Option 1",
            "Option 2",
            "Option 3"
        };
        int opt = 0;
        System.out.println("------------------------------------");
        System.out.println("| Hoàng Phan Minh Đức - N17DCAT018 |");
        System.out.println("|               Menu               |");
        System.out.println("------------------------------------");
        for(int i = 0; i < items.length; i++){
            System.out.printf("%d. %s\n", i + 1, items[i]);
        }
        System.out.print("Chọn: ");
        try{
            opt = scan.nextInt();
        }
        catch(InputMismatchException e){
            scan.next();
            return -1;
        }
        return opt;
    }
    
    public void run()throws IOException{
        int opt = -1;
        while(opt != 0){
            opt = menu();
            switch(opt){
                case 1:
                    System.out.println("Option 1");
                    out.writeInt(opt);
                    break;
                case 2:
                    System.out.println("Option 2");
                    out.writeInt(opt);
                    break;
                case 3:
                    System.out.println("Option 3");
                    out.writeInt(opt);
                    break;
                case 0:
                    out.writeInt(opt);
                    System.out.println("Thoát");
                    scan.close();
                    out.close();
                    in.close();
                default:
                    System.out.println("Nhập sai lựa chọn. Vui lòng nhập lại.");
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        TcpClient client = new TcpClient();
        client.run();
    }
}
