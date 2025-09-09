package 다익스;
import java.util.*;
import java.io.*;

public class Day19_중량제한 {
    static int N,M;
    static List<int[]>[] map;
    static int[] dist;
    static int A,B;
    static boolean [] visited;
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

            map[index].add(new int[] {end,path});
            map[end].add(new int[] {index,path});
        }
        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken())-1;
        B = Integer.parseInt(st.nextToken())-1;

        dist = new int[N];
        Arrays.fill(dist,0);
        visited = new boolean[N];
        dij(A,0);
        System.out.println(Arrays.toString(dist));
        System.out.println(dist[B]);
    }
    public static void dij(int start, int path){
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->Integer.compare(b[1],a[1]));
        pq.add(new int[] {start,path});
        dist[start] = Integer.MAX_VALUE;
        visited[start] = true;
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            
            for(int[] next: map[cur[0]]){
                if(!visited[next[0]] && dist[next[0]] < next[1]+dist[cur[0]]){
                    dist[next[0]] = next[1]+dist[cur[0]];
                    pq.add(new int[] {next[0],dist[next[0]]});
                    visited[next[0]] = true;
                }
            }
        }
    }
}
