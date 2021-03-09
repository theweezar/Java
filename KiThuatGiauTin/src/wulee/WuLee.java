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
    
    private Image s_image = null;
    private String message;
    private boolean[][] key = null;
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public void setImage(String path){
        s_image = new Image();
        s_image.readImage(path);
    }
    
    public void generateKeyFromString(String keyString){
        // Key này có độ dài <= 8
        key = new boolean[8][8];
        if (keyString.length() > 8){
            System.out.println("Key is too long. Only equal or smaller than 8 characters");
            return;
        }
        for(int i = 0; i < 8 - keyString.length(); i++){
            keyString+=" ";
        }
        for(int i = 0; i < 8; i++){
            key[i] = get8BitBinaryArray(keyString.charAt(i));
        }
    }
    
    public boolean[] get8BitBinaryArray(int decimal){
        boolean[] bitArrray = new boolean[8];
        String binString = String.format("%8s", Integer.toBinaryString(decimal)).replaceAll(" ", "0");
        //System.out.println(binString);
        for(int i = 0; i < bitArrray.length; i++){
            bitArrray[i] = binString.charAt(i) == '1' ? true:false;
        }
        return bitArrray;
    }
    
    public int[] convert8BitBinaryArrayToIntArray(boolean[][] bitArray){
        int[] intArr = new int[8];
        for(int i = 0; i < bitArray.length; i++){
            String binStr = "";
            for(int j = 0; j < bitArray[i].length; j++){
                binStr += bitArray[i][j] ? "1":"0";
            }
            intArr[i] = Integer.parseInt(binStr, 2);
        }
        return intArr;
    }
    
    public void print8BitBinaryArray(boolean[][] bitArray){
        // True : 1
        // False: 2
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                System.out.print((bitArray[i][j] ? 1:0) + " ");
            }
            System.out.println();
        }
    }
    
    public boolean[][] bitwiseAnd(boolean[][] bitArray_1, boolean[][] bitArray_2){
        boolean[][] and_array = new boolean[8][8];
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                and_array[i][j] = bitArray_1[i][j] & bitArray_2[i][j];
            }
        }
        return and_array;
    }
    
    public int sum(boolean[][] bitArray){
        int sum = 0;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if (bitArray[i][j]) sum++;
            }
        }
        return sum;
    }
    
    public Pixel[][] hideInBlock(int channel, Pixel[][] block, char c){
        int[][] channel_array = Image.getChannel(channel, block);
        boolean[] bin_array = get8BitBinaryArray(c);
        Image.print2DArrayPixel(Image.CHANNEL_BLUE, block);
        // Duyệt theo cột, i là cột
        for(int i = 0; i < 8; i++){
            // fi là ma trận 8x8 nhị phân theo cột số màu
            boolean[][] fi = new boolean[8][];
            // Duyệt theo hàng, j là hàng từ trên xuống dưới. Vì đây là ma trận có shape [8,1]
            for(int j = 0; j < 8; j++){
                // Duyệt từng phần tử trong ma trận [8,1] để biến thành ma trận binary [8,8]
                fi[j] = get8BitBinaryArray(channel_array[j][i]);
            }
//            System.out.println("Binary 2DArray at column " + i);
//            print8BitBinaryArray(fi);
            // SUM(fi & key)
            int and_sum = sum(bitwiseAnd(fi, key));
            // SUM(key)
            int key_sum = sum(key);
            // Binary number 
            int bin_number = bin_array[i] ? 1:0;
            System.out.printf("SUM(fi&key): %d, SUM(key): %d\n", and_sum, key_sum);
            boolean replace = false;
            if (and_sum > 0 && and_sum < key_sum){
                if (and_sum % 2 == bin_number){
                    System.out.println("Do nothing");
                }
                else if (and_sum == 1){
                    for(int y = 0; y < 8; y++){
                        for(int x = 0; x < 8; x++){
                            if (!fi[y][x] && key[y][x]){
                                fi[y][x] = true;
                                replace = true;
                                break;
                            }
                        }
                        if (replace) break;
                    }
                    System.out.println("and_sum == 1");
                }
                else if(and_sum == key_sum - 1){
                    for(int y = 0; y < 8; y++){
                        for(int x = 0; x < 8; x++){
                            if (fi[y][x] && key[y][x]){
                                fi[y][x] = false;
                                replace = true;
                                break;
                            }
                        }
                        if (replace) break;
                    }
                    System.out.println("and_sum == key_sum - 1");
                }
                else{
                    for(int y = 0; y < 8; y++){
                        for(int x = 0; x < 8; x++){
                            if (key[y][x]){
                                fi[y][x] = !fi[y][x];
                                replace = true;
                                break;
                            }
                        }
                        if (replace) break;
                    }
                    System.out.println("in range (and_sum, keysum)");
                }
            }
//            System.out.println("New column");
//            print8BitBinaryArray(fi);
            int[] intArray = convert8BitBinaryArrayToIntArray(fi);
            // Duyệt theo hàng j để thay đổi channel_array
            for(int j = 0; j < 8; j++){
                block[j][i].setValueInChannel(Image.CHANNEL_BLUE, intArray[j]);
            }
        }
        System.out.println("New block:");
        Image.print2DArrayPixel(Image.CHANNEL_BLUE, block);
        return block;
    }
    
    public void hide(){
        if (s_image == null){
            System.out.println("The image has not been imported");
            return;
        }
        if (key == null){
            System.out.println("The key has not been generated");
        }
        
        int height = s_image.getHeight();
        int width = s_image.getWidth();
        System.out.printf("Height: %d - Width: %d\n", height, width);
        
        int wordHidden = 0;
        
        for(int i = 0; i < height; i+=8){
            for(int j = 0; j < width; j+=8){
                Pixel[][] block = s_image.get2DArrayPixel(j,i,8,8);
                if (block != null){
                    block = hideInBlock(Image.CHANNEL_BLUE, block, message.charAt(0));
                    wordHidden++;
                    // Chỉnh sửa hàm bên Image trước vì đang cần thiết
                }
                if (wordHidden == message.length()) break;
            }
            if (wordHidden == message.length()) break;
        }
        
        System.out.println("Hidden successfully");
        
    }
    
    public static void main(String[] args) {
        WuLee wulee = new WuLee();
        wulee.setImage("cat.jpeg");
        wulee.setMessage("d");
        wulee.generateKeyFromString("minhduc");
        wulee.hide();
//        System.out.println(Integer.parseInt("01110010", 2));
    }
}
