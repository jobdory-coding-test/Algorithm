import java.io.*;
import java.util.*;

public class Day13_핀볼게임 {
	private static int N;
	private static int[][] board;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static List<int[]>[] teleport;
	private static int[][] pairs;
	private static final int[][] REFLECT = { { 0, 0, 0, 0 }, { 1, 3, 0, 2 }, { 3, 0, 1, 2 }, { 2, 0, 3, 1 },
			{ 1, 2, 3, 0 }, { 1, 0, 3, 2 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			board = new int[N][N];

			List<int[]> loc = new ArrayList<>();
			teleport = new ArrayList[5];
			for (int i = 0; i < 5; i++) {
				teleport[i] = new ArrayList<>();
			}

			pairs = new int[5][4];

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());

				for (int c = 0; c < N; c++) {
					board[r][c] = Integer.parseInt(st.nextToken());

					if (board[r][c] == 0) {
						loc.add(new int[] { r, c });
					}

					if (board[r][c] >= 6 && board[r][c] <= 10) {
						teleport[board[r][c] - 6].add(new int[] { r, c });
					}
				}
			}

			for (int i = 0; i < 5; i++) {
				if (!teleport[i].isEmpty()) {
					int[] portal1 = teleport[i].get(0);
					int[] portal2 = teleport[i].get(1);
					pairs[i][0] = portal2[0];
					pairs[i][1] = portal2[1];
					pairs[i][2] = portal1[0];
					pairs[i][3] = portal1[1];
				}
			}

			int answer = 0;

			for (int[] start : loc) {
				for (int i = 0; i < 4; i++) {
					answer = Math.max(answer, simulate(start, i, false));
				}
			}

			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static int simulate(int[] start, int dir, boolean isStart) {
		int count = 0;
		int[] curr = new int[] { start[0], start[1], dir };

		while (true) {
			// 출발지에 또 왔을 경우 종료
			if (isStart && start[0] == curr[0] && start[1] == curr[1]) {
				break;
			}

			// 출발 flag true
			isStart = true;

			curr[0] += delta[curr[2]][0];
			curr[1] += delta[curr[2]][1];

			// 벽을 만났다면
			if (!isRange(curr[0], curr[1])) {
				curr[2] = REFLECT[5][curr[2]];
				count++;
				continue;
			}

			// 포탈을 만났다면
			if (board[curr[0]][curr[1]] >= 6 && board[curr[0]][curr[1]] <= 10) {
				int r = pairs[board[curr[0]][curr[1]] - 6][0];
				int c = pairs[board[curr[0]][curr[1]] - 6][1];

				// 다음 포탈을 찾는다
				if (curr[0] == r && curr[1] == c) {
					r = pairs[board[curr[0]][curr[1]] - 6][2];
					c = pairs[board[curr[0]][curr[1]] - 6][3];
				}

				curr[0] = r;
				curr[1] = c;
				continue;
			}

			// 블랙홀을 만났을 때
			if (board[curr[0]][curr[1]] == -1) {
				break;
			}

			// 반사각을 만났다면
			if (board[curr[0]][curr[1]] >= 1 && board[curr[0]][curr[1]] <= 5) {
				curr[2] = REFLECT[board[curr[0]][curr[1]]][curr[2]];
				count++;
				continue;
			}
		}
		return count;
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}