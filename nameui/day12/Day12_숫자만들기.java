import java.io.*;
import java.util.*;

public class Day12_숫자만들기 {

	static int N;
	static int[] numbers;
	static int[] operator;
	static int min;
	static int max;

	public static void main(String[] args) throws IOException {

		System.setIn(new FileInputStream("src/input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());

			// 연산자 입력받기.
			st = new StringTokenizer(br.readLine());

			operator = new int[4];
			for (int i = 0; i < 4; i++) {
				operator[i] = Integer.parseInt(st.nextToken());
			}

			// 숫자 입력받기.
			numbers = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				numbers[i] = Integer.parseInt(st.nextToken());
			}

			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;

			dfs(1, numbers[0]);

			sb.append("#").append(t).append(" ").append((max - min)).append("\n");

		}

		System.out.println(sb.toString());
	}

	static void dfs(int cnt, int sum) {
		if (cnt == N) {
			min = Math.min(min, sum);
			max = Math.max(max, sum);
			return;
		} else {
			for (int i = 0; i < 4; i++) {
				if (operator[i] == 0) {
					continue;
				}

				operator[i]--;

				if (i == 0) {
					dfs(cnt + 1, sum + numbers[cnt]);
				} else if (i == 1) {
					dfs(cnt + 1, sum - numbers[cnt]);
				} else if (i == 2) {
					dfs(cnt + 1, sum * numbers[cnt]);
				} else {
					dfs(cnt + 1, sum / numbers[cnt]);
				}

				operator[i]++;
			}
		}
	}

}
