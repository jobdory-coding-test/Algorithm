import java.io.*;
import java.util.*;

public class Solution {
	private static int D, W, K;
	private static int[][] board;
	private static int answer;
	private static int[] treat;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
        
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			answer = D;
			treat = new int[D];
			board = new int[D][W];

			for (int r = 0; r < D; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < W; c++) {
					board[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			simulate(0, 0);
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void simulate(int depth, int cnt) {
		if (cnt >= answer) {
			return;
		}

		if (depth == D) {
			if (check()) {
				answer = Math.min(answer, cnt);
			}
			return;
		}
	
		// 해당 열에 약품을 투과하지 않았을 때
		treat[depth] = -1;
		simulate(depth + 1, cnt);
		
		// 해당 열에 A 약품 투과
		treat[depth] = 0;
		simulate(depth + 1, cnt + 1);
		// 해당 열에 B 약품 투과
		treat[depth] = 1;
		simulate(depth + 1, cnt + 1);
	}

	private static boolean check() {
		for (int c = 0; c < W; c++) {
			int cnt = 1;
			int state = cellValue(0, c);
			// 각 열마다 연속적으로 K개 이상 같은 상태가 있는지 확인
			for (int r = 1; r < D; r++) {
				// 이전 상태랑 현재 상태가 같다면 cnt 증가
				if (state == cellValue(r, c)) {
					cnt++;
				} else {
					// 이전 상태랑 다르면 이전 상태 변경하고, cnt 초기화
					state = cellValue(r, c);
					cnt = 1;
				}
				// cnt가 K개 이상이라면 해당 열은 통과이기 때문에 break 걸어주기
				if (cnt >= K) {
					break;
				}
			}
			// cnt가 K개 이하라면 해당 보호필름은 기준 미달이기 때문에 false 반환
			if (cnt < K) {
				return false;
			}
		}
		// 모든 열이 기준 통과이기 때문에 true 반환
		return true;
	}
	
	// treat[r] 상태에 따라 {원래 값, 0, 1} 세 개 중 반환
	private static int cellValue(int r, int c) {
		if (treat[r] >= 0) {
			return treat[r];
		}
		return board[r][c];
	}
}