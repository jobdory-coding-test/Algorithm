package 서가은.day11;

import java.util.*;
import java.io.*;

public class Day11_15655 {

	static int N;
	static int M;
	static ArrayList<Integer> arr = new ArrayList<>();
	static boolean[] visited;
	
	static void DFS(int node, boolean[] visited, String text, int cnt) {
//		System.out.println("node "+arr.get(node)+"text "+text+"cnt "+cnt);
		//중단점 만들어
		if(cnt == M) {
			System.out.println(text);
			return;
		}
		
		for(int i=node; i<arr.size(); i++){
			if(visited[i]==false) {
				visited[i]=true;
				DFS(i,visited,text+arr.get(i)+" ",cnt+1);
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
		visited = new boolean[N+1];
		DFS(0,visited,"",0);
		
		

	}

}
