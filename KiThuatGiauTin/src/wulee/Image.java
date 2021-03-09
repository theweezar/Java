/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wulee;

import java.io.File; 
import java.io.IOException; 
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 *
 * @author hpmdu
 */
public class Image {
    
    private BufferedImage source = null;
    private int height = 1024;
    private int width = 1024;
    public static int CHANNEL_RED = 1;
    public static int CHANNEL_GREEN = 2;
    public static int CHANNEL_BLUE = 3;
    public static int CHANNEL_ALPHAL = 4;
    
    
    public int getHeight(){
        return source.getHeight();
    }
    
    public int getWidth(){
        return source.getWidth();
    }
    
    public void readImage(String path){
        try{ 
            File input_file = new File(path);

            source = new BufferedImage(width, height, 
                                    BufferedImage.TYPE_INT_ARGB); 
  
            source = ImageIO.read(input_file); 
  
            System.out.println("Reading complete.");
        } 
        catch(IOException e) { 
            System.out.println("Error: "+e); 
        }
    }
    
    public void writeImage(String path){
        // WRITE IMAGE 
        try
        { 
            // Output file path 
            File output_file = new File(path); 
  
            // Writing to file taking type and path as 
            ImageIO.write(source, "png", output_file); 
  
            System.out.println("Writing complete."); 
        } 
        catch(IOException e) { 
            System.out.println("Error: "+e); 
        } 
    }
    
    public Pixel getPixel(int x, int y){
        int p = source.getRGB(x, y); 
  
        // get alpha 
        int a = (p>>24) & 0xff; 
  
        // get red 
        int r = (p>>16) & 0xff; 
  
        // get green 
        int g = (p>>8) & 0xff; 
  
        // get blue 
        int b = p & 0xff; 
        return new Pixel(a, r, g, b);
    }
    
    public void setPixel(int x, int y, Pixel p){
        int rgb = (p.getAlphal()<<24) | (p.getRed()<<16) | (p.getGreen()<<8) | p.getBlue(); 
        source.setRGB(x, y, rgb);
    }
    
    public Pixel[][] get2DArrayPixel(int xStart, int yStart, int width, int height){
        if (xStart < 0 || xStart > source.getWidth()){
            return null;
        }
        if (yStart < 0 || yStart > source.getHeight()){
            return null;
        }
        if (width < 0 || height < 0){
            return null;
        }
        if (xStart + width > source.getWidth()){
            return null;
        }
        if (yStart + height > source.getHeight()){
            return null;
        }
    
        Pixel[][] array2D = new Pixel[height][width];
        // Duyệt y
        for(int i = 0; i < height; i++){
            // Duyệt x
            for(int j = 0; j < width; j++){
                array2D[i][j] = getPixel(xStart + j, yStart + i);
            }
        }
        return array2D;
    }
    
    public void set2DArrayPixel(int xStart, int yStart, int width, int height, Pixel[][] array2D){
        if (xStart < 0 || xStart > source.getWidth()){
            return;
        }
        if (yStart < 0 || yStart > source.getHeight()){
            return;
        }
        if (width < 0 || height < 0){
            return;
        }
        if (xStart + width > source.getWidth()){
            return;
        }
        if (yStart + height > source.getHeight()){
            return;
        }
        // Hàm này còn chưa xong nhớ chỉnh sửa lại
        
        // Duyệt y
        for(int i = 0; i < height; i++){
            // Duyệt x
            for(int j = 0; j < width; j++){
                array2D[i][j] = getPixel(xStart + j, yStart + i);
            }
        }
        
    }
    
    public static int[][] getChannel(int channel, Pixel[][] block){
        int[][] channel_array = new int[block.length][block[0].length];
        if (channel == Image.CHANNEL_RED){
            for(int i = 0; i < block.length; i++){
                for(int j = 0; j < block[i].length; j++){
                    channel_array[i][j] = block[i][j].getRed();
                }
            }
        }
        if (channel == Image.CHANNEL_GREEN){
            for(int i = 0; i < block.length; i++){
                for(int j = 0; j < block[i].length; j++){
                    channel_array[i][j] = block[i][j].getGreen();
                }
            }
        }
        if (channel == Image.CHANNEL_BLUE){
            for(int i = 0; i < block.length; i++){
                for(int j = 0; j < block[i].length; j++){
                    channel_array[i][j] = block[i][j].getBlue();
                }
            }
        }
        return channel_array;
    }
    
    public static void print2DArrayPixel(int channel, Pixel[][] array2D){
        if (channel == Image.CHANNEL_RED){
            for(int i = 0; i < array2D.length; i++){
                for(int j = 0; j < array2D[i].length; j++){
                    System.out.printf("%d ",array2D[i][j].getRed());
                }
                System.out.println();
            }
        }
        if (channel == Image.CHANNEL_GREEN){
            for(int i = 0; i < array2D.length; i++){
                for(int j = 0; j < array2D[i].length; j++){
                    System.out.printf("%d ",array2D[i][j].getGreen());
                }
                System.out.println();
            }
        }
        if (channel == Image.CHANNEL_BLUE){
            for(int i = 0; i < array2D.length; i++){
                for(int j = 0; j < array2D[i].length; j++){
                    System.out.printf("%d ",array2D[i][j].getBlue());
                }
                System.out.println();
            }
        }
    }
    
    public void merge(){
        
    }
    
    public static void main(String[] args) {
        Image image = new Image();
        image.readImage("cat.jpeg");
        image.merge();
        
    }
}
