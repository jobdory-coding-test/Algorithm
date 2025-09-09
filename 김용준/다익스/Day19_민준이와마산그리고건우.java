package 다익스;
import java.util.*;
import java.io.*;

public class Day19_민준이와마산그리고건우 {
    static int V,E,P;
    static List<int[]> [] map;
    static List<Integer>[] prev;
    static int[] dist;
    static boolean flag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken())-1;

        map = new ArrayList[V];
        for(int i=0;i<V;i++){
            map[i] = new ArrayList<>();
        }
        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            int index = Integer.parseInt(st.nextToken())-1;
            int end = Integer.parseInt(st.nextToken())-1;
            int path = Integer.parseInt(st.nextToken());

            map[index].add(new int[] {end,path});
            map[end].add(new int[] {index,path});
        }
        dist = new int[V];
        prev = new ArrayList[V];
        for(int i=0;i<V;i++){
            prev[i] = new ArrayList<>();
        }
        Arrays.fill(dist,Integer.MAX_VALUE);
        dij(0,0);
        check(V-1);


        if(flag) System.out.println("SAVE HIM");
        else System.out.println("GOOD BYE");
    }
    public static void check(int now){
        if(now == P) flag = true;
        if(now == 0) return;
        for(int pp : prev[now]){
            check(pp);
        }
    }
    public static void dij(int start, int path){
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->Integer.compare(a[1],b[1]));
        pq.add(new int[] {start,path});
        dist[start] = 0;
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            if(cur[1] > dist[cur[0]]) continue;
            for(int[] next: map[cur[0]]){
                if(dist[next[0]] > next[1]+dist[cur[0]]){
                    dist[next[0]] = next[1]+dist[cur[0]];
                    prev[next[0]].clear();
                    prev[next[0]].add(cur[0]);
                    pq.add(new int[] {next[0],dist[next[0]]});
                }
                else if(dist[next[0]] == next[1]+dist[cur[0]]){
                    prev[next[0]].add(cur[0]);
                }
            }
        }
    }
}
