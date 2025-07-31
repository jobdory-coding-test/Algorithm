import java.io.*;
import java.util.*;

public class Main {
	private static int N, K, L;
	private static int[][] board;
	private static int[][] delta = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		board = new int[N][N];

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			board[r][c] = 1;
		}

		L = Integer.parseInt(br.readLine());
		int[][] orders = new int[L][2];
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			String str = st.nextToken();
			int dir = str.equals("L") ? -1 : 1;

			orders[i][0] = x;
			orders[i][1] = dir;
		}
		System.out.println(simulate(orders));
	}

	private static int simulate(int[][] orders) {
		Queue<int[]> snake = new ArrayDeque<>();
		snake.add(new int[] { 0, 0 });

        int time = 0;
		int idx = 0;
		int r = 0;
		int c = 0;
		int dir = 0;
		board[0][0] = -1;

		while (true) {
            // 현재 시간에 90도 회전하는 메서드가 들어왔을 때
			if (idx < orders.length && time == orders[idx][0]) {
				dir = (dir + orders[idx][1] + 4) % 4;
				idx++;
			}

            // 한 칸 이동
			r += delta[dir][0];
			c += delta[dir][1];

            // 만약 벽을 만나거나 뱀 자기 자신의 몸을 만날 때 종료
			if (!isRange(r, c) || board[r][c] < 0) {
				break;
			} else {
                // 만약 사과가 없다면 꼬리 부분을 제거한다.
				if (board[r][c] == 0) {
					int[] tail = snake.poll();
					board[tail[0]][tail[1]] = 0;
				}
                // 한 칸 몸을 늘림
				board[r][c] = -1;
                snake.add(new int[] { r, c });
			}
			time++;
		}
		return time + 1;
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}