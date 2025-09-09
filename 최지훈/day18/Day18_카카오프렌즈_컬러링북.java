import java.util.*;
import java.io.*;

class Solution {
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    
    private static boolean[][] visited;
    
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        visited = new boolean[m][n];
        for(int r=0; r<m; r++) {
            for(int c=0; c<n; c++) {
                if(!visited[r][c] && picture[r][c] != 0) {
                    numberOfArea++;
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, bfs(r, c, m, n, picture));
                }
            }
        }
        
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    
    private static int bfs(int r, int c, int m, int n, int[][] picture) {
        int count = 0;
        int base = picture[r][c];
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {r, c});
        visited[r][c] = true;
        
        while(!q.isEmpty()) {
            int[] info = q.poll();
            int xpos = info[0];
            int ypos = info[1];
            
            count++;
            
            for(int d=0; d<4; d++) {
                int nx = xpos + dx[d];
                int ny = ypos + dy[d];
                
                if(!valid(nx, ny, m, n) || visited[nx][ny] || picture[nx][ny] != base) continue;
                
                visited[nx][ny] = true;
                q.offer(new int[]{nx, ny});
            }
        }
        return count;
    }
    
    private static boolean valid(int x, int y, int m, int n) {
        return x>=0 && x<m && y>=0 && y<n;
    }
}