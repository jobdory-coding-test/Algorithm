package Day13;

import java.io.*;
import java.util.*;

public class Day13_핀볼게임 {
    static int N;
    static int [][]n;
    static int []dr = {-1,0,1,0};
    static int []dc = {0,1,0,-1};
    static int[] start;
    static int count;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for(int test_case = 1;test_case<=T;test_case++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            n = new int[N][N];
            start = new int[2];
            for(int i=0;i<N;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<N;j++){
                    n[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            count =0;
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    for(int k=0;k<4;k++){
                        if(n[i][j] == 0){
                        start[0] = i;
                        start[1] = j;
                        trip(i,j,0,k,0);
                    }
                    }
                }
            }
            sb.append("#").append(test_case).append(" ").append(count).append("\n");
        }
        System.out.println(sb);
    }
    public static void trip(int r, int c, int cnt, int d, int flag){
        while(true){
            if(flag == 1 && ((start[0] == r && start[1] == c) || n[r][c] == -1)){
                count = Math.max(count,cnt);
                break;
            }
            flag = 1;
            int rr = r+dr[d];
            int cc = c+dc[d];
            if(rr<0 ||cc<0 || rr>=N || cc>=N){
                d = (d+2)%4;
                rr=r;
                cc=c;
                cnt++;
            }
            if(n[rr][cc] == 0){
                r = rr;
                c = cc;
            }
            else{
                switch(d){
                    case 0:
                        if(n[rr][cc] == 2){
                            d = 1;
                            cnt++;
                            r = rr;
                            c = cc;
                        }
                        else if(n[rr][cc] == 3){
                            d = 3;
                            cnt++;
                            r =rr;
                            c = cc;
                        }
                        else if(n[rr][cc] >=6 && n[rr][cc] <=10){
                            int [] m = findpotal(rr,cc,n[rr][cc]);
                            r = m[0];
                            c = m[1];
                        }
                        else if(n[rr][cc] == -1){
                            r=rr;
                            c=cc;
                        }
                        else{
                            d = (d+2)%4;
                            cnt++;
                            r = rr;
                            c = cc;
                        }
                        break;
                    case 1:
                        if(n[rr][cc] == 3){
                            d = 2;
                            cnt++;
                            r = rr;
                            c = cc;
                        }
                        else if(n[rr][cc] == 4){
                            d = 0;
                            cnt++;
                            r =rr;
                            c = cc;
                        }
                        else if(n[rr][cc] >=6 && n[rr][cc] <=10){
                            int [] m = findpotal(rr,cc,n[rr][cc]);
                            r = m[0];
                            c = m[1];
                        }
                        else if(n[rr][cc] == -1){
                            r=rr;
                            c=cc;
                        }
                        else{
                            d = (d+2)%4;
                            r = rr;
                            c = cc;
                            cnt++;
                        }
                        break;
                    case 2:
                        if(n[rr][cc] == 1){
                            d = 1;
                            cnt++;
                            r = rr;
                            c = cc;
                        }
                        else if(n[rr][cc] == 4){
                            d = 3;
                            cnt++;
                            r =rr;
                            c = cc;
                        }
                        else if(n[rr][cc] >=6 && n[rr][cc] <=10){
                            int [] m = findpotal(rr,cc,n[rr][cc]);
                            r = m[0];
                            c = m[1];
                        }
                        else if(n[rr][cc] == -1){
                            r=rr;
                            c=cc;
                        }
                        else{
                            d = (d+2)%4;
                            r = rr;
                            c = cc;
                            cnt++;
                        }
                        break;
                    case 3:
                        if(n[rr][cc] == 1){
                            d = 0;
                            cnt++;
                            r = rr;
                            c = cc;
                        }
                        else if(n[rr][cc] == 2){
                            d = 2;
                            cnt++;
                            r =rr;
                            c = cc;
                        }
                        else if(n[rr][cc] >=6 && n[rr][cc] <=10){
                            int [] m = findpotal(rr,cc,n[rr][cc]);
                            r = m[0];
                            c = m[1];
                        }
                        else if(n[rr][cc] == -1){
                            r=rr;
                            c=cc;
                        }
                        else{
                            d = (d+2)%4;
                            r = rr;
                            c = cc;
                            cnt++;
                        }
                        break;
                }
            }
        }
    }
    public static int[] findpotal(int r, int c, int m){
        int []temp = new int[2];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(n[i][j] == n[r][c] && !(i == r && j == c)){
                    temp[0] = i;
                    temp[1] = j;
                }
            }
        }
        return temp;
    }
   
}
