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


public class Bai2 {

  public static String path = "D:\\BasicJava\\SlideT7\\bai2\\Sinhvien.txt";

  public static void GhiFile() throws FileNotFoundException, IOException{
    SinhVien sv = new SinhVien("Minh Duc Dep Trai", "30-10-1999", 9.0);
    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
    oos.writeObject(sv);
    oos.close();
  }

  public static void DocFile() throws FileNotFoundException, IOException, ClassNotFoundException{
    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
    SinhVien sv = (SinhVien)ois.readObject();
    System.out.println(sv.toString());
    ois.close();
  }
  public static void main(String[] args) throws IOException, ClassNotFoundException{
    try {
      // GhiFile();
      DocFile();
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