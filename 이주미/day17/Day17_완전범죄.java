import java.util.Arrays;

public class Day17_완전범죄 {

	int[][] weight, dp;
	int X, N, M;
	
	public int solution(int[][] info, int n, int m) {
		weight = info;
		X = info.length; N=n; M=m;
		
		int sumofB=0;
		for(int i=0; i<X; i++) {
			sumofB+=info[i][1];
		}
		
		if(sumofB<M) return 0;
		
		dp = new int[X+1][N];
		for(int i=0; i<dp.length; i++){
			Arrays.fill(dp[i], Integer.MAX_VALUE);
			dp[i][0] = sumofB;
		}
		
		filldp();
        
		return solve();
	}

	private int solve() {
		int result = Integer.MAX_VALUE;
		for(int i=1; i<dp[X].length; i++) {
			if(dp[X][i]<M && dp[X][i]>=0) result = Math.min(result, i);
		}
		return result==Integer.MAX_VALUE? -1 : result;
	}

	private void filldp() {
		for(int i=1; i<=X; i++) {
			int a = weight[i-1][0];
			int b = weight[i-1][1];
            
			for(int j=1; j<a && j<dp[i].length; j++) {
				dp[i][j] = dp[i-1][j];
			}
			
			for(int idx=N-1; idx>=a; idx--) {
                
				if(dp[i-1][idx]==Integer.MAX_VALUE && dp[i-1][idx-a]==Integer.MAX_VALUE) {
                    // 추가할 수 없는 경우
					continue;
				} else {
					// 추가할 수 있는 경우 : 기존 값과 갱신하는 것 중에 최소값 비교
					dp[i][idx] = Math.min(dp[i-1][idx], dp[i-1][idx-a]-b);
				}
			}
		}
	}
	
	
	/*
	 * DFS 구현했던 거
	 
		int[][] weight;
		int X, N, M;
		boolean[] visited;
		
		public int solution(int[][] info, int n, int m) {
			weight = info;
			X = info.length; N=n; M=m;
			
			visited = new boolean[X];
			solutionRes = Integer.MAX_VALUE;;
			
			int sumofB=0;
			for(int i=0; i<X; i++) {
				sumofB+=info[i][1];
			}
			
			if(sumofB<M) return 0;
			solve(0, 0, sumofB);
			
			return solutionRes==Integer.MAX_VALUE ? -1 : solutionRes;
		}
		
		private void solve(int idx, int sumofA, int sumofB) {
			if(idx==X) return;
			
			for(int i=idx; i<X; i++) {
				visited[i]=true;
				
				if(sumofA+weight[i][0] < N) {
					// B 흔적합 확인하고 갱신 가능하면 갱신
					if(sumofB-weight[i][1] < M) {
						solutionRes = Math.min(solutionRes, sumofA+weight[i][0]);
					}
					
					// 아래로 넘어가기
					solve(i+1, weight[i][0]+sumofA, sumofB-weight[i][1]);
				}
				
				visited[i]=false;
			}
		}
		
	 * */

}
