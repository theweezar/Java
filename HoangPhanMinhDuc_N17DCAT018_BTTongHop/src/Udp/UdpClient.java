/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Udp;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author hpmdu
 */
public class UdpClient {
    
    private DatagramSocket client = null;
    private DataInputStream in = null;
    private Scanner scan = null;
    private InetAddress address = null;
    int port = 1235;

    public UdpClient()throws IOException{
        client = new DatagramSocket();
        in = new DataInputStream(System.in);
        // Ở đây dùng scanner thay vì DataInputStream cũng được, vì nếu dùng Scanner để nextLine() thì chuỗi đó
        // Vẫn có thể dùng hàm getBytes() để chuyển qua server
        scan = new Scanner(System.in);
        address = InetAddress.getByName("localhost");
    }
    
    public void sendPacket(byte[] buf) throws UnknownHostException, IOException{
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
        client.send(packet);
    }
    
    public String receiveData() throws IOException {
        byte[] buf = new byte[256];
        DatagramPacket dPacket = new DatagramPacket(buf, buf.length);
        client.receive(dPacket);
        String strRec = new String(dPacket.getData(), 0, buf.length).trim();
        return strRec;
    }
    
    public static boolean isValidPassword(String password) 
    { 
  
        String regex = "^(?=.*[0-9])"
                       + "(?=.*[a-z])(?=.*[A-Z])"
                       + "(?=.*[@#$%^&+=])"
                       + "(?=\\S+$).{8,20}$"; 

        Pattern p = Pattern.compile(regex); 

        if (password == null) { 
            return false; 
        } 

        Matcher m = p.matcher(password); 

        return m.matches(); 
    }
    
    public void formLogin() throws IOException {
        scan.nextLine();
        String userName, passWord;
        System.out.println("Đăng nhập");
        System.out.print("Username: ");
        userName = scan.nextLine();
        System.out.print("Password: ");
        passWord = scan.nextLine();
        
        sendPacket(userName.trim().getBytes());
        sendPacket(passWord.trim().getBytes());
    }
    
    public void formRegister() throws IOException {
        scan.nextLine();
        String userName, passWord;
        System.out.println("Đăng ký");
        while(true){
            System.out.print("Username: ");
            userName = scan.nextLine();
            if (userName.trim().isEmpty()) System.out.println("Không được để trống");
            else break;
        }
        while(true){
            System.out.print("Password: ");
            passWord = scan.nextLine();
            if (passWord.trim().isEmpty()){
                System.out.println("Không được để trống");
            }
            else if (!isValidPassword(passWord)){
                System.out.println("Password không hợp lệ");
            }
            else break;
        }
        
        sendPacket(userName.trim().getBytes());
        sendPacket(passWord.trim().getBytes());
    }
    
    public int menu() throws IOException {
        String[] items = {
            "Đăng nhập",
            "Tạo tài khoản"
        };
        int opt = 0;
        System.out.println("------------------------------------");
        System.out.println("| Hoàng Phan Minh Đức - N17DCAT018 |");
        System.out.println("|               Menu               |");
        System.out.println("------------------------------------");
        for(int i = 0; i < items.length; i++){
            System.out.printf("%d. %s\n", i + 1, items[i]);
        }
        System.out.print("Chọn: ");
        try{
            opt = scan.nextInt();
        }
        catch(InputMismatchException e){
            scan.next();
            return -1;
        }
        return opt;
    }
    
    public void inputFile() throws IOException {
        String fileName;
        while(true){
            System.out.print("Nhập đường link file: "); // D:\\BAITAPTONGMON.txt
            fileName = scan.nextLine();
            sendPacket(fileName.trim().getBytes());
            String rs = receiveData();
            if (rs.equals("0")) System.out.println("File không tồn tại");
            else{
                System.out.println(rs);
                String kq = receiveData();
                System.out.println("\n===========================");
                System.out.println(kq);
                System.out.println("===========================\n");
            }
        }
    }
    
    public void run() throws IOException{
        int opt = -1;
        while(opt != 0){
            opt = menu();
            // Vì opt vẫn là int nên ta dùng nó trong switch case
            switch(opt){
                case 1: // Đăng nhập
                    sendPacket(String.format("%d", opt).getBytes());
                    formLogin();
                    break;
                case 2: // Tạo tài khoản
                    sendPacket(String.format("%d", opt).getBytes());
                    formRegister();
                    System.out.println(receiveData());
                    break;
                case 0:
                    sendPacket(String.format("%d", opt).getBytes());
                    System.out.println("Thoát");
                    scan.close();
                    in.close();
                    client.close();
                    break;
                default:
                    System.out.println("Nhập sai lựa chọn. Vui lòng nhập lại.");
                    sendPacket("-1".getBytes());
                    break;
            }
            if (opt != 0) {
                String str = receiveData();
                if (str.equals("1")){
                    System.out.println("Đăng nhập thành công");
                    inputFile();
                }
                else System.out.println(str);
            }
        }
        
        
    }
    
    public static void main(String[] args) throws IOException{
        UdpClient client = new UdpClient();
        client.run();
    }
}
