package btvnt3;

import java.util.Scanner;
/**
 * NHANVIEN
 */
class NHANVIEN {
  private String msnv;
  private String hoten;
  private double luongcb;
  private double hsluong;
  NHANVIEN(boolean input){
    if (input) nhapNV();
    else{
      msnv = "";
      hoten = "";
      hsluong = 0;
      luongcb = 0;
    }
  }
  NHANVIEN(String msnv,String hoten,double hsluong){
    this.msnv = msnv;
    this.hoten = hoten;
    this.hsluong = hsluong;
    this.luongcb = 1500000 * this.hsluong;
  }
  public void setMSNV(String msnv){
    this.msnv = msnv;
  }
  public void setHoTen(String hoten){
    this.hoten = hoten;
  }
  public void setHsluong(double hsluong){
    this.hsluong = hsluong;
    this.luongcb = 1500000 * this.hsluong;
  }
  public String getMSNV(){
    return msnv;
  }
  public String getHoTen(){
    return hoten;
  }
  public double getHsluong(){
    return hsluong;
  }
  public double getLuongCB(){
    return luongcb;
  }
  public void nhapNV(){
    Scanner scan = new Scanner(System.in);
    System.out.print("Nhap MSNV: ");
    this.msnv = scan.nextLine();
    System.out.print("Nhap ho va ten: ");
    this.hoten = scan.nextLine();
    System.out.print("Nhap he so luong: ");
    this.hsluong = scan.nextDouble();
    this.luongcb = 1500000 * this.hsluong;
    scan.close();
  }

}
