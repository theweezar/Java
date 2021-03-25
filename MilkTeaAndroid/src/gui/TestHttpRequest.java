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

/**
 *
 * @author hpmdu
 */
public class TestHttpRequest {
    
    public static void main(String[] args) throws MalformedURLException, IOException {
        URL url = new URL("http://127.0.0.1:8000/register");
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestMethod("GET");
        BufferedReader br = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
                
        String input;

        while ((input = br.readLine()) != null){
           System.out.println(input);
        }
        br.close();
    }
}
