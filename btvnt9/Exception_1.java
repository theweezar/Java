package btvnt9;

import javax.swing.*;

/**
 * test
 */
public class Exception_1 {

  public static void main(String[] args) {
    int n;
    try{
      n = Integer.parseInt(JOptionPane.showInputDialog(null, "Nhap so nguyen",""));
    }
    catch(Exception e){
      System.out.println(e.toString());
      e.printStackTrace();
    }
    finally{
      System.out.println("Have fun");
    }
  }
}