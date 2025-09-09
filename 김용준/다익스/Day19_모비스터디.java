package 다익스;
import java.util.*;
import java.io.*;

public class Day19_모비스터디 {
    static int N,M,A,B;
    static List<int[]> [] map;
    static int[] dist;
    static List<Integer>[] prev;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken())-1;
        B = Integer.parseInt(st.nextToken())-1;

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
        dist = new int[N];
        prev = new ArrayList[N];
        for(int i=0;i<N;i++){
            prev[i] = new ArrayList<>();
        }
        Arrays.fill(dist,Integer.MAX_VALUE);
        
        dij(A,0);
        // PriorityQueue<Integer> pq = new PriorityQueue<>();
        // int now = B;
        for(int i=0;i<N;i++){
            for(int a : prev[i]){
                System.out.print(a+ " ");
            }
            System.out.println();
        }
        // while(now !=-1){
        //     if(now == A) break;
        //     pq.add(now);
            
        // }
        // pq.add(A);
        // System.out.println(pq.size());
        // StringBuilder sb = new StringBuilder();
        // while(!pq.isEmpty()){
        //     sb.append(pq.poll()+1).append(" ");
        // }
        // System.out.println(sb);
    }
    public static void dij(int start, int path){
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->Integer.compare(a[1],b[1]));
        pq.add(new int[] {start,path});
        dist[start] = 0;
        prev[start].add(-1);
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            if(dist[cur[0]] < cur[1]) continue;
            for(int[] next : map[cur[0]]){
                if(dist[next[0]] > next[1]+dist[cur[0]]){
                    dist[next[0]] = next[1]+dist[cur[0]];
                    prev[next[0]].clear();
                    prev[next[0]].add(cur[0]);
                    pq.add(new int[] {next[0],dist[next[0]]});
                }
                else if(dist[next[0]] == next[1]+dist[cur[0]]){
                    prev[next[0]].add(cur[0]);
                    pq.add(new int[] {next[0],dist[next[0]]});
                }
            }
        }
    }
}
