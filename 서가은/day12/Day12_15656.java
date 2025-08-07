import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Day12_15656 {
	static int N;
	static int M;
	static ArrayList<Integer> arr;

	static int[] path;
	static StringBuilder result = new StringBuilder();

	public static void dfs(int node, int cnt) {
		if (cnt == M) {
			for (int i = 0; i < M; i++) {
				result.append(path[i]);
				result.append(" ");
			}
			result.append("\n");
			return;
		}

		for (int i = 0; i < N; i++) {
			path[cnt] = arr.get(i);
			dfs(i, cnt + 1);
		}
	}

	public static void main(String[] args) throws IOException {
		// N,M

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		StringTokenizer st = new StringTokenizer(line);

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		path = new int[M + 1];
		// N개의 수
		line = br.readLine();
		st = new StringTokenizer(line);
		arr = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			arr.add(Integer.parseInt(st.nextToken()));
		}

		arr.sort(null);
		dfs(0, 0);

		System.out.println(result.toString());

		// 같은 수 여러번 가능
		// N개 자연수 중 M개

		// 한 줄에 하나씩 출력
		// 사전순 증가
	}

}
