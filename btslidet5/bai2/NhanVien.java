package btslidet5.bai2;

import java.util.Scanner;

/**
 * NhanVien
 */
public class NhanVien {

  private String ho;
  private String tendem;
  private String ten;
  private long luong;
  private NgaySinh sn;

  public NhanVien(){
    Nhap();
  }

  public String getHo() {
    return ho;
  }

  public void setHo(String ho) {
    this.ho = ho;
  }

  public String getTendem() {
    return tendem;
  }

  public void setTendem(String tendem) {
    this.tendem = tendem;
  }

  public String getTen() {
    return ten;
  }

  public void setTen(String ten) {
    this.ten = ten;
  }

  public long getLuong() {
    return luong;
  }

  public void setLuong(long luong) {
    this.luong = luong;
  }

  public NgaySinh getSn() {
    return sn;
  }

  public void setSn(NgaySinh sn) {
    this.sn = sn;
  }

  public void Nhap(){
    Scanner scan = new Scanner(System.in);
    System.out.print("Nhap ho      : ");
    setHo(scan.nextLine());
    System.out.print("Nhap ten dem : ");
    setTendem(scan.nextLine());
    System.out.print("Nhap ten     : ");
    setTen(scan.nextLine());
    setSn(new NgaySinh());
    System.out.print("Nhap luong   : ");
    setLuong(scan.nextLong());
  }

  public void Xuat(){
    System.out.printf("FullName: %s %s %s ; Birthday: %d-%d-%d ; Salary: %d\n",getHo(),getTendem(),getTen(),sn.getNgay(),sn.getThang(),sn.getNam(),getLuong());
  }

  public static void main(String[] args) {
    NhanVien nv = new NhanVien();
  }
}