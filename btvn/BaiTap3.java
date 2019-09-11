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
class BaiTap3 {
    BaiTap3(){
        Scanner scan = new Scanner(System.in);
        int n,gt = 1;
        System.out.print("Nhap n: ");
        n = scan.nextInt();
        if (n > 0){
            for(int i=1;i<=n;i++){
                gt *= i;
            }
            System.out.printf("Giai thua cua %d la %d\n",n,gt);
        }
        else System.out.println("Error");
    }
}
