import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Day13_15664 {

	static int N;
	static int M;
	static ArrayList<Integer> arr;
	static int[] path;
	static StringBuilder result = new StringBuilder();
	static int beforeNum = -1;

	static void dfs(int node, int depth, int[] path) {

		path[depth] = arr.get(node);

		if (depth == M - 1) {
			for (int item : path) {
				result.append(item);
				result.append(" ");
			}
			result.append("\n");

			return;
		}

		for (int i = node + 1; i < N; i++) {

			int item = arr.get(i);
			if (beforeNum == item)
				continue;
			dfs(i, depth + 1, path);
			beforeNum = item;

		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		path = new int[M];

		st = new StringTokenizer(br.readLine());

		arr = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			arr.add(Integer.parseInt(st.nextToken()));
		}

		arr.sort(null);

		for (int i = 0; i < N; i++) {
			int item = arr.get(i);
			if (beforeNum == item)
				continue;
			dfs(i, 0, path);
			beforeNum = item;
		}

		System.out.println(result.toString());

	}

}
