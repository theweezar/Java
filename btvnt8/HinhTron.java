package btvnt8;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * HinhTron
 */
public class HinhTron implements Hinh{
  private double r;
  HinhTron(){
    Nhap();
  }
  public void Nhap(){
    Scanner scan = new Scanner(System.in);
    System.out.print("Nhap ban kinh: ");
    this.r = scan.nextDouble();
  }
  public void Xuat(){
    DecimalFormat df = new DecimalFormat("0.00");
    System.out.printf("Ban kinh : %s\n",df.format(r));
    System.out.printf("Dien tich: %s\n",df.format(DienTich()));
  }
  public float DienTich(){
    return (float)r * (float)r * (float)pi;
  }
}