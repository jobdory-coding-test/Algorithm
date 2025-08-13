package Day14;
import java.util.*;
import java.io.*;

public class Day14_줄기세포배양 {
    static class Node{
        int life;
        int delay;
        int origin;
        boolean flag;
        
        Node(int life, int delay, int origin, boolean flag){
            this.life = life;
            this.delay = delay;
            this.origin = origin;
            this.flag = flag;
        }
    }
    static int N,M,K;
    static Node [][] list;
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};
    static int startn;
    static int startm;
    static int endn;
    static int endm;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for(int test_case = 1;test_case<=T;test_case++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            list = new Node[2*K+N][2*K+M];
            for(int i=0;i<N;i++){  //한가운데에 넣기
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<M;j++){
                    int temp = Integer.parseInt(st.nextToken());
                    if(temp != 0) list[K+i][K+j] = new Node(temp, temp, temp, false);
                }
            }
            startn = K;
            startm = K;
            endn = K+N;
            endm = K+M;
            
            for(int time=0;time<K;time++){
                fix();
                for(int i=startn;i<endn;i++){
                    for(int j=startm;j<endm;j++){
                        if(list[i][j] == null || list[i][j].life == 0 || !list[i][j].flag) continue;
                        if(list[i][j].delay == 0){
                            list[i][j].life--;
                            spread(i,j,list[i][j].origin);
                        }else list[i][j].delay--;
                    }
                }
            }

            int count=check();
            sb.append("#").append(test_case).append(" ").append(count).append("\n");
        }
        System.out.println(sb);
    }
    public static void fix(){
        for(Node[] a : list){
            for(Node cell : a){
                if(cell !=null && !cell.flag ) cell.flag = true;
            }
        }
    }
    public static void spread(int r, int c, int m){
        for(int d=0;d<4;d++){
            int rr = r+dr[d];
            int cc = c+dc[d];
            if(list[rr][cc] == null){
                list[rr][cc] = new Node(m, m, m,false);
                if(rr < startn) startn--;
                else if(rr >= endn) endn++;
                if(cc < startm) startm--;
                else if(cc >= endm) endm++;
            }
            else if(!list[rr][cc].flag && list[rr][cc].origin < m){
                list[rr][cc] = new Node(m, m, m,false);
            }

        }
    }
    public static int check(){
        int count = 0;
        for(Node[] a : list){
            for(Node cell : a){
                if(cell !=null && cell.life !=0 ) count++;
            }
        }
        return count;
    }
}
