package btvnt6;

import java.util.Scanner;

/**
 * PhongBanKhoa
 */
public class PhongBanKhoa {
  private String ma;
  private String ten;
  PhongBanKhoa(){
    Nhap();
  }
  void Nhap(){
    Scanner scan = new Scanner(System.in);
    System.out.print("Nhap ma PBK : ");
    this.ma = scan.nextLine();
    System.out.print("Nhap ten PBK: ");
    this.ten = scan.nextLine();
  }
  void Xuat(){
    System.out.printf("Ma PBK : %s\n",ma);
    System.out.printf("Ten PBK: %s\n",ten);
  }
}