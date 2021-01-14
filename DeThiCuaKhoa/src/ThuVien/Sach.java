/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThuVien;

/**
 *
 * @author hpmdu
 */
public class Sach {
    
    private String maSach;
    private String tenSach;
    private String chuyenNganh;

    public Sach(String maSach, String tenSach, String chuyenNganh) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.chuyenNganh = chuyenNganh;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getChuyenNganh() {
        return chuyenNganh;
    }

    public void setChuyenNganh(String chuyenNganh) {
        this.chuyenNganh = chuyenNganh;
    }
    
    
}
