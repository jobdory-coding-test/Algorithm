package 다익스;
import java.util.*;
import java.io.*;

public class Day19_택배배송 {
    static int N,M;
    static int[] dist;
    static List<int[]>[] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new ArrayList[N];
        for(int i=0;i<N;i++){
            map[i] = new ArrayList<>();
        }
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int index = Integer.parseInt(st.nextToken())-1;
            int end = Integer.parseInt(st.nextToken())-1;
            int path = Integer.parseInt(st.nextToken());

            map[index].add(new int[]{end,path});
            map[end].add(new int[] {index,path});
        }
        dist = new int[N];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dij(0,0);

        System.out.println(dist[N-1]);
    }
    public static void dij(int start, int path){
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->Integer.compare(a[1],b[1]));
        pq.add(new int[] {start,path});
        dist[start] = 0;
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            if(dist[cur[0]] < cur[1]) continue;

            for(int[] next : map[cur[0]]){
                if(dist[next[0]] > next[1]+dist[cur[0]]){
                    dist[next[0]] = next[1]+dist[cur[0]];
                    pq.add(new int[] {next[0],dist[next[0]]});
                }
            }
        }
    }
}
