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
class BaiTap6 {
    BaiTap6(){
        for(int i=2;i<10;i++){
            if (i % 2 != 0) continue;
            else System.out.printf("%d ",i);
        }
        System.out.println();
        int j = 2;
        while(j < 10){
            if (j % 2 == 0) System.out.printf("%d ",j);
            j++;
        }
        System.out.println();
    }
}
