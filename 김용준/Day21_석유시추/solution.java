package Day21_석유시추;

import java.util.*;
class Solution {
    static boolean [][] visited;
    static int cnt;
    static Set<Integer>[] meet;
    static List<Integer> size;
    static int [] dr = {-1,0,1,0};
    static int [] dc = {0,1,0,-1};
    public int solution(int[][] land) {
        int answer = 0;
        visited = new boolean[land.length][land[0].length];
        int count = 2;
        size = new ArrayList<>();
        meet = new HashSet[land[0].length];
        for(int i=0;i<land[0].length;i++){
            meet[i] = new HashSet<>();
        }
        for(int i=0;i<land.length;i++){
            for(int j=0;j<land[0].length;j++){
                if(!visited[i][j] && land[i][j] == 1){
                    change(i,j,land,count,0);
                    count++;
                }
            }
        }
      
        for(int i=0;i<land[0].length;i++){
            int c = 0;
            for(int s : meet[i]){
                c+= size.get(s-2);
            }
            answer = Math.max(c,answer);
        }
        
        
        return answer;
    }
    public static void change(int row, int col, int[][] land, int c, int cnt){
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {row,col});
        land[row][col] = c;
        visited[row][col] = true;
        cnt++;
        if(!meet[col].contains(c)) meet[col].add(c);
        while(!q.isEmpty()){
            int[] cur = q.poll();
            for(int i=0;i<4;i++){
                int rr = cur[0]+dr[i];
                int cc = cur[1]+dc[i];
                if(check(rr,cc,land) && !visited[rr][cc] && land[rr][cc] == 1){
                    land[rr][cc] = c;
                    visited[rr][cc] = true;
                    q.add(new int[] {rr,cc});
                    if(!meet[cc].contains(c)) meet[cc].add(c);
                    cnt++;
                }
            }
        }
        size.add(cnt);
    }
    public static boolean check(int row,int col, int[][] land){
        return row>=0 && row<land.length && col>=0 && col<land[0].length;
    }
}
