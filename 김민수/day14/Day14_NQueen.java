import java.io.*;
import java.util.*;

public class Day14_NQueen {
	private static int N;
	private static int mask;
	private static int answer;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		mask = (1 << N) - 1;
		simulate(0, 0, 0);
		System.out.println(answer);
	}

	private static void simulate(int col, int diag1, int diag2) {
		if(col == mask) {
			answer++;
			return;
		}
		
		// 가능한 경우의 수 뽑기, 이미 뽑은 곳과 안되는 대각선을 제거함
		int possible = ~(col | diag1 | diag2) & mask;
		
		while(possible != 0) {
			// 최하위 비트 찾기
			int u = possible & -possible;
			// 최하위 비트 제거
			possible -= u;
			// 최하위 비트를 뽑았다고 가정하고 다음 행으로 이동
			simulate((col | u), (diag1 | u) << 1, (diag2 | u) >> 1);
		}
	}
}