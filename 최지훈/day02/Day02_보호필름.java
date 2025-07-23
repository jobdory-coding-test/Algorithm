import java.util.*;
import java.io.*;

public class Solution {

	static StringBuilder sb = new StringBuilder();
	static int T, D, W, K;
	static int result;
	static int[][] board;
	
	static boolean check(int[] visited) {
		int[][] copyBoard = new int[D][W];
		for(int i=0; i<D; i++) {
			for(int j=0; j<W; j++) {
				if(visited[i] != -1) {
					copyBoard[i][j] = visited[i];
				}else {
					copyBoard[i][j] = board[i][j];
				}
			}
		}
		
		for(int c=0; c<W; c++) {
			boolean chk = false;
			int cnt = 1;
			int val = copyBoard[0][c];
			for(int r=1; r<D; r++) {
				if(val == copyBoard[r][c]) {
					cnt++;
					if(cnt == K) {
						chk = true; // K개 연속으로 되면 true
						break;
					}
				}else {
					val = copyBoard[r][c]; // 값 달라지면 다시 시작
					cnt = 1;
				}
			}
			
			if(!chk) return false;
		}
		
		return true;
	}

	static void combi(int idx, int cnt, int target, int[] visited) {
		if (cnt == target) {
			// target 개수만큼 뽑았으면 체크
			if(check(visited) || K == 1) result = Math.min(target, result);
			return;
		}

		for (int i = idx; i < D; i++) {
			// A
			visited[i] = 0;
			combi(i + 1, cnt + 1, target, visited);

			// B
			visited[i] = 1;
			combi(i + 1, cnt + 1, target, visited);
			
			visited[i] = -1;
		}
	}

	public static void main(String[] args) throws IOException {
		// System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		T = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			result = Integer.MAX_VALUE;

			board = new int[D][W];
			for (int r = 0; r < D; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < W; c++) {
					board[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			sb.append("#").append(t).append(" ");

			int[] visited = new int[D];
			Arrays.fill(visited, -1); // -1은 변경x => 0은 A, 1은 B

			for (int c = 0; c <= D; c++) {
				combi(0, 0, c, visited);
				if(result != Integer.MAX_VALUE) break;
			}
			
			if(t == T) sb.append(result);
			else sb.append(result).append("\n");
		}

		System.out.println(sb);
		br.close();
	}
}