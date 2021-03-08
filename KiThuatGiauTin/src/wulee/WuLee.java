/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wulee;

/**
 *
 * @author hpmdu
 */
public class WuLee {
    
    public static boolean[] get8BitBinaryArray(int decimal){
        boolean[] bitArrray = new boolean[8];
        String binString = String.format("%8s", Integer.toBinaryString(decimal)).replaceAll(" ", "0");
        System.out.println(binString);
        for(int i = 0; i < bitArrray.length; i++){
            bitArrray[i] = binString.charAt(i) == 1 ? true:false;
        }
        return bitArrray;
    }
    
    public static boolean[][] bitwiseAnd(boolean[][] bitArray_1, boolean[][] bitArray_2){
        boolean[][] and_array = new boolean[8][8];
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                and_array[i][j] = bitArray_1[i][j] & bitArray_2[i][j];
            }
        }
        return and_array;
    }
    
    public static int sum(boolean[][] bitArray){
        int sum = 0;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                
            }
        }
        return sum;
    }
    
    public Pixel[][] hideInBlock(Pixel[][] block){
        return block;
    }
    
    public void hide(){
        
    }
}
