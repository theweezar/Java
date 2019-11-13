/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlks.nhanvien;

/**
 *
 * @author MinhTam
 */
public class NhanVien {
    private String username;
    private String password;
    private boolean active;
    private boolean admin;
    private boolean allowed;
    private CTNhanVien detail;

    public NhanVien(String username, String password, boolean active, boolean admin, boolean allowed, CTNhanVien detail) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.admin = admin;
        this.allowed = allowed;
        this.detail = detail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isAllowed() {
        return allowed;
    }

    public void setAllowed(boolean allowed) {
        this.allowed = allowed;
    }

    public CTNhanVien getDetail() {
        return detail;
    }

    public void setDetail(CTNhanVien detail) {
        this.detail = detail;
    }

    
}
