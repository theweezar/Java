/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package L4G;

import java.sql.Date;

/**
 *
 * @author hpmdu
 */
public class SinhVien {
    
    private String maSv;
    private String hoTen;
    private Date ngaysinh;
    private String diaChi;
    private String maLop;
    private String tenDN;
    private String matKhau;

    public SinhVien(String maSv, String hoTen, Date ngaysinh, String diaChi, String maLop, String tenDN, String matKhau) {
        this.maSv = maSv;
        this.hoTen = hoTen;
        this.ngaysinh = ngaysinh;
        this.diaChi = diaChi;
        this.maLop = maLop;
        this.tenDN = tenDN;
        this.matKhau = matKhau;
    }

    public SinhVien() {
    }

    public String getMaSv() {
        return maSv;
    }

    public void setMaSv(String maSv) {
        this.maSv = maSv;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
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
    
    
}
