import java.util.*;
import java.io.*;

public class Main {
	private static int N;
	private static int[][] board;
	private static int[][] visited;
	private static int maxArea;

	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int n = Integer.parseInt(st.nextToken());
				board[i][j] = n;
			}
		}
		
		// 장마로 차오르는 물의 높이 0~100까지
		maxArea = 0;
		for(int h=0; h<=100; h++) {
			visited = new int[N][N];
			int areaCnt = 0; // 첫번째 영역
			
			// 첫칸부터 마지막칸까지 방문 가능한 칸에서 bfs 진행
			// visited 값이 0이며 h이상이라면 bfs
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(visited[i][j] == 0 && board[i][j] > h) {
						areaCnt++;
						bfs(i, j, areaCnt, h);
					}
				}
			}
			maxArea = Math.max(maxArea, areaCnt);
		}
		
		System.out.print(maxArea);
	}
	
	private static void bfs(int x, int y, int areaCnt, int limitH) {
		Queue<int[]> q = new ArrayDeque<>();
		
		q.offer(new int[] {x, y});
		visited[x][y] = areaCnt;
		
		while(!q.isEmpty()) {
			int[] pos = q.poll();
			int xpos = pos[0];
			int ypos = pos[1];
			
			for(int d=0; d<4; d++) {
				int nx = xpos + dx[d];
				int ny = ypos + dy[d];
				
				if(!check(nx, ny, limitH)) continue;
				
				visited[nx][ny] = areaCnt;
				q.offer(new int[] {nx, ny});
			}
		}
	}
	
	// 이동 검증
	private static boolean check(int x, int y, int limitH) {
		return x >= 0 && x < N && y >= 0 && y < N && visited[x][y] == 0 && board[x][y] > limitH;
	}
}
