package btvnt5;

import java.text.DecimalFormat;
import java.time.LocalDate;


/**
 * ThucPham
 */
public class ThucPham extends HangHoa{
  private String nsx;
  private String hsd;
  ThucPham(String mahang,String tenhang,double giatien,String nsx,String hsd){
    super(mahang, tenhang, giatien);
    this.nsx = nsx;
    this.hsd = hsd;
  }
  // ThucPham(){
  //   Scanner scan = new Scanner(System.in);
  //   String mh,th;
  //   double gt;
  //   System.out.print("Nhap ma hang : ");
  //   mh = scan.nextLine();
  //   System.out.print("Nhap ten hang: ");
  //   th = scan.nextLine();
  //   System.out.print("Nhap gia tien: ");
  //   gt = scan.nextDouble();
  //   super(mh,th,gt);
  // }
  public void show(){
    DecimalFormat df = new DecimalFormat("0.0");
    System.out.printf("Ma thuc pham : %s\n",getMH());
    System.out.printf("Ten thuc pham: %s\n",getTH());
    System.out.printf("Gia tien     : %s VND\n",df.format(getPrice() * 1000));
    System.out.printf("Ngay san xuat: %s\n",nsx);
    System.out.printf("Han su dung  : %s\n",hsd);
  }
  public static void main(String[] args) {
    // LocalDate d = LocalDate.now();
    ThucPham tp = new ThucPham("PZC", "PizzaCheese", 105,LocalDate.now().toString(),LocalDate.now().plusDays(7).toString());
    tp.show();
  }
}