/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btvn;
import java.util.Scanner;

/**
 *
 * @author hocvien
 */
class BaiTap4 {
    BaiTap4(){
        Scanner scan = new Scanner(System.in);
        int a,b;
        System.out.print("Nhap a: ");
        a = scan.nextInt();
        System.out.print("Nhap b: ");
        b = scan.nextInt();
        
        if (b % a == 0) System.out.printf("%d la BCNN\n",b);
        else if (a % b == 0) System.out.printf("%d la BCNN\n",a);
        else System.out.printf("%d la BCNN\n",a*b);
    }
}
