import java.io.*;
import java.util.*;

public class Day15_홈방범서비스 {
	private static int N, M, answer;
	private static int[][] board;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			board = new int[N][N];
			answer = 0;

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					board[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			int maxCost = 0;
			for (int k = 1; k <= N + 2; k++) {
				int firstCost = (int) Math.pow(k, 2) + (int) Math.pow(k - 1, 2);
				for (int r = 0; r < N; r++) {
					for (int c = 0; c < N; c++) {
						int houses = simulate(r, c, k - 1);
						int cost = houses * M - firstCost;
						if (cost >= 0) {
							answer = Math.max(answer, houses);
						}
					}
				}
			}

			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static int simulate(int sr, int sc, int k) {
		int count = board[sr][sc];
		boolean[][] visited = new boolean[N][N];
		visited[sr][sc] = true;

		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { sr, sc, k });

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int r = curr[0];
			int c = curr[1];
			int l = curr[2];

			if (l > 0) {
				for (int i = 0; i < 4; i++) {
					int nr = r + delta[i][0];
					int nc = c + delta[i][1];

					if (isRange(nr, nc) && !visited[nr][nc]) {
						if (board[nr][nc] == 1) {
							count++;
						}
						visited[nr][nc] = true;
						q.add(new int[] { nr, nc, l - 1 });
					}
				}

			}
		}
		return count;
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}