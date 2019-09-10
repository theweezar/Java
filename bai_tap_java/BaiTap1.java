package bai_tap_java;

/**
 * BaiTap1
 */
public class BaiTap1 {

  public static void main(String[] args) {
    int tong = 0;
    for(int i=1;i<100;i++){
      if (i % 2 == 0) tong += i;
    }
    System.out.printf("Tong = %d",tong);
  }
}