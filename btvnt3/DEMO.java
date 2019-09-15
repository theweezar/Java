package btvnt3;

import java.text.DecimalFormat;

/**
 * DEMO
 */
public class DEMO {

  public static void main(String[] args) {
    NHANVIEN[] dsnv = new NHANVIEN[3];
    DecimalFormat df = new DecimalFormat("0.00");
    dsnv[0] = new NHANVIEN("nv01","Tran Van A",1.23);

    dsnv[1] = new NHANVIEN(true);
    dsnv[1].setHoTen("Do Van B");
    System.out.printf("Ho ten nhan vien 2: %s\n",dsnv[1].getHoTen());

    dsnv[2] = new NHANVIEN(false);
    dsnv[2].setMSNV("nv03");
    dsnv[2].setHoTen("Nguyen Thi C");
    dsnv[2].setHsluong(2.4);

    NHANVIEN tmp = dsnv[0];
    for(int i = 1; i < dsnv.length; i++){
      if (tmp.getHsluong() < dsnv[i].getHsluong()) tmp = dsnv[i];
    }
    System.out.printf("Nhan vien co HSluong cao nhat: %s | %s | %s | %s\n",tmp.getMSNV(),tmp.getHoTen(),df.format(tmp.getHsluong()),df.format(tmp.getLuongCB()));
    
    for(int i = 1; i < dsnv.length; i++){
      System.out.printf("Nhan vien thu %d\n",i+1);
      System.out.printf("MSNV: %s\n",dsnv[i].getMSNV());
      System.out.printf("HoTen: %s\n",dsnv[i].getHoTen());
      System.out.printf("HSLuong: %s\n",df.format(dsnv[i].getHsluong()));
      System.out.printf("LuongCB: %s\n",df.format(dsnv[i].getLuongCB()));
    }

    System.out.printf("\nSo luong nhan vien trong danh sach: %d",dsnv.length);
  }
}