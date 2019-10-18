package btvnt3;

import java.text.DecimalFormat;
import java.util.Scanner;
/**
 * NHANVIEN
 */
class NHANVIEN {
  private String msnv;
  private String hoten;
  private double luongcb;
  private double hsluong;
  public DecimalFormat df = new DecimalFormat("0.00");
  NHANVIEN(){
    Nhap();
  }
  NHANVIEN(String msnv,String hoten,double hsluong){
    this.msnv = msnv;
    this.hoten = hoten;
    this.hsluong = hsluong;
    this.luongcb = 1500000 * this.hsluong;
  }

  public String getMsnv() {
    return msnv;
  }

  public void setMsnv(String msnv) {
    this.msnv = msnv;
  }

  public String getHoten() {
    return hoten;
  }

  public void setHoten(String hoten) {
    this.hoten = hoten;
  }

  public double getLuongcb() {
    return luongcb;
  }

  public void setLuongcb(double luongcb) {
    this.luongcb = luongcb;
  }

  public double getHsluong() {
    return hsluong;
  }

  public void setHsluong(double hsluong) {
    this.hsluong = hsluong;
  }
  
  public void Nhap(){
    Scanner scan = new Scanner(System.in);
    System.out.print("Nhap MSNV: ");
    this.msnv = scan.nextLine();
    System.out.print("Nhap ho va ten: ");
    this.hoten = scan.nextLine();
    System.out.print("Nhap he so luong: ");
    this.hsluong = scan.nextDouble();
    this.luongcb = 1500000 * this.hsluong;
    
  }

  public void Xuat(){
    System.out.println("\n------------------------\n");
    System.out.printf("MSNV : %s\n",getMsnv());
    System.out.printf("HoTen: %s\n",getHoten());
    System.out.printf("Luong: %s\n",df.format(getLuongcb()));
  }
}
