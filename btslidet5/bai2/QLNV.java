package btslidet5.bai2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * QLNV
 */
public class QLNV {

  private ArrayList<NhanVien> dsnv;
  
  public QLNV(){
    dsnv = new ArrayList<>();
    int sl;
    Scanner scan = new Scanner(System.in);
    System.out.print("Nhap so luong nhan vien: ");
    sl = scan.nextInt();
    for(int i=0;i<sl;i++) dsnv.add(new NhanVien());
    inDS();
  }

  public void inDS(){
    for(NhanVien nv:dsnv){
      nv.Xuat();
    }
  }

  public void SapXepTheoTen(){
    Collections.sort(dsnv,new Comparator<NhanVien>(){
      @Override
      public int compare(NhanVien nv1, NhanVien nv2){
        int tmp = nv1.getTen().compareToIgnoreCase(nv2.getTen());
        if (tmp != 0) return tmp;
        else{
          tmp = nv1.getTendem().compareToIgnoreCase(nv2.getTendem());
          if (tmp != 0) return tmp;
          else{
            tmp = nv1.getHo().compareToIgnoreCase(nv2.getHo());
            return tmp;
          }
        }
      }
    });
  }

  public void SapXepTheoLuong(){
    Collections.sort(dsnv,new Comparator<NhanVien>(){
      @Override
      public int compare(NhanVien nv1, NhanVien nv2){
        return (int)(nv1.getLuong() - nv2.getLuong());
      }
    });
  }

  public void SapXepTheoNgaySinh(){
    Collections.sort(dsnv,new Comparator<NhanVien>(){
      @Override
      public int compare(NhanVien nv1, NhanVien nv2){
        int tmp = nv1.getSn().getNam() - nv2.getSn().getNam();
        if (tmp != 0) return tmp;
        else{
          tmp = nv1.getSn().getThang() - nv2.getSn().getThang();
          if (tmp != 0) return tmp;
          else{
            tmp = nv1.getSn().getNgay() - nv2.getSn().getNgay();
            return tmp;
          }
        }
      }
    });
  }

  public void run(){
    Scanner scan = new Scanner(System.in);
    while(true){
      System.out.print("1. Sap xep theo ten\n2. Sap xep theo luong\n3. Sap xep theo ngay sinh\n0. Thoat\nLua chon: ");
      switch (scan.next()) {
        case "1":
          SapXepTheoTen();
          break;
        case "2":
          SapXepTheoLuong();
          break;
        case "3":
          SapXepTheoNgaySinh();
          break;
        case "0":
          System.exit(0);
        default:
          break;
      }
      inDS();
    }
  }

  public static void main(String[] args) {
    QLNV qlnv = new QLNV();
    qlnv.run();
  }
}