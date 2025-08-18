package Day15;
import java.util.*;
import java.io.*;

public class Day15_홈방범서비스 {
    static int N;
    static int M;
    static int[][] n;
    static int count;
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for(int test_case=1;test_case<=T;test_case++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            n = new int[N][N];
            for(int i=0;i<N;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<N;j++){
                    n[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            count = 0;
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    find(i,j);
                }
            }
            sb.append("#").append(test_case).append(" ").append(count).append("\n");
        }
        System.out.println(sb);
    }
    public static void find(int r, int c){
        boolean [][] range = new boolean[N][N];
        int home = 0;
        Queue<int[]>q = new ArrayDeque<>();
        q.add(new int[]{r,c});
        range[r][c] = true;
        if(n[r][c] == 1) home++;
        for(int k=1;k<=45;k++){
            if(k*k+(k-1)*(k-1) <= M*home) count = Math.max(home,count);
            int s = q.size();
            for(int i=0;i<s;i++){
                int []cur = q.poll();
                for(int d=0;d<4;d++){
                    int rr = cur[0]+dr[d];
                    int cc = cur[1]+dc[d];
                    if(check(rr,cc) && !range[rr][cc]){
                        range[rr][cc] = true;
                        q.add(new int[]{rr,cc});
                        if(n[rr][cc] == 1)home++;
                    }
                }
            }
        }
    }
    public static boolean check(int r, int c){
        return r>=0 && c>=0 && r<N && c<N;
    }
}