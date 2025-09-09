import java.util.*;

class Day18_카카오_프렌즈_컬러링북 {
    static boolean[][] visited;
	static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int R, C;
    
    public static int[] solution(int m, int n, int[][] picture) {
		// 초기화.
		R = m;
		C = n;

		visited = new boolean[R][C];
		int target = 1;

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (picture[r][c] == 0 || visited[r][c])
					continue;

				bfs(r, c, target, picture);
				target++;
			}
		}

		int numberOfArea = target - 1;
		int maxSizeOfOneArea = 0;

		// 최대 크기 구하기
		int[] cnt = new int[target + 1];
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				cnt[picture[r][c]]++;
			}
		}

		for (int c = 1; c < target + 1; c++) {
			maxSizeOfOneArea = Math.max(maxSizeOfOneArea, cnt[c]);
		}

		int[] answer = new int[2];
		answer[0] = numberOfArea;
		answer[1] = maxSizeOfOneArea;
		return answer;
	}

	public static void bfs(int r, int c, int target, int[][] picture) {
		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.offer(new int[] { r, c });
		visited[r][c] = true;

		while (!queue.isEmpty()) {
			int[] now = queue.poll();

			for (int d = 0; d < 4; d++) {
				int nr = now[0] + dir[d][0];
				int nc = now[1] + dir[d][1];

				if (!isRange(nr, nc) || visited[nr][nc] || picture[nr][nc] == 0)
					continue;

				if (picture[nr][nc] == picture[now[0]][now[1]]) {
					queue.offer(new int[] { nr, nc });
					visited[nr][nc] = true;
				}
			}
			
			picture[now[0]][now[1]] = target;
			
		}
	}

	private static boolean isRange(int nr, int nc) {
		return 0 <= nr && nr < R && 0 <= nc && nc < C;
	}

}
