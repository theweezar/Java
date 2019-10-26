package btslidet5.bai3;

import java.util.HashMap;
import java.util.Scanner;

/**
 * NganHang
 */
public class NganHang {

  private HashMap<String,KhachHang> dskh;
  private Scanner scan = new Scanner(System.in);
  
  public NganHang(){
    dskh = new HashMap<>();
  }

  public void ThemKhachHang(){
    System.out.print("Nhap so tai khoan: ");
    dskh.put(scan.nextLine(), new KhachHang());
  }

  public void inDsKhach(){
    for(KhachHang kh:dskh.values()){
      kh.Xuat();
    }
  }

  public void NapTien(){
    System.out.print("Nhap so tai khoan: ");
    KhachHang tmp = dskh.get(scan.nextLine());
    if (tmp == null){
      System.out.print("Nhap sai so tai khoan\n");
    }
    else{
      System.out.printf("MK la: %s\n",tmp.getPassword());
      System.out.print("Nhap mat khau    : ");
      if (tmp.getPassword().equalsIgnoreCase(scan.nextLine())){
        System.out.print("Nhap so tien can nap: ");
        tmp.setSodu(tmp.getSodu() + scan.nextLong());
      }
      else System.out.print("Nhap sai mat khau\n");
    }
  }

  public void run(){
    ThemKhachHang();
    NapTien();
    inDsKhach();
  }

  public static void main(String[] args) {
    NganHang ql = new NganHang();
    ql.run();
  }
}