/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThuVien;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 *
 * @author hpmdu
 */
public class QuanLyThuVien {
    public void readBook() throws IOException{
        ObjectInputStream ois = null;
        
        try {
            ois = new ObjectInputStream(new FileInputStream("D:\\testout.txt"));
            Sach book = (Sach) ois.readObject();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            ois.close();
        }
    }
}
