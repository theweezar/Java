/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baitap_Employee;

/**
 *
 * @author hpmdu
 */
public class testEmployee {
    public static void main(String[] args) {
        Employee e = new Employee(0, "To Anh", "Khoa", 1000);
        System.out.println(e.toString());
        System.out.println("Full name: " + e.getName());
        System.out.println("Annual Salary: " + e.getAnnualSalary());
        
        System.out.println("Lương cũ: " + e.getSalary());
        // Tăng lương thêm 50%
        e.raiseSalary(50);
        System.out.println("Lương mới: " + e.getSalary());
        
        
    }
}
