/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package L4G;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author hpmdu
 */
public class NhanVien {
    private String maNV;
    private String hoTen;
    private String eMail;
    private String luong;
    private String tenDN;
    private String matKhau;

    public NhanVien(String maNV, String hoTen, String eMail, String luong, String tenDN, String matKhau) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.eMail = eMail;
        this.luong = luong;
        this.tenDN = tenDN;
        this.matKhau = matKhau;
    }
    
    public NhanVien(){
        this.maNV = this.hoTen = this.eMail = this.luong = this.tenDN = this.matKhau = null;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getLuong() {
        return luong;
    }

    public void setLuong(String luong) {
        this.luong = luong;
    }

    public String getTenDN() {
        return tenDN;
    }

    public void setTenDN(String tenDN) {
        this.tenDN = tenDN;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
    
    public static void main(String[] args) {
        Hash hash = new Hash();
        System.out.println(hash.getSHA1("abcd12"));
    }
}
