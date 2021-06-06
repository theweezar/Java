/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package httpjson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.*;
import entities.*;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author hpmdu
 */
public class HttpRequest {
    public static void main(String[] args) throws MalformedURLException, IOException {
        
        HttpRequest http = new HttpRequest();
        
//        String phongbanJson = http.requestHttp("http://192.168.1.6/PhongBanController-show");
//        List<Object> phongBanList = http.parseJSON(phongbanJson, "PhongBan");
//        http.printPhongBanEntity(phongBanList);
//        System.out.println();
//        String nhanvienJson = http.requestHttp("http://192.168.1.6/NhanVienController-show");
//        http.parseJSON(nhanvienJson, "NhanVien");
//        System.out.println();
//        String vppJson = http.requestHttp("http://192.168.1.6/VanPhongPhamController-show");
//        http.parseJSON(vppJson, "VanPhongPham");
        
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("MAPP", "PB01");
        hashMap.put("TENPB", "PHONG KY THUAT");
        http.makeRequestBody(hashMap);
    }
    
    public void makeRequestBody(HashMap<String, String> hashMap) {
        for(String key: hashMap.keySet()) {
            System.out.println(hashMap.get(key));
        }
    }
    
    public String requestHttp(String request) {
        try {
            URL url = new URL(request);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("GET");
            BufferedReader br = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));

            String input;
            String result = "";

            while ((input = br.readLine()) != null){
                result += input;
            }
            
            br.close();
            return result;
        }
        catch (MalformedURLException malformedURLException) {
            malformedURLException.printStackTrace();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
        return "";
    }
    
    public List<Object> parseJSON(String json, String objectName) {
        JSONObject jsonObj = new JSONObject(json);
        JSONArray viewData = jsonObj.getJSONArray("viewData");
        System.out.println(json);
        
        List<Object> entitiesList = new ArrayList<>();
        
        if (objectName.trim().equals("PhongBan")) {    
            for(int i = 0; i < viewData.length(); i++) {
                PhongBan phongBan = new PhongBan(viewData.getJSONObject(i).getString("MAPB"), 
                        viewData.getJSONObject(i).getString("TENPB"));
                entitiesList.add(phongBan);
            }
        }
        
        if (objectName.trim().equals("NhanVien")) {
            for(int i = 0; i < viewData.length(); i++) {
                NhanVien nhanVien = new NhanVien(
                        viewData.getJSONObject(i).getString("MANV"), 
                        viewData.getJSONObject(i).getString("HOTEN"),
                        viewData.getJSONObject(i).getString("NGAYSINH"),
                        viewData.getJSONObject(i).getString("MAPB")
                );
                entitiesList.add(nhanVien);
            }
        }
        
        if (objectName.trim().equals("VanPhongPham")) {
            for(int i = 0; i < viewData.length(); i++) {                
                VanPhongPham vanPhongPham = new VanPhongPham(
                        viewData.getJSONObject(i).getString("MAVPP"),
                        viewData.getJSONObject(i).getString("TENVPP"),
                        viewData.getJSONObject(i).getString("DVT"),
                        viewData.getJSONObject(i).getString("GIANHAP"),
                        viewData.getJSONObject(i).get("HINH").toString(),
                        viewData.getJSONObject(i).getInt("SOLUONG"),
                        viewData.getJSONObject(i).getString("MANCC")
                );
                entitiesList.add(vanPhongPham);
            }
        }
        
        if (objectName.trim().equals("CapPhat")) {
            
        }
        
        if (objectName.trim().equals("NhaCungCap")) {
            
        }
        
        if (objectName.trim().equals("PhieuCungCap")) {
            
        }
        
        return entitiesList;
    }
    
    public void printPhongBanEntity(List<Object> phongBanList) {
        for(Object obj: phongBanList) {
            PhongBan pb = (PhongBan)obj;
            System.out.println(pb.getTenpb());
        }
    }
}
