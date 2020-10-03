/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baitap;

import java.util.Scanner;

/**
 *
 * @author hpmdu
 */
public class Fibonaci {

    /**
     * @param args the command line arguments
     */
    
    public static int fibonaci(int n){
        if (n == 0 || n == 1) return n;
        else{
            return fibonaci(n - 1) + fibonaci(n - 2);
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scan = new Scanner(System.in);
        boolean ok = true;
        while(true){
            if (!ok) System.out.println("Nhap lai so nguyen duong");
            try{
                int n;
                System.out.print("Nhap n: ");
                n = scan.nextInt();
                if (n > 0){
                    System.out.println("So fibonaci: " + fibonaci(n));
                    break;
                }
                else ok = false;
                
            }
            catch(Exception e){
                ok = false;
                scan.nextLine();
            }
        }
    }
    
}
