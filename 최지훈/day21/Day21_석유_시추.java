import java.util.*;
import java.io.*;

class Solution {
    private static HashMap<Integer, Integer> hm;
    private static int[][] boardIdx;
    
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    
    public int solution(int[][] land) {
        hm = new HashMap<>();
        boardIdx = new int[land.length][land[0].length];
        
        int idx = 1;
        for(int i=0; i<land.length; i++) {
            for(int j=0; j<land[i].length; j++) {
                if(land[i][j] == 1) {
                    int cnt = bfs(i, j, idx, land);
                    hm.put(idx, cnt);
                    idx++;
                }
            }
        }
        
        int answer = 0;
        for(int i=0; i<land[0].length; i++) {
            boolean[] cv = new boolean[idx];
            int sum = 0;
            for(int j=0; j<land.length; j++) {
                if(hm.containsKey(boardIdx[j][i]) && !cv[boardIdx[j][i]]) {
                    sum += hm.get(boardIdx[j][i]);
                    cv[boardIdx[j][i]] = true;
                }
            }
            answer = Math.max(answer, sum);
        }
        
        return answer;
    }
    
    private static int bfs(int r, int c, int idx, int[][] land) {
        int cnt = 1;
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {r, c});
        boardIdx[r][c] = idx;
        land[r][c] = 0;
        
        while(!q.isEmpty()) {
            int[] info = q.poll();
            int xpos = info[0];
            int ypos = info[1];
            
            for(int d=0; d<4; d++) {
                int nx = xpos + dx[d];
                int ny = ypos + dy[d];
                
                if(!valid(nx, ny, land.length, land[0].length) || land[nx][ny] != 1) continue;
                
                boardIdx[nx][ny] = idx;
                q.offer(new int[] {nx, ny});
                land[nx][ny] = 0;
                cnt++;
            }
        }
        return cnt;
    }
    
    private static boolean valid(int x, int y, int n, int m) {
        return x>=0 && x<n && y>=0 && y<m;
    }
}