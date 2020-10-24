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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author hpmdu
 */
public class ClientTracNghiem {
    
    private Socket client = null;
    private DataOutputStream out = null;
    private DataInputStream in = null;
    private Scanner scan = null;
    
    public ClientTracNghiem() throws IOException{
        this.client = new Socket("localhost", 1234);
        this.out = new DataOutputStream(this.client.getOutputStream());
        this.in = new DataInputStream(this.client.getInputStream());
        this.scan = new Scanner(System.in);
    }
    
    public void getQuestionsThenAnswer()throws IOException{
        int i = 0;
        while(i < 10){
            // Nhận câu hỏi từ server và in ra
            String q = in.readUTF();
            System.out.println(q);
            System.out.print("Câu trả lời: ");
            // Gửi câu trả lời tới server
            out.writeUTF(scan.nextLine());
            // Nếu trả lời đúng định dạng câu hỏi thì i sẽ tăng để nhảy qua câu tiếp theo
            if (in.readBoolean()) i++;
            else System.out.println("Vui lòng nhập đúng định dạng câu trả lời");
        }
        // Nhận điểm từ server
        System.out.println("\nĐiểm của bạn: " + in.readInt());
    }
    
    public void login() throws IOException{
        String username, password;
        boolean ok = false;
        while(!ok){
            // client nhập username và password
            System.out.print("Username: ");
            username = scan.nextLine();
            System.out.print("Password: ");
            password = scan.nextLine();
            // Gửi username và password tới server để xử lý
            out.writeUTF(username);
            out.writeUTF(password);
            // Nhận phản hồi hợp lệ từ server
            ok = in.readBoolean();
            if (!ok) System.out.println("Dang nhap that bai");
        }
        System.out.println("Dang nhap thanh cong");
    }
    
    public static void main(String[] args) throws IOException{
        ClientTracNghiem tn = new ClientTracNghiem();
        tn.login();
        tn.getQuestionsThenAnswer();
    }
}
