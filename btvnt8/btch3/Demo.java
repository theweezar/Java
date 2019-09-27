package btvnt8.btch3;

import btvnt8.btch3.mypackage.Calculate;
import btvnt8.btch3.mypackage.shape.Circle;

/**
 * Demo
 */
public class Demo {

  public static void main(String[] args) {
    System.out.println(Calculate.Volume(2, 5, 5));
    Circle C = new Circle(5);
    System.out.println(C.Area());
  }
}