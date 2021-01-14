/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThuVien;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 *
 * @author hpmdu
 */
public class ThuVien {
    
    private Sach[] thuvien;
    private int soLuong;
    
    public ThuVien(){
        thuvien = new Sach[1000];
        soLuong = 0;
    }
    
    public Sach laySach(int vitri){
        return thuvien[vitri];
    }
    
    public void themSach(Sach sach){
        thuvien[soLuong++] = sach;
    }
    
    public void suaThongTinSach(Sach sach, int vitri){
        thuvien[vitri] = sach;
    }
    
    public void xoaSach(int vitri){
        for(int i = vitri + 1; i < soLuong; i++){
            thuvien[i - 1] = thuvien[i];
        }
        soLuong--;
    }
    
    public void sapXepTheoMaSachTangDan(){
        for(int i = 0; i < soLuong; i++){
            for(int j = 0; j < soLuong; j++){
                if (thuvien[i].getMaSach().compareTo(thuvien[j].getMaSach()) > 0){
                    Sach sach = thuvien[i];
                    thuvien[i] = thuvien[j];
                    thuvien[j] = sach;
                }
            }
        }
    }
    
    public Sach timKiemTheoMaSach(String maSach){
        for(int i = 0; i < soLuong; i++){
            if (thuvien[i].getMaSach().equalsIgnoreCase(maSach)){
                return thuvien[i];
            }
        }
        return null;
    }
    
    public static void main(String[] args) {
        
    }
}
