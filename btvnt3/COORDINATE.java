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