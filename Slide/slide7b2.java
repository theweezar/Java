package Slide;


// import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
// import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dai
 */
class Sinhvien1 implements Serializable{
    public String hoten;
    public String ngaysinh;
    public int dtb;
    public Sinhvien1(){}
    public Sinhvien1(String hoten, String ngaysinh, int dtb) {
        this.hoten = hoten;
        this.ngaysinh = ngaysinh;
        this.dtb = dtb;
    }
    
    public void input(){
        Scanner s = new Scanner(System.in);
        System.out.println("Nhap hoten / ngaysinh / dtb ");
        hoten = s.nextLine(); ngaysinh = s.nextLine();
        dtb = s.nextInt();
        s.nextLine();
    }
    public void output(){
        System.out.printf("\nThông tin sinh vien: %10s %6s %2d\n",hoten,ngaysinh,dtb);
    }
    public String toString(){
        return hoten +" "+ ngaysinh +" "+ dtb;
    }
}
public class slide7b2 {
    public static void writeFile(String path, Sinhvien1 obj) throws FileNotFoundException, IOException{
        FileOutputStream fos = new FileOutputStream( path + "Student.txt" , /* append = */true);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(obj);
//                oos.reset();
                oos.close();
                fos.close();
    }
    
    public static void readFile(String path) throws FileNotFoundException, IOException, ClassNotFoundException{
//        FileInputStream fis = new FileInputStream(path+ "Student.txt");
                
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path+ "Student.txt"));
                
                Sinhvien1 sow = (Sinhvien1)ois.readObject();sow.output();
                 sow = (Sinhvien1)ois.readObject();sow.output();
//                String ss = null;  
//                ss += sow.toString();
//                sow = (Sinhvien1)ois.readObject();sow.output();
//                ss += sow.toString();
//                
//                    ois.reset();
                ois.close();
//                fis.close();
//                System.out.pri1ntln("ss : "+ss);
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner s = new Scanner(System.in);
        int n = 0;
        String path = "D:\\BasicJava\\Slide";

        try{
                
            switch(s.nextInt()){
                
                case 1: 
//     Choice #1.  Show by FileInputStream and ObjectInputStream ( fis and ois )    
                   // Conruption by AC because when u open and close at the time, 
                    // The data will be reset so just consistency on just one at specific time
                readFile(path);
                
                break;

        //Choice #2.  Append FileOutputStream and ObjectOutputStream (fos and oos)
                case 2:    
                writeFile(path,new Sinhvien1("PDAi","30081999",4));
                break;
                
                case 0: 
                System.exit(0);
            }
            
        }catch(FileNotFoundException ex){

        /*Code xử lý thêm vào file*/
//        FileOutputStream fos = new FileOutputStream(path+"Student.txt",true );
        File f = new File(path + "Student.txt");
        f.createNewFile();
        
//        ObjectOutputStream oos = new ObjectOutputStream(fos);
        
//        oos.writeUTF("This/These is/are student(s) profile: ");
//        oos.writeObject(new Sinhvien1("PhanDAi","30081999",4));
//        oos.writeObject(new Sinhvien1("PhanDAi","30081999",4));
//        n = n+1;
//        oos.close(); 
//        fos.close();
        
        }
        finally{
            System.out.println("Ghi vào file .. ! Hoàn tất");
        }
        
        }
//    }
}
