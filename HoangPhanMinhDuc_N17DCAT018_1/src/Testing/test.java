/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;

import java.util.Scanner;

/**
 *
 * @author hpmdu
 */
public class test {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        String s = "";
        do{
            System.out.print("Enter 'A' for option A, 'B' for option B: ");
            s = keyboard.next();
        }
        while(!s.toLowerCase().matches("[(a|b)+\\w,(a|b)]")); 
    }
}
