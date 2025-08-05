import java.util.*;
import java.io.*;

public class Day09_디저트카페 {

	static int startR;
	static int startC;
	static int N;
	static boolean[] visited;
	static int[][] arr;

    static int[][] dir = { { 1, 1 }, { 1, -1 }, { -1, -1 }, { -1, 1 } };

	static int ans;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		for (int t = 1; t <= T; t++) {
			// 초기화.
			ans = 0;

			// 입력받기.
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());

			arr = new int[N][N];
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					arr[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					startR = r;
					startC = c;
					visited = new boolean[101];
					visited[arr[r][c]] = true;
					solve(r, c, 0, 1, 0);
					visited[arr[r][c]] = false;
					
				}
			}

			ans = ans == 0? -1 : ans;
			System.out.println("#" + t + " " + ans);
		}
	}

	static void solve(int r, int c, int d, int cnt, int turn) {

		
		// 끝났을 때 처리
		if (r == startR && c == startC && cnt > 3 && turn == 3) {
			ans = Math.max(ans, cnt);
			return;
		}

		// 4가지 방향으로 돈다 -> 가능한 방향만 돈다. 직진, 꺾기 한 번씩
		for(int i = 0; i<2; i++) {
			int nd = (d + i) % 4;
			
			int nr = r + dir[nd][0];
			int nc = c + dir[nd][1];
			
			if(!check(nr, nc)) continue;
			if(nr == startR && nc == startC) {
				if(cnt > 2 && turn + i == 3) {
					solve(nr, nc, nd, cnt, turn + i);
				}
				continue;
			}
			
			if(visited[arr[nr][nc]]) continue;
			
			visited[arr[nr][nc]] = true;
			solve(nr, nc, nd, cnt+1, turn+i);
			visited[arr[nr][nc]] = false;
		}
	}

	static boolean check(int nr, int nc) {
		return 0 <= nr && nr < N && 0 <= nc && nc < N;
	}
}
