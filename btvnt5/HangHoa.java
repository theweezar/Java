package btvnt5;

/**
 * HangHoa
 */
public class HangHoa {
  final private String mahang;
  final private String tenhang;
  final private double giatien;
  HangHoa(String mahang,String tenhang,double giatien){
    this.mahang = mahang;
    this.tenhang = tenhang;
    this.giatien = giatien;
  }
  public String getMH(){
    return mahang;
  }
}