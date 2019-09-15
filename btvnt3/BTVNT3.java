package btvnt3;

/**
 * BTVNT3
 */
public class BTVNT3 {

  public static void main(String[] args) {
    // FRACTION ft1 = new FRACTION(6,8);
    // ft1.showFraction();
    // ft1.reduceFraction();
    // ft1.showFraction();
    // FRACTION ft2 = new FRACTION(2,5);
    // FRACTION ft3 = ft1.plus(ft2);
    // ft3.showFraction();
    COORDINATE coord1 = new COORDINATE(6,8.7);
    COORDINATE coord2 = new COORDINATE(50.5,8);
    System.out.printf("Khoang cach: %f",coord1.khoangCach(coord2));
  }
}