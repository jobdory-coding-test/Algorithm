package Day17_완전범죄;

import java.util.*;
class Solution {
    public int solution(int[][] info, int n, int m) {
        Queue<int[]>q = new ArrayDeque<>();
        boolean [][][] visited = new boolean[n+1][m+1][info.length+1];
        q.add(new int[] {0,0,0});
        visited[0][0][0] = true;
        int answer = Integer.MAX_VALUE;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            if(cur[2] == info.length) {
                answer = Math.min(answer, cur[0]);
                continue;
            }
            if(cur[0]+info[cur[2]][0]<n && !visited[cur[0]+info[cur[2]][0]][cur[1]][cur[2]+1]){
                visited[cur[0]+info[cur[2]][0]][cur[1]][cur[2]+1] = true;
                q.add(new int[] {cur[0]+info[cur[2]][0], cur[1], cur[2]+1});
            }
            if(cur[1]+info[cur[2]][1]<m&& !visited[cur[0]][cur[1]+info[cur[2]][1]][cur[2]+1]){
                visited[cur[0]][cur[1]+info[cur[2]][1]][cur[2]+1] = true;
                q.add(new int[] {cur[0],cur[1]+info[cur[2]][1], cur[2]+1});
            }
        }
        return answer == Integer.MAX_VALUE ? -1 : answer;
        
    }
}
