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
public class MyCircle {
    
    private MyPoint center;
    private int radius;

    public MyCircle(MyPoint center, int radius) {
        this.center = center;
        this.radius = radius;
    }
    
    public MyCircle(int x, int y, int radius){
        this.center = new MyPoint(x, y);
        this.radius = radius;
    }

    public MyCircle() {
    }
    
    public MyPoint getCenter() {
        return center;
    }

    public void setCenter(MyPoint center) {
        this.center = center;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
    
    public void setCenterX(int x){
        this.center.setX(x);
    }
    
    public void setCenterY(int y){
        this.center.setY(y);
    }
    
    public int getCenterX(){
        return this.center.getX();
    }
    
    public int getCenterY(){
        return this.center.getY();
    }
    
    public int[] getCenterXY(){
        return this.center.getXY();
    }
    
    public void setCenterXY(int x, int y){
        this.center.setXY(x, y);
    }

    @Override
    public String toString() {
        return String.format("MyCirle[radius = %d, center = (%s)]", this.radius, this.center.toString());
    }
    
    public double getArea(){
        return (double)(this.radius * this.radius * Math.PI);
    }
    
    public double getCircumference(){
        return (double)(this.radius * 2 * Math.PI);
    }
    
    public double distance(MyCircle another){
        return this.center.distance(another.getCenter());
    }
}
