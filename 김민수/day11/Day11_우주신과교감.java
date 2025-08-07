import java.io.*;
import java.util.*;

public class Main {
	private static class Edge {
		int from, to;
		double w;

		public Edge(int from, int to, double w) {
			super();
			this.from = from;
			this.to = to;
			this.w = w;
		}
	}

	private static int N, M;
	private static int[] p;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		p = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			p[i] = i;
		}

		int[][] infos = new int[N + 1][2];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			infos[i][0] = Integer.parseInt(st.nextToken());
			infos[i][1] = Integer.parseInt(st.nextToken());
		}

		List<Edge> edges = saveInfo(infos);

		Collections.sort(edges, (a, b) -> Double.compare(a.w, b.w));

		int pick = 0;
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			int px = findP(x);
			int py = findP(y);
			if (px != py) {
				pick++;
				union(px, py);
			}
		}

		System.out.println(String.format("%.2f", cal(pick, edges)));
	}

	private static List<Edge> saveInfo(int[][] infos) {
		List<Edge> edges = new ArrayList<>();

		for (int i = 1; i <= N - 1; i++) {
			for (int j = i + 1; j <= N; j++) {
				long x = Math.abs(infos[i][0] - infos[j][0]);
				long y = Math.abs(infos[i][1] - infos[j][1]);
				double w = Math.sqrt(x * x + y * y);

				edges.add(new Edge(i, j, w));
			}
		}
		return edges;
	}

	private static double cal(int pick, List<Edge> edges) {
		int idx = 0;
		double ans = 0;
		while (pick < N - 1) {
			int x = edges.get(idx).from;
			int y = edges.get(idx).to;

			int px = findP(x);
			int py = findP(y);

			if (px != py) {
				pick++;
				union(px, py);
				ans += edges.get(idx).w;
			}
			idx++;
		}
		return ans;
	}

	private static int findP(int x) {
		return p[x] == x ? x : (p[x] = findP(p[x]));
	}

	private static void union(int x, int y) {
		p[y] = x;
	}

}