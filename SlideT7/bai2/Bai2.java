package SlideT7.bai2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
// import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Bai2
 */

class SinhVien {

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
    return "SinhVien [dtb=" + dtb + ", ns=" + ns + ", ten=" + ten + "]";
  } 
}

public class Bai2 {

  public static String path = "D:\\BasicJava\\SlideT7\\bai2\\Sinhvien.txt";

  public static void GhiFile() throws FileNotFoundException, IOException{
    SinhVien sv = new SinhVien("Minh Duc", "30-10-1999", 9.0);
    FileOutputStream fos = new FileOutputStream(path);
    ObjectOutputStream oos = new ObjectOutputStream(fos);
    oos.writeObject(sv);
    oos.close();
    fos.close();
  }

  public static void DocFile() throws FileNotFoundException, IOException, ClassNotFoundException{
    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
    SinhVien sv = (SinhVien)ois.readObject();
    // System.out.println(sv.toString());
    ois.close();
  }
  public static void main(String[] args) throws IOException, ClassNotFoundException{
    try {
      GhiFile();
    // DocFile();
    } 
    catch (FileNotFoundException e) {
      File f = new File(path);
      f.createNewFile();
    }
    finally{
      System.out.println("finally");
    }
  }
}