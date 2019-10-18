package btvnt3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * QLNV
 */
public class QLNV {

  private NHANVIEN[] dsnv;
  private int soluong;
  public QLNV(){
    dsnv = new NHANVIEN[3];
    soluong = 0;
  }

  public int TimKiem(String msnv){
    for(int i = 0; i < soluong; i++){
      if (dsnv[i].getMsnv().equalsIgnoreCase(msnv)){ // dsnv[i].getMsnv() == msnv cũng được
        return i; // tìm ra thì return cái vị trí thằng nhân viên trong danh sách
      }
    }
    return -1; // -1 có nghĩa là không tìm ra
  }

  public void Xoa(){
    Scanner scan = new Scanner(System.in);
    System.out.print("Nhap MSNV: ");
    int vitri = TimKiem(scan.nextLine());
    if (vitri != -1){
      for(int i = vitri + 1; i < soluong; i++){
        dsnv[i-1] = dsnv[i];
      }
      soluong--; // xóa xog thì giảm số lượng nhân viên
    }
    else System.out.println("Khong tim ra nhan vien");
    scan.close();
  }

  public int getSoluong() {
    return soluong;
  }

  public void setSoluong(int soluong) {
    this.soluong = soluong;
  }

  public void SapXepTangDanTheoLuong(){
    Arrays.sort(dsnv, new Comparator<NHANVIEN>() {
      @Override
      public int compare(NHANVIEN nv1, NHANVIEN nv2){
        return (int)(nv1.getLuongcb() - nv2.getLuongcb());
      }
    });
  }

  public void InDS(){
    for(int i=0;i<soluong;i++){
      dsnv[i].Xuat();
    }
  }

  public void ThemNhanVien(){
    dsnv[soluong] = new NHANVIEN();
    soluong++;
  }

  public static void main(String[] args) {
    QLNV ql = new QLNV();
    
    for(int i=0;i<3;i++) ql.ThemNhanVien();
    ql.InDS();
    ql.SapXepTangDanTheoLuong();
    ql.InDS();

  }
  
}