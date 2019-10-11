package SlideT6;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.lang.Math;
import java.text.DecimalFormat;

/**
 * Bai3
 */
public class Bai3 {

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    DecimalFormat df = new DecimalFormat("0.00");
    double inBank = 0;
    long send = 0;
    long withdraw = 0;
    int n = 0;
    while(true){
      System.out.println("1. Goi tien - 2. Rut tien - 0. Thoat");
      System.out.printf("So tien hien co: %s",df.format(inBank));
      System.out.print("\nLua chon:");
      try {
        n = scan.nextInt();
        switch (n) {
          case 1:
            System.out.print("Nhap so tien can goi: ");
            send = (long)Math.abs(scan.nextLong());
            inBank += (double)send;
            break;
          case 2:
            System.out.print("Nhap so tien can rut: ");
            withdraw = (long)Math.abs(scan.nextLong());
            if (withdraw > inBank){
              System.out.println("So tien trong tai khoan khong du de rut\n");
            }
            else inBank -= (double)withdraw;
            break;
          case 0:
            scan.close();
            System.exit(0);
          default:
            break;
        }
      } 
      catch (InputMismatchException e) {
        System.out.println("Nhap sai !!\n");
        scan.next(); // clear scanner when we get the input wrong
      }
    }
  }
}