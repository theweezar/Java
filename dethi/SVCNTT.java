package dethi;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * SVCNTT
 */
public class SVCNTT extends SinhVien{

  private double hdt;
  private double csdl;

  SVCNTT(){
    super();
    NhapDiem();
  }

  public double getHdt() {
    return hdt;
  }

  public void setHdt(double hdt) {
    this.hdt = hdt;
  }

  public double getCsdl() {
    return csdl;
  }

  public void setCsdl(double csdl) {
    this.csdl = csdl;
  }

  public void NhapDiem(){
    Scanner scan = new Scanner(System.in);
    try {
      System.out.print("Diem huong doi tuong: ");
      setHdt(scan.nextDouble());
      System.out.print("Diem co so du lieu  : ");
      setCsdl(scan.nextDouble());
      super.setDtk((3 * getHdt() + 4 * getCsdl()) / 7);
    } catch (InputMismatchException e) {
      System.out.println("Vui long nhap ky tu so !!");
      NhapDiem();
    }
  }

  public String Xuat() {
    return "SVCNTT [csdl=" + csdl + ", hdt=" + hdt + "]";
  }

}