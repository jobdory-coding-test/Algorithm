import java.io.*;
import java.util.*;

public class Day12_안전영역 {
	private static int N;
	private static int[][] board;
	private static boolean[][] visited;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		board = new int[N][N];

		int max = 0;

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
				max = Math.max(max, board[r][c]);
			}
		}

		int result = 0;
		for (int height = 0; height < max; height++) {
			visited = new boolean[N][N];
			int cnt = 0;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (board[r][c] > height && !visited[r][c]) {
						cnt++;
						visited[r][c] = true;
						bfs(r, c, height);
					}
				}
			}
			result = Math.max(result, cnt);
		}
		System.out.println(result);
	}

	private static void bfs(int r, int c, int height) {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { r, c });

		while (!q.isEmpty()) {
			int[] curr = q.poll();

			for (int i = 0; i < 4; i++) {
				int nr = curr[0] + delta[i][0];
				int nc = curr[1] + delta[i][1];

				if (nr >= 0 && nr < N && nc >= 0 && nc < N && board[nr][nc] > height && !visited[nr][nc]) {
					visited[nr][nc] = true;
					q.add(new int[] { nr, nc });
				}
			}
		}
	}
}
