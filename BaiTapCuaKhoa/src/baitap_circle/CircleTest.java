/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baitap_circle;

/**
 *
 * @author hpmdu
 */
public class CircleTest {
    public static void main(String[] args) {
        Circle c = new Circle(1, "red");
        System.out.println(c.toString());
        System.out.println("Circle Area : " + c.getArea());
    }
}
