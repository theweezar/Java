package dethi;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * SVCNTT
 */
public class SVATTT extends SinhVien{

  private double anm;
  private double bm;
  public DecimalFormat df = new DecimalFormat("0.00");

  SVATTT(){
    super();
    NhapDiem();
  }

  public double getAnm() {
    return anm;
  }

  public void setAnm(double anm) {
    this.anm = anm;
  }

  public double getBm() {
    return bm;
  }

  public void setBm(double bm) {
    this.bm = bm;
  }

  public String loai(){
    return "attt";
  }

  public void NhapDiem(){
    Scanner scan = new Scanner(System.in);
    System.out.print("Diem an ninh mang: ");
    setAnm(scan.nextDouble());
    System.out.print("Diem bao mat     : ");
    setBm(scan.nextDouble());
    super.setDtk((3 * getAnm() + 4 * getBm()) / 7);
  }

  @Override
  public void Xuat(){
    super.Xuat();
    System.out.printf("ANM: %s - BM: %s\n",df.format(getAnm()),df.format(getBm()));
  }
}