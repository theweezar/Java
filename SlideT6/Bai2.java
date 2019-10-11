package SlideT6;

/**
 * Bai2
 */
public class Bai2 {

  public static void main(String[] args) {
    int[] arr = new int[5];
    try {
      System.out.printf("Phan tu thu 6: %d\n",arr[5]);
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Arr ko co phan tu thu 6");
    }
  }
}