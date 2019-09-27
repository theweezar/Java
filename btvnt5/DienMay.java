package btvnt5;

import java.util.Scanner;

/**
 * DienMay
 */
public class DienMay extends HangHoa{
  private int baohanh;
  private int dienap;
  private int congsuat;
  DienMay(String mahang,String tenhang,int giatien,int baohanh,int dienap,int congsuat){
    super(mahang,tenhang,giatien);
    this.baohanh = baohanh;
    this.dienap = dienap;
    this.congsuat = congsuat;
  }
  DienMay(){
    super();
    Scanner scan = new Scanner(System.in);
    System.out.print("Thoi gian bao hanh: ");
    this.baohanh = scan.nextInt();
    System.out.print("Dien ap: ");
    this.dienap = scan.nextInt();
    System.out.print("Cong suat: ");
    this.congsuat = scan.nextInt();
  }
  public void show(){
    System.out.printf("\nMa hang hoa : %s\n",getMH());
    System.out.printf("Ten thuc pham: %s\n",getTH());
    System.out.printf("Gia tien     : %d VND\n",getPrice() * 1000);
    System.out.printf("Bao hanh     : %d nam\n",baohanh);
    System.out.printf("Dien ap      : %d\n",dienap);
    System.out.printf("Cong suat    : %d VND\n",congsuat);

  }
}