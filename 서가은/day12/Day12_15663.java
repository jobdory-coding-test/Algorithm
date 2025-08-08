import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class Day12_15663 {

	static int N;
	static int M;
	static ArrayList<Integer> arr;
	static LinkedHashSet<String> result = new LinkedHashSet<>();
	static boolean[] visited;

	static void dfs(int node, int cnt, String text, boolean[] visited) {
		if (cnt == M) {
			result.add(text);
			return;
		}

		for (int i = 0; i < N; i++) {
			if (visited[i] == true)
				continue;
			visited[i] = true;
			dfs(i, cnt + 1, text + arr.get(i) + " ", visited);
			visited[i] = false;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		StringTokenizer st = new StringTokenizer(line);

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new ArrayList<Integer>();
		visited = new boolean[N];
		line = br.readLine();
		st = new StringTokenizer(line);

		for (int i = 0; i < N; i++) {
			arr.add(Integer.parseInt(st.nextToken()));
		}

		arr.sort(null);

		dfs(0, 0, "", visited);

		for (String item : result) {
			System.out.println(item);
		}
	}

}
