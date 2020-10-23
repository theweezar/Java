/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KetNoiDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author hpmdu
 */
public class QLSinhVien {
    
    public void add(NhanVien nv){
        Connection conn = new MssqlConnection().getConnection();
        try{
            String sql = "INSERT INTO QL_NHANVIEN VALUES(?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nv.getMaNv());
            ps.setString(2, nv.getTenNv());
            ps.setString(3, nv.getChucVu());
            ps.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void delete(NhanVien nv){
        Connection conn = new MssqlConnection().getConnection();
        try{
            String sql = "DELETE FROM QL_NHANVIEN WHERE MANV = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nv.getMaNv());
            ps.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void select(){
        Connection conn = new MssqlConnection().getConnection();
        try{
            String sql = "SELECT * FROM QL_NHANVIEN";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            System.out.println("Danh sach nhan vien: ");
            while(rs.next()){
                System.out.println(rs.getString("MANV") + ": " + rs.getString("TENNV"));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void update(NhanVien nv){
        
    }
    
    public static void main(String[] args) {
        QLSinhVien qlsv = new QLSinhVien();
//        qlsv.add();
//        qlsv.delete();
        qlsv.select();
    }
}
