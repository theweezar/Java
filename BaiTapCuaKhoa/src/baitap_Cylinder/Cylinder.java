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
public class Cylinder extends Circle {
    
    private double height;

    public Cylinder(double height, double radius, String color) {
        super(radius, color);
        this.height = height;
    }

    public Cylinder(double height, double radius) {
        super(radius);
        this.height = height;
    }

    public Cylinder(double radius) {
        super(radius);
    }

    public Cylinder() {
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getVolume() { return super.getArea()*height; }
    
    @Override
    public double getArea(){
        return 2 * Math.PI * getRadius() * getHeight() + 2 * super.getArea() ;
    }
    
}
