import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Day11_우주신과의교감 {
	
	private static int N, M;
	private static Node[] nodes;
	private static int[] parent;
	private static int[] rank;
	private static Queue<Edge> pq;
	private static List<Edge> list;
	private static int edgeCnt;
	
	public static class Node{
		int x, y;
		int idx;
	}
	
	public static class Edge implements Comparable<Edge>{
		Node endNode1, endNode2;
		double weight;
		Edge(Node endNode1, Node endNode2, double weight){
			this.endNode1 = endNode1;
			this.endNode2 = endNode2;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge e) {
			return Double.compare(this.weight, e.weight);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parent = new int[N+1];
		rank = new int[N+1];
		for(int i=1; i<=N; i++) {
			parent[i] = i;
			rank[i] = 1;
		}
		
		// N개 노드 입력
		nodes = new Node[N+1];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			nodes[i] = new Node();
			
			nodes[i].idx = i;
			nodes[i].x = Integer.parseInt(st.nextToken());
			nodes[i].y = Integer.parseInt(st.nextToken());
		}
		
		// M개 간선 입력
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			// 추가된 간선 입력 받으면서 동시에 parent 노드들 갱신
			if(find(from) != find(to)) {
				union(from, to);
				edgeCnt++;
			}
		}
		
		// 노드 기준으로 모든 간선 생성
		pq = new PriorityQueue<>();
//		list = new ArrayList<>();
		for(int i=1; i<N; i++) {
			Node n1 = nodes[i];
			for(int j=i+1; j<=N; j++) {
				Node n2 = nodes[j];
				
				// 두 노드가 이미 입력받은 간선이 아니라면 추가
				if(find(i) != find(j)) {
					pq.add(new Edge(n1, n2, calcW(n1, n2)));
//					list.add(new Edge(n1, n2, calcW(n1, n2)));
				}
			}
		}
		
		System.out.printf("%.2f\n", kruskal());
	}
	
	private static double kruskal() {
		double edgeLength=0;
		
		while(!pq.isEmpty()) {
//			System.out.printf("[%f] pq.size: %d\n", edgeLength, pq.size());
			Edge e = pq.poll();
			int pn1 = find(e.endNode1.idx);
			int pn2 = find(e.endNode2.idx);
//			System.out.printf("   pn1: %d, pn2: %d\n", pn1, pn2);
			
			// 새로 연결해야 하는 노드일 때
			if(pn1 != pn2) {
				union(pn1, pn2);
				edgeLength += e.weight;
				edgeCnt++;
			}
			
			if(edgeCnt >= N-1) break;
		}
		
		return edgeLength;
	}
	
	private static int find(int node) {
		if(parent[node] == node) {
			return node;
		}
		return parent[node] = find(parent[node]);
	}
	
	private static void union(int n1, int n2) {
		int pn1 = find(n1);
		int pn2 = find(n2);
		
		// pn1이 더 큰 트리의 부모 : pn1으로 합치기
		if(rank[pn1] < rank[pn2]) {
			int temp = pn1;
			pn1 = pn2;
			pn2 = temp;
		}
		
		parent[pn2] = pn1;
		rank[pn1]+= rank[pn2];
	}
	
	private static double calcW(Node n1, Node n2) {
		return Math.sqrt(Math.pow(n1.x-n2.x, 2) + Math.pow(n1.y-n2.y, 2));
	}

}
