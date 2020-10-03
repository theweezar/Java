/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baitap;

import java.util.Scanner;

/**
 *
 * @author hpmdu
 */
public class MaTran {
    
    public static Scanner scan = new Scanner(System.in);
    
    public static int[][] nhapMaTran(int n){
        int[][] m = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                while(true){
                    try{
                        System.out.printf("Nhap phan tu m[%d][%d]: ", i, j);
                        m[i][j] = scan.nextInt();
                        if (m[i][j] < 0) throw new Exception();
                        else break;
                    }
                    catch(Exception e){
                        System.out.println("Nhap lai so nguyen duong");
                        scan.nextLine();
                    }
                }
            }
        }
        return m;
    }
    
    public static void bieuDienMaTran(int[][] m){
        System.out.println("Bieu dien ma tran: ");
        for(int i = 0; i < m.length; i++){
            for(int j = 0; j < m.length; j++){
                System.out.printf("%d\t", m[i][j]);
            }
            System.out.println();
        }
    }
    
    public static void timSoLonNhat(int[][] m){
        int max = m[0][0];
        String vt = "";
        for(int i = 0; i < m.length; i++){
            for(int j = 0; j < m.length; j++){
                if (m[i][j] > max) {
                    max = m[i][j];
                    vt = String.format("Vi tri lon nhat: %d, %d", i, j);
                }
            }
        }
        System.out.printf("So lon nhat cua ma tran: %d\n", max);
        System.out.println(vt);
    }
    
    public static boolean isSNT(int s){
        for(int i = 2; i <= (int)Math.sqrt(s); i++){
            if (s % i == 0) return false;
        }
        return true;
    }
    
    public static void timSoNguyenTo(int[][] m){
        System.out.println("\nTim so nguyen to _ So khong phai so nguyen to in ra 0");
        for(int i = 0; i < m.length; i++){
            for(int j = 0; j < m.length; j++){
                if (isSNT(m[i][j])) System.out.printf("%d\t", m[i][j]);
                else System.out.print("0\t");
            }
            System.out.println();
        }
    }
    
    public static int[] sapXepTangDanMang(int[] a){ // Bubble sort
        int tmp = 0;
        for(int i = 0; i < a.length; i++){
            for(int j = i; j < a.length; j++){
                if (a[j] < a[i]){
                    tmp = a[j];
                    a[j] = a[i];
                    a[i] = tmp;
                }
            }
        }
        return a;
    }
    
    public static void sapXepTangDanMaTran(int[][] m){
        System.out.println("\nSap xep tang dan theo hang");
        for(int i = 0; i < m.length; i++){
            m[i] = sapXepTangDanMang(m[i]);
        }
        bieuDienMaTran(m);
    }
    
    public static void main(String[] args) {
        boolean ok = true;
        while(true){
            if (!ok) System.out.println("Nhap lai so nguyen duong");
            try{
                int n;
                System.out.print("Nhap ma tran n: ");
                n = scan.nextInt();
                if (n >= 2){
                    int[][] m = nhapMaTran(n);
                    bieuDienMaTran(m);
                    timSoLonNhat(m);
                    timSoNguyenTo(m);
                    sapXepTangDanMaTran(m);
                    break;
                }
                else ok = false;
                
            }
            catch(Exception e){
                ok = false;
                scan.nextLine();
            }
        }
        
    }
}
