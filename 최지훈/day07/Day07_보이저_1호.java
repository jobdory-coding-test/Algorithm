import java.util.*;
import java.io.*;

public class Main {
	private static StringBuilder sb = new StringBuilder();
	private static int N, M, startX, startY;
	private static char[][] board;

	// 상U 우R 하D 좌L
	private static int[] dx = { -1, 0, 1, 0 };
	private static int[] dy = { 0, 1, 0, -1 };
	private static HashMap<Integer, Character> map = new HashMap<>();
	
	private static char dir;
	private static int dis;
	private static boolean voy = false;

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
 
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		board = new char[N][M];
		for (int i = 0; i < N; i++) {
			board[i] = br.readLine().toCharArray();
		}
		st = new StringTokenizer(br.readLine());
		startX = Integer.parseInt(st.nextToken()) - 1;
		startY = Integer.parseInt(st.nextToken()) - 1;
		
		map.put(0, 'U');
		map.put(1, 'R');
		map.put(2, 'D');
		map.put(3, 'L');
		
		for(int i=0; i<4; i++) {
			if(voy) break;
			solve(i);
		}

		System.out.println(dir);
		System.out.println(voy ? "Voyager" : dis);
		br.close();
	}
	
	private static void solve(int baseDir) {
		int x = startX;
		int y = startY;
		int d = baseDir;
		int disCnt = 1;
		
		while(true) {
			x += dx[d];
			y += dy[d];
			
			// 범위 이탈 or 블랙홀
			if(x < 0 || x >= N || y < 0 || y >= M || board[x][y] == 'C') {
				if(disCnt > dis) {
					// 최대 이동 거리 및 base 방향 변경
					dis = disCnt;
					dir = map.get(baseDir);
				}
				break;
			}
			
			// 방문한 적이 있고 들어오는 진행 방향이 같다면
			if(x == startX && y == startY && d == baseDir) {
				voy = true;
				dir = map.get(baseDir);
				break;
			}
			
			// 행성
			if(board[x][y] == '/') {
				d = d ^ 1;
			} else if(board[x][y] == '\\') {
				d = 3 - d;
			}
			
			disCnt++;
		}
	}
}
