import java.util.*;

class Day22_등대 {
    
    static int[][] dp;
    static ArrayList<Integer>[] arr;
    static boolean[] visited;
    
    public int solution(int n, int[][] lighthouse) {
        int answer = 0;
        
        visited = new boolean[n+1];
                
        // 1. 입력 인접리스트로 만들기.
        arr = new ArrayList[n+1];
                                                
        for(int i = 0; i<=n; i++) {
            arr[i] = new ArrayList<>();
        }
        for(int i = 0; i < n-1; i++) {
            int a = lighthouse[i][0];
            int b = lighthouse[i][1];
            
            arr[a].add(b);
            arr[b].add(a);
        }
                
        // 2. dp 선언
        dp = new int[n+1][2];
        
        dfs(1);
        
        answer = Math.min(dp[1][0], dp[1][1]);
        
        return answer;
    }
    
    // 부모가 켜지면, 자식은 켜져도 되고, 안 켜져도 됨.
    // 부모가 꺼지면, 자식은 무조건 다 켜져야 함.
    public void dfs(int n) {
        
        visited[n] = true;
        
        dp[n][1] = 1; // 지금 등대 켜져 있음.
        dp[n][0] = 0; // 지금 등대 꺼져 있음.
        
        for(int i = 0; i<arr[n].size(); i++) {
            int child = arr[n].get(i);
            
            if(visited[child]) continue;
            
            dfs(child); // child 가 루트인 것 계산 됨.
            
            dp[n][0] += dp[child][1]; // 자식이 모두 켜지도록 반영(부모가 안 켜지면 자식을 다 켜야하므로)
            dp[n][1] += Math.min(dp[child][0], dp[child][1]); // 부모가 켜지면 자식은 켜져도, 안 켜져도 되므로
        }
    }
}