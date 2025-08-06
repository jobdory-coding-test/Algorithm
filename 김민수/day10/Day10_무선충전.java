import java.io.*;
import java.util.*;

public class Day10_무선충전 {
	private static int M, A;
	private static int[][] delta = { { 0, 0 }, { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	private static int[][][] board;
	private static int[][][] indexInfo;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());

			board = new int[10][10][2];
			indexInfo = new int[10][10][2];

			int[][] times = new int[M][2];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				times[i][0] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				times[i][1] = Integer.parseInt(st.nextToken());
			}

			for (int index = 1; index <= A; index++) {
				st = new StringTokenizer(br.readLine());

				int x = Integer.parseInt(st.nextToken()) - 1;
				int y = Integer.parseInt(st.nextToken()) - 1;
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				bfs(y, x, c, p, index);
			}
			sb.append("#").append(t).append(" ").append(simulate(times)).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static int simulate(int[][] times) {
		int ar = 0;
		int ac = 0;
		int br = 9;
		int bc = 9;
		
		int result = cal(new int[][] { { ar, ac }, { br, bc } });
		for (int[] time : times) {
			ar += delta[time[0]][0];
			ac += delta[time[0]][1];

			br += delta[time[1]][0];
			bc += delta[time[1]][1];

			result += cal(new int[][] { { ar, ac }, { br, bc } });
		}
		return result;
	}

	private static int cal(int[][] info) {
		int[] a = info[0];
		int[] b = info[1];

		// 같은 BC 구간을 쓰고 있다면
		if (indexInfo[a[0]][a[1]][0] == indexInfo[b[0]][b[1]][0]) {
			int tmpA = 0;
			int tmpB = 0;

			// A가 다른 BC와 겹치는 구간에 있다면
			if (board[a[0]][a[1]][1] > 0) {
				tmpA = board[a[0]][a[1]][0] + board[a[0]][a[1]][1];
			}

			// B가 다른 BC와 겹치는 구간에 있다면
			if (board[b[0]][b[1]][1] > 0) {
				tmpB = board[b[0]][b[1]][0] + board[b[0]][b[1]][1];
			}
			return Math.max(tmpA, Math.max(board[a[0]][a[1]][0], tmpB));
		}

		return board[a[0]][a[1]][0] + board[b[0]][b[1]][0];
	}

	/**
	 * board[][][0] => 보여지는 충전양 board[][][1] => 겹치는 구간의 두 번째로 큰 충전양 map[][][0] =>
	 * 보여지는 충전하는 장소의 index map[][][1] => 겹치는 구간의 두 번째로 큰 충전 장소 index
	 */

	private static void bfs(int r, int c, int range, int power, int index) {
		boolean[][] visited = new boolean[10][10];
		visited[r][c] = true;

		// 이미 충전할 위치에 충전양이 존재하고 현재 power보다 크다면
		if (board[r][c][0] > power) {
			// board[r][c][1] 겹치는 구간에 power 값을 최신화 시켜준다.
			// 겹치는 구간에 이미 값이 존재할 수 있기 때문에 대소비교해서 넣어준다.
			if (board[r][c][1] < power) {
				board[r][c][1] = power;
				indexInfo[r][c][1] = index;
			}
		} else { // 만약 현재 power가 기존에 있던 power보다 크다면
			board[r][c][1] = board[r][c][0];
			board[r][c][0] = power;
			indexInfo[r][c][1] = indexInfo[r][c][0];
			indexInfo[r][c][0] = index;
		}

		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { r, c, range });

		while (!q.isEmpty()) {
			int[] curr = q.poll();

			for (int i = 1; i <= 4; i++) {
				int nr = curr[0] + delta[i][0];
				int nc = curr[1] + delta[i][1];

				if (isRange(nr, nc) && !visited[nr][nc] && curr[2] > 0) {
					if (board[nr][nc][0] > power) {
						// board[nr][nc][1] 겹치는 구간에 power 값을 최신화 시켜준다.
						// 겹치는 구간에 이미 값이 존재할 수 있기 때문에 대소비교해서 넣어준다.
						if (board[nr][nc][1] < power) {
							board[nr][nc][1] = power;
							indexInfo[nr][nc][1] = index;
						}
					} else { // 만약 현재 power가 기존에 있던 power보다 크다면
						board[nr][nc][1] = board[nr][nc][0];
						board[nr][nc][0] = power;
						indexInfo[nr][nc][1] = indexInfo[nr][nc][0];
						indexInfo[nr][nc][0] = index;
					}
					visited[nr][nc] = true;
					q.add(new int[] { nr, nc, curr[2] - 1 });
				}
			}
		}
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < 10 && c >= 0 && c < 10;
	}
}