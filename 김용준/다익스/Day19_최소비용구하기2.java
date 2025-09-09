package 다익스;
import java.util.*;
import java.io.*;

public class Day19_최소비용구하기2 {
    static int N,M,start,end;
    static int[] dist;
    static int[] prev;
    static List<int[]> []map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        map = new ArrayList[N];
        for(int i=0;i<N;i++){
            map[i] = new ArrayList<>();
        }
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int index = Integer.parseInt(st.nextToken())-1;
            int go = Integer.parseInt(st.nextToken())-1;
            int path = Integer.parseInt(st.nextToken());
            map[index].add(new int[] {go,path});
        }
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken())-1;
        end = Integer.parseInt(st.nextToken())-1;

        dist = new int[N];
        Arrays.fill(dist,Integer.MAX_VALUE);
        prev = new int[N];
        Arrays.fill(prev,-1);

        
        dij(start);
        StringBuilder sb = new StringBuilder();
        sb.append(dist[end]).append("\n");
        List<Integer> answer = new ArrayList<>();
        int aa = end;
        while(aa != -1){
            answer.add(aa+1);
            aa = prev[aa];
        }
        Collections.reverse(answer);
        sb.append(answer.size()).append("\n");
        for(int a : answer){
            sb.append(a).append(" ");
        }
        System.out.println(sb);
    }
    public static void dij(int start){
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> Integer.compare(a[1],b[1]));
        dist[start] = 0;
        pq.add(new int[]{start,0});
        while(!pq.isEmpty()){
            int [] cur = pq.poll();

            if(cur[1] > dist[cur[0]]) continue;

            for(int [] next : map[cur[0]]){
                if(dist[next[0]] > next[1]+dist[cur[0]]){
                    dist[next[0]] = next[1]+dist[cur[0]];
                    prev[next[0]] = cur[0];
                    pq.add(new int[]{next[0], dist[next[0]]});
                }
            }
        }
    }

}
