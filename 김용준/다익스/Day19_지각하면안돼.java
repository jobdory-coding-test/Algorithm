package 다익스;
import java.util.*;
import java.io.*;

public class Day19_지각하면안돼 {
    static int N,T,M,L;
    static List<int[]> [] map;
    static int[] dist;
    static int[] money;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());

        map = new ArrayList[N];
        for(int i=0;i<N;i++){
            map[i] = new ArrayList<>();
        }
        for(int i=0;i<L;i++){
            st = new StringTokenizer(br.readLine());
            int index = Integer.parseInt(st.nextToken())-1;
            int end = Integer.parseInt(st.nextToken())-1;
            int time = Integer.parseInt(st.nextToken());
            int pay = Integer.parseInt(st.nextToken());
            map[index].add(new int[] {end,time,pay});
            map[end].add(new int[] {index,time,pay});
        }
        dist = new int[N];
        money = new int[N];
        Arrays.fill(dist,Integer.MAX_VALUE);
        Arrays.fill(money,Integer.MAX_VALUE);

        dij(0,0,0);
        System.out.println(Arrays.toString(dist));
        System.out.println(Arrays.toString(money));

        System.out.println(money[N-1]);
    }
    public static void dij(int start,int path, int pay){
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->Integer.compare(a[2],b[2]));
        pq.add(new int[] {start,path,pay});
        dist[start] = 0;
        money[start] = 0;
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            if(money[cur[0]] < cur[2]) continue;

            for(int[] next : map[cur[0]]){
                if(money[next[0]] > next[2]+money[cur[0]] && next[2]+money[cur[0]]<=M && next[1]+dist[cur[0]]<=T){
                    money[next[0]] = next[2]+money[cur[0]];
                    dist[next[0]] = next[1]+dist[cur[0]];
                    pq.add(new int[] {next[0],dist[next[0]],money[next[0]]});
                }
            }
        }
    }
}
