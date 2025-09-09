package 다익스;
import java.util.*;
import java.io.*;


public class Day19_지하철 {
    static int N,M;
    static List<int[]>[] map;
    static int[] company;
    static int[] dist;
    static int[]change;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new ArrayList[N];
        company = new int[N];
        for(int i=0;i<N;i++){
            map[i] = new ArrayList<>();
        }
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            company[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                int path = Integer.parseInt(st.nextToken());
                if(path != 0){
                    map[i].add(new int[] {j,path});
                    map[j].add(new int[] {i,path});
                }
            }
        }
        dist = new int[N];
        change = new int[N];
        Arrays.fill(dist,Integer.MAX_VALUE);
        Arrays.fill(change,Integer.MAX_VALUE);
        dij(0,0,0);
        System.out.println(Arrays.toString(dist));
        System.out.println(Arrays.toString(change));
        System.out.println(change[M]+" "+dist[M]);
    }
    public static void dij(int start, int path, int c){
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->Integer.compare(a[2],b[2]));
        pq.add(new int[] {start,path,c});
        dist[start] = 0;
        change[start] = 0;
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            if(cur[1]>dist[cur[0]]) continue;
            if(cur[2] > change[cur[0]]) continue;
            for(int[] next : map[cur[0]]){
                if(dist[next[0]] > next[1]+dist[cur[0]])
                {
                    if(company[next[0]]== company[cur[0]] && change[next[0]] >= cur[2]){
                        dist[next[0]] = next[1]+dist[cur[0]];
                        change[next[0]] = cur[2];
                        pq.add(new int[] {next[0],dist[next[0]],cur[2]});
                    }

                    else if(company[next[0]]!= company[cur[0]] && change[next[0]] >= cur[2]+1){
                        dist[next[0]] = next[1]+dist[cur[0]];
                        change[next[0]] = cur[2]+1;
                        pq.add(new int[] {next[0],dist[next[0]],cur[2]+1});
                    }
                }           
             }
        }
    }
}
