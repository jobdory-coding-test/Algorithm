import java.io.*;
import java.util.*;

class Day18_카카오프렌즈_컬러링북 {
    private static boolean[][] visited;
    private static int[][] delta = {{-1, 0}, {1,0},{0, -1}, {0, 1}};
    
    public int[] solution(int m, int n, int[][] picture) {
        visited = new boolean[m][n];
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        for(int r = 0; r < m; r++) {
            for(int c= 0; c < n; c++) {
                if(picture[r][c] > 0 && !visited[r][c]) {
                    maxSizeOfOneArea = Math.max(bfs(r, c, picture), maxSizeOfOneArea);
                    numberOfArea++;
                }
            }
        }
        
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    private static int bfs(int r, int c, int[][] picture) {
        visited[r][c] = true;
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {r, c, picture[r][c]});
        
        int result = 1;
        while(!q.isEmpty()) {
            int[] curr = q.poll();
            
            for(int i = 0; i < 4; i++) {
                int nr = curr[0] + delta[i][0];
                int nc = curr[1] + delta[i][1];
                
                if(nr >= 0 && nr < picture.length && nc >= 0 && nc < picture[0].length 
                   && !visited[nr][nc] && picture[nr][nc] == curr[2]) {
                    visited[nr][nc] = true;
                    q.add(new int[] {nr, nc, curr[2]});
                    result++;
                }
            }
        }
        
        return result;
    }
}