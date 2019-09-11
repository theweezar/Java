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
class BaiTap2 {
    BaiTap2(){
        int n = 1, tong = 0,tong_1 = 0;
        while(n * 7 < 100){
            tong += n * 7;
            n++;
        }
        System.out.printf("Tong cac boi so cua 7: %d\n",tong);
    }
}
