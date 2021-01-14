/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tim_duong_di_nho_nhat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author daiph
 */

public class Test {
 
      public static String chuanHoaDanhTuRieng(String str) {
         str = str.trim();
        
        str = str.replaceAll("\\W+"," ");
        String temp[] = str.split(" ");
        str = "";
        for (int i = 0; i < temp.length; i++) {
            str += String.valueOf(temp[i].charAt(0)).toUpperCase() + temp[i].substring(1);
            if (i < temp.length - 1) // ? ^-^
                str += " ";
        }
        return str;
    }
      private static void copyDirectory(File sourceDirectory, File destinationDirectory) throws IOException {
    if (!destinationDirectory.exists()) {
        destinationDirectory.mkdir();
    }
    for (String f : sourceDirectory.list()) {
        copyDirectoryCompatibityMode(new File(sourceDirectory, f), new File(destinationDirectory, f));
    }
}
      public static void copyDirectoryCompatibityMode(File source, File destination) throws IOException {
    if (source.isDirectory()) {
        copyDirectory(source, destination);
    } else {
        copyFile(source, destination);
    }
}
   private static void copyFile(File sourceFile, File destinationFile) 
  throws IOException {
    try (InputStream in = new FileInputStream(sourceFile); 
            OutputStream out = new FileOutputStream(destinationFile)) {
        byte[] buf = new byte[1024];
        int length;
        while ((length = in.read(buf)) > 0) {
            out.write(buf, 0, length);
        }
    }
}
   public static String findNumber( int cost[][], int n, int m, int x){
       for( int i = 0; i <= n; i++)
           for( int j =0; j<= m;j++){
               if( cost[i][j] == x ) return String.format("%d %d",i,j);
           }
       return "";
   }
   
//static int row = 3; 
//static int col = 2; 
   
static int minCost(int cost[][], int row, int col) 
{ 
  // for 1st colum
    ArrayList<Integer> path = new ArrayList();
  for (int i = 1; i < row; i++)
  { 
    cost[i][0] += cost[i - 1][0]; 
  } 
  
  // for 1st row 
  for (int j = 1; j < col; j++)
  { 
    cost[0][j] += cost[0][j - 1]; 
  } 
 
  // for rest of the 2d matrix 
  for (int i = 1; i < row; i++) 
  { 
    for (int j = 1; j < col; j++) 
    { 
      System.out.println(String.format("Section: %d %d %d with i = %d, j = %d",cost[i - 1][j],cost[i - 1][j - 1],cost[i][j - 1],i,j));
      int min = Math.min(cost[i - 1][j - 1], 
                    Math.min(cost[i - 1][j], 
                             cost[i][j - 1]));
      cost[i][j] += min; 
//        System.out.println(min);
    } 
  } 
  int i = row-1, j = col-1;
    while( true ){
        int top = ( i - 1 < 0 ) ? 0 : i - 1;
        int left = ( j - 1 < 0 ) ? 0 : j - 1;
    int min = Math.min(cost[top][left], 
                    Math.min(cost[top][j], 
                             cost[i][left]));
        if( i == 0 && j == 0 ) break;    
        System.out.println(String.format("%d %d %d %s", top, left, min,path));
        path.add( cost[i][j] - min);
        
        String[] cord = findNumber(cost,i,j, min).split(" ");
        i = Integer.parseInt(cord[0]);
        j = Integer.parseInt(cord[1]);
    }
    path.add(cost[0][0]);
    Collections.reverse(path);
    System.out.println("Path : "+path.toString());
  // Returning the value in 
  // last cell 
  return cost[row - 1][col - 1]; 
}
 public static void main(String[] args) throws IOException {
       int[][] cost =
        {
//            { 12, 1, 6, 2},
//            { 18, 22, 3,5 },
//            { 54, 19, 4,81 },
//            { 7, 16, 24, 68},
            {4 ,5, 6},
            {2, 6, 8}
        };
 
      System.out.print(minCost(cost,2,3) + "\n"); 
 }

    
}
