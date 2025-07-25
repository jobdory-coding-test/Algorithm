import java.io.*;
import java.util.*;

/**
 * 힌트 : 깊은 복사, 얕은 복사
 * 
 * @author SSAFY
 *
 */
class SWEA_212 {
	static int D, W, K;
	static int[][] arr;
	static int ans;
	
	static final int A = 0;
	static final int B = 1;
	
	public static void main(String args[]) throws Exception {

		System.setIn(new FileInputStream("src/input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T;
		T = Integer.parseInt(st.nextToken());

		for (int test_case = 1; test_case <= T; test_case++) {

			StringBuilder sb = new StringBuilder();

			// 입력 받기
			st = new StringTokenizer(br.readLine());

			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			ans = Integer.MAX_VALUE;

			arr = new int[D][W];

			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			solution(0, 0);
						
			sb.append("#").append(test_case).append(" ").append(ans);
			
			System.out.println(sb);
			
		}
	}
	
	static void solution(int depth, int cnt) {
		if(cnt >= ans) {
			return;
		}
		
		if(depth >= D) {
			if(check()) {
				ans = Math.min(ans, cnt);
			}
			return;
		}
		
		// 약물 주입 X
		solution(depth+1, cnt);
		
		// 약물 A 주입
		int[] temp = arr[depth].clone();
		Arrays.fill(arr[depth], A);
		solution(depth+1, cnt+1);
		
		// 약물 B 주입 
		Arrays.fill(arr[depth], B);
		solution(depth+1, cnt+1);
		
		arr[depth] = temp;
	}

	static boolean check() {
		// 안되는 경우를 거르기
		for (int c = 0; c < W; c++) {
			int cur = 1;
			int before = arr[0][c];
			
			for (int r = 1; r < D; r++) {
				if (before == -1) {
					before = arr[r][c];
					continue;
				}
				if (cur >= K) {
					break;
				}

				if (before == arr[r][c]) {
					cur++;
					continue;
				} else {
					before = arr[r][c];
					cur = 1;
				}
			}
			if(cur < K) {
				return false;
			}
		}
		
		return true;
	}
}