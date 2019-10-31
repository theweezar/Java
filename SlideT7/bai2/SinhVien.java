package SlideT7.bai2;

import java.io.Serializable;

/**
 * SinhVien
 */
public class SinhVien implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 1L;
  private String ten;
  private String ns;
  private double dtb;

  public SinhVien(String ten, String ns, double dtb) {
    this.ten = ten;
    this.ns = ns;
    this.dtb = dtb;
  }

  public String getTen() {
    return ten;
  }

  public void setTen(String ten) {
    this.ten = ten;
  }

  public String getNs() {
    return ns;
  }

  public void setNs(String ns) {
    this.ns = ns;
  }

  public double getDtb() {
    return dtb;
  }

  public void setDtb(double dtb) {
    this.dtb = dtb;
  }

  @Override
  public String toString() {
    return "SinhVien [dtb = " + dtb + ", ns = " + ns + ", ten = " + ten + "]";
  }

  
  
}