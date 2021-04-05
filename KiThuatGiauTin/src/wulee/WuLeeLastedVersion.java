/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wulee;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;

/**
 *
 * @author hpmdu
 */
public class WuLeeLastedVersion {
    
    static{
        // Load thư viện vào
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    
    private Mat coverImage = null;
    private Mat key = null;
    private int blockWidth = 0;
    private int blockHeight = 0;
    private String message = "";
    private int hiddenCount = 0;
    private String retrieveMessage = "";
    private int retrieveCount = 0;
    private int retrieveMax = 20;
    private int CHANNEL_BLUE = 0;
    private int CHANNEL_GREEN = 1;
    private int CHANNEL_RED = 2;
    
    public void setKey(String keyString){
        // Khởi tạo ma trận khóa với kích thước rows = keyString.length(), cols = 8
        key = new Mat(keyString.length(), 8, CvType.CV_8UC1);
        for(int i = 0; i < keyString.length(); i++){
            // binString là chuyển đổi của 1 ký tự trong keyString sang dạng chuỗi nhị phân có độ dài là 8 -> %8s
            String binString = String.format("%8s", Integer.toBinaryString(keyString.charAt(i))).replaceAll(" ", "0");
            // Gán chuỗi nhị phân trên vào đúng vị trí trong ma trận key
            for(int j = 0; j < 8; j++){
                key.row(i).col(j).setTo(new Scalar(Double.parseDouble(String.format("%c", binString.charAt(j)))));
            }
        }
        blockHeight = key.rows();
        blockWidth = key.cols();
//        System.out.println("Key:\n" + key.dump());
    }
    
    public void setKeyToNull(){
        key = null;
    }
    
    public void setCoverImage(String path){
        // Đọc theo cấu trúc BGR
//        coverImage = null;
        coverImage = Imgcodecs.imread(path);
    }
    
    public boolean coverIsNull(){
        return coverImage == null;
    }
    
    public void saveStegoImage(String path){
        Imgcodecs.imwrite(path, coverImage);
    }
    
    public void setMessage(String message){
        this.message = message;
    }

    public String getRetrieveMessage() {
        return retrieveMessage;
    }

    public void setRetrieveMax(int retrieveMax) {
        this.retrieveMax = retrieveMax;
    }
    
    public void resetWhenHide(){
        message = "";
        hiddenCount = 0;
    }
    
    public void resetWhenRetrieve(){
        retrieveCount = 0;
        retrieveMax = 0;
        retrieveMessage = "";
    }
    
//    public String calculate(){
//        int 
//    }
    
    public Mat fi_to_binary(Mat col){
        Mat fi = new Mat(blockHeight, blockWidth, CvType.CV_8UC1, new Scalar(0));
        // Duyệt theo chiều cao - theo hàng vì col truyền vô là 1 ma trận (blockHeight,1)
        for(int i = 0; i < blockHeight; i++){
            String binary = String.format("%8s", 
                    Integer.toBinaryString((int)col.get(i, 0)[0])).replaceAll(" ", "0");
            // Mặc định blockWidth = 8
            for(int j = 0; j < blockWidth; j++){
                // Set 1 hoặc 0 theo chuỗi binary ở trên
                fi.row(i).col(j).setTo(new Scalar(binary.charAt(j) == '1'? 1.0 : 0.0));
            }
        }
        
        return fi;
    }
    
    public Mat fi_to_int(Mat fi){
        Mat newFi = new Mat(blockHeight,1,CvType.CV_8UC1, new Scalar(0));
        for(int i = 0; i < blockHeight; i++){
            String binString = "";
            for(int j = 0; j < blockWidth; j++){
                // .get sẽ return array double vì Mat là ma trận 3 chiều
                binString += (int)fi.get(i, j)[0];
            }
            // col(0) là vì newFi chỉ có 1 cột
            newFi.col(0).row(i).setTo(new Scalar((double)Integer.parseInt(binString, 2)));
        }
        return newFi;
    }
    
    public Mat randomBinaryReplace(Mat fi, int target, int replace){
        for(int i = 0; i < fi.rows(); i++){
            for(int j = 0; j < fi.cols(); j++){
                if ((int)fi.get(i, j)[0] == target && (int)key.get(i, j)[0] == 1){
                    fi.row(i).col(j).setTo(new Scalar(replace));
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
                    return fi;
                }
            }
        }
        return fi;
    }
    
    public Mat hideInBlock(Mat block,  char c, int channel){
        // block ở trên có 3 channel BGR, (blockHeight,blockWidth,3) BGR
        List<Mat> listChannel = new ArrayList<Mat>();
        // Core.split là phương thức tách chiều sau của ma trận rồi return vào 1 list<Mat> đã khởi tạo ở trên
        Core.split(block, listChannel);
        // channel_block là ma trận int blockHeight x blockWidth = 8 được lấy từ listChannel ở trên thôi thứ tự 0,1,2 tương đương với BGR
        Mat channel_block = listChannel.get(channel);
        
//        System.out.printf("\nchannel_block:\n%s\n",channel_block.dump());
        
        // Chuyển kí tự thành dãy nhị phân 8bit
        String binChar = String.format("%8s", Integer.toBinaryString(c)).replaceAll(" ", "0");
//        System.out.printf("\n%c ===> %s\n", c, binChar);
        // Duyệt theo từng cột - channel_block.cols()
        for(int i = 0; i < channel_block.cols(); i++){
            // Fi là ma trận nhị phân (blockHeight,8) của từng cột (blockHeight,1)
            Mat fi = fi_to_binary(channel_block.col(i));
            // In ra màn hình giá trị fi int cũ
//            System.out.printf("fi[%d] int :\n%s\n",i,channel_block.col(i).dump());
            // In ra màn hình giá trị fi binary cũ
//            System.out.printf("fi[%d] bin :\n%s\n",i,fi.dump());
            // Function Core.bitwise_and giữa fi và key, sau đó return kết quả vào biến matAnd
            Mat matAnd = new Mat();
            Core.bitwise_and(fi, key, matAnd);
            // Function Core.sumElems là phương thức cộng tất cả phần tử trong ma trận lại
            int and_sum = (int)Core.sumElems(matAnd).val[0];
            int key_sum = (int)Core.sumElems(key).val[0];
//            System.out.printf("fi[%d] ===> and_sum = %d ; key_sum = %d\n", i, and_sum, key_sum);
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
//                System.out.printf("fi[%d] bin :\n%s\n",i,fi.dump());
                fi = fi_to_int(fi);
                // In ra màn hình giá trị fi int mới
//                System.out.printf("fi[%d] int :\n%s\n",i,fi.dump());
                // Gán giá trị fi mới vào channel_block. Duyệt theo channel_block.rows()
                for(int j = 0; j < channel_block.rows(); j++){
                    channel_block.put(j, i, fi.get(j, 0));
                }
//                System.out.printf("\nNewchannel_block:\n%s\n",channel_block.dump());
            }
        }
        // Sau khi channel_block đã được chỉnh sửa, ta gán ngược lại vào listChannel ở trên đúng với thứ tự channel
        listChannel.set(channel, channel_block);
        // Sau đó merge listChannel mới vào Mat block để ra 1 block ảnh mới đã được giấu tin và return nó về
        Core.merge(listChannel, block);
//        System.out.println(block.dump());
        // Sau khi dấu xong thì hiddenCount sẽ tăng lên 1 vì đã dấu được 1 ký tự
        hiddenCount++;
        return block;
    }
    
