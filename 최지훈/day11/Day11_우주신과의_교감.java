import java.util.*;
import java.io.*;

public class Main {
    private static int N, M;
    private static List<Node> nodeList;
    private static List<Edge> edgeList;
    private static int[] parent;
    private static int[] rank;

    private static class Node {
        long x;
        long y;

        public Node(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Edge implements Comparable<Edge> {
        double weight;
        int node1;
        int node2;

        public Edge(double weight, int node1, int node2) {
            this.weight = weight;
            this.node1 = node1;
            this.node2 = node2;
        }

        @Override
        public int compareTo(Edge e) {
            return Double.compare(this.weight, e.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nodeList = new ArrayList<>();
        edgeList = new ArrayList<>();
        parent = new int[N];
        rank = new int[N];

        // 자신의 부모 본인으로 초기화
        for(int i=0; i<N; i++) {
            parent[i] = i;
            rank[i] = 1;
        }

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());

            nodeList.add(new Node(x, y));
        }

        // 모든 간선 가중치 계산
        for(int i=0; i<N; i++) {
            for(int j=i+1; j<N; j++) {
                Node n1 = nodeList.get(i);
                Node n2 = nodeList.get(j);
                double w = Math.sqrt(Math.pow(n1.x - n2.x, 2) + Math.pow(n1.y - n2.y, 2));
                edgeList.add(new Edge(w, i, j));
            }
        }

        // 연결된 통로는 union으로 연결
        for(int j=0; j<M; j++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken()) - 1;
            int n2 = Integer.parseInt(st.nextToken()) - 1;

            if(!isSame(n1, n2)) {
                union(n1, n2);
            }
        }
        
        Collections.sort(edgeList);

        double weightSum = 0;
        for(int i=0; i<edgeList.size(); i++) {
            int n1 = edgeList.get(i).node1;
            int n2 = edgeList.get(i).node2;

            if(!isSame(n1, n2)) {
                union(n1, n2);
                weightSum += edgeList.get(i).weight;
            }
        }

        System.out.printf("%.2f", weightSum);
    }

    private static int find(int n) {
        if(parent[n] == n) {
            return n;
        }
        else return parent[n] = find(parent[n]);
    }

    private static void union(int n1, int n2) {
        n1 = find(n1);
        n2 = find(n2);

        if(rank[n1] < rank[n2]) {
            int tmp = n1;
            n1 = n2;
            n2 = tmp;
        }

        parent[n2] = n1;
        rank[n1] += rank[n2];
    }

    private static boolean isSame(int n1, int n2) {
        return find(n1) == find(n2);
    }
}
