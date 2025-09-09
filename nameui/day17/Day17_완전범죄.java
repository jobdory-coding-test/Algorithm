import java.io.*;
import java.util.*;

public class Day17_완전범죄 {

	public static void main(String[] args) throws IOException {
		int[][] info = { { 1, 2 }, { 1, 2 }, { 1, 1 } };
		int n = 1;
		int m = 4;

		System.out.println(solution(info, n, m));

	}

	public static int solution(int[][] info, int n, int m) {

		int answer = Integer.MAX_VALUE;

		int size = info.length;
		int[][] dp = new int[size][m]; // b 의 흔적 최대값까지 만들어주기.

		for (int i = 0; i < size; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE);
		}

		// 초기값 설정
		if(info[0][0] < n) dp[0][0] = info[0][0]; // A 선택		
		if(info[0][1] < m) dp[0][info[0][1]] = 0; // B 선택

		for (int i = 1; i < size; i++) {
			for (int j = 0; j < m; j++) {
				if (dp[i - 1][j] != Integer.MAX_VALUE) {
					if (dp[i - 1][j] + info[i][0] < n) {
						dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + info[i][0]); // A 선택.
					}
					if (j + info[i][1] < m) {
						dp[i][j + info[i][1]] = Math.min(dp[i][j + info[i][1]], dp[i - 1][j]); // B 선택.
					}
				}
			}
		}

		for (int i = 0; i < m; i++) {
			answer = Math.min(answer, dp[size - 1][i]);
		}

		return answer == Integer.MAX_VALUE ? -1 : answer;
	}

}
