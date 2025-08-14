import java.io.*;
import java.util.*;

public class Day14_N_Queen {

	private static int N, res;
	private static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());

		res = 0;

		arr = new int[N];
		
		// 순열 만들기
		perm(0);

		sb.append(res).append("\n");
		System.out.println(sb.toString());
	}

	private static void perm(int cnt) {
		if (cnt == N) {
			res++;
			return;
		}

		for (int n = 0; n < N; n++) {
			if(!valid(n, cnt)) continue;
			perm(cnt+1);
		}
	}
	
	static boolean valid(int n, int cnt) {
		for(int i = 0; i<cnt; i++) {
			// 대각선
			if(Math.abs(i-cnt) == Math.abs(arr[i] - n)) return false;
			// 열 제외
			if(n == arr[i]) return false;
		}
		arr[cnt] = n;
		return true;
	}

}

