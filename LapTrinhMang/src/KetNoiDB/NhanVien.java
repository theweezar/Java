/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KetNoiDB;

/**
 *
 * @author hpmdu
 */
public class NhanVien {
    
    private String maNv;
    private String tenNv;
    private String chucVu;

    public NhanVien(String maNv, String tenNv, String chucVu) {
        this.maNv = maNv;
        this.tenNv = tenNv;
        this.chucVu = chucVu;
    }

    public String getMaNv() {
        return maNv;
    }

    public void setMaNv(String maNv) {
        this.maNv = maNv;
    }

    public String getTenNv() {
        return tenNv;
    }

    public void setTenNv(String tenNv) {
        this.tenNv = tenNv;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }
    
    
}
