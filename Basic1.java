import java.util.Scanner;

/**
 * Basic1
 */
public class Basic1 {
  static void TestingMethod(){ // This is how we create a function in java
    int i = 10;
    System.out.println(++i);
  }
  public static void main(String[] args) {
    // Init the scanner to scan input variables
    Scanner scan = new Scanner(System.in);
    // The basic is just like C/C++
    String name = "Duc";
    int age = 20;
    System.out.printf("Hello Mr.%s",name);
    System.out.printf("\nMy age: %d",age);
    // Object Math
    System.out.println(Math.random());
    TestingMethod();
    // Input variables
    System.out.print("Nhap a: ");
    int a = scan.nextInt(); // this function will auto create a new line when we finish the input
    System.out.print("Nhap b: ");
    int b = scan.nextInt();
    System.out.printf("Result: %d",a+b);
  }
}