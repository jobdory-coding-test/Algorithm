import java.util.*;

class Day21_석유시추 {
    static boolean[][] visited;
    static int R, C;
    static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
    static HashMap<Integer, Integer> cntMap;
    
    public int solution(int[][] land) {
        int answer = 0;
        
        R = land.length;
        C = land[0].length;
        
        visited = new boolean[R][C];
        cntMap = new HashMap<>();
        
        // 석유 구간 나누기.
        int cnt = 1;
        for(int r = 0; r<R; r++) {
            for(int c = 0; c<C; c++) {
                if(land[r][c] != 0 && !visited[r][c]) {
                    bfs(r, c, land, cnt);
                    cnt++;
                }
            }
        }
        
        // 열마다 석유 누적값 구하고 최대값 갱신하기.
        for(int c = 0; c<C; c++) {
            boolean[] find = new boolean[cnt];
            int sum = 0;
            for(int r = 0; r<R; r++) {
                if(land[r][c] != 0 && !find[land[r][c]]) {
                    sum += cntMap.get(land[r][c]);
                    find[land[r][c]] = true;
                }
            }
            
            answer = Math.max(answer, sum);
        }
        
        return answer;
    }
    
    private static void bfs(int r, int c, int[][] land, int cnt) {
        Queue<int[]> queue = new ArrayDeque<>();
        
        cntMap.put(cnt, 0);
        
        queue.offer(new int[] {r, c});
        visited[r][c] = true;
        
        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            
            land[now[0]][now[1]] = cnt;
            cntMap.put(cnt , cntMap.get(cnt)+1);
            
            for(int d = 0; d<4; d++) {
                int nr = now[0] + dir[d][0];
                int nc = now[1] + dir[d][1];
                
                if(!isRange(nr, nc) || visited[nr][nc]) continue;
                if(land[nr][nc] == 0) {
                    visited[nr][nc] = true;
                    continue;
                }
                
                queue.offer(new int[] {nr, nc});
                visited[nr][nc] = true;
            }
        }
    }
    
    private static boolean isRange(int nr, int nc) {
        return 0 <= nr && nr < R && 0 <= nc && nc < C;
    }
}