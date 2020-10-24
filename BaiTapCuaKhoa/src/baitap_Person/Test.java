/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baitap_Person;

/**
 *
 * @author hpmdu
 */
public class Test {
    public static void main(String[] args) {
        Student student = new Student("yolo", 2056, 1052, "Con chó Trưng Vương", "Ống cống");
        System.out.println(student.toString());
        Staff staff = new Staff("Trường", 0, "loz", "ko biết");
        System.out.println(staff.toString());
    }
}
