import java.io.*;
import java.util.*;

public class Solution {
	private static int N, M, K, count;
	private static boolean[][] visited;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static Cell[] cell;
	
	/**
	 * Cell을 연결리스트 방식으로 구현
	 */
	private static class Cell {
		int r, c, life;
		Cell next;

		public Cell(int r, int c, int life, Cell next) {
			super();
			this.r = r;
			this.c = c;
			// 비활성 상태 + 활성 상태
			this.life = life * 2;
			this.next = next;
		}
	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			visited = new boolean[(K * 2) + N][(K * 2) + M];
			cell = new Cell[11];
			count = 0;

			for (int r = K; r < N + K; r++) {
				st = new StringTokenizer(br.readLine());

				for (int c = K; c < M + K; c++) {
					int life = Integer.parseInt(st.nextToken());

					if (life > 0) {
						visited[r][c] = true;
						cell[life] = new Cell(r, c, life, cell[life]);
						count++;
					}
				}
			}
			simulate();
			sb.append("#").append(t).append(" ").append(count).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void simulate() {
		while (K-- > 0) {
			for (int life = 10; life > 0; life--) {
				for (Cell curr = cell[life]; curr != null; curr = curr.next) {
					// 현재 줄기세포 생명력이 0이라면 반복문 종료
					// 다음 올 줄기세포들도 다 생명력이 0이다.
					if (curr.life == 0) {
						break;
					}

					// 비활성화 상태 감소
					curr.life--;

					// 활성화 상태가 가능하다면
					if (curr.life == life - 1) {
						for (int dir = 0; dir < 4; dir++) {
							int nr = curr.r + delta[dir][0];
							int nc = curr.c + delta[dir][1];

							if (visited[nr][nc]) {
								continue;
							}
							
							visited[nr][nc] = true;
							// 새로운 줄기 세포 cell에 추가
							cell[life] = new Cell(nr, nc, life, cell[life]);
							count++;
						}
					}
					
					// 현재 줄기세포의 생명력이 끝났다면 총 갯수 감소
					if (curr.life == 0) {
						count--;
					}
				}
			}
		}
	}
}