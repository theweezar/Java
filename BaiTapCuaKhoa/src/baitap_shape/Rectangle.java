/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baitap_shape;

import baitap_Rectangle.*;

/**
 *
 * @author hpmdu
 */
public class Rectangle extends Shape {
    
    private float length;
    private float width;

    public Rectangle(float length, float width, String color, boolean filled) {
        super(color, filled);
        this.length = length;
        this.width = width;
    }

    public Rectangle(String color, boolean filled) {
        super(color, filled);
    }
    

    public Rectangle(float length, float width) {
        this.length = length;
        this.width = width;
    }

    public Rectangle() {
    }
    
    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "Rectangle{" + "length=" + length + ", width=" + width + '}';
    }
    
    public float getArea(){
        return length * width;
    }
    
    public float getPerimeter(){
        return (length + width) * 2;
    }
    
}
