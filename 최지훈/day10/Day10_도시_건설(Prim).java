import java.util.*;
import java.io.*;

public class Main {
	private static int N, M;
	private static long totalWeight;
	private static List<Edge>[] edgeList;
	
	private static class Edge {
		// 연결된 노드와 가중치
		int weight;
		int node;
		
		public Edge(int weight, int node) {
			this.weight = weight;
			this.node = node;
		}
 	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		edgeList = new ArrayList[N]; // 0번부터 N-1번 노드들에 붙어있는 노드 리스트
		for(int i=0; i<N; i++) edgeList[i] = new ArrayList<>();
		
		// 간선 정보 입력
		totalWeight = 0;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken()) - 1;
			int n2 = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			totalWeight += w;
			
			edgeList[n1].add(new Edge(w, n2));
			edgeList[n2].add(new Edge(w, n1));
		}
		
		// prim 방식으로 간선 선택 및 연결
		prim();
	}
	
	// 1. 임의의 노드부터 시작해서 갈 수 있는 노드를 우선 순위 큐에 삽입
	// 2. 우선 순위 큐의 조건은 가중치 크기 오름차순
	// 3. top 꺼내고 연결 가능하면 삽입
	private static void prim() {
		PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
		boolean[] visited = new boolean[N];
		
		int visitCnt = 1;
		long weightSum = 0;
		for(Edge e : edgeList[0]) {
			pq.offer(e);
		}
		visited[0] = true;
		
		while(!pq.isEmpty()) {
			Edge top = pq.poll();
			int toEdge = top.node;
			int weight = top.weight;
			
			// 이미 추가한 노드라면 패스
			if(visited[toEdge]) continue;
			
			// 다음 연결할 노드 방문 처리 후, 해당 노드에 연결된 노드들을 pq에 추가
			visited[toEdge] = true;
			visitCnt++;
			weightSum += weight;
			for(Edge e : edgeList[toEdge]) {
				// 방문하지 않은 노드만 추가
				if(!visited[e.node]) {
					pq.offer(e);
				}
			}
		}
		
		if(visitCnt != N) System.out.print(-1);
		else System.out.print(totalWeight - weightSum);
	}
}
