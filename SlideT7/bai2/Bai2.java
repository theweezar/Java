package SlideT7.bai2;

import java.io.FileInputStream;
// import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Bai2
 */
public class Bai2 {

  public static void GhiFile(){
    try{
      SinhVien sv = new SinhVien("Minh Duc", "30-10-1999", 9.0);
      ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("SlideT7\\bai2\\Sinhvien.txt"));
      oos.writeObject(sv);
      oos.close();
    }
    catch(IOException e){

    }
  }

  public static void DocFile(){
    try{
      ObjectInputStream ois = new ObjectInputStream(new FileInputStream("SlideT7\\bai2\\Sinhvien.txt"));
      SinhVien sv = (SinhVien)ois.readObject();
      System.out.println(sv.toString());
      ois.close();
    }
    catch(IOException e){
      System.out.println("IOException");
    }
    catch(ClassNotFoundException e){
      System.out.println("ClassNotFoundException");
    }
  }
  public static void main(String[] args) {
    // GhiFile();
    DocFile();
  }
}