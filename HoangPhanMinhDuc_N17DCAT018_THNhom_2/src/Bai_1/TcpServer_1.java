/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bai_1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author hpmdu
 */
public class TcpServer_1 {
    
    private ServerSocket server = null;
    private Socket client = null;
    private DataOutputStream out = null;
    private DataInputStream in = null;

    public TcpServer_1() throws IOException {
        server = new ServerSocket(1234);
        client = server.accept();
        in = new DataInputStream( client.getInputStream() );
        out = new DataOutputStream( client.getOutputStream() );
    }
    
    public void yeuCau1()throws IOException{
        int n = in.readInt();
        int tong = 0;
        for(int i = 0; i <= n; i++){
            tong += (2*i + 1);
        }
        out.writeInt(tong);
    }
    
    public void yeuCau2()throws IOException{
        int n = in.readInt();
        int tong = 0;
        for(int i = 0; i <= n; i++){
            tong += i*(i+1);
        }
        out.writeInt(tong);
    }
    
    public void yeuCau3()throws IOException{
        int n = in.readInt();
        int tong = 0;
        for(int i = 1; i <= n; i++){
            int tmp = i;
            if (i % 2 == 0) tmp = -tmp;
            tong += tmp;
        }
        out.writeInt(tong);
    }
    
    public void yeuCau4() throws IOException{ // a+b
        int a = in.readInt();
        int b = in.readInt();
        out.writeInt(a + b);
    }
    
    public void yeuCau5() throws IOException{ // (a+b)^3
        int a = in.readInt();
        int b = in.readInt();
        out.writeInt((int)Math.pow((double)(a+b), 3));
    }
    
    public void yeuCau6() throws IOException{ // (a+b)^2
        int a = in.readInt();
        int b = in.readInt();
        out.writeInt((int)Math.pow((double)(a+b), 2));
    }
    
    public void yeuCau7()throws IOException{
        int n = in.readInt();
        int s1 = 1, s2 = 0;
        for(int i = 1; i <= n; i++){
            s1 *= i;
        }
        for(int i = 1; i <= n; i++){
            s2 += i;
        }
        out.writeInt(s1 * s2);
    }
    
    public void run_1()throws IOException{
        int option = 0;
        do{
            option = in.readInt();
//            System.out.println("Nhận yêu cầu "+option);

            // Trả về yêu cầu 
            switch( option ){
                case 1: 
                    yeuCau1();
                    break;
                case 2:
                    yeuCau2();
                    break;
                case 3: 
                    yeuCau3();
                    break;
                case 4:
                    yeuCau4();
                    break;
                case 5: 
                    yeuCau5();
                    break;
                case 6:
                    yeuCau6();
                    break;
                case 7:
                    yeuCau7();
                    break;
                case 0:
                    client.close();
                    server.close();
                    return;
                default:
                    System.out.println(" x ");
                    break;
            }

        }while(option != 0);
    }
    
    public static void main(String[] args) throws IOException{
        TcpServer_1 server = new TcpServer_1();
        server.run_1();
    }
}
