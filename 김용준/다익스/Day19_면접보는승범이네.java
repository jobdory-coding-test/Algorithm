package 다익스;
import java.util.*;
import java.io.*;

public class Day19_면접보는승범이네 {
    static int N,M,K;
    static int[]dist;
    static List<int[]> [] map;
   static int[] city;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new ArrayList[N];
        for(int i=0;i<N;i++){
            map[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int index = Integer.parseInt(st.nextToken())-1;
            int end = Integer.parseInt(st.nextToken())-1;
            int path = Integer.parseInt(st.nextToken());

            map[end].add(new int[] {index,path});
        }
        city = new int[K];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<K;i++){
            city[i] = Integer.parseInt(st.nextToken())-1;
        }

        dist = new int[N];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dij();

        int max_val = 0;
        int c = 1;
        for(int i=0;i<N;i++){
            if(max_val < dist[i]){
                max_val = dist[i];
                c = i;
            }
        }
        System.out.println((c+1)+"\n"+max_val);
        

    }
   public static void dij(){
    PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->Integer.compare(a[1],b[1]));
        for(int a : city){
            pq.add(new int[] {a,0});
            dist[a] = 0;
        }
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            if(cur[1] > dist[cur[0]]) continue;
            for(int [] next : map[cur[0]]){
                if(dist[next[0]] > next[1]+dist[cur[0]]){
                    dist[next[0]] = next[1]+dist[cur[0]];
                    pq.add(new int[] {next[0],dist[next[0]]});
                }
            }
        }
   }
}
