package btvnt3;

import java.util.ArrayList;

/**
 * ARRAY
 */
public class ARRAY {
  private int[] arr;
  private int sl;
  ARRAY(int[] arr){
    this.sl = arr.length;
    this.arr = arr;
    show();
  }
  public void trungbinhle(){
    int tong = 0, soluong = 0;
    for(int i=0;i<sl;i++){
      if (arr[i] % 2 != 0){
        tong += arr[i];
        soluong += 1;
      }
    }
    System.out.printf("Trung binh cac so le: %f\n",(float)((float)tong/(float)soluong));
  }
  public void phantulonnhat(){
    int max = arr[0];
    for(int i=1;i<sl;i++){
      if (max < arr[i]) max = arr[i];
    }
    System.out.printf("Phan tu lon nhat: %d\n",max);
  }
  public void sapxeptangdan(){
    int tmp = 0;
    for(int i=0;i<sl;i++){
      for(int j=i;j<sl;j++){
        if (arr[i] > arr[j]){
          tmp = arr[i];
          arr[i] = arr[j];
          arr[j] = tmp;
        }
      }
    }
    show();
  }
  public void show(){
    for(int i=0;i<sl;i++){
      System.out.println(arr[i]);
    }
  }
}