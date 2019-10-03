package btslidet5.bai1;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Bai1
 */
public class TuDien {
  private ArrayList<Tu> tudien = new ArrayList<Tu>();
  public Scanner scan = new Scanner(System.in);
  TuDien(){
    for(int i=0;i<2;i++){
      Nhap();
    }
  }
  public void Nhap(){
    String tu,nghia;
    System.out.print("Tu: ");
    tu = scan.nextLine();
    System.out.print("Nghia: ");
    nghia = scan.nextLine();
    tudien.add(new Tu(tu,nghia));
  }
  public void Xuat(){
    for(int i=0;i<tudien.size();i++){
      tudien.get(i).Xuat();
    }
  }
  public int Tim(){
    System.out.print("Tim tu: ");
    String t = scan.nextLine();
    for(int i=0;i<tudien.size();i++){
      if (tudien.get(i).getTu().equalsIgnoreCase(t)){
        return i;
      }
    }
    System.out.println("Khong tim thay");
    return -1;
  }
  public void TraTu(){
    int vt = Tim();
    if (vt != -1) tudien.get(vt).Xuat();
  }
  public void SuaTu(){
    int vt = Tim();
    if (vt != -1){
      System.out.print("Nghia moi: ");
      tudien.get(vt).setNghia(scan.nextLine());
    }
  }
  public void XoaTu(){
    int vt = Tim();
    if (vt != -1) tudien.remove(vt);
  }
  public void run(){
    
  }
  public static void main(String[] args) {
    TuDien tudien = new TuDien();
    tudien.Xuat();
    tudien.TraTu();
    tudien.SuaTu();
    tudien.Xuat();
    tudien.XoaTu();
    tudien.Xuat();
  }
}