/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DeThiTracNghiem;

import java.sql.Date;

/**
 *
 * @author hpmdu
 */
public class BangDiem {
    private String msSv;
    private int lan;
    private String ngayThi;
    private int diem;
    private String baiThi;

    public BangDiem(String msSv, int lan, String ngayThi, int diem, String baiThi) {
        this.msSv = msSv;
        this.lan = lan;
        this.ngayThi = ngayThi;
        this.diem = diem;
        this.baiThi = baiThi;
    }

    public String getMsSv() {
        return msSv;
    }

    public void setMsSv(String msSv) {
        this.msSv = msSv;
    }

    public int getLan() {
        return lan;
    }

    public void setLan(int lan) {
        this.lan = lan;
    }

    public String getNgayThi() {
        return ngayThi;
    }

    public void setNgayThi(String ngayThi) {
        this.ngayThi = ngayThi;
    }

    public int getDiem() {
        return diem;
    }

    public void setDiem(int diem) {
        this.diem = diem;
    }

    public String getBaiThi() {
        return baiThi;
    }

    public void setBaiThi(String baiThi) {
        this.baiThi = baiThi;
    }
    
    
}
