import java.io.*;
import java.util.*;

public class Main {
	static class Dir {
		int x;
		String c;

		public Dir(int x, String c) {
			this.x = x;
			this.c = c;
		}

		int getX() {
			return x;
		}

		String getC() {
			return c;
		}
	}

	static int N, K, L;
	static int[][] board;
	// 상 우 좌 하 (시계 방향)
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static Queue<Dir> dirQ;
	static Deque<int[]> snake;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		board = new int[N][N];
		snake = new ArrayDeque<>();
		board[0][0] = 1; // 뱀 위치 값 1
		snake.addFirst(new int[] { 0, 0 });

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			board[x-1][y-1] = 2; // 사과 위치 값 2
		}

		L = Integer.parseInt(br.readLine());
		dirQ = new ArrayDeque<>();
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			String c = st.nextToken();
			dirQ.add(new Dir(x, c));
		}

		move();
		br.close();
	}

	static void move() {
		int time = 0;
		int dir = 1; // 첫 방향 오른쪽

		while (true) {
			time++;
			
			// 1. 뱀 이동 & 몸 길이 변환
			int nx = snake.getFirst()[0] + dx[dir];
			int ny = snake.getFirst()[1] + dy[dir];
			
			// 벽을 넘거나 뱀의 몸이라면 종료
			if(nx < 0 || nx >= N || ny < 0 || ny >= N || board[nx][ny] == 1) {
				break;
			}
			
			int nvalue = board[nx][ny];
			board[nx][ny] = 1;
			snake.addFirst(new int[] {nx, ny});
			
			// 이동 위치가 사과가 아니라면 꼬리 줄이기
			if(nvalue == 0) {
				int[] tail = snake.pollLast();
				board[tail[0]][tail[1]] = 0;
			}
			
			// 2. 방향 전환
			// 방향 큐의 X와 현재 시간이 같다면
			if(!dirQ.isEmpty() && dirQ.peek().getX() == time) {
				String C = dirQ.poll().getC();
				if(C.equals("L")) { // 왼쪽
					dir--;
					if(dir < 0) dir = 3;
				} else if(C.equals("D")) { // 오른쪽
					dir++;
					if(dir > 3) dir = 0;
				}
			}
		}
		
		System.out.print(time);
	}
}