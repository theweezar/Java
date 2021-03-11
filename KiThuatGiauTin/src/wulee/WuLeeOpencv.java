/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wulee;

import java.util.ArrayList;
import java.util.List;
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
    
    public Mat fi_to_binary(Mat col){
        Mat fi = new Mat(8,8,CvType.CV_8UC1, new Scalar(0));
        
        for(int i = 0; i < col.rows(); i++){
            String binary = String.format("%8s", 
                    Integer.toBinaryString((int)col.get(i, 0)[0])).replaceAll(" ", "0");
            
            for(int j = 0; j < 8; j++){
                fi.row(i).col(j).setTo(new Scalar(binary.charAt(j) == '1'? 1.0 : 0.0));
            }
        }
        
        return fi;
    }
    
    public Mat fi_to_int(Mat fi){
        Mat newFi = new Mat(8,1,CvType.CV_8UC1, new Scalar(0));
        for(int i = 0; i < fi.rows(); i++){
            String binString = "";
            for(int j = 0; j < fi.cols(); j++){
                binString += (int)fi.get(i, j)[0];
            }
            newFi.col(0).row(i).setTo(new Scalar((double)Integer.parseInt(binString, 2)));
        }
        return newFi;
    }
    
    public Mat randomBinaryReplace(Mat fi, int target, int replace){
        for(int i = 0; i < fi.rows(); i++){
            for(int j = 0; j < fi.cols(); j++){
                if ((int)fi.get(i, j)[0] == target && (int)key.get(i, j)[0] == 1){
                    fi.row(i).col(j).setTo(new Scalar(replace));
//                    System.out.println("change");
                    return fi;
                }
            }
        }
        return fi;
    }
    
    public Mat randomBinaryReverse(Mat fi){
        for(int i = 0; i < fi.rows(); i++){
            for(int j = 0; j < fi.cols(); j++){
                if ((int)key.get(i, j)[0] == 1){
                    int rev = (int)fi.get(i, j)[0] == 1 ? 0:1;
                    fi.row(i).col(j).setTo(new Scalar(rev));
//                    System.out.println("change");
                    return fi;
                }
            }
        }
        return fi;
    }
    
    public Mat hideInBlock(Mat block,  char c, int channel){
        // block ở trên có 3 channel BGR
        // channel_block là ma trận 8x8 đã được tách từng channel ra theo số int channel
        Mat channel_block = new Mat(8,8,CvType.CV_8UC1, new Scalar(0));
        List<Mat> listChannel = new ArrayList<Mat>();
        Core.split(block, listChannel);
        channel_block = listChannel.get(channel);
        // Duyệt để tách channel
//        for(int i = 0; i < 8; i++){
//            for(int j = 0; j < 8; j++){
//                channel_block.row(i).col(j).setTo(new Scalar(block.get(i, j)[channel]));
//            }
//        }
        System.out.printf("\nchannel_block:\n%s\n",channel_block.dump());
        
        // Chuyển kí tự thành dãy nhị phân 8bit
        String binChar = String.format("%8s", Integer.toBinaryString(c)).replaceAll(" ", "0");
        System.out.printf("\n%c ===> %s\n", c, binChar);
        // Duyệt theo từng cột - channel_block.cols()
        for(int i = 0; i < channel_block.cols(); i++){
            Mat fi = fi_to_binary(channel_block.col(i));
            // In ra màn hình giá trị fi int cũ
            System.out.printf("fi[%d] int :\n%s\n",i,channel_block.col(i).dump());
            // In ra màn hình giá trị fi binary cũ
            System.out.printf("fi[%d] bin :\n%s\n",i,fi.dump());
            // Function bitwise_and giữa fi và key, sau đó return kết quả vào biến matAnd
            Mat matAnd = new Mat();
            Core.bitwise_and(fi, key, matAnd);
            int and_sum = (int)Core.sumElems(matAnd).val[0];
            int key_sum = (int)Core.sumElems(key).val[0];
            System.out.printf("fi[%d] ===> and_sum = %d ; key_sum = %d\n", i, and_sum, key_sum);
            if (and_sum > 0 && and_sum < key_sum){
                int bit = binChar.charAt(i) == '1' ? 1:0;
                if (and_sum % 2 == bit){
                    
                }
                else if (and_sum == 1){
                    fi = randomBinaryReplace(fi, 0, 1);
                }
                else if (and_sum == key_sum - 1){
                    fi = randomBinaryReplace(fi, 1, 0);
                }
                else {
                    fi = randomBinaryReverse(fi);
                }
                // In ra màn hình giá trị fi binary mới
                System.out.printf("fi[%d] bin :\n%s\n",i,fi.dump());
                fi = fi_to_int(fi);
                // In ra màn hình giá trị fi int mới
                System.out.printf("fi[%d] int :\n%s\n",i,fi.dump());
                // Gán giá trị fi mới vào channel_block. Duyệt theo channel_block.rows()
                for(int j = 0; j < channel_block.rows(); j++){
                    channel_block.put(j, i, fi.get(j, 0));
                }
                System.out.printf("\nNewchannel_block:\n%s\n",channel_block.dump());
                // Khúc này chưa thử, mai thử
//                Core.merge(listChannel, fi);
            }
        }
        
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
                block = hideInBlock(block, message.charAt(hiddenCount),CHANNEL_BLUE);
            }
        }
    }
    
    public void hideInGreenChannel(){
        
    }
    
    public void hideInRedChannel(){
        
    }
    
    public void hide(){
        // Kiểm lỗi
        if (coverImage == null){
            System.out.println("The image has not been imported");
        }
        else if (key == null){
            System.out.println("The key has not been generated");
        }
        else{
            System.out.printf("Rows: %d, Cols: %d\n",coverImage.rows(),coverImage.cols());
            // Bắt đầu dấu tin qua 3 channel, nếu đã dấu đủ số lượng tin thì 3 hàm này sẽ tự động return
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

// First channel blue block, Hide message "d" in block
//[175, 175, 177, 176, 177, 177, 178, 178;
// 177, 174, 175, 175, 174, 175, 175, 176;
// 176, 175, 176, 175, 176, 174, 176, 176;
// 178, 174, 173, 175, 175, 176, 174, 176;
// 174, 173, 175, 175, 177, 174, 175, 175;
// 178, 175, 174, 174, 175, 178, 177, 176;
// 177, 176, 175, 174, 177, 176, 178, 177;
// 176, 176, 173, 175, 175, 176, 177, 177]

// result
//[239, 175, 241, 240, 177, 241, 242, 242;
// 177, 174, 175, 175, 174, 175, 175, 176;
// 176, 175, 176, 175, 176, 174, 176, 176;
// 178, 174, 173, 175, 175, 176, 174, 176;
// 174, 173, 175, 175, 177, 174, 175, 175;
// 178, 175, 174, 174, 175, 178, 177, 176;
// 177, 176, 175, 174, 177, 176, 178, 177;
// 176, 176, 173, 175, 175, 176, 177, 177]