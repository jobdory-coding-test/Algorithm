import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class Day13_15666 {

	static int N;
	static int M;
	static int[] arr;
	static int[] path;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		path = new int[M];

		st = new StringTokenizer(br.readLine());
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		arr = Arrays.stream(arr).distinct().sorted().toArray();
		
		for(int i=0; i<arr.length; i++) {
			dfs(i, 0);			
		}


		System.out.println(sb.toString());

	}

	private static void dfs(int node, int depth) {
		path[depth] = arr[node];

		if (depth == M - 1) {
			for (int item : path) {
				sb.append(item);
				sb.append(" ");
			}
			sb.append("\n");
			return;
		}

		for (int i=node; i<arr.length; i++) {
			dfs(i, depth + 1);
		}
	}

}
