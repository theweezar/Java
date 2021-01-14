/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bai_copy_file_folder;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author hpmdu
 */
public class TcpClient {
    
    private Socket client = null;
    private DataOutputStream out = null;
    private DataInputStream in = null;
    private Scanner scan = null;
    
    public TcpClient() throws IOException{
        this.client = new Socket("localhost", 1235);
        this.out = new DataOutputStream(this.client.getOutputStream());
        this.in = new DataInputStream(this.client.getInputStream());
        scan = new Scanner(System.in);
    }
    
    public void upload() throws IOException{
        String path = "D:\\input.txt";
        // Đọc file từ client trước rồi sau đó chuyển data qua server
        // Đây là luồng đọc dữ liệu file từ máy người dùng
        BufferedInputStream buff_in = new BufferedInputStream(new FileInputStream(new File(path)));
        // Đây là luồng gửi dữ liệu byte từ máy người dùng lên server
        BufferedOutputStream buff_out = new BufferedOutputStream(this.client.getOutputStream());
        byte[] b = new byte[1024 * 8];
        // Bắt đầu quá trình upload
        System.out.println("Uploading.....");
        while(buff_in.read(b) != -1){
            buff_out.write(b);
            buff_out.flush();
        }
        System.out.println("File is uploaded");
        // Kết thúc quá trình upload
    }
    
    public void download() throws IOException {
        String path = "D:\\output.txt";
        // Luồng đọc dữ liệu byte từ file
        BufferedInputStream buff_in = new BufferedInputStream(this.client.getInputStream());
        // Luồng ghi byte ra file
        BufferedOutputStream buff_out = new BufferedOutputStream(new FileOutputStream(new File(path)));
        byte[] b = new byte[1024 * 8];
        while(buff_in.read(b) != -1){
            buff_out.write(b);
            buff_out.flush();
        }
    }
    
//    public void viet_file_byte() throws IOException {
//        String path = "D:\\output_1.txt";
//        String data = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
//        BufferedOutputStream buff_out = new BufferedOutputStream(new FileOutputStream(new File(path)));
//        byte[] b = data.getBytes();
//        buff_out.write(b);
//        buff_out.flush();
//    }
    
    public static void main(String[] args) throws IOException {
        TcpClient client = new TcpClient();
        client.upload();
        client.download();
//        client.viet_file_byte();
    }
}
