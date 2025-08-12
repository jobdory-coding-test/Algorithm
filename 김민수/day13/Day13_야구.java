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

    // 야구 선수 타순 정하기
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

            // 아웃카운트 3개가 되면 이닝 종료
			while (outCount < 3) {
                // 타자 안타 정보
				int runner = infos[g][order[idx++]];
				idx %= 9;

				if (runner == 0) {
					outCount++;
					continue;
				}

                // 베이스 이동
				state <<= (runner);
                // 타자 베이스 이동
				state += (1 << (runner - 1));
                // 홈에 들어온 사람 갯수 카운트
				score += Integer.bitCount(state >> 3);
                // 베이스에 있는 선수만 남기기
				state = state & 7;
			}
		}
		return score;
	}
}
