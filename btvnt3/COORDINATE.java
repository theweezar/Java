package btvnt3;

import java.text.DecimalFormat;

/**
 * COORDINATE
 */
class COORDINATE {

  private float x;
  private float y;
  public DecimalFormat df = new DecimalFormat("0.00");
  COORDINATE(){
    x = 0;
    y = 0;
  }
  COORDINATE(float x,float y){
    this.x = x;
    this.y = y;
  }
  public void showCOORD(){
    System.out.printf("X: %s, Y: %s",df.format(x),df.format(y));
  }
}