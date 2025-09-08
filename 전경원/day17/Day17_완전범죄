import java.util.*;

class Solution {
    
    static Queue<int[]> q;
    static boolean[][] visited;
    
    public int solution(int[][] info, int n, int m) {
        int answer = Integer.MAX_VALUE;
            
        q = new ArrayDeque<>();
        q.offer(new int[] {n, m});
        
        for (int[] item : info) {
            int cur = q.size();
            visited = new boolean[n + 1][m + 1];
    
            for (int i = 0; i < cur; i++) {
                int[] thief = q.poll();
                int a = thief[0];
                int b = thief[1];
                int nextA = a - item[0];
                int nextB = b - item[1];
                
                if (nextA > 0 && !visited[nextA][b]) {
                    visited[nextA][b] = true;
                    q.offer(new int[] {nextA, b});
                }
                if (nextB > 0 && !visited[a][nextB]) {
                    visited[a][nextB] = true;
                    q.offer(new int[] {a, nextB});
                }
            }
        }
        
        while (!q.isEmpty()) {
                int[] thief = q.poll();
                answer = Math.min(n - thief[0], answer);
        }
            
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}