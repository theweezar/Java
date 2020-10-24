/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DeThiTracNghiem;

import KetNoiDB.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author hpmdu
 */
public class ServerTracNghiem {
    
    private ServerSocket server = null;
    private Socket client = null;
    private DataOutputStream out = null;
    private DataInputStream in = null;
    private List<Question> boDe = null;
    private BangDiem bd = null;
    
    public ServerTracNghiem() throws IOException{
        this.server = new ServerSocket(1234);
        this.client = this.server.accept();
        System.out.println("Ket noi server thanh cong");
        this.out = new DataOutputStream(this.client.getOutputStream());
        this.in = new DataInputStream(this.client.getInputStream());
        this.boDe = new ArrayList<>();
    }

    public DataOutputStream getOut() {
        return out;
    }

    public DataInputStream getIn() {
        return in;
    }
    
    public void getQuestions() throws IOException{
        Connection conn = new MssqlConnection().getConnection();
        try{
            // SELECT RANDOM phần câu hỏi trong bộ đề database
            String sql = "SELECT TOP 10 * FROM BODE ORDER BY NEWID()";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            int i = 1;
            while(rs.next()){
                boDe.add(new Question(String.format("\nCâu %d: %s\nA. %s\nB. %s\nC. %s\nD. %s\n",
                        i++, 
                        rs.getString("NOIDUNG"),
                        rs.getString("A"),
                        rs.getString("B"),
                        rs.getString("C"),
                        rs.getString("D")),rs.getString("DAP_AN")));
            }   
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public boolean isAnswer(String ans){
        return ans.matches("[a,b,c,d,A,B,C,D]");
    }
    
    public void sendQuestionsAndProcess() throws IOException{
        int i = 0;
        int diem = 0;
        while(i < 10){
            // Truyền câu hỏi qua client
            Question q = boDe.get(i);
            out.writeUTF(q.getNoiDung());
            // Đọc câu trả lời
            String ans = in.readUTF();
            // Nếu trả lời đúng định dạng thì ta sẽ tiếp tục xét xem có đúng đáp án ko
            if (isAnswer(ans)){
                // Trả lời đúng định dạng thì việc trả lời đúng hay sai đáp án thì i vẫn tăng để nhảy sang câu tiếp theo
                i++;
                // Đúng đáp án thì tăng điểm
                if (ans.equalsIgnoreCase(q.getDapAn())){
                    diem++;
                }
                out.writeBoolean(true);
            }
            // Nếu ko đúng định dạng thì sẽ gửi cho client boolean false để in lại câu hỏi
            else out.writeBoolean(false);
        }
        // Set điểm cho biến BangDiem bd
        bd.setDiem(diem);
        // Gửi điểm đến cho client
        out.writeInt(diem);
    }
    
    public void saveResult(){
        Connection conn = new MssqlConnection().getConnection();
        try{
            String sql = "INSERT INTO BANGDIEM VALUES(?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, bd.getMsSv());
            ps.setInt(2, bd.getLan());
            ps.setTimestamp(3, new Timestamp(new java.util.Date().getTime()));
            ps.setInt(4, bd.getDiem());
            ps.setString(5, bd.getBaiThi());
            ps.execute();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void getResutlTable(){
        Connection conn = new MssqlConnection().getConnection();
        try{
            String sql = "SELECT * FROM BANGDIEM WHERE MASV = ? AND BAITHI = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, bd.getMsSv());
            ps.setString(2, bd.getBaiThi());
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                bd.setLan(rs.getInt("LAN") + 1);
            }
            else bd.setLan(1);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void validate() throws IOException{
        boolean ok = false;
        while(!ok){
            String username = in.readUTF();
            String password = in.readUTF();
            Connection conn = new MssqlConnection().getConnection();
            try{
                String sql = "SELECT * FROM SINHVIEN WHERE UserName = ? AND PassWord = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    ok = true;
                    bd = new BangDiem(rs.getString("MASV"), 1,
                            String.format("23/10/2020")
                            , 0, "Bai thi da mon");
                }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
            out.writeBoolean(ok);
        }
    }
    
    public static void main(String[] args) throws IOException{
        ServerTracNghiem s = new ServerTracNghiem();
        s.validate();
        s.getQuestions();
        s.sendQuestionsAndProcess();
        s.saveResult();
    }
}
