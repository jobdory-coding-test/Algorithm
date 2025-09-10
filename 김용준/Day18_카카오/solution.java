package Day18_카카오;

import java.util.*;
class Solution {
    static int cnt;
    static int width;
    static int temp;
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};
    static int N,M;
    static boolean[][] visited;
    public int[] solution(int m, int n, int[][] picture) {
        cnt = 0;
        width = 0;
        N = n;
        M = m;

        visited = new boolean[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(!visited[i][j]&&picture[i][j] !=0){
                    cnt++;
                    temp = 1;
                    bfs(i,j,picture[i][j],picture);
                }
            }
        }
        int []answer = {cnt,width};
        return answer;
        
    }
    public static void bfs(int row, int col, int num, int[][] picture){
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {row,col});
        visited[row][col] = true;
        while(!q.isEmpty()){
            int [] cur = q.poll();
            for(int i=0;i<4;i++){
                int rr = cur[0]+dr[i];
                int cc = cur[1]+dc[i];
                if(check(rr,cc) && !visited[rr][cc] && picture[rr][cc] == num){
                    visited[rr][cc] = true;
                    q.add(new int[]{rr,cc});
                    temp++;
                }
            }
        }
        width = Math.max(width,temp);
    }
    public static boolean check(int row, int col){
        return row>=0 && row<M && col>=0 && col<N;
    }
}
