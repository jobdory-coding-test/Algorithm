import java.io.*;
import java.util.*;

class Day21_석유시추 {
    private static int[][] delta = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static boolean[][] visited;
    private static int[] count;
    private static int N, M;
    private static int answer;
    public int solution(int[][] land) {
        N = land.length;
        M = land[0].length;
        count = new int[M];
        visited = new boolean[N][M];
        answer = 0;
        
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < M; c++) {
                if(land[r][c] == 1 && !visited[r][c]) {
                    bfs(r, c, land);
                }
            }
        }
        return answer;
    }
    
    private static void bfs(int r, int c, int[][] land) {
        visited[r][c] = true;
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {r, c});
        int minC = M;
        int maxC = 0;
        int cnt= 0;
        
        while(!q.isEmpty()) {
            int[] curr = q.poll();
            
            cnt++;
            minC = Math.min(curr[1], minC);
            maxC = Math.max(curr[1], maxC);
            
            for(int i = 0; i < 4; i++) {
                int nr = curr[0] + delta[i][0];
                int nc = curr[1] + delta[i][1];
                
                if(nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc] && land[nr][nc] == 1) {
                    visited[nr][nc] = true;
                    q.add(new int[] {nr, nc});
                }
            }
        }
        
        for(int i = minC; i <= maxC; i++) {
            count[i] += cnt;
            answer = Math.max(answer, count[i]);
        }
    }
}