
package BT1_LTM;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static java.lang.Math.acos;
import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.net.ServerSocket;
import java.net.Socket;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author hpmdu
 */
public class TCPServer {

    public static File file;
    public static int PORT = 1234;

    public static void main(String[] args)  throws IOException {
        int option;
        System.out.println("Server đã kích hoạt thành công !");
            do{
                ServerSocket server = new ServerSocket(PORT);
                System.out.print(" Nhận yêu cầu ");
                Socket client = server.accept();
            DataInputStream inp = new DataInputStream( client.getInputStream() ); 
            DataOutputStream out = new DataOutputStream( client.getOutputStream() );
            option = inp.readInt();
            switch(option){
                
                case 1: System.out.println("số "+option);
                   
                    System.out.println("Đang giải phương trình bậc 3 với ");
                    double a,b,c,d,delta=0, k=0, x1=0, x2=0, x3=0, x0=0, x=0, X=0;
                        a = inp.readDouble();
                        b = inp.readDouble();
                        c = inp.readDouble();
                        d = inp.readDouble();
                    System.out.println(a+"x^3 + ("+b+"x^2) + (" +c+ "x) + (" + d+") = 0" );
                    delta = (double)b*b - 3*a*c;
                    k = (double)(9*a*b*c - 2*pow(b,3) - 27*pow(a,2)*d)/ (2*sqrt(abs(pow(delta,3))));
                   if(delta >0 ) {
                        if(abs(k)<=1) {
                        x1 = (2*sqrt(delta)*cos((acos(k)/3)) - b)/ (3*a);
                        x2 = (2*sqrt(delta)*cos((acos(k)/3 - (2*PI/3))) - b)/(3*a);
                        x3 = (2*sqrt(delta)*cos((acos(k)/3 + (2*PI/3))) - b)/(3*a);
                          System.out.println("PT có 3 nghiệm x1="+x1+" x2= "+"x2"+" x3= "+x3);
                        }
                        else{
                           x0 = ((sqrt(delta)*abs(k))/(3*a*k))*(pow(abs(k) + sqrt(pow(k,2) - 1),1/3) + pow(abs(k) - sqrt(pow(k,2) - 1),1/3)) - (b/(3*a));      
                            System.out.println("PT có nghiệm duy nhất x= "+x0);
                          }
                   }
                   if(delta==0){
                    X = (-b + pow(pow(b,3) - 27*pow(a,2)*d,1/3))/(3*a);
                       System.out.println("PT có nghiệm bội X"+ X);
                    }
                    if(delta<0){
                        x = (sqrt(abs(delta))/(3*a))*(pow(k + sqrt(pow(k,2) + 1),1/3) + pow(k - sqrt(pow(k,2) + 1),1/3)) - (b/(3*a));
                        System.out.println("PT có nghiệm duy nhất x"+x);
                 }
                break;
                default: break;
            }

            client.close();
            server.close();

            }while(option != 0);
        
    }
    
}
