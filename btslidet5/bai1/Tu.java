package btslidet5.bai1;

/**
 * Tu
 */
public class Tu {

  private String tu;
  private String nghia;

  Tu(){
    
  }

  Tu(String tu, String nghia) {
    this.tu = tu;
    this.nghia = nghia;
  }

  public String getTu() {
    return tu;
  }

  public void setTu(String tu) {
    this.tu = tu;
  }

  public String getNghia() {
    return nghia;
  }

  public void setNghia(String nghia) {
    this.nghia = nghia;
  }
  public void Xuat(){
    System.out.printf("%s : %s\n",tu,nghia);
  }
}