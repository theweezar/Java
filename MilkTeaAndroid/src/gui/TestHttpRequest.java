/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import org.json.*;
/**
 *
 * @author hpmdu
 */
public class TestHttpRequest {
    
    public static void main(String[] args) throws MalformedURLException, IOException {
        URL url = new URL("http://192.168.1.6/PhongBanController-show");
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestMethod("GET");
        BufferedReader br = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
                
        String input;
        String result = "";

        while ((input = br.readLine()) != null){
            result += input;
        }
        System.out.println(result);
        JSONObject json = new JSONObject(result);
        br.close();
        System.out.println(json.getJSONArray("viewData").getJSONObject(0).getString("MAPB"));
    }
}
