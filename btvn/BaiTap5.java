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
class BaiTap5 {
    BaiTap5(){
        Scanner scan = new Scanner(System.in);
        int m,n;
        System.out.print("Nhap m: ");
        m = scan.nextInt();
        System.out.print("Nhap n: ");
        n = scan.nextInt();
        if (m % n == 0) System.out.printf("%d la UCLN",n);
        else if (n % m == 0) System.out.printf("%d la UCLN",m);
        else {
            int hNum, lNum;
            if (m > n){
                hNum = m;
                lNum = n;
            }
            else {
                hNum = n;
                lNum = m;
            }
            for(int i=lNum;i>0;i--){
                if (m % i == 0 && n % i == 0){
                    System.out.printf("%d la UCLN\n",i);
                    break;
                }
            }
        }
    }
}
