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
public class Employee {
    
    private int id;
    private String firstName;
    private String lastName;
    private int salary;

    public Employee(int id, String firstName, String lastName, int salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }
    
    public String getName(){
        return String.format("%s %s", firstName, lastName);
    }
    
    public int getAnnualSalary(){
        return salary * 12;
    }
    
    public void raiseSalary(int percent){ // 1% -> 100%
        this.salary = (int)(float)(this.salary * (1 + (float)percent / 100));
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", salary=" + salary + '}';
    }
    
    
}
