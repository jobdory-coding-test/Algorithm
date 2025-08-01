import java.io.*;
import java.util.*;

public class Day06_탈주범검거 {
	private static int N, M, R, C, L;
	private static int[][] board;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static boolean[][] isInPoss = new boolean[8][4];
	private static boolean[][] isOutPoss = new boolean[8][4];
	static {
		Arrays.fill(isInPoss[1], true);
		isInPoss[2][0] = true;
		isInPoss[2][1] = true;

		isInPoss[3][2] = true;
		isInPoss[3][3] = true;

		isInPoss[4][1] = true;
		isInPoss[4][2] = true;

		isInPoss[5][0] = true;
		isInPoss[5][2] = true;

		isInPoss[6][0] = true;
		isInPoss[6][3] = true;

		isInPoss[7][1] = true;
		isInPoss[7][3] = true;
	}
	static {
		Arrays.fill(isOutPoss[1], true);
		isOutPoss[2][0] = true;
		isOutPoss[2][1] = true;

		isOutPoss[3][2] = true;
		isOutPoss[3][3] = true;

		isOutPoss[4][0] = true;
		isOutPoss[4][3] = true;

		isOutPoss[5][1] = true;
		isOutPoss[5][3] = true;

		isOutPoss[6][1] = true;
		isOutPoss[6][2] = true;

		isOutPoss[7][0] = true;
		isOutPoss[7][2] = true;
	}

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
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			board = new int[N][M];

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < M; c++) {
					board[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			sb.append("#").append(t).append(" ").append(bfs()).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static int bfs() {
		int count = 0;
		boolean[][] visited = new boolean[N][M];
		visited[R][C] = true;

		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { R, C, 1 });

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			if (curr[2] > L) {
				break;
			}
			count++;

			int r = curr[0];
			int c = curr[1];
			for (int i = 0; i < 4; i++) {
                // 내가 현재 있는 터널에서 나갈 수 있는 방향이라면
				if (isOutPoss[board[r][c]][i]) {
					int nr = r + delta[i][0];
					int nc = c + delta[i][1];

                    // 다음 터널이 방문하지 않았고 내가 현재 있는 위치에서 들어갈 수 있는 곳이라면
					if (isRange(nr, nc) && board[nr][nc] != 0 && !visited[nr][nc] && isInPoss[board[nr][nc]][i]) {
						visited[nr][nc] = true;
						q.add(new int[] { nr, nc, curr[2] + 1 });
					}
				}
			}
		}
		return count;
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}