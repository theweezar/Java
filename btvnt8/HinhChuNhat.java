package btvnt8;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * HinhChuNhat
 */
public class HinhChuNhat implements Hinh{
  private double dai;
  private double rong;

  HinhChuNhat(){
    Nhap();
  }
  public void Nhap(){
    Scanner scan = new Scanner(System.in);
    System.out.print("Nhap chieu dai: ");
    this.dai = scan.nextDouble();
    System.out.print("Nhap chieu rong: ");
    this.rong = scan.nextDouble();
  }
  public void Xuat(){
    DecimalFormat df = new DecimalFormat("0.00");
    System.out.printf("Chieu dai : %s\n",df.format(dai));
    System.out.printf("Chieu rong: %s\n",df.format(rong));
    System.out.printf("Dien tich : %s\n",df.format(DienTich()));
  }
  public float DienTich(){
    return (float)dai * (float)rong;
  }
}