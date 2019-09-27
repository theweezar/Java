package btvnt5;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * DSHANGHOA
 */
public class DSHANGHOA {
  private ArrayList<HangHoa> dshh;
  DSHANGHOA(){
    dshh = new ArrayList<HangHoa>();
  }
  public void addFood(){
    dshh.add(new ThucPham());
  }
  public void addMachine(){
    dshh.add(new DienMay());
  }
  public void showListFood(){
    for(int i=0;i<dshh.size();i++){
      if (dshh.get(i).getClass() == ThucPham.class){
        ((ThucPham)dshh.get(i)).show();
      }
    }
  }
  public void showListMachine(){
    for(int i=0;i<dshh.size();i++){
      if (dshh.get(i).getClass() == DienMay.class){
        ((DienMay)dshh.get(i)).show();
      }
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
          case 3:
            addMachine();
            break;
          case 4:
            showListMachine();
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