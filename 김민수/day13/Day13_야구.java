import java.io.*;
import java.util.*;

public class Day13_야구 {
	private static int N;
	private static int[][] infos;
	private static int max;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		infos = new int[N][10];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 1; j <= 9; j++) {
				infos[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int[] order = new int[9];
		order[3] = 1;
		dfs(0, order, new boolean[10]);
		System.out.println(max);
	}

	private static void dfs(int depth, int[] order, boolean[] visited) {
		if (depth == 9) {
			max = Math.max(max, game(order));
			return;
		}

		if (depth == 3) {
			dfs(depth + 1, order, visited);
			return;
		}

		for (int i = 2; i <= 9; i++) {
			if (visited[i])
				continue;

			visited[i] = true;
			order[depth] = i;
			dfs(depth + 1, order, visited);
			visited[i] = false;
		}
	}

	private static int game(int[] order) {
		int score = 0;
		int idx = 0;

		for (int g = 0; g < N; g++) {
			int outCount = 0;
			int state = 0;

			while (outCount < 3) {
				int runner = infos[g][order[idx++]];
				idx %= 9;

				if (runner == 0) {
					outCount++;
					continue;
				}

				state <<= (runner);
				state += (1 << (runner - 1));
				score += Integer.bitCount(state >> 3);
				state = state & 7;
			}
		}
		return score;
	}
}
