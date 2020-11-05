/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bai_3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author hpmdu
 */
public class TcpServer {
    
    private ServerSocket server = null;
    private Socket client = null;
    private DataOutputStream out = null;
    private DataInputStream in = null;
    
    public TcpServer() throws IOException{
        this.server = new ServerSocket(1234);
        this.client = this.server.accept();
        System.out.println("Ket noi server thanh cong");
        this.out = new DataOutputStream(this.client.getOutputStream());
        this.in = new DataInputStream(this.client.getInputStream());
    }
    
    public void calPoint() throws IOException{
        String table = "MÃ SV\t\tHọ lót\t\t\tTên\t\tMã lớp\t\t\tDTB\t\t\tKết quả\t\t\n";
        try{
            String sql = "SELECT * FROM LTM_LOPHOC L, LTM_SINHVIEN SV, LTM_DIEM D WHERE L.MaLop = 'D15CQAT01-N' AND SV.IDSinhVien = D.IDSinhVien AND L.IDLop = SV.IDLop";
            Connection conn = new MssqlConnection().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(); 
            while(rs.next()){
                double d1, d2, d3, dtb;
                d1 = rs.getDouble("DiemBT1");
                d2 = rs.getDouble("DiemBT2");
                d3 = rs.getDouble("DiemBT3");
                dtb = (d1 + d2 + d3) / 3;
                table += String.format("%s\t\t%s\t\t%s\t\t%s\t\t%f\t\t%s\t\t\n", rs.getString("MaSV"), rs.getString("HoLot"),
                        rs.getString("Ten"), rs.getString("MaLop"), dtb, dtb >= 5.0 ? "Đ":"R");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        out.writeUTF(table);
    }
    
    public void validate() throws IOException{
        boolean ok = false;
        while(!ok){
            String username = in.readUTF();
            String password = in.readUTF();
            Connection conn = new MssqlConnection().getConnection();
            try{
                String sql = "SELECT * FROM LTM_TAIKHOAN WHERE UserName = ? AND PassWord = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    ok = true;
                }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
            out.writeBoolean(ok);
        }
    }
    
    public void run() throws IOException {
        validate();
        calPoint();
    }
    
    public static void main(String[] args) throws IOException {
        TcpServer server = new TcpServer();
        server.run();
    }
}
