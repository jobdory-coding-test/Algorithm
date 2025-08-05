import java.util.*;
import java.io.*;

public class Main {
	private static int N, M;
	private static long totalWeight;
	private static int[] parent;
	private static int[] rank;
	private static Edge[] edgeArr;
	
	private static class Edge implements Comparable<Edge> {
		int weight;
		int node1;
		int node2;
		
		public Edge(int weight, int node1, int node2) {
			this.weight = weight;
			this.node1 = node1;
			this.node2 = node2;
		}
		
		@Override
		public int compareTo(Edge e) {
			return this.weight - e.weight;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parent = new int[N];
		rank = new int[N];
		edgeArr = new Edge[M];
		
		// 노드의 부모노드를 본인으로 초기화
		for(int i=0; i<N; i++) {
			parent[i] = i;
			rank[i] = 1; // 본인 이하의 트리 size
		}
		
		// 간선 정보 입력
		totalWeight = 0;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken()) - 1;
			int n2 = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			totalWeight += w;
			
			edgeArr[i] = new Edge(w, n1, n2);
		}
		
		// 가중치 기준 오름차순 정렬
		Arrays.sort(edgeArr);
		
		// union-find 방식으로 간선 선택 및 연결
		unionFind();
	}
	
	private static void unionFind() {
		long weightSum = 0;
		int edgeCnt = 0;
		
		// 정렬된 간선을 순회하며 연결되지 않은 노드들이라면 연결 (가중치 더하기)
		for(int i=0; i<M; i++) {
			int weight = edgeArr[i].weight;
			int node1 = edgeArr[i].node1;
			int node2 = edgeArr[i].node2;
			
			// 두 노드의 부모가 다르다면 -> 연결 안된 상태 -> 연결
			if(!isSame(node1, node2)) {
				weightSum += weight;
				union(node1, node2);
				edgeCnt++;
			}
		}
		
		// 모든 건물이 연결되어있는지 체크
		if(edgeCnt != N-1) System.out.print(-1);
		else System.out.print(totalWeight - weightSum);
	}
	
	// 최상위 부모 조회
	private static int find(int node) {
		// node의 부모가 node일 경우 최상위 노드
		if(parent[node] == node) {
			return node;
		}
		else return parent[node] = find(parent[node]);
	}
	
	// 두 노드를 연결
	private static void union(int node1, int node2) {
		node1 = find(node1);
		node2 = find(node2);
		
		// node1의 트리 크기가 node2의 크기보다 작다면
		// node2의 루트에 node1을 붙임
		if(rank[node1] < rank[node2]) {
			int tmp = node1;
			node1 = node2;
			node2 = tmp;
		}
		
		// size가 더 작은 노드(node2)의 부모는 사이즈가 더 큰 노드(node1)
		parent[node2] = node1;
		rank[node1] += rank[node2];
	}
	
	// 두 노드가 연결된 노드인지 확인
	private static boolean isSame(int node1, int node2) {
		if(find(node1) == find(node2)) return true;
		else return false;
	}
}
