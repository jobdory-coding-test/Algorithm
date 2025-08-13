import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Day15_홈방범서비스 {
	
	static StringBuilder sb = new StringBuilder();
	static int T, N, M;
	static boolean[][] arr;
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		T = Integer.parseInt(st.nextToken());
		
		for(int tc=1; tc<=T; tc++) {
			sb.append("#"+tc+" ");
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			arr = new boolean[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					int inp = Integer.parseInt(st.nextToken());
					arr[i][j] = inp==1 ? true : false;
				}
			}
			
			sb.append(runservice()).append('\n');
			
		}
		System.out.println(sb);
	}

	private static int runservice() {
		int maxhouse = 0, areahouse=0;
		int maxarea = N%2==1 ? N : N+1;
		for(int area=1; area<=maxarea; area++) {
			int cost = area*area + (area-1)*(area-1);
			for(int r=0; r<N; r++) {
				for(int c=0; c<N; c++) {
					areahouse = calcarea(area, r, c);
					if(areahouse*M>=cost && areahouse>maxhouse) {
						maxhouse = areahouse;
					}
					
				}
			}
		}
		return maxhouse;
	}

	private static int calcarea(int area, int r, int c) {
		int cnt=0;
		for(int i=r-area+1; i<r+area; i++) {
			for(int j=c-area+1; j<c+area; j++) {
				if(!isinArea(area, r, c, i, j)) continue;
				if(arr[i][j]) cnt++;
				
			}
		}
		return cnt;
	}
	
	private static boolean isinArea(int area, int centerR, int centerC, int r, int c) {
		if(r>=0&& r<N && c>=0 && c<N) {
			if (Math.abs(centerR - r) + Math.abs(centerC - c) < area) return true;
		}
		return false;
	}
	

}
