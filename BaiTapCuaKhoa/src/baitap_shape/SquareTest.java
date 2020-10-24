/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baitap_shape;

/**
 *
 * @author hpmdu
 */
public class SquareTest {
    public static void main(String[] args) {
        Square square = new Square(9, "red", true);
        square.setLength((float)square.getSide());
        square.setWidth((float)square.getSide());
        System.out.println(square.toString());
    }
}
