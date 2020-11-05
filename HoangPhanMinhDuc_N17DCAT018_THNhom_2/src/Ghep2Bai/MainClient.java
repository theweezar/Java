/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ghep2Bai;
import Bai_1.*;
import Bai_2.*;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author hpmdu
 */
public class MainClient extends Thread{
    
    public String option = "";
    
    public static void main(String[] args) throws IOException {
        MainClient mClient = new MainClient();
        String o = "";
        while(true){
            Scanner sc = new Scanner(System.in);
            System.out.print("\n1. Chạy bài 1\n2. Chạy bài 2\nYêu cầu của bạn: ");
            o = sc.nextLine();
            mClient.option = o;
            if (o.equals("1")){
                mClient.start();
                TcpClient_1 c1 = new TcpClient_1();
                c1.run_1();
            }
            else if (o.equals("2")){
                mClient.start();
                TcpClient_2 c2 = new TcpClient_2();
                c2.run_2();
            }
            else if (o.equals("0")){
                System.exit(0);
            }
            else{
                System.out.println("\nNhập sai, vui lòng nhập lại\n");
            }
            o = "";
        }
    }
    
    public void run() {
        try{
            if (this.option.equals("1")){
                TcpServer_1 s1 = new TcpServer_1();
                s1.run_1();
            }
            else if (this.option.equals("2")){
                TcpServer_2 s2 = new TcpServer_2();
                s2.run_2();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
                
    }
}
