package 서가은.day11;

import java.util.*;
import java.io.*;

public class Day11_15652 {
	
	static int N;
	static int M;
//	static boolean[] visited;
	
	static int[] path;
	static StringBuilder result = new StringBuilder(); 
	
	
	static void dfs(int v, boolean[] visited, int cnt) {
		//중단점
		if(cnt == M) {
//			System.out.println(text);
//			return;			
			for(int i=0; i<M; i++) {
				result.append(path[i]);
				result.append(" ");
			}
			result.append("\n");
			return;

		}
		
		//반복문
		for(int i=v; i<N+1;i++) {
				path[cnt]=i;
				dfs(i,visited, cnt+1);
		}
	}
	
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		StringTokenizer st = new StringTokenizer(line);
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		path=new int[M];
		boolean[] visited = new boolean[N+1];
		dfs(1, visited,0); 			
		
		System.out.println(result.toString());
		
	}

}
