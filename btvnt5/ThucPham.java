package btvnt5;


import java.time.LocalDate;
import java.util.Scanner;


/**
 * ThucPham
 */
public class ThucPham extends HangHoa{
  private String nsx;
  private String hsd;
  ThucPham(String mahang,String tenhang,int giatien,String nsx,String hsd){
    super(mahang, tenhang, giatien);
    this.nsx = nsx;
    this.hsd = hsd;
  }
  ThucPham(){
    super();
    Scanner scan = new Scanner(System.in);
    int n;
    this.nsx = LocalDate.now().toString();
    System.out.print("Thuc pham co the su dung trong: ");
    n = scan.nextInt();
    this.hsd = LocalDate.now().plusDays(n).toString();
  }
  public void show(){
    System.out.printf("\nMa hang hoa : %s\n",getMH());
    System.out.printf("Ten thuc pham: %s\n",getTH());
    System.out.printf("Gia tien     : %d VND\n",getPrice() * 1000);
    System.out.printf("Ngay san xuat: %s\n",nsx);
    System.out.printf("Han su dung  : %s\n",hsd);
  }
}