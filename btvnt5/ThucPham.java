package btvnt5;


import java.time.LocalDate;


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
  public void show(){
    System.out.printf("\nMa thuc pham : %s\n",getMH());
    System.out.printf("Ten thuc pham: %s\n",getTH());
    System.out.printf("Gia tien     : %d VND\n",getPrice() * 1000);
    System.out.printf("Ngay san xuat: %s\n",nsx);
    System.out.printf("Han su dung  : %s\n",hsd);
  }
  public static void main(String[] args) {
    // LocalDate d = LocalDate.now();
    ThucPham tp = new ThucPham("PZC", "PizzaCheese", 105,LocalDate.now().toString(),LocalDate.now().plusDays(7).toString());
    tp.show();
  }
}