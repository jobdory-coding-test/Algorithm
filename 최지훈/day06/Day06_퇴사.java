import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] T, P, dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		T = new int[N];
		P = new int[N];
		dp = new int[N+1];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		
		solve();
		
		System.out.print(dp[N]);
		
		br.close();
	}
	
	static void solve() {
		for(int i=0; i<N; i++) {
			if(i + T[i] <= N) {
				dp[i + T[i]] = Math.max(dp[i + T[i]], dp[i] + P[i]);
			}
			dp[i+1] = Math.max(dp[i], dp[i+1]);
		}
	}
}