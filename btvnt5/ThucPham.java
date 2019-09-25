package btvnt5;

import java.time.LocalDate;

/**
 * ThucPham
 */
public class ThucPham extends HangHoa{
  
  ThucPham(String mahang,String tenhang,double giatien){
    super(mahang, tenhang, giatien);
  }
  public static void main(String[] args) {
    LocalDate d = LocalDate.now();
    ThucPham tp = new ThucPham("PZC", "PizzaCheese", 105);
    System.out.println(tp.getMH());
    System.out.println(d.toString());
  }
}