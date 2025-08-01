import java.io.*;
import java.util.*;

public class Day08_활주로건설 {
	
	static int[][] arr;
	static int N, X, ans;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int t  = 1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			
			arr = new int[N][N];
			
			ans = 0;
			
			for(int r = 0; r<N; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0; c<N; c++) {
					arr[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 행 처리
			for(int i = 0; i<N; i++) {
				int[] temp = Arrays.copyOf(arr[i], N);
				if(solve(temp)) {
					ans += 1;
				}
			}
			
			// 열 처리 
			for(int c = 0; c<N; c++) {
				int[] col = new int[N];
				for(int r = 0; r<N; r++) {
					col[r] = arr[r][c];
				}
				if(solve(col)) {
					ans += 1;
				}
			}
			
			System.out.println("#" + t + " " + ans);
			
		}
	}
	
	static boolean solve(int[] temp) {
		boolean[] check = new boolean[N]; // 활주로 지은 곳에 또 지을 수 없으므로 체크 
		
		for (int i = 0; i<N-1; i++) {
			int now = temp[i];
			int next = temp[i+1];
			
			if(Math.abs(now - next) > 1) {
				return false;
			}
			
			if(now == next) continue;
			
			if(now > next) { 
				// 오른쪽으로 이동 
				for(int j = i + 1; j<=i+X; j++) {
					// 유효 범위를 넘어가거나, 이미 활주로를 설치했거나, 지금 것이 다음 것과 다르면 활주로를 지을 수 없으므로 false 리턴 
					if(j >= N || check[j] || temp[j] != next) {
						return false;
					}
					
					check[j] = true;
				}
			}
			
			if(now < next) {
				// 왼쪽으로 이동
				for(int j = i; j >= i-X+1; j--) {
					// 유효 범위를 넘어가거나, 이미 활주로를 설치했거나, 지금 것이 다음 것과 다르면 활주로를 지을 수 없으므로 false 리턴 
					if(j < 0 || check[j] || temp[j] != now) {
						return false;
					}
					
					check[j] = true;
				}
			}
		}
		return true;
	}

}
