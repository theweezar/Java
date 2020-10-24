/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baitap_PointAndCircle;

/**
 *
 * @author hpmdu
 */
public class TestMyCircle {
    public static void main(String[] args) {
        MyCircle mc = new MyCircle(new MyPoint(2, 2), 5);
        System.out.println(mc.toString());
        System.out.println("Area: " + mc.getArea());
        System.out.println("Circumference: " + mc.getCircumference());
        System.out.println("Distance: " + mc.distance(new MyCircle(-1, 7, 3)));
    }
}
