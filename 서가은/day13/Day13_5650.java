package 서가은.day13;

import java.io.*;
import java.util.*;

public class Day13_5650 {

	static int T, N;
	static int[][] arr;

	// 0=상, 1=우, 2=하, 3=좌
	static final int[] dx = { 0, 1, 0, -1 };
	static final int[] dy = { -1, 0, 1, 0 };

	// 현재 위치/방향
	static int now, x, y;

	// 안드로메다 위치 담아두기
	static int[][] hole = { { -1, -1, -1, -1 }, { -1, -1, -1, -1 }, { -1, -1, -1, -1 }, { -1, -1, -1, -1 },
			{ -1, -1, -1, -1 } };

	// 이미 빈칸에서 특정 방향으로 출발한 적 있으면생략 하

//    static boolean[][][] skip;

	// 디버그 하는 방법!!

	static int changePosition(int blockType, int dir) {
		switch (blockType) {
		case 1 -> {
			int before = dir;
			if (dir == 2)
				dir = 1;
			else if (dir == 3)
				dir = 0;
			else
				dir = (dir + 2) % 4;
		}
		case 2 -> {
			int before = dir;
			if (dir == 0)
				dir = 1;
			else if (dir == 3)
				dir = 2;
			else
				dir = (dir + 2) % 4;
		}
		case 3 -> {
			int before = dir;
			if (dir == 0)
				dir = 3;
			else if (dir == 1)
				dir = 2;
			else
				dir = (dir + 2) % 4;
		}
		case 4 -> {
			int before = dir;
			if (dir == 2)
				dir = 3;
			else if (dir == 1)
				dir = 0;
			else
				dir = (dir + 2) % 4;
		}
		case 5 -> {
			int before = dir;
			dir = (dir + 2) % 4;
		}
		case 6, 7, 8, 9, 10 -> {
			int idx = blockType - 6;
			int ox = x, oy = y;
			if (x == hole[idx][1] && y == hole[idx][0]) {
				x = hole[idx][3];
				y = hole[idx][2];
			} else {
				x = hole[idx][1];
				y = hole[idx][0];
			}
		}
		default -> {
			// 혹시 모를 잘못된 값
			int before = dir;
			dir = (dir + 2) % 4;
		}
		}
		return dir;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			arr = new int[N][N];

			// 웜홀 초기화
			for (int i = 0; i < 5; i++)
				Arrays.fill(hole[i], -1);
//            skip = new boolean[N][N][4];

			// 맵 입력
			for (int yy = 0; yy < N; yy++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int xx = 0; xx < N; xx++) {
					arr[yy][xx] = Integer.parseInt(st.nextToken());
					if (6 <= arr[yy][xx] && arr[yy][xx] <= 10) {
						int idx = arr[yy][xx] - 6;
						if (hole[idx][0] == -1) {
							hole[idx][0] = yy;
							hole[idx][1] = xx;
						} else {
							hole[idx][2] = yy;
							hole[idx][3] = xx;
						}
					}
				}
			}

			int best = 0;

			for (int dir = 0; dir < 4; dir++) {
				for (int sy = 0; sy < N; sy++) {
					for (int sx = 0; sx < N; sx++) {
						if (arr[sy][sx] != 0) {
							continue;
						}
//                        if (skip[sy][sx][dir]) {
//                            System.out.printf("경로 스킵! (%d,%d,%s)\n", sy, sx, d2s(dir));
//                            continue; // 최적화!
//                        }

						int initX = sx, initY = sy;
						x = sx;
						y = sy;
						now = dir;
						int score = 0;

						// (y,x,dir) 재방문 감지용
//                        boolean[][][] visited = new boolean[N][N][4];

						while (true) {
							// 
//                            if (visited[y][x][now]) {
//                                System.out.printf("사이클 감지~!!: (%d,%d,%s) → 중단\n", y, x, d2s(now));
//                                break;
//                            }
//                            visited[y][x][now] = true;

							// 1) 빈칸에서 특정 방향 출발은 다음엔 생략 가능!
//                            if (arr[y][x] == 0) {
//                                skip[y][x][now] = true;
//                            }

							// 2) 담 칸
							int nx = x + dx[now];
							int ny = y + dy[now];

							// 3) 벽이면 반사 + 점수
							if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
								now = (now + 2) % 4; // 벽 반사
								score++; // 벽 점수

								// 시작 타일에서 곧장 벽 반사로 제자리면 종료
								if (x == initX && y == initY) {
//                                    skip[y][x][now] = false; // 반사 후 방향은 다시 시도 가능하도록
									break;
								}

								int v = arr[y][x]; // 아직 현재 칸에 서 있음
								if (1 <= v && v <= 5) {
									score++; // 새 방향으로 재진입 -> 점수 추가
									now = changePosition(v, now);
								} else if (6 <= v && v <= 10) {
									now = changePosition(v, now); // 웜홀
								}
								continue;
							}

							//이동
							x = nx;
							y = ny;

							// 5) 블랙홀
							if (arr[y][x] == -1) {
								break;
							}

							// 6) 블록/웜홀 처리
							if (arr[y][x] != 0) {
								if (1 <= arr[y][x] && arr[y][x] <= 5) {
									score++;
								}
								now = changePosition(arr[y][x], now); // 웜홀은 x,y도 바뀜
							}

							// 7) 시작 좌표로 복귀(방향 무관)
							if (x == initX && y == initY) {
								break;
							}
						}

						best = Math.max(best, score);
					}
				}
			}

			System.out.println("#" + tc + " " + best);
		}
	}
}
