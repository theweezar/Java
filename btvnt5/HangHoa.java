package btvnt5;

import java.util.Scanner;

/**
 * HangHoa
 */
public class HangHoa {
  protected String mahang;
  protected String tenhang;
  protected int giatien;
  HangHoa(){
    input();
  }
  HangHoa(String mahang,String tenhang,int giatien){
    this.mahang = mahang;
    this.tenhang = tenhang;
    this.giatien = giatien;
  }
  public void input(){
    Scanner scan = new Scanner(System.in);
    System.out.print("Nhap ma hang : ");
    this.mahang = scan.nextLine();
    System.out.print("Nhap ten hang: ");
    this.tenhang = scan.nextLine();
    System.out.print("Nhap gia tien: ");
    this.giatien = scan.nextInt();
  }
  public String getMH(){
    return mahang;
  }
  public String getTH(){
    return tenhang;
  }
  public int getPrice(){
    return giatien;
  }
}