/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DeThiTracNghiem;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;

/**
 *
 * @author hpmdu
 */
public class Question {
    
    private String noiDung;
    private String dapAn;

    public Question(String noiDung, String dapAn) {
        this.noiDung = noiDung;
        this.dapAn = dapAn;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getDapAn() {
        return dapAn;
    }

    public void setDapAn(String dapAn) {
        this.dapAn = dapAn;
    }
    
    public static void main(String[] args) {
        String a = "a";
        System.out.println(a.matches("[a,b,c,d,A,B,C,D]"));
    }
    
}
