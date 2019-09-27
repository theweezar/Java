package btvnt8;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * HinhVuong
 */
public class HinhVuong implements Hinh{
  private double canh;

  HinhVuong(){
    Nhap();
  }
  public void Nhap(){
    Scanner scan = new Scanner(System.in);
    System.out.print("Nhap chieu dai canh: ");
    this.canh = scan.nextDouble();
    scan.close();
  }
  public void Xuat(){
    DecimalFormat df = new DecimalFormat("0.00");
    System.out.printf("Chieu dai canh : %s",df.format(canh));
    System.out.printf("Dien tich : %s",df.format(DienTich()));
  }
  public float DienTich(){
    return (float)canh * (float)canh;
  }
}