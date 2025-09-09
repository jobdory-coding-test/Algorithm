package 다익스;
import java.util.*;
import java.io.*;

public class Day19_야쿠르트아줌마 {
    static int V,E;
    static List<int[]> []map;
    static int[] dist;
    static int[] yo_dist;
    static int[] my_dist;
    static int[] way = new int[10];
    static int me;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
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
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<10;i++){
            way[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        me = Integer.parseInt(st.nextToken())-1;

        dist = new int[V];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dij(me,0);
        my_dist = yo_dist = Arrays.copyOf(dist, dist.length);
        //System.out.println(Arrays.toString(my_dist)+"\n");
    

        int s = 0;
        int time = 0;
        int answer = Integer.MAX_VALUE;
        for(int target : way){
            Arrays.fill(dist,Integer.MAX_VALUE);
            dij(s,0);
            
            if(dist[target-1] == Integer.MAX_VALUE) continue;
            time+=dist[target-1];
            s = target-1;
            //System.out.println(Arrays.toString(dist) + "::" + target+ "::"+time);
            if(my_dist[target-1] <= time) answer = Math.min(answer,target);
        }
        if(answer == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(answer);
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
