import java.io.*;
import java.util.*;

class Day22_등대 {
    private static boolean[] visited, light;
    private static int answer = 0;
    private static List<Integer>[] arr;
    
    public int solution(int n, int[][] lighthouse) {
        visited = new boolean[n + 1];
        light = new boolean[n + 1]; 

        arr = new ArrayList[n + 1];
        for(int i = 1; i<=n; i++) {
            arr[i] = new ArrayList<>();
        }
        
        for (int i = 0; i<lighthouse.length; i++) {  
           int from = lighthouse[i][0];
           int to = lighthouse[i][1];    
           arr[from].add(to);
           arr[to].add(from);
        }
        
        dfs(1, 1);
        return answer;
    }
    
    private static boolean dfs(int st, int pt) {
        visited[st] = true;   
        boolean isLight = true;
        
        for(int w : arr[st]) {
            if(visited[w] || w == pt) continue;
            boolean state = dfs(w, st);
            if(!state) {
                isLight = false;
            }
            
        }
        
        if(!isLight) {
            answer++;
            return true;
        }      
        return false;       
    }
}