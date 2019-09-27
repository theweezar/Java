package btvnt6;

import java.util.Scanner;

/**
 * ConNguoi
 */
public class ConNguoi {
  protected String hoten;
  protected int namsinh;
  ConNguoi(String hoten,int namsinh){
    this.hoten = hoten;
    this.namsinh = namsinh;
  }
  ConNguoi(){
    Nhap();
  }
  public void Nhap(){
    Scanner scan = new Scanner(System.in);
    System.out.print("Nhap ho va ten: ");
    this.hoten = scan.nextLine();
    System.out.print("Nhap nam sinh : ");
    this.namsinh = scan.nextInt(); 
  }
  public void XuatThongTinChung(){
    System.out.printf("Ho ten  : %s\n",hoten);
    System.out.printf("Nam sinh: %d\n",namsinh);
  }
}