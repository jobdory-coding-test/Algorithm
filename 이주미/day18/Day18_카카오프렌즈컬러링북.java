import java.util.*;

class Day18_카카오프렌즈컬러링북 {
    
    static int M, N;
    static boolean[][] visited;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
        
    static class Node{
        int r, c, w;
        Node(int r, int c, int w){this.r=r; this.c=c; this.w=w;}
    }
    
    public static int[] solution(int m, int n, int[][] picture) {
        M = m; N = n;
        visited = new boolean[M][N];
        
        Queue<Node> queue = new ArrayDeque<>();
        
        int numberOfArea = 0;
        int sizeOfOneArea = 0;
        int maxSizeOfOneArea = 0;
        
        for(int i=0; i<M; i++){
            for(int j=0; j<N; j++){
                if(visited[i][j] || picture[i][j]==0) continue;
                
                numberOfArea++;
                sizeOfOneArea = 1;
                
                visited[i][j]=true;
                queue.offer(new Node(i, j, picture[i][j]));
        
                int r, c, w, nr, nc;
                Node node;
                while(!queue.isEmpty()){
                    node = queue.poll();
                    r = node.r; c=node.c; w=node.w;

                    for(int d=0; d<dr.length; d++){
                        nr = r+dr[d];
                        nc = c+dc[d];
                        
                        if(!isValid(nr, nc)) continue;
                        if(picture[nr][nc]!=w) continue;
                        
                        visited[nr][nc]=true;
                        sizeOfOneArea++;
                        queue.offer(new Node(nr, nc, picture[nr][nc]));
                    }

                }
                
                maxSizeOfOneArea = Math.max(maxSizeOfOneArea, sizeOfOneArea);
            }
        }
        
        return new int[] {numberOfArea, maxSizeOfOneArea};
    }
    
    private static boolean isValid(int r, int c){
        return r>=0 && r<M && c>=0 && c<N
            && !visited[r][c];
    }

}
