package btvn1;
import java.util.Scanner;
import java.lang.Math;
/**
 * BTVN1
 */
public class BTVN1 {
  static Scanner scan = new Scanner(System.in);
  public static void main(String[] args) {
    baitap1();
    baitap2();
    baitap3();
    baitap4();
    baitap5();
    baitap6();
    baitap7();
    baitap8();
    baitap9();
    baitap10();
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
    System.out.print("dtb: ");
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
    System.out.print("\nNhap m dong: ");
    m = Math.abs(scan.nextInt());
    System.out.print("Nhap n cot : ");
    n = Math.abs(scan.nextInt());
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
  static void baitap5(){
    for(int i=2;i<=9;i++){
      for(int j=1;j<=10;j++){
        System.out.printf("%d x %d = %d\n",i,j,i*j);
      }
    }
  }
  static void baitap6(){
    for(int i=1;i<=10;i++){
      System.out.printf("1 : Binh phuong la %d, lap phuong la %d\n",(int)Math.pow((double)i, 2),(int)Math.pow((double)i, 3));
    }
  }
  static void baitap7(){
    int[] arr = new int[10];
    int min = 1; 
    int max = 50;
    int tmp;
    System.out.print("Mang luc dau: ");
    for(int i=0;i<arr.length;i++){
      arr[i] = (int)Math.floor(Math.random()*(max+1-min)+min);
      System.out.printf("%d ",arr[i]);
    }
    for(int i=0;i<arr.length;i++){
      for(int j=i+1;j<arr.length;j++){
        if (arr[i] > arr[j]){
          tmp = arr[i];
          arr[i] = arr[j];
          arr[j] = tmp;
        }
      }
    }
    System.out.print("\nMang luc sau: ");
    for(int i=0;i<arr.length;i++){
      System.out.printf("%d ",arr[i]);
    }
  }
  static void baitap8(){
    // ascii
    // Tao 1 mang ky tu random voi so luong n duoc nhap tu ban phim 
    int min = 32;
    int max = 126;
    int n;
    System.out.print("\nNhap so luong ky tu: ");
    n = Math.abs(scan.nextInt());
    char[] arr = new char[n];
    System.out.print("Mang chu: ");
    for(int i=0;i<arr.length;i++){
      arr[i] = (char)Math.floor(Math.random()*(max+1-min)+min);
      System.out.printf("%c ",arr[i]);
    }
    System.out.print("\nMang ma ascii: ");
    for(int i=0;i<arr.length;i++){
      System.out.printf("%d ",(int)arr[i]);
    }
    System.out.println();
  }
  static void baitap9(){
    long[] arr = new long[100];
    arr[0] = arr[1] = 1;
    for(int i=2;i<arr.length;i++){
      arr[i] = (long)(arr[i-1] + arr[i-2]);
    }
    for(int i=0;i<arr.length;i++){
      System.out.printf("%d ",arr[i]);
    }
  }
  static void baitap10(){
    int k;
    int m,n;
    int min = 0, max = 50;
    int tong_hang = 0;
    System.out.print("\nNhap m dong: ");
    m = Math.abs(scan.nextInt());
    System.out.print("Nhap n cot : ");
    n = Math.abs(scan.nextInt());
    int[][] matrix = new int[m][n];
    for(int y=0;y<matrix.length;y++){
      for(int x=0;x<matrix[y].length;x++){
        matrix[y][x] = (int)Math.floor(Math.random()*(max+1-min)+min);
        System.out.printf("%d   ",matrix[y][x]);
      }
      System.out.println();
    }
    System.out.print("\nTinh tong hang thu: ");
    k = Math.abs(scan.nextInt());
    if (k > 0 && k <= matrix.length){
      for(int i=0;i<matrix[k-1].length;i++){
        tong_hang+=matrix[k-1][i];
      }
      System.out.printf("Tong cac phan tu hang thu %d la %d\n",k,tong_hang);
    }
    else System.out.print("\nError");
  }
}