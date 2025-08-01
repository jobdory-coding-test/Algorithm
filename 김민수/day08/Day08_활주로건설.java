import java.io.*;
import java.util.*;

public class Day08_활주로건설 {
	static int N, L;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int result = 0;

			for (int i = 0; i < N; i++) {
				if (canPass(map[i]))
					result++;
			}

			for (int j = 0; j < N; j++) {
				int[] col = new int[N];
				for (int i = 0; i < N; i++)
					col[i] = map[i][j];
				if (canPass(col))
					result++;
			}

			sb.append("#").append(t).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static boolean canPass(int[] line) {
		boolean[] used = new boolean[N]; // 경사로 설치한 칸 표시

		for (int i = 0; i < N - 1; i++) {
			int curr = line[i];
			int next = line[i + 1];

			if (curr == next)
				continue;

			if (next - curr == 1) {
				for (int k = i; k > i - L; k--) {
					if (k < 0 || line[k] != curr || used[k])
						return false;
					used[k] = true;
				}
			} else if (curr - next == 1) {
				for (int k = i + 1; k <= i + L; k++) {
					if (k >= N || line[k] != next || used[k])
						return false;
					used[k] = true;
				}
				i += L - 1;
			} else {
				return false; // 높이 차 >=2
			}
		}
		return true;
	}
}