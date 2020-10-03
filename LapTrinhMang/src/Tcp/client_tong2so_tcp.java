/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package JaVaChieuThu3;
package Tcp;
import com.sun.org.apache.bcel.internal.util.ByteSequence;
import java.util.*;
import java.io.*;
import java.net.*;
import javax.net.ssl.SSLSocket;
/**
 *
 * @author ASUS
 */
public class client_tong2so_tcp {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("localhost",1234);
        DataInputStream inpu = new DataInputStream(client.getInputStream());
        DataOutputStream oupu = new DataOutputStream(client.getOutputStream());
        Scanner nhap = new Scanner(System.in);
        Boolean kiemtra = false;
        Boolean kiemtra1 = false;
        int a =0;
        int b=0;
        int tong =0;
        while (!kiemtra) {
            try
            {
                System.out.println("Nhap vao so a: "); 
                a = nhap.nextInt();
                kiemtra = true;
            }catch(Exception e){
                    System.out.println("Ban phai nhap so! hay nhap lai..."); 
                    nhap.nextLine();
            }
        }
        while (!kiemtra1) {
            try
            {
                System.out.println("Nhap vao so b: "); 
                b = nhap.nextInt();
                kiemtra1 = true;
            }catch(Exception e){
                    System.out.println("Ban phai nhap so! hay nhap lai..."); 
                    nhap.nextLine();
            }
        }
        oupu.writeInt(a);
        oupu.writeInt(b);
        tong = inpu.readInt();
        System.out.println("Tong 2 so la: " + tong); 
        client.close();
    }
}
