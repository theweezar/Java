package btvnt3;

/**
 * BTVNT3
 */
public class BTVNT3 {

  public static void main(String[] args) {
    FRACTION ft1 = new FRACTION(3,4);
    // ft1.showFraction();
    // ft1.reduceFraction();
    // ft1.showFraction();
    FRACTION ft2 = new FRACTION(2,5);
    FRACTION ft3 = ft1.plus(ft2);
    ft3.showFraction();
  }
}