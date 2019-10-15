package dethi;

import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * SVCNTT
 */
public class SVCNTT extends SinhVien{

  private double hdt;
  private double csdl;
  public DecimalFormat df = new DecimalFormat("0.00");

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

  public String loai(){
    return "cntt";
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

  @Override
  public void Xuat(){
    super.Xuat();
    System.out.printf("HDH: %s - CSDL: %s\n",df.format(getHdt()),df.format(getCsdl()));
  }

}