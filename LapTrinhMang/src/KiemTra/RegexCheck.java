/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KiemTra;
import java.util.regex.*;

/**
 *
 * @author hpmdu
 */
public class RegexCheck {
    
    public boolean isEmail(String email){
        return true;
    }
    
    public boolean isNum(String n){
        // Tồn tại kí tự chữ trong String thì sai
        Pattern p = Pattern.compile("([a-zA-z])+");
        return !(p.matcher(n).matches() || n.trim().isEmpty());
    }
    
    public boolean isValidFileName(String fName){
        // Kiểm tra kí tự unicode
        Pattern p1 = Pattern.compile("(.*)[\\/:*?\"<>|ẮẰẲẴẶĂẤẦẨẪẬÂÁÀÃẢẠĐẾỀỂỄỆÊÉÈẺẼẸÍÌỈĨỊỐỒỔỖỘÔỚỜỞỠỢƠÓÒÕỎỌỨỪỬỮỰƯÚÙỦŨỤÝỲỶỸỴắằẳẵặăấầẩẫậâáàãảạđếềểễệêéèẻẽẹíìỉĩịốồổỗộôớờởỡợơóòõỏọứừửữựưúùủũụýỳỷỹỵ](.*)");
        // Kiểm tra đuôi file. Ví dụ: đuôi file ko dc có kí tự đặc biệt
        Pattern p2 = Pattern.compile("([.]+(\\W|(.*)\\W+))");
//        if (fName.trim().isEmpty()) return false;
//        else if (p1.matcher(fName).matches()) return false;
//        else if (p2.matcher(fName).matches()) return false;
//        return true;
        return !(p1.matcher(fName).matches() || p2.matcher(fName).matches() || fName.trim().isEmpty());
    }
    
    public boolean match(String regex, String input){
        return Pattern.compile(regex).matcher(input).matches();
    }
    
    public static void main(String[] args) {
        RegexCheck r = new RegexCheck();
        System.out.println(r.isValidFileName("minhduc.txt"));
        int a = 9;
        System.out.println(r.isNum("" + "a"));
    }
}
