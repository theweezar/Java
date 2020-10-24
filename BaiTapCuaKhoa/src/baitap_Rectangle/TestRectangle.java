/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baitap_Rectangle;

/**
 *
 * @author hpmdu
 */
public class TestRectangle {
    public static void main(String[] args) {
        Rectangle rect = new Rectangle(5.2f, 6.5f);
        System.out.println(rect.toString());
        System.out.println("Area: " + rect.getArea());
        System.out.println("Perimeter: " + rect.getPerimeter());
    }
}
