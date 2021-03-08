/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package De_CN03;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 *
 * @author hpmdu
 */
public class ThuatToan {
    
    public static void bai_1(int[] arr){
        // Tìm 2 số lớn nhất và nhì trong mảng
        int max_1 = arr[0];
        int max_2 = 0;
        for(int i = 1; i < arr.length; i++){
            if (arr[i] > max_1) max_1 = arr[i];
        }
        for(int i = 0; i < arr.length; i++){
            if (arr[i] != max_1){
                max_2 = arr[i];
                for(int j = 0; j < arr.length; j++){
                    if (arr[j] > max_2 && arr[j] != max_1){
                        max_2 = arr[j];
                    }
                }
                break;
            }
        }
        System.out.printf("Max 1: %d\nMax 2: %d\n", max_1, max_2);
        // Tìm các số bằng với số lớn nhất và nhì và vị trí
        String max_list_1 = "Max 1: " + max_1 + ". Vị trí: ";
        String max_list_2 = "Max 2: " + max_2 + ". Vị trí: ";
        for(int i = 0; i < arr.length; i++){
            if (arr[i] == max_1) max_list_1 += i + ",";
            if (arr[i] == max_2) max_list_2 += i + ",";
        }
        System.out.println(max_list_1);
        System.out.println(max_list_2);
        // Sắp xếp thứ tự giảm dần
        for(int i = 0; i < arr.length; i++){
            for(int j = i; j < arr.length; j++){
                if (arr[i] < arr[j]){
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
        // Tạo mảng mới và gán cho mảng đó mảng vừa rồi sắp xếp
        int[] new_arr = new int[arr.length + 1];
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + ",");
            new_arr[i] = arr[i];
        }
        System.out.println();
        // Chèn phần tử nhưng vẫn giữ tính giảm dần
        int x = 7;
        if (arr[0] <= x){
            for(int j = new_arr.length - 1; j > 0; j--){
                new_arr[j] = new_arr[j - 1];
            }
            new_arr[0] = x;
        }
        else for(int i = 1; i < arr.length; i++){
            if (arr[i - 1] >= x && arr[i] < x){
                for(int j = new_arr.length - 1; j > i; j--){
                    new_arr[j] = new_arr[j - 1];
                }
                new_arr[i] = x;
                break;
            }
            // Nếu như duyệt đến cuối mảng mà vẫn chưa hợp điều kiện trên thì có nghĩa x là phần tử bé nhất
            // Nên ta sẽ gán x vào vị trí cuối cùng trong mảng mới
            if (i == arr.length - 1 && x <= arr[i]) new_arr[i + 1] = x;
        }
        // Xuất mảng sau khi chèn
        for(int i = 0; i < new_arr.length; i++){
            System.out.print(new_arr[i] + ",");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        int[] arr = {7,7,4,8,2,6,3,9};
        ThuatToan.bai_1(arr);
        
    }
}
