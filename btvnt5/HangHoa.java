package btvnt5;

/**
 * HangHoa
 */
public class HangHoa {
  final private String mahang;
  final private String tenhang;
  final private int giatien;
  HangHoa(String mahang,String tenhang,int giatien){
    this.mahang = mahang;
    this.tenhang = tenhang;
    this.giatien = giatien;
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