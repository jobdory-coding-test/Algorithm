import java.io.*;
import java.util.*;

public class Day04_벌꿀채취 {
	private static int N, M, C;
	private static int[][] board;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			board = new int[N][N];
			int[][] dist = new int[N][N - M + 1];

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					board[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			// info 배열에 들어가는 정보: dist 배열의 최대 값의 행 열 좌표 및 값
			int[] info = new int[3];
			for (int r = 0; r < dist.length; r++) {
				for (int c = 0; c < dist[r].length; c++) {
					dist[r][c] = selectHoneyCal(r, c);
					if (info[2] < dist[r][c]) {
						info[2] = dist[r][c];
						info[0] = r;
						info[1] = c;
					}
				}
			}
			sb.append("#").append(t).append(" ").append(cal(info, dist)).append("\n");
		}

		System.out.println(sb.toString());
	}
	
	// 나올 수 있는 최대 값 계산 후 리턴
	private static int cal(int[] info, int[][] dist) {
		// 먼저 뽑은 최대 값에서 좌우 겹치는 구간 0으로 변경
		for(int c = Math.max(0, info[1] - M + 1);
				c < Math.min(dist[info[0]].length, info[1] + M); c++) {
			dist[info[0]][c] = 0;
		}
		
		// 두 번째 가장 큰 값 찾기
		int max = 0;
		for (int r = 0; r < dist.length; r++) {
			for (int c = 0; c < dist[r].length; c++) {
				max = Math.max(max, dist[r][c]);
			}
		}
		return max + info[2];
	}

	/**
	 * 선택한 벌통 중에서 가능한 조합 중 가장 큰 값을 리턴
	 */
	private static int selectHoneyCal(int r, int c) {
		int max = 0;
		int[] arr = new int[M];

		for (int i = 0; i < M; i++) {
			arr[i] = board[r][c + i];
		}
		int limit = 1 << M;
		
		// 모든 경우의 수를 찾아
		for (int mask = 0; mask < limit; mask++) {
			int sum = 0;
			int squreSum = 0;
			// i 번째 비트가 켜져있으면 M개 고른 벌통 중 i번째 벌통을 사용한 것
			for (int i = 0; i < M; i++) {
				if ((mask & (1 << i)) != 0) {
					sum += arr[i];
					squreSum += (arr[i] * arr[i]);
				}
			}
			
			// 만약 sum이 기준치에 맞다면 max 값 최신화
			if (sum <= C) {
				max = Math.max(max, squreSum);
			}
		}

		return max;
	}

}