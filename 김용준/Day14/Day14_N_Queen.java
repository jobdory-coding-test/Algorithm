package Day14;
import java.util.*;
import java.io.*;

public class Day14_N_Queen {
    static int N;
    static int [][]n;
    static boolean [][]visited;
    static int count;
    public static void main(String[] agrs)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        n = new int[N][N];
        count = 0;
        backtrack(0);
        System.out.println(count);
    }
    public static void backtrack(int cnt){
        if(cnt == N){
            count++;
            return;
        }
        for(int i=0;i<N;i++){
            boolean flag = check(cnt,i);
            if(flag){
                n[cnt][i] = 1;
                backtrack(cnt+1);
                n[cnt][i] = 0;
            }
            
        }
    }
    public static boolean check(int cnt, int m){
        for(int k=1;k<=cnt;k++){
            if(n[cnt-k][m] == 1)return false;
            if(m-k>=0 &&n[cnt-k][m-k] == 1)return false;
            if(m+k <N && n[cnt-k][m+k] == 1) return false;
        }
        return true;
    }
}
