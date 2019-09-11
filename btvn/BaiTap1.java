/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btvn;

/**
 *
 * @author hocvien
 */
class BaiTap1 {
    BaiTap1(){
        System.out.println("Bai tap 1");
        int tong = 0;
        for(int i=1;i<100;i++){
            if (i%2==0) tong += i;
        }
        
        System.out.printf("Tong: %d\n",tong);
    }
}
