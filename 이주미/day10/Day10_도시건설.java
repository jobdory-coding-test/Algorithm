import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Day10_도시건설 {

    private static int N, M;
    private static Queue<Edge> queue;
    private static boolean[] visited;
    private static int addedNode;
    private static long totalweight;
    private static long sum;

    // 2차 구현
    private static int[] unions;
    private static int addedEdge;


    public static class Edge implements Comparable<Edge> {
        private int[] nodes = new int[2];
        private int weight;

        Edge(int[] nodes, int weight) {
            this.nodes = nodes;
            this.weight = weight;
        }

        // 쓸데없는 고민 함
        private boolean isLoop(int[] checkNode) {
            if( (checkNode[0]==this.nodes[0] && checkNode[1]==this.nodes[1])
                    || (checkNode[0]!=this.nodes[1] && checkNode[1]!=this.nodes[0])) {
                return true;
            }
            return false;
        }

        @Override
        public int compareTo(Edge e) {
            return this.weight - e.weight;
        }


    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        queue = new PriorityQueue<>();
        visited = new boolean[N+1];
        unions = new int[N+1];

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            queue.add(new Edge(new int[] {a, b}, c));
            totalweight += c;
        }

        for(int i=0; i<N+1; i++) {
            unions[i] = i;
        }

        kruskal_uf_while();

        if(addedEdge != N-1) {
            System.out.println(-1);
        } else {
            System.out.println(totalweight-sum);
        }

    }


    private static void kruskal_uf_while() {
        Edge edge;
        int[] nodes;
        while (!queue.isEmpty() && addedEdge < N - 1) {
            edge = queue.poll();
            nodes = edge.nodes;

            if (find(nodes[0]) == find(nodes[1])) continue;

            union(nodes[0], nodes[1]);
            sum += edge.weight;
            addedEdge++;
        }
    }

    private static void kruskal_uf() {
        Edge edge;
        int[] nodes;
        for(int i=0; i<M; i++) {
            edge = queue.poll();
            nodes = edge.nodes;

            if(find(nodes[0]) == find(nodes[1])) continue;

            union(nodes[0], nodes[1]);
            sum+= edge.weight;
            addedEdge++;

            if(addedEdge==N-1) break;
        }
    }

    static int find(int x) {
        if (unions[x] != x) {
            unions[x] = find(unions[x]);
        }
        return unions[x];
    }

    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) {
            unions[rootB] = rootA;
        }
    }

    /************* 1차 구현**************/

    private static void kruskal() {
        Edge edge;
        int[] nodes;
        for(int i=0; i<M; i++) {
            edge = queue.poll();
            nodes = edge.nodes;

            // 루프가 생성되는 경우
            if(visited[nodes[0]] && visited[nodes[1]]) continue;

            if(!visited[nodes[0]]) {
                visited[nodes[0]]=true;
                addedNode++;
            }
            if(!visited[nodes[1]]) {
                visited[nodes[1]]=true;
                addedNode++;
            }

            sum+= edge.weight;

            if(addedNode==N) break;
        }

    }


}
