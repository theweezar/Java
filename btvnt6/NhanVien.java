package btvnt6;

import java.util.Scanner;

/**
 * NhanVien
 */
public class NhanVien extends ConNguoi {
  private int luong;
  private String ngaynv;
  private PhongBanKhoa pbk;
  NhanVien(){
    super();
    nhapNV();
    pbk = new PhongBanKhoa();
  }
  public void nhapNV(){
    Scanner scan = new Scanner(System.in);
    System.out.print("Nhap luong          : ");
    this.luong = scan.nextInt();
    scan.nextLine();
    System.out.print("Nhap ngay nhan viec : ");
    this.ngaynv = scan.nextLine();
  }
  public void XuatNV(){
    XuatThongTinChung();
    System.out.printf("Luong: %d\n",luong);
    System.out.printf("Ngay nhan vien: %s\n",ngaynv);
    pbk.Xuat();
  }
  public static void main(String[] args) {
    NhanVien nv = new NhanVien();
    nv.XuatNV();
  }
}