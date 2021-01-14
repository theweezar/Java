/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tim_duong_di_nho_nhat;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hpmdu
 */
public class ThuatToan {
    
    
    public int min(int x, int y, int z)
    {
        if (x < y)
            return (x < z) ? x : z;
        else
            return (y < z) ? y : z;
    } 
     
    /* Returns cost of minimum cost path 
    from (0,0) to (m, n) in mat[R][C]*/
    public int minCost(int cost[][], int m,
                                     int n)
    {
        if (n < 0 || m < 0)
            return Integer.MAX_VALUE;
        else if (m == 0 && n == 0)
            return cost[m][n];
        else
            return cost[m][n] + 
                min( minCost(cost, m-1, n-1),
                     minCost(cost, m-1, n), 
                     minCost(cost, m, n-1) );
    }
    
    public int[] cost(int[][] cost, int m, int n){
        int[] a = {-1, m ,n}; 
        if (n < 0 || m < 0)
            a[0] = Integer.MAX_VALUE;
        else
            a[0] = cost[m][n];
        return a;
    }
    
    public int[] min_arr(int[] x, int[] y, int[] z){
        if (x[0] < y[0])
            return (x[0] < z[0]) ? x : z;
        else
            return (y[0] < z[0]) ? y : z;
    }
    
    public int[] dijkstra(int[][] graph, int s){
	int [] dist = new int[graph.length];
	for(int i = 0; i < graph.length; i++){
		dist[i] = Integer.MAX_VALUE;
	}
	dist[s] = 0;
	int [] visit = new int[graph.length]; 
	for(int i = 0; i < graph.length; i ++){
		int v = closestVertice(graph[s], visit);
		for(int j = 0; j < graph[v].length; j++){
			if (graph[v][j] != -1){ // neu co canh noi giua v va j
				int currentDist = dist[v] + graph[v][j];
				if (currentDist < dist[j]){
					dist[j] = currentDist;
				}
			}
		}
	}
	return dist;
    }
    /**
     * Chon ra dinh o gan s nhat va danh dau dinh do la da tham
     * */
    public int closestVertice(int[] adjacentVertices, int[] visit){
        int closest = -1;
        int minDist = Integer.MAX_VALUE;
        for(int i = 0; i < adjacentVertices.length; i ++){
                if (visit[i] == 0 && adjacentVertices[i] < minDist){
                        closest = i;
                        minDist = adjacentVertices[i];
                }
        }
        visit[closest] = 1;
        return closest;
    }
    
    public void run(){
        int arr[][] = { {1, 20, 3},
                        {4, 8, 2},
                        {1, 5, 3} };
        // Khởi tạo địa điểm đầu tiên
        int[] dist = new int[9];
        for(int i = 0; i < dist.length; i++){
            dist[i] = Integer.MAX_VALUE;
        }
        dist[0] = 0;
        
                          
//        System.out.println(minCost(arr, 2, 2));
    }
    
    public static void main(String[] args) {
        ThuatToan tt = new ThuatToan();
        tt.run();
    }
}
