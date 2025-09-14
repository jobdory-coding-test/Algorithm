import java.util.*;
class Day21_석유시추 {
    
    static int[][] arr;
    static int N, M, result;
    static boolean[][] visited;
    static int[] rankOfC;
    static Queue<int[]> q;
    
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    
    public int solution(int[][] land) {
        arr = land;
        N = land.length; M = land[0].length;
        visited = new boolean[N][M];
        rankOfC = new int[M];
        q = new ArrayDeque<>();
        result = Integer.MIN_VALUE;
        
        for(int r=0; r<N; r++){
            for(int c=0; c<M; c++){
                if(land[r][c]==0 || visited[r][c]) continue;
                makegroup(r, c);
            }
        }
        
        return result;
    }
    
    static void makegroup(int r, int c){
        int count=1, minC=c, maxC=c, nr, nc;
        int[] loc;
        
        q.clear();
        q.offer(new int[]{r, c});
        visited[r][c]=true;
        
        while(!q.isEmpty()){
            loc = q.poll();
            for(int d=0; d<dr.length; d++){
                nr = loc[0]+dr[d];
                nc = loc[1]+dc[d];
                if(!isValid(nr, nc)) continue;
                
                q.offer(new int[] {nr, nc});
                visited[nr][nc]=true;
                count++;
                
                minC = Math.min(minC, nc);
                maxC = Math.max(maxC, nc);
            }
        }
        
        for(nc=minC; nc<=maxC; nc++){
            rankOfC[nc]+= count;
            result = Math.max(rankOfC[nc], result);
        }
        
    }
    
    static boolean isValid(int r, int c){
        return r>=0 && r<N && c>=0 && c<M
            && arr[r][c]==1 && !visited[r][c];
    }
}