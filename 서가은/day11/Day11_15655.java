package 서가은.day11;

import java.util.*;
import java.io.*;

public class Day11_15655 {

	static int N;
	static int M;
	static ArrayList<Integer> arr = new ArrayList<>();
	static boolean[] visited;
	
	static StringBuilder result = new StringBuilder();
	static int[] path;
	
	static void DFS(int node, boolean[] visited, int cnt) {
		if(cnt == M) {
			for(int i=0; i<M; i++) {
				result.append(path[i]);
				result.append(" ");
			}
			
			result.append("\n");
			return;
			
//			System.out.println(text);
//			return;
		}
		
		for(int i=node; i<arr.size(); i++){
			if(visited[i]==false) {
				visited[i]=true;
				path[cnt]=arr.get(i);
				DFS(i,visited,cnt+1);
				visited[i]=false;
			}
		}
		
		
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		StringTokenizer st = new StringTokenizer(line);
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		line = br.readLine();
		st = new StringTokenizer(line);

		for (int i = 0; i < N; i++) {
			arr.add(Integer.parseInt(st.nextToken()));
		}
		
		arr.sort(null);
//		System.out.println(arr.toString());
		//구현 시작
		path = new int[N];
		visited = new boolean[N+1];
		DFS(0,visited,0);
		
		System.out.println(result.toString());
		

	}

}