    public void assignNewBlockToCoverImage(Mat block, int row, int col){
        // Đây là phương thức gán block(BGR) vào ảnh(BGR) - row, col là điểm bắt đầu trong ma trận
        for(int i = 0; i < blockHeight; i++){
            for(int j = 0; j < blockWidth; j++){
                coverImage.put(row + i, col + j, block.get(i, j));
            }
        }
    }
    
    public void hideInChannel(int channel){
        int cols = coverImage.cols();
        int rows = coverImage.rows();
        // Duyệt chiều cao
        for(int i = 0; i < rows; i += blockHeight){
            if (i + blockHeight > rows || hiddenCount == message.length()) return;
            for(int j = 0; j < cols; j += blockWidth){
                if (j + blockWidth > cols || hiddenCount == message.length()) break;
                // Trích xuất block - 1 block này sẽ dấu được hết 1 ký tự
                Mat block = coverImage.colRange(j,j + blockWidth).rowRange(i,i + blockHeight);
                // block mới sau khi được dấu tin
                block = hideInBlock(block, message.charAt(hiddenCount),channel);
                // gán block mới vào ảnh
                assignNewBlockToCoverImage(block, i, j);
            }
        }
    }
    
    public void hide(){
        hideInChannel(CHANNEL_BLUE);
        hideInChannel(CHANNEL_GREEN);
        hideInChannel(CHANNEL_RED);
    }
    
