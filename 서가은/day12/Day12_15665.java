import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Day12_15665 {

	static int N;
	static int M;
	static TreeSet<Integer> arr = new TreeSet<>();

	static void dfs(int node, int cnt, String text) {
		if (cnt == M) {
			System.out.println(text);
			return;
		}

		for (int item : arr) {
			dfs(item, cnt + 1, text + item + " ");
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		StringTokenizer st = new StringTokenizer(line);

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		line = br.readLine();
		st = new StringTokenizer(line);

		for (int i = 0; i < N; i++) {
			arr.add(Integer.parseInt(st.nextToken()));
		}

//		set.first();
		//// 구현 시작
		dfs(0, 0, "");

	}

}
