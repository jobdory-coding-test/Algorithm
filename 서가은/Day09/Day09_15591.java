package 서가은.day09;

import java.io.*;
import java.util.*;

public class Day09_15591 {

	// 끝 인덱스와 USADO를 담는 노드
	public static class Node {
		private int index;
		private int USADO;

		public Node(int q, int r) {
			// TODO Auto-generated constructor stub
			this.index = q;
			this.USADO = r;
		}
	}

	// 인접리스트 (근데 이제 노드에 USADO를 곁들인...)
	public static class ListGraph {
		public static ArrayList<ArrayList<Node>> listGraph;

		public ListGraph(int intSize) {
			this.listGraph = new ArrayList<>();

			for (int i = 0; i < intSize + 1; i++) {
				listGraph.add(new ArrayList<Node>());
			}
		}

		public void put(int p, int q, int r) {
			listGraph.get(p).add(new Node(q, r)); // 무방향 그래프라 양쪽 다..
			listGraph.get(q).add(new Node(p, r));
		}

		public static void printGraph() { // 확인용
			for (int i = 1; i < listGraph.size(); i++) {
				System.out.println(i + " 이웃들");
				for (int j = 0; j < listGraph.get(i).size(); j++) {
					System.out.println("->" + listGraph.get(i).get(j).index);
				}
				System.out.println();
			}
		}

		public ArrayList<Node> get(int v) {
			return listGraph.get(v);
		}

	}

	// USADO가 min이었음...
	// DFS 돌려서 비어있는 부분 봐야함
	// 트리구조를 순회하는 방식을 이번 문제에 활용하자
	static int dfs(ListGraph graph, int v, int k, boolean[] visited) {
		int count = 0;
		visited[v] = true;
		for (Node item : graph.get(v)) {
			if (!visited[item.index] && item.USADO >= k) {
				count++;
				count += dfs(graph, item.index, k, visited);
			}
		}
		return count;
	}

	public static void main(String[] args) throws IOException {

		// [입력]
		//
		// <첫 번째 줄>
		// int N, Q :
		//
		// <~N-1 줄> 두 동영상 쌍의 USADO
		// int p, q, r : p와 q가 USADO r로 서로 연결되어있음
		//
		// <Q개의 줄> 농부 존's Q개의 질문
		// int k, v : i번째 질문이 만약 K=k(i)라면, 동영상 v(i)를 보고 있는 소들에게 몇 개의 동영상이 추천될 지 묻는 것

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		StringTokenizer st = new StringTokenizer(line);

		// <첫 번째 줄>
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());

		ListGraph neiborHood = new ListGraph(N);

		// <두 번째~N 줄>
		for (int i = 0; i < N - 1; i++) {
			line = br.readLine();
			st = new StringTokenizer(line);
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());

			neiborHood.put(p, q, r);

		}

		// neiborHood.printGraph();
		StringBuffer sb = new StringBuffer();

		// <Q개의 줄> 농부 존's Q개의 질문
		for (int j = 0; j < Q; j++) {
			line = br.readLine();
			st = new StringTokenizer(line);
			int k = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			boolean[] visited = new boolean[N + 1];

			// dfs메서드 호출로 result카운트
			int result = dfs(neiborHood, v, k, visited);
			sb.append(result + "\n");
		}

		String strResult = sb.toString();
		System.out.println(strResult);
	}
}
