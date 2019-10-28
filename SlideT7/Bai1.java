package SlideT7;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Bai1
 */
public class Bai1 {

  public static void main(String[] args) throws IOException{
    String s = "Hi D17CQAT01-N";
    System.out.printf("Kich thuoc cua s : %d\n",s.length());
    System.out.printf("%s\n",s.toUpperCase());
    System.out.printf("Nien khoa: 20%s\n",s.replaceAll("[a-z,A-Z,-]","").trim().substring(0,2));
    System.out.printf("%s Java\n",s);

    // BufferedWriter bw = new BufferedWriter(new FileWriter("SlideT7\\Text.txt"));
    FileWriter fw = new FileWriter(new File("SlideT7\\Text.txt"));
    fw.write(s);
    fw.close();

  }
}