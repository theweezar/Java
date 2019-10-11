package SlideT6;

import java.util.Scanner;

/**
 * Bai1
 */
public class Bai1 {

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    int n;
    try {
      System.out.print("Nhap so n: ");
      n = scan.nextInt();
      System.out.printf("So vua nhap la %d\n",n);
    } catch (Exception e) {
      System.out.println("Khong duoc nhap ky tu chu");
    }
    scan.close();
  }
}