import java.util.*;
import java.io.*;

public class Main {
	private static int movieNum;
	private static int questionNum;
	private static List<List<int[]>> movieList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		movieNum = Integer.parseInt(st.nextToken());
		questionNum = Integer.parseInt(st.nextToken());
		movieList = new ArrayList<>();
		
		// movieList의 크기를 movieNum+1 만큼 초기화
		for(int i=0; i<=movieNum; i++) {
			movieList.add(new ArrayList<>());
		}
		
		for(int i=0; i<movieNum-1; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			int edge = Integer.parseInt(st.nextToken());
			
			// node1과 node2에 대한 연결 정보
			movieList.get(node1).add(new int[] {node2, edge});
			movieList.get(node2).add(new int[] {node1, edge});
		}
		
		for(int q=0; q<questionNum; q++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			sb.append(bfs(k, v)).append("\n");
		}
		
		System.out.println(sb);
	}
	
	// videoNum에서 연결된 정점을 확인하며 usadoK보다 큰 정점들만 카운팅
	private static int bfs(int usadoK, int videoNum) {
		int resultCnt = 0;
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[movieNum+1];
		
		visited[videoNum] = true;
		q.add(videoNum);
		
		while(!q.isEmpty()) {
			int node = q.poll();
			
			for(int[] nodeInfo : movieList.get(node)) {
				int nextNode = nodeInfo[0];
				int edgeValue = nodeInfo[1];
				
				if(edgeValue < usadoK || visited[nextNode]) continue;
				
				q.add(nextNode);
				resultCnt++;
				visited[nextNode] = true;
			}
		}
		
		return resultCnt;
	}
}
