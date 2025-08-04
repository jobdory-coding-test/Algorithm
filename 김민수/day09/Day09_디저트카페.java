import java.io.*;
import java.util.*;

public class Day09_디저트카페 {
	private static int N, max;
	private static int[][] board;
	private static int[][] delta = { { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };
	private static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			board = new int[N][N];
			max = 0;

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					board[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					visited = new boolean[N][N];
					dfs(r, c, r, c, 0, 0, new boolean[101], new boolean[4]);
				}
			}
			sb.append("#").append(t).append(" ").append(max = max == 0 ? -1 : max).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void dfs(int r, int c, int sr, int sc, int count, int preDir, boolean[] tasted, boolean[] dir) {
		// 다시 처음 자리에 돌아왔다면
		if (count > 0 && r == sr && c == sc) {
			// 처음자리로 온 방향 방문 처리
			dir[preDir] = true;
			// 모든 4방향을 다 탐색했다면
			if (check(sr, sc, dir)) {
				// max 값 최신화
				max = Math.max(max, count);
			}
			dir[preDir] = false;
			return;
		}

		for (int i = 0; i < 4; i++) {
			if (dir[i]) {
				continue;
			}

			// 현재 방향이 이전 방향과 다르다면 이전 방향을 방문처리한다.
			if (preDir != i) {
				dir[preDir] = true;
			}

			int nr = r + delta[i][0];
			int nc = c + delta[i][1];

			// 범위 안에 있으며 전에 방문하지 않았고, 맛도 이전에 맛본 상태가 아니라면
			if (isRange(nr, nc) && !visited[nr][nc] && !tasted[board[nr][nc]]) {
				visited[nr][nc] = true;
				tasted[board[nr][nc]] = true;
				dfs(nr, nc, sr, sc, count + 1, i, tasted, dir);
				visited[nr][nc] = false;
				tasted[board[nr][nc]] = false;
			}

			dir[preDir] = false;
		}

	}

	private static boolean check(int sr, int sc, boolean[] dir) {
		for (boolean v : dir) {
			if (!v) {
				return false;
			}
		}
		return true;
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}