package btvnt3;

/**
 * HOCSINH
 */
class HOCSINH {
  private String ms;
  private String hoten;
  private double dtb;
  HOCSINH(){
    input();
  }
  HOCSINH(String ms,String hoten,double dtb){
    this.ms = ms;
    this.hoten = hoten;
    this.dtb = dtb;
  }
  public void setMS(String ms){
    this.ms = ms;
  }
  public void setHoTen(String hoten){
    this.hoten = hoten;
  }
  public void setDTB(double dtb){
    this.dtb = dtb;
  }
  public String getMS(){
    return ms;
  }
  public String getHoTen(){
    return hoten;
  }
  public double getDTB(){
    return dtb;
  }
  public void input(){

  }
  public void output(){
    System.out.printf("Ma so: %s\n",ms);
    System.out.printf("Ho ten: %s\n",hoten);
    System.out.printf("DTB: %f\n",dtb);
  }
  public String rank(){
    String rank = "";
    if (dtb < 5) rank = "Duoi TB";
    else if (dtb == 5) rank = "TB";
    else if (dtb > 5 && dtb < 8) rank = "Kha";
    else rank = "Gioi";
    return rank;
  }
}