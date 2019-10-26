package btslidet5.bai2;

import java.util.Scanner;

/**
 * NgaySinh
 */
public class NgaySinh {

  private int ngay;
  private int thang;
  private int nam;

  public NgaySinh(){
    Nhap();
  }

  public NgaySinh(int ngay, int thang, int nam) {
    this.ngay = ngay;
    this.thang = thang;
    this.nam = nam;
  }

  public int getNgay() {
    return ngay;
  }

  public void setNgay(int ngay) {
    this.ngay = ngay;
  }

  public int getThang() {
    return thang;
  }

  public void setThang(int thang) {
    this.thang = thang;
  }

  public int getNam() {
    return nam;
  }

  public void setNam(int nam) {
    this.nam = nam;
  }

  public void Nhap(){
    Scanner scan = new Scanner(System.in);
    System.out.print("Ngay sinh : ");
    setNgay(scan.nextInt());
    System.out.print("Thang sinh: ");
    setThang(scan.nextInt());
    System.out.print("Nam sinh  : ");
    setNam(scan.nextInt());
  }
  
}