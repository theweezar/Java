package mypackage.shape;

import java.lang.Math;

/**
 * Circle
 */
public class Circle {

  double r;
  public Circle(double rr){
    r = rr;
  }
  public double Circumterence(){
    return 2 * Math.PI * r;
  }
  public double Area(){
    return Math.PI * r * r;
  }
}