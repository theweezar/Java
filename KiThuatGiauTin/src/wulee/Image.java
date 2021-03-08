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
                array2D[i][j] = getPixel(j, i);
            }
        }
        
        return array2D;
    }
    
    public void print2DArrayPixel(Pixel[][] array2D){
        for(int i = 0; i < array2D.length; i++){
            // Duyệt x
            for(int j = 0; j < array2D[i].length; j++){
                System.out.printf("%d ",array2D[i][j].getBlue());
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        Image image = new Image();
        image.readImage("cat.jpeg");
        Pixel[][] block = image.get2DArrayPixel(0, 0, 8, 8);
        image.print2DArrayPixel(block);
        
    }
}
