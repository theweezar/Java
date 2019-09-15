package btvnt3;

import java.text.DecimalFormat;
import java.lang.Math;

/**
 * COORDINATE
 */
class COORDINATE {

  private double x;
  private double y;
  public DecimalFormat df = new DecimalFormat("0.00");
  COORDINATE(){
    x = 0;
    y = 0;
  }
  COORDINATE(double x,double y){
    this.x = x;
    this.y = y;
  }
  public double getX(){
    return x;
  }
  public double getY(){
    return y;
  }
  public double khoangCach(COORDINATE other){
    double xDis = x - other.getX();
    double yDis = y - other.getY();
    return Math.sqrt(Math.pow(xDis, 2) + Math.pow(yDis, 2));
  }
  public void inToaDo(){
    System.out.printf("X: %s, Y: %s\n",df.format(x),df.format(y));
  }
  public COORDINATE dx_truc_X(){
    return new COORDINATE(x,0 - y);
  }
  public COORDINATE dx_truc_Y(){
    return new COORDINATE(0 - x,y);
  }
}