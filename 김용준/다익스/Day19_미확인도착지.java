package 다익스;
import java.util.*;
import java.io.*;

public class Day19_미확인도착지 {
    static int n,m,t,s,g,h;
    static List<int[]> []map;
    static int[] targets;
    static int[] dist;
    static int[] prev;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for(int test_case = 0;test_case<T;test_case++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            map = new List[n];
            for(int i=0;i<n;i++){
                map[i] = new ArrayList<>();
            }
            for(int i=0;i<m;i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken())-1;
                int b = Integer.parseInt(st.nextToken())-1;
                int path = Integer.parseInt(st.nextToken());

                map[a].add(new int[] {b,path});
                map[b].add(new int[] {a,path});
            }
            targets = new int[t];
            for(int i=0;i<t;i++){
                st = new StringTokenizer(br.readLine());
                targets[i] = Integer.parseInt(st.nextToken());
            }

            dist = new int[n];
            Arrays.fill(dist,Integer.MAX_VALUE);
            prev = new int[n];
            Arrays.fill(prev,-1);
            dij(s-1);

            PriorityQueue <Integer>pq = new PriorityQueue<>();
            for(int i=0;i<t;i++){
                int aa = targets[i];
                if(check(aa-1)){
                    pq.add(aa);
                }
            }
            while(!pq.isEmpty()) sb.append(pq.poll()).append(" ");
            sb.append("\n");
        }
        System.out.println(sb);
    }
    public static boolean check(int target){
        boolean flag1 = false;
        boolean flag2 = false;
        while(target != -1){
            if(flag1 && flag2) return true;
            if(target == g-1) flag1 = true;
            else if(target == h-1) flag2 = true;
            target = prev[target];
        }
        return false;
    }
    public static void dij(int start){
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->Integer.compare(a[1],b[1]));
        pq.add(new int[] {start,0});
        dist[start] = 0;
        while(!pq.isEmpty()){
            int[]cur = pq.poll();
            if(dist[cur[0]] < cur[1]) continue;

            for(int[] next: map[cur[0]]){
                if(dist[next[0]] > next[1]+dist[cur[0]]){
                    dist[next[0]] = next[1]+dist[cur[0]];
                    prev[next[0]] = cur[0];
                    pq.add(new int[] {next[0],dist[next[0]]});
                }
            }
        }
    }
}
