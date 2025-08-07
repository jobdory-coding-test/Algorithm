package Day12;
import java.io.*;
import java.util.*;
public class Day12_안전영역 {
    static int N;
    static int [][]n;
    static int [][] temp;
    static boolean[][] visited;
    static int count;
    static int [] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};
    static int hi;
    static int low;
   public static void main(String [] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        n = new int[N][N];
        hi = 1;
        low = 100;

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                n[i][j] = Integer.parseInt(st.nextToken());
                if(n[i][j] >hi) hi = n[i][j];
                if(n[i][j] <low) low = n[i][j];
            }
        }
        count = 1;
        for(int t=low;t<hi;t++){
            int temp_count = 0;
            visited = new boolean[N][N];
            temp = new int[N][N];
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(n[i][j] <= t)temp[i][j] = 1;
                }
            }
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(!visited[i][j] && temp[i][j] == 0){
                        bfs(i,j);
                        temp_count++;
                    }
                }
            }
            if(temp_count > count) count = temp_count;
        }

        System.out.println(count);
   }

   public static void bfs(int r, int c){
    Queue<int[]> q = new ArrayDeque<>();
    visited[r][c] = true;
    q.add(new int[] {r,c});
    while(!q.isEmpty()){
        int[] cur = q.remove();
        int rr = cur[0];
        int cc = cur[1];
        for(int d=0;d<4;d++){
            int ddr = rr+dr[d];
            int ddc = cc+dc[d];
            if(check(ddr,ddc) && !visited[ddr][ddc] && temp[ddr][ddc] == 0){
                q.add(new int[] {ddr,ddc});
                visited[ddr][ddc] = true;
            }
        }
    }
   }
   public static boolean check(int r, int c){
    return r>=0 && r<N && c>=0 && c<N;
   }
}
