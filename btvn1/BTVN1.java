package btvn1;
import java.util.Scanner;
import java.lang.Math;
/**
 * BTVN1
 */
public class BTVN1 {
  static Scanner scan = new Scanner(System.in);
  public static void main(String[] args) {
    // baitap1();
    // baitap2();
    // baitap3();
    baitap4();
  }
  static void baitap1(){
    System.out.println("Hello! I’m Hoang Phan Minh Duc.");
    System.out.println("I’m 20 years old.");
    System.out.println("This is my first java program.");
  }
  static void baitap2(){
    String mssv, hoten;
    int tuoi,namsinh;
    float dtb;
    System.out.print("MSSV: ");
    mssv = scan.nextLine();
    System.out.print("Ho va ten: ");
    hoten = scan.nextLine();
    System.out.print("Tuoi: ");
    tuoi = scan.nextInt();
    System.out.print("Nam sinh: ");
    namsinh = scan.nextInt();
    System.out.print("dtb");
    dtb = scan.nextFloat();
    System.out.printf("%s %s %d %d %f\n",mssv,hoten,tuoi,namsinh,dtb);
  }
  static void baitap3(){
    int[] arr = new int[5];
    for(int i=0;i<arr.length;i++){
      System.out.printf("Nhap phan tu thu %d: ",i+1);
      arr[i] = scan.nextInt();
    }
    int max = arr[0];
    for(int i=1;i<arr.length;i++){
      if (max < arr[i]) max = arr[i];
    }
    System.out.printf("So lon nhat trong mang la: %d\n",max);
  }
  static void baitap4(){
    int m,n;
    int min = 0, max = 50;
    System.out.print("Nhap m dong: ");
    m = scan.nextInt();
    System.out.print("Nhap n cot : ");
    n = scan.nextInt();
    int[][] matrix = new int[m][n];
    int tong = 0;
    for(int y=0;y<matrix.length;y++){
      for(int x=0;x<matrix[y].length;x++){
        matrix[y][x] = (int)Math.floor(Math.random()*(max+1-min)+min);
        tong += matrix[y][x];
        System.out.printf("%d   ",matrix[y][x]);
      }
      System.out.println();
    }
    System.out.printf("Tong gia tri cac phan tu trong ma tran: %d\n",tong);
  }
}