    public void retrieveInBlock(Mat block, int channel){
        // block ở trên có 3 channel BGR
        List<Mat> listChannel = new ArrayList<Mat>();
        // Core.split là phương thức tách chiều sau của ma trận rồi return vào 1 list<Mat> đã khởi tạo ở trên
        Core.split(block, listChannel);
        // channel_block là ma trận int blockHeight x blockWidth = 8 được lấy từ listChannel ở trên thôi thứ tự 0,1,2 tương đương với BGR
        Mat channel_block = listChannel.get(channel);
//        System.out.printf("\nchannel_block:\n%s\n",channel_block.dump());
        
        String binString = "";
        for(int i = 0; i < channel_block.cols(); i++){
            // Fi là ma trận nhị phân (blockHeight,blockWidth = 8) của từng cột (blockHeight,1)
            Mat fi = fi_to_binary(channel_block.col(i));
            // In ra màn hình giá trị fi int cũ
//            System.out.printf("fi[%d] int :\n%s\n",i,channel_block.col(i).dump());
            // In ra màn hình giá trị fi binary cũ
//            System.out.printf("fi[%d] bin :\n%s\n",i,fi.dump());
            // Function Core.bitwise_and giữa fi và key, sau đó return kết quả vào biến matAnd
            Mat matAnd = new Mat();
            Core.bitwise_and(fi, key, matAnd);
            // Function Core.sumElems là phương thức cộng tất cả phần tử trong ma trận lại
            int and_sum = (int)Core.sumElems(matAnd).val[0];
            int key_sum = (int)Core.sumElems(key).val[0];
//            System.out.printf("fi[%d] ===> and_sum = %d ; key_sum = %d\n", i, and_sum, key_sum);
            if (and_sum > 0 && and_sum < key_sum){
                if (and_sum % 2 == 0){
                    binString += "0";
                }
                else {
                    binString += "1";
                }
            }
        }
        retrieveMessage += String.format("%c", (char)Integer.parseInt(binString, 2));
        retrieveCount++;
    }
    
    public void retrieveInChannel(int channel){
        int cols = coverImage.cols();
        int rows = coverImage.rows();
        for(int i = 0; i < rows; i += blockHeight){
            if (i + blockHeight > rows || retrieveCount == retrieveMax) return;
            for(int j = 0; j < cols; j += blockWidth){
                if (j + blockWidth > cols || retrieveCount == retrieveMax) break;
                Mat block = coverImage.colRange(j,j + blockWidth).rowRange(i,i + blockHeight);
                retrieveInBlock(block, channel);
            }
        }
    }
    
    public void retrieve(){
        // Kiểm lỗi
        retrieveInChannel(CHANNEL_BLUE);
        retrieveInChannel(CHANNEL_GREEN);
        retrieveInChannel(CHANNEL_RED);
    }
    
    public static void main(String[] args) {
        WuLeeLastedVersion wulee = new WuLeeLastedVersion();
        wulee.setKey("a");
        wulee.setCoverImage("tree.jpg");
        wulee.setMessage("minhducducminh");
        wulee.hide();
        wulee.retrieve();
        System.out.println(wulee.getRetrieveMessage());
//        System.out.println(Pattern.matches("(.*).png", "ab-c.png"));
    }
}
