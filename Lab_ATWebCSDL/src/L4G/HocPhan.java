/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package L4G;

/**
 *
 * @author hpmdu
 */
public class HocPhan {
    
    private String maHP;
    private String tenHP;
    private String stc;

    public HocPhan(String maHP, String tenHP, String stc) {
        this.maHP = maHP;
        this.tenHP = tenHP;
        this.stc = stc;
    }

    public String getMaHP() {
        return maHP;
    }

    public void setMaHP(String maHP) {
        this.maHP = maHP;
    }

    public String getTenHP() {
        return tenHP;
    }

    public void setTenHP(String tenHP) {
        this.tenHP = tenHP;
    }

    public String getStc() {
        return stc;
    }

    public void setStc(String stc) {
        this.stc = stc;
    }
    
    
    
    public static void main(String[] args) {
        
    }
}
