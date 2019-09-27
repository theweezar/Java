package btvnt6;

import java.util.Scanner;

/**
 * HocVien
 */
public class HocVien extends ConNguoi {
  private double[] diem;
  HocVien(){
    super();
    nhapDiemHV();
  }
  public void nhapDiemHV(){
    diem = new double[3];
    Scanner scan = new Scanner(System.in);
    for(int i=0;i<diem.length;i++){
      System.out.printf("Nhap diem %d: ",i+1);
      diem[i] = scan.nextDouble();
    }
  }
  public void xuatDiemHV(){
    for(int i=0;i<diem.length;i++){
      System.out.printf("Diem %d: %f \n",i+1,diem[i]);
    }
  }
}