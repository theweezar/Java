/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baitap_Cylinder;

/**
 *
 * @author hpmdu
 */
public class testCylinder {
    
    public static void main(String[] args) {
        Cylinder c1 = new Cylinder(2,10); System.out.println("Cylinder:" + " radius=" + c1.getRadius() + " height=" + c1.getHeight() + " area=" + c1.getArea() + " volume=" + c1.getVolume());
    }
}
