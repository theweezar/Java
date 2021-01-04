/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thuattoan;

/**
 *
 * @author hpmdu
 */
public class ThuatToan {
    
    public static String bai_17(String input){
        String kq = "";
        // chuyển chữ cái đầu thành chữ hoa
        kq += String.format("%c", input.charAt(0)).toUpperCase();
        // Loại bỏ cách thừa và đổi các chữ còn lại thành kí tự thường
        for(int i = 1; i < input.length(); i++){
            if (input.charAt(i - 1) == ' ' && input.charAt(i) == ' '){
            }
            else kq += String.format("%c", input.charAt(i)).toLowerCase();
        }
        return kq;
    }
    
    public static String bai_18(String input){
        String[] arr = input.split(" ");
        int max = arr[0].length();
        int pos = 0;
        for(int i = 1; i < arr.length; i++){
            if (arr[i].length() > max){
                max = arr[i].length();
                pos = i;
            } 
        }
        return String.format("Kí tự dài: %s, vị trí: %d", arr[pos], pos + 1);
    }
    
    public static void main(String[] args) {
        System.out.println(ThuatToan.bai_17("hoang      mINh duc"));
        System.out.println(ThuatToan.bai_18("Hoang phannn minh  duc"));
    }
}
