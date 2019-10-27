package btslidet5.bai3;

import java.util.HashMap;
import java.util.Scanner;

/**
 * NganHang
 */
public class NganHang {

  private HashMap<String,KhachHang> dskh;
  
  public NganHang(){
    dskh = new HashMap<>();
  }

  public void ThemKhachHang(){
    Scanner scan = new Scanner(System.in);
    System.out.print("Nhap so tai khoan: ");
    dskh.put(scan.nextLine(), new KhachHang());
  }

  public void inDsKhach(){
    for(KhachHang kh:dskh.values()){
      kh.Xuat();
    }
  }

  public void NapTien(){
    Scanner scan = new Scanner(System.in);
    System.out.print("Nhap so tai khoan: ");
    KhachHang tmp = dskh.get(scan.nextLine());
    if (tmp == null){
      System.out.print("Nhap sai so tai khoan\n");
    }
    else{
      System.out.print("Nhap mat khau: ");
      if (tmp.getPassword().equalsIgnoreCase(scan.nextLine())){
        System.out.print("Nhap so tien can nap: ");
        tmp.setSodu(tmp.getSodu() + scan.nextLong());
      }
      else System.out.print("Nhap sai mat khau\n");
    }
  }

  public void RutTien(){
    Scanner scan = new Scanner(System.in);
    System.out.print("Nhap so tai khoan: ");
    KhachHang tmp = dskh.get(scan.nextLine());
    if (tmp == null){
      System.out.print("Nhap sai so tai khoan\n");
    }
    else{
      System.out.print("Nhap mat khau: ");
      if (tmp.getPassword().equalsIgnoreCase(scan.nextLine())){
        long rut;
        System.out.print("Nhap so tien can rut: ");
        rut = scan.nextLong();
        if (rut > tmp.getSodu()) System.out.print("TK khong du tien de rut\n");
        else{
          tmp.setSodu(tmp.getSodu() - rut);
        }
      }
      else System.out.print("Nhap sai mat khau\n");
    }
  }

  public void run(){
    Scanner scan = new Scanner(System.in);
    while(true){
      System.out.print("1. Them KH\n2. Nap tien\n3. Rut tien\n4. In DSKH\n0. Thoat\nLua chon: ");
      switch (scan.next()) {
        case "1":
          ThemKhachHang();
          break;
        case "2":
          NapTien();
          break;
        case "3":
          RutTien();
          break;
        case "4":
          inDsKhach();
          break;
        case "0":
          System.exit(0);
        default:
          break;
      }
    }
  }

  public static void main(String[] args) {
    NganHang ql = new NganHang();
    ql.run();
  }
}