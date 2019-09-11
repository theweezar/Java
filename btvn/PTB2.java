/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btvn;
import java.util.Scanner;
import java.lang.Math;
/**
 *
 * @author hocvien
 */
public class PTB2 {
    PTB2(){
        Scanner scan = new Scanner(System.in);
        double a,b,c,kq,dt;
        System.out.print("Nhap a: ");
        a = scan.nextFloat();
        if (a != 0){
            System.out.print("Nhap b: ");
            b = scan.nextFloat();
            System.out.print("Nhap c: ");
            c = scan.nextFloat(); // 2 => 2+"x" = 2x | "+" + 2x = "+2x"
            String as = a + "x^2"; // -3 => -3 + "x" = "-3x"
            String bs = b != 0 ? b + "x":"";
            String cs = c != 0 ? c + "" :"";
            if (b > 0) bs = "+" + bs;
            if (c > 0) cs = "+" + cs;
            dt = b*b - 4*a*c;
            if (dt > 0){
                double x1,x2;
                x1 = (-b + Math.sqrt(dt)) / (2*a);
                x2 = (-b - Math.sqrt(dt)) / (2*a);
                System.out.printf("PT co 2 nghiem %f va %f\n",x1,x2);
            }
            else if (dt == 0){
                double x;
                x = -b/2*a;
                System.out.printf("PT co nghiem kep %f\n",x);
            }
            else {
                System.out.println("PT vo nghiem");
            }
            System.out.printf("%s %s %s = 0\n",as,bs,cs);
        }
    }
}
