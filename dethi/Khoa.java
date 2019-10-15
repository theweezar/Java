package dethi;

import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Vector;

/**
 * Khoa
 */
public class Khoa {

  private Vector<SinhVien> ds = null;

  public Khoa(){
    ds = new Vector<SinhVien>();
    
  }

  public void InDS(String loai){
    for(SinhVien sv:ds){
      if (loai.equalsIgnoreCase("attt") && sv.getClass() == SVATTT.class){
        ((SVATTT)sv).Xuat();
      }
      else if(loai.equalsIgnoreCase("cntt") && sv.getClass() == SVCNTT.class){
        ((SVCNTT)sv).Xuat();
      }
    }
  }

  public void SapXepDTK(){
    Collections.sort(ds, new Comparator<SinhVien>() {
      @Override
      public int compare(SinhVien s1,SinhVien s2){
        return (int)(s1.getDtk() - s2.getDtk()); 
      }
    });
  }

  public void run(){
    Scanner scan = new Scanner(System.in);
    while(true){
      System.out.println("Nhap sinh vien (1.CNTT | 2.ATTT)");
      System.out.println("3. In ds sinh vien CNTT");
      System.out.println("4. In ds sinh vien ATTT");
      System.out.println("5. Sap xep tang dan theo DTK");
      System.out.println("6. Tim va xoa sinh vien");
      System.out.println("0. Thoat");
      System.out.print("Chon: ");
      switch (scan.next()) {
        case "1":
          ds.add(new SVCNTT());
          break;
        case "2":
          ds.add(new SVATTT());
          break;
        case "3":
          InDS("cntt");
          break;
        case "4":
          InDS("attt");
          break;
        case "5":
          SapXepDTK();
          break;
        case "6":
          break;
        case "0":
          System.exit(0);
        default:
          break;
      }
    }
  }
  public static void main(String[] args) {
    Khoa khoa = new Khoa();
    khoa.run();
  }
}