package btvnt5;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

/**
 * DSHANGHOA
 */
public class DSHANGHOA {
  private ArrayList<ThucPham> dstp;
  DSHANGHOA(){
    dstp = new ArrayList<ThucPham>();
    
  }
  public void addFood(){
    Scanner scan = new Scanner(System.in);
    String mh,th;
    int gt;
    System.out.print("Nhap ma hang : ");
    mh = scan.nextLine();
    System.out.print("Nhap ten hang: ");
    th = scan.nextLine();
    System.out.print("Nhap gia tien: ");
    gt = scan.nextInt();
    dstp.add(new ThucPham(mh, th, gt, LocalDate.now().toString(),LocalDate.now().plusDays(7).toString()));
  }
  public void showListFood(){
    for(int i=0;i<dstp.size();i++){
      dstp.get(i).show();
    }
  }
  public void run(){
    Scanner scan = new Scanner(System.in);
    int c;
    while(true){
      System.out.println("1. Them thuc pham ");
      System.out.println("2. Xuat DS thuc pham ");
      System.out.println("3. Them dien may ");
      System.out.println("4. Xuat DS dien may ");
      System.out.println("5. Thoat ");
      System.out.print("Lua chon: ");
      c = scan.nextInt();
      if (c == 5) break;
      else{
        switch (c) {
          case 1:
            addFood();
            break;
          case 2:
            showListFood();
            break;
          default:
            break;
        }
      }
    }
    scan.close();
  }
  public static void main(String[] args) {
    DSHANGHOA dshh = new DSHANGHOA();
    dshh.run();
  }
}