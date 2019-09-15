package btvnt3;

import java.text.DecimalFormat;

import java.util.Scanner;


/**
 * FRACTION
 * Numeration : Tu so
 * Denominator: Mau so
 * Decimal: So thap phan cua~ phan so do
 * inputFraction: Nhap phan so
 * showFraction: in ra phan so
 * calcDivisor: tim UCLN
 */
class FRACTION {
  private int Numerator;
  private int Denominator;
  private float Decimal;
  public Scanner scan = new Scanner(System.in);
  public DecimalFormat df = new DecimalFormat("0.00");
  FRACTION(){ 
    inputFraction();
  }
  FRACTION(int numerator,int denominator){
    if (denominator != 0){
      Numerator = numerator;
      Denominator = denominator;
      Decimal = (float)Numerator / (float)Denominator;
    }
    else System.out.print("Error");
  }
  public int getNumerator(){
    return Numerator;
  }
  public int getDenominator(){
    return Denominator;
  }
  public void inputFraction(){
    while (Denominator == 0){
      System.out.print("\nNumerator (Tu so): ");
      Numerator = scan.nextInt();
      System.out.print("Denominator (Mau so): ");
      Denominator = scan.nextInt();
      if (Denominator == 0) System.out.print("Error, please try again !\n");
      else Decimal = (float)Numerator / (float)Denominator;
    }
  }
  public void showFraction(){
    System.out.printf("Phan so : %d/%d | So thap phan : %s\n",Numerator,Denominator,df.format(Decimal));
  }
  public int calcDivisor(){
    int hNum,lNum,divisor = 1;
    hNum = Numerator >= Denominator ? Numerator : Denominator;
    lNum = (Numerator + Denominator) - hNum;
    for(int i = lNum; i > 0; i--){
      if (hNum % i == 0 && lNum % i == 0){
        divisor = i;
        break;
      }
    }
    return divisor;
  }
  public void reduceFraction(){
    int divisor = calcDivisor();
    Numerator /= divisor;
    Denominator /= divisor;
  }
  public FRACTION plus(FRACTION otherFraction){
    int newNumerator = 1, newDenominator = 1;
    if (Denominator == otherFraction.getDenominator()){
      newNumerator = Numerator + otherFraction.getNumerator();
      newDenominator = Denominator;
    }
    else{
      newNumerator = Numerator * otherFraction.getDenominator() + otherFraction.getNumerator() * Denominator;
      newDenominator = Denominator * otherFraction.getDenominator();
    }
    return new FRACTION(newNumerator,newDenominator);
  }
}