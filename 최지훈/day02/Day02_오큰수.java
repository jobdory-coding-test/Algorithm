import java.io.*;
import java.util.*;

public class Main {

	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[] arr;

	static void nge() {
		Deque<int[]> st = new ArrayDeque<>();
		int[] result = new int[N];

		for (int i = 0; i < N; i++) {
			while (!st.isEmpty() && st.peek()[0] < arr[i]) {
				result[st.pop()[1]] = arr[i];
			}
			st.push(new int[] { arr[i], i });
		}

		while (!st.isEmpty()) {
			result[st.pop()[1]] = -1;
		}

		for (int i = 0; i < result.length; i++) {
			sb.append(result[i]).append(' ');
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++) {
			arr[n] = Integer.parseInt(st.nextToken());
		}

		nge();

		System.out.println(sb);
		br.close();
	}
}