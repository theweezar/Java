/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlks;
import qlks.nhanvien.*;
import java.sql.*;
import java.util.Vector;

/**
 *
 * @author MinhTam
 */
public class NhanVienModel {
    private Connection conn;
    
    public NhanVienModel(){
        this.conn = new MSSQLConnection().getConnection();
    }
    
    public void register(NhanVien nv,CTNhanVien ctnv){
        /*
        Trước khi dùng hàm register này thì ta phải kiểm lỗi ở bên ngoài trước, sau đó khởi tạo nhân viên mới
        với đầy đủ thông tin, thì ta mới truyền vào
        Trước mắt chỉ cần bảng NHANVIEN thôi, chưa cần đụng tới bảng chi tiết vì nó chỉ là thông tin 
        cá nhân của nhân viên
        */
        String query1 = "INSERT INTO NHANVIEN (username,pw,isActive,isAdmin,isAllowed) VALUES(?,?,?,?,?)";
//        String query2 = "INSERT INTO CTNHANVIEN (username,ten,phai,ngaysinh,diachi,sdt,email,maBP) VALUES(?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement pstt = this.conn.prepareStatement(query1);
            pstt.setString(1, nv.getUsername());
            pstt.setString(2, nv.getPassword());
            pstt.setBoolean(3, nv.isActive());
            pstt.setBoolean(4, nv.isAdmin());
            pstt.setBoolean(5, nv.isAllowed());
            pstt.execute();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public boolean validate(String username, String password){
        /*
        Sau khi executeQuery thì rs sẽ = 0. Nếu như tồn tại 1 thằng nhân viên thì sau khi rs.next(), rs.getRow() sẽ = 1
        Nếu ko tồn tại thì rs.getRow() mãi = 0
        */
        String query = "SELECT * FROM NHANVIEN WHERE username = ? AND pw = ?";
        try{
            PreparedStatement pstt = this.conn.prepareStatement(query);
            pstt.setString(1, username);
            pstt.setString(2, password);
            ResultSet rs = pstt.executeQuery();
            rs.next();
            if (rs.getRow() == 1) return true;
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }
    
    public void promote(String username) throws SQLException{
        /*
        Ủy quyền cho nhân viên có thể sử dụng thêm 1 vài chức năng
        */
        String query = "UPDATE NHANVIEN SET isAllowed = 1 WHERE username = ?";
        PreparedStatement pstt = this.conn.prepareStatement(query);
        pstt.setString(1, username);
        pstt.execute();
    }
    
    public void demote(String username) throws SQLException{
        /*
        Bỏ ủy quyền cho nhân viên
        */
        String query = "UPDATE NHANVIEN SET isAllowed = 0 WHERE username = ?";
        PreparedStatement pstt = this.conn.prepareStatement(query);
        pstt.setString(1, username);
        pstt.execute();
    }
    
    public void active(String username) throws SQLException{
        /*
        Cho nhân viên được đăng nhập vào app
        */
        String query = "UPDATE NHANVIEN SET isActive = 1 WHERE username = ?";
        PreparedStatement pstt = this.conn.prepareStatement(query);
        pstt.setString(1, username);
        pstt.execute();
    }
    
    public void deactive(String username) throws SQLException{
        /*
        Cho khứa nhân viên nghỉ việc - hoặc nghỉ phép
        */
        String query = "UPDATE NHANVIEN SET isActive = 0 WHERE username = ?";
        PreparedStatement pstt = this.conn.prepareStatement(query);
        pstt.setString(1, username);
        pstt.execute();
    }
    
    public void getAll() throws SQLException{
        /*
        Lấy tất cả nhân viên trong database ra rồi bỏ vào vector
        */
        Vector<NhanVien> dsnv = new Vector<>();
        String query = "SELECT * FROM NHANVIEN JOIN CTNHANVIEN on NHANVIEN.username = CTNHANVIEN.username ";
        Statement stt = this.conn.createStatement();
        ResultSet rs = stt.executeQuery(query);
        while(rs.next()){
            
        }
    }
}
