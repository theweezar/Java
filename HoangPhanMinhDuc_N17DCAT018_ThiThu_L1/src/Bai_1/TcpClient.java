/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bai_1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author hpmdu
 */
public class TcpClient {
    
    private Socket client = null;
    private DataOutputStream out = null;
    private DataInputStream in = null;
    
    public TcpClient() throws IOException{
        this.client = new Socket("localhost", 1234);
        this.out = new DataOutputStream(this.client.getOutputStream());
        this.in = new DataInputStream(this.client.getInputStream());
    }
    
    public void run() throws IOException{
        int a = 0;
        Scanner scan = new Scanner(System.in);
        while(a != -1){
            System.out.print("Nhập số a: ");
            try{
                // Nhập số a và gửi số a đó lên server
                a = scan.nextInt();
                if (a >= -1){
                    out.writeInt(a);
                    // Nếu như a khác -1 thì server sẽ thực hiện tính toán và gửi về cho client
                    // client đọc kết quả vừa nhận được từ server
                    if (a != -1) System.out.printf("Tổng các chữ số của %d là %d\n\n", a, in.readInt());
                    else System.out.println("Kết thúc chương trình");
                }
                else System.out.println("Không được nhập số bé hơn -1");
            }
            catch(InputMismatchException e){
                System.out.println("Vui lòng nhập ký tự số\n");
                scan.next();
                e.printStackTrace();
            }
        }
        scan.close();
        out.close();
        in.close();
        client.close();
        
    }
    
    public static void main(String[] args) throws IOException {
        TcpClient client = new TcpClient();
        client.run();
    }
}
