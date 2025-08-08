package Day12;
import java.util.*;
import java.io.*;
public class Day12_숫자만들기 {
    static int N;
    static int []op;
    static int[]num;
    static int min;
    static int max;

    public static void main(String[] args)throws IOException{
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());
       int T = Integer.parseInt(st.nextToken());
       StringBuilder sb = new StringBuilder();
       for(int test_case = 1;test_case<=T;test_case++){
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        op = new int[4];
        num = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<4;i++){
            op[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            num[i] = Integer.parseInt(st.nextToken());
        }
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
        dfs(num[0],1);
        sb.append("#").append(test_case).append(" ").append(max-min).append("\n");
       }
       System.out.println(sb);
    }
    public static void dfs(int value, int index){
        if(index == N){
            max = Math.max(value,max);
            min = Math.min(value,min);
            return;
        }
        for(int i=0;i<4;i++){
            if(op[i]>0){
                op[i]--;
                int a=0;
                switch(i){
                    case 0:
                        a = value+num[index];
                        break;
                    case 1:
                        a = value-num[index];
                        break;
                    case 2:
                        a = value*num[index];
                        break;
                    case 3:
                    if(num[index] == 0){
                        op[i]++;
                        continue;
                    }
                    a = value/num[index];
                        break;
                }
                dfs(a,index+1);
                op[i]++;
            }
        }
    }
   

}
