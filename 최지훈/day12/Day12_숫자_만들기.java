import java.util.*;
import java.io.*;

public class Solution {
	private static StringBuilder sb = new StringBuilder();
	private static int N, maxCal, minCal;
	private static int[] numberArr;
	private static int[] calArr;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			numberArr = new int[N];
			calArr = new int[4];
			maxCal = Integer.MIN_VALUE;
			minCal = Integer.MAX_VALUE;
			
			// 연산자 배열 입력
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<4; i++) {
				// 0 : + / 1 : - / 2 : * / 3 : /
				calArr[i] = Integer.parseInt(st.nextToken());
			}
			
			// 숫자 배열 입력
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				numberArr[i] = Integer.parseInt(st.nextToken());
			}
			
			// 각 연산을 한번 씩 차감하며 dfs 돌기
			boolean[] visited = new boolean[N-1];
			nextPerm(0, visited, numberArr[0], calArr[0], calArr[1], calArr[2], calArr[3]);
			
			sb.append("#").append(t).append(" ").append(maxCal - minCal).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	private static void nextPerm(int count, boolean[] visited, int result, int plus, int minus, int mul, int div) {
		if(count == N-1) {
			maxCal = Math.max(maxCal, result);
			minCal = Math.min(minCal, result);
			return;
		}
		
		int nextN = numberArr[count+1];
		// 4가지의 연산 진행 -> 횟수가 남아있다면
		if(plus > 0) {
			nextPerm(count + 1, visited, result + nextN, plus - 1, minus, mul, div);
		}
		if(minus > 0) {
			nextPerm(count + 1, visited, result - nextN, plus, minus - 1, mul, div);
		}
		if(mul > 0) {
			nextPerm(count + 1, visited, result * nextN, plus, minus, mul - 1, div);
		}
		if(div > 0) {
			nextPerm(count + 1, visited, result / nextN, plus, minus, mul, div - 1);
		}
	}
}
