package btslidet5.bai3;

import java.util.Scanner;

/**
 * KhachHang
 */
public class KhachHang {

  private String password;
  private String ten;
  private long sodu; // số dư trong tài khoản

  public KhachHang(){
    Nhap();
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getTen() {
    return ten;
  }

  public void setTen(String ten) {
    this.ten = ten;
  }

  public long getSodu() {
    return sodu;
  }

  public void setSodu(long sodu) {
    this.sodu = sodu;
  }

  public void Nhap(){
    Scanner scan = new Scanner(System.in);
    System.out.print("Nhap ten: ");
    setTen(scan.nextLine());
    System.out.print("Nhap mk : ");
    setPassword(scan.nextLine());
    setSodu(0);
  }

  public void Xuat(){
    System.out.printf("Ten: %s - so du tk: %d\n",getTen(),getSodu());
  }
}