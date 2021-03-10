/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wulee;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.CvType;
import org.opencv.core.Range;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
/**
 *
 * @author hpmdu
 */
public class WuLeeOpencv {
    
    static{
        // Load thư viện vào
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    
    private Mat coverImage = null;
    private Mat key = null;
    private String message;
    private int hiddenCount = 0;
    private int CHANNEL_BLUE = 0;
    private int CHANNEL_GREEN = 1;
    private int CHANNEL_RED = 2;
    
    
    public void setKey(String keyString){
        key = new Mat(8, 8, CvType.CV_8UC1);
        if (keyString.length() > 8){
            System.out.println("Key is too long. Only equal or smaller than 8 characters");
            return;
        }
        for(int i = 0; i < 8 - keyString.length(); i++){
            keyString+=" ";
        }
        for(int i = 0; i < keyString.length(); i++){
            String binString = String.format("%8s", Integer.toBinaryString(keyString.charAt(i))).replaceAll(" ", "0");
            for(int j = 0; j < 8; j++){
                key.row(i).col(j).setTo(new Scalar(Double.parseDouble(String.format("%c", binString.charAt(j)))));
            }
        }
        System.out.println("Key:\n" + key.dump());
    }
    
    public void setCoverImage(String path){
        // Đọc theo cấu trúc BGR
        coverImage = new Imgcodecs().imread(path);
        System.out.println("Load cover image successfull!");
    }
    
    public void printPixel(int row, int col){
        double[] rgb = coverImage.get(row, col);
        System.out.println(String.format("%.1f %.1f %.1f", rgb[0], rgb[1], rgb[2]));
    }
    
    public void setMessage(String message){
        this.message = message;
    }
    
    public Mat HideInBlock(Mat block, int channel){
        Mat channel_block = new Mat(8,8,CvType.CV_8UC1, new Scalar(0));
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                channel_block.row(i).col(j).setTo(new Scalar(block.get(i, j)[channel]));
            }
        }
        System.out.println(channel_block.dump());
        hiddenCount++;
        return block;
    }
    
    public void hideInBlueChannel(){
        int cols = coverImage.cols();
        int rows = coverImage.rows();
        for(int i = 0; i < rows; i += 8){
            if (i + 8 > rows || hiddenCount == message.length()) return;
            for(int j = 0; j < cols; j += 8){
                if (j + 8 > cols || hiddenCount == message.length()) break;
                Mat block = coverImage.colRange(j,j+8).rowRange(i,i+8);
                block = HideInBlock(block, CHANNEL_BLUE);
            }
        }
    }
    
    public void hideInGreenChannel(){
        
    }
    
    public void hideInRedChannel(){
        
    }
    
    public void hide(){
        if (coverImage == null){
            System.out.println("The image has not been imported");
        }
        else if (key == null){
            System.out.println("The key has not been generated");
        }
        else{
            System.out.printf("Rows: %d, Cols: %d\n",coverImage.rows(),coverImage.cols());
            hideInBlueChannel();
            hideInGreenChannel();
            hideInRedChannel();
        }
    }
    
    
    public static void main(String[] args) {
//        System.out.println("Welcome to OpenCV " + Core.VERSION);
//        Mat m = new Mat(8, 8, CvType.CV_8UC1, new Scalar(0));
        WuLeeOpencv wulee = new WuLeeOpencv();
        wulee.setCoverImage("cat.jpeg");
        wulee.setKey("minhduc");
        wulee.setMessage("d");
        wulee.hide();
        
    }
}

//First channel blue block
//[175, 175, 177, 176, 177, 177, 178, 178;
// 177, 174, 175, 175, 174, 175, 175, 176;
// 176, 175, 176, 175, 176, 174, 176, 176;
// 178, 174, 173, 175, 175, 176, 174, 176;
// 174, 173, 175, 175, 177, 174, 175, 175;
// 178, 175, 174, 174, 175, 178, 177, 176;
// 177, 176, 175, 174, 177, 176, 178, 177;
// 176, 176, 173, 175, 175, 176, 177, 177]