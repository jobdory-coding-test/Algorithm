package 서가은.day11;
import java.io.*;
import java.util.*;

public class Day11_15650 {
	static int N;
	static int M; 

	//자 이제 이걸 StringBuilder로 바꿔보자...
	static StringBuilder result = new StringBuilder();
	static int[] path; //왜 int일까? 경로저장까지만 알아먹음 -> 애초에 순열이 int자늠..
	
	static void dfs(int node, int cnt, boolean[] visited){
		
		
		//dfs중단점 - cnt가 원하는 깊이랑 같아지면 경로 출력하고 빠져나오기
		if(cnt==M) {
			
			//일단 배열에 담기
			for(int i=0; i<M;i++) {
				result.append(path[i]);
				if(i<M-1)result.append(" ");				
			}
			result.append('\n');
			return;
		}
		
		//아니면 자식 노드들 dfs순회 (중복이 안됨, 따라서 node부터 시작해야함)
		for(int i=node; i<N+1; i++) {
			path[cnt]=i;
			//자기 자신이 아니고 방문 안했으면 
			if(i!=node && visited[i]==false) {
				visited[node]=true; //방문처리 
				dfs(i,cnt+1,visited); //dfs 실행
				visited[node]=false; // 다음 턴을 위한 점유 해제
			}
		}
	}
	
	
	public static void main (String[] args) throws IOException {
		//[입력]
		//		<Line 1>
		//		int N : 1부터 N까지
		//		int M : 중복없이 M개
		//
		//		(1<= N,M <=8)		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		StringTokenizer st = new StringTokenizer(line);
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		path = new int[M];
		
		//[구현]
		boolean visited[] = new boolean[N+1];
		dfs(0,0,visited);
		System.out.println(result.toString());
	}
}
