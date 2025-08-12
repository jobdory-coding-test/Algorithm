import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Day14_NQueen {

	static StringBuilder sb = new StringBuilder();
	static int N, res;
	static int[] rowVis;
	static int[] colVis;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		rowVis = new int[N+1];
		colVis = new int[N+1];
		
		runchess(1);
		
		System.out.println(res);
	}
	
	private static void runchess(int row) {
		if(row == N+1) {
			res++;
			return;
		}
		for(int i=1; i<=N; i++) {
			// 한 줄에 있을 때
			if(colVis[i]!=0) continue;
			
			// 대각선에 있을 때
			boolean isDiag = false;
			for(int j=1; j<=N; j++) {
				if(colVis[j]!=0) {
					if(Math.abs(colVis[j]-row) == Math.abs(j-i)) {
						isDiag = true;
						break;
					}
				}
			}
			if(isDiag) continue;
			
			// 추가할 수 있을 때 : DFS
			rowVis[row] = i;
			colVis[i] = row;
			runchess(row+1);
			
			rowVis[row] = 0;
			colVis[i] = 0;
		}
	}
	

}
