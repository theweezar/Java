package btvnt3;
import java.util.Scanner;

/**
 * DEMO1
 */
public class DEMO1 {

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    HOCSINH hs1 = new HOCSINH("n017","Nhat Khang",5.8);
    System.out.printf("Xep loai: %s\n",hs1.rank());
    System.out.print("Nhap ten moi cho hoc sinh : ");
    String nName = scan.nextLine();
    hs1.setHoTen(nName);
    HOCSINH hs2 = new HOCSINH();
    if (hs1.getDTB() > hs2.getDTB()) System.out.printf("%s co DTB lon hon\n",hs1.getHoTen());
    else if (hs1.getDTB() < hs2.getDTB()) System.out.printf("%s co DTB lon hon\n",hs2.getHoTen());
    else System.out.print("DTB bang nhau\n");
    scan.close();
  }
}