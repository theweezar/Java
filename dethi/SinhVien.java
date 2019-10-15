package dethi;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * SinhVien
 */
public class SinhVien {

  private String mssv;
  private String hoten;
  private double dtk;
  public DecimalFormat df = new DecimalFormat("0.00");

  public SinhVien(String mssv, String hoten, double dtk) {
    this.mssv = mssv;
    this.hoten = hoten;
    this.dtk = dtk;
  }

  public SinhVien(){
    Nhap();
  }

  public String getMssv() {
    return mssv;
  }

  public void setMssv(String mssv) {
    this.mssv = mssv;
  }

  public String getHoten() {
    return hoten;
  }

  public void setHoten(String hoten) {
    this.hoten = hoten;
  }

  public double getDtk() {
    return dtk;
  }

  public void setDtk(double dtk) {
    this.dtk = dtk;
  }

  public void Nhap(){
    Scanner scan = new Scanner(System.in);
    System.out.print("Nhap mssv: ");
    setMssv(scan.nextLine());
    System.out.print("Nhap ho ten: ");
    setHoten(scan.nextLine());
  }

  public void XepLoai(){
    if (dtk < 5) System.out.println("Sinh vien xep loai kem");
    else if (dtk >= 5 && dtk < 6.5) System.out.println("Sinh vien xep loai TB");
    else if (dtk >= 6.5 && dtk < 8) System.out.println("Sinh vien xep loai kha");
    else if (dtk >= 8 && dtk < 9) System.out.println("Sinh vien xep loai gioi");
    else System.out.println("Sinh vien xep loai xuat sac");
  }

  public void Xuat(){
    System.out.println("------------------------------------------------");
    System.out.printf("Mssv: %s - HoTen: %s - DTK: %s\n",getMssv(),getHoten(),df.format(getDtk()));
  }
}