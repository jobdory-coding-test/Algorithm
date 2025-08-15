import java.io.*;
import java.util.*;

public class Day16_보물상자비밀번호 {
	private static int N, K;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			char[] numbers = br.readLine().toCharArray();
			int idx = 0;
			int range = N / 4;

			TreeSet<Long> ts = new TreeSet<>();

			for (int i = 0; i < range; i++) {
				for (int j = 0; j < N; j += range) {
					StringBuilder nums = new StringBuilder();
					for (int k = 0; k < range; k++) {
						nums.append(numbers[(idx + j + k) % N]);
					}
					ts.add(changeHexDecimal(nums.toString()));
				}
				idx = (idx - 1 + N) % N;
			}

			int size = ts.size();

			long answer = 0;
			for (int i = 0; i < size - K + 1; i++) {
				answer = ts.pollFirst();
			}
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static long changeHexDecimal(String num) {
		long result = 0;
		for (int i = 0; i < num.length(); i++) {
			char n = num.charAt(i);
			int tmp = 0;
			if (n >= 'A' && n <= 'F') {
				tmp = 10 + (n - 'A');
			} else {
				tmp = n - '0';
			}

			result = (result) * 16 + (tmp);
		}
		return result;
	}
}