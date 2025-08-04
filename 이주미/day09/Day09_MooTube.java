
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Day09_MooTube {

    private static int N, Q;
    private static int[][] arr;
    private static boolean[] visited;
    private static Queue<Integer> queue;

    // 2차 구현
//    private static List<int[]>[] graph; // 제네릭에 배열 넣지 말라고 했어...
    private static List<Edge>[] graph;

    static class Edge {
        int node, weight;
        public Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        arr = new int[N+1][N+1];
        graph = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

//            arr[r][c] = w;
//            arr[c][r] = w;

            graph[r].add(new Edge(c, w));
            graph[c].add(new Edge(r, w));
        }

        for(int i=0; i<Q; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            sb.append(weight_edge(k, v)+"\n");
        }
        System.out.println(sb.toString());

    }

    private static int weight_edge(int k, int node) {
        visited = new boolean[N + 1];
        visited[node] = true;

        queue = new LinkedList<>();
        queue.offer(node);

        int cnt = 0;
        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (Edge edge : graph[now]) {
                // 아직 방문 안 했고 무게가 k 이상일 때
                if (!visited[edge.node] && edge.weight>=k) {
                    visited[edge.node] = true;
                    queue.offer(edge.node);
                    cnt++;
                }
            }

        }

        return cnt;
    }

    private static int weight(int k, int node) {
        visited = new boolean[N + 1];
        visited[node] = true;

        queue = new LinkedList<>();
        queue.offer(node);

        int cnt = 0;
        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int next = 1; next <= N; next++) {
                // 아직 방문 안 했고 무게가 k 이상일 때
                if (!visited[next] && arr[now][next]>=k) {
                    visited[next] = true;
                    queue.offer(next);
                    cnt++;
                }
            }
        }

        return cnt;
    }

}
