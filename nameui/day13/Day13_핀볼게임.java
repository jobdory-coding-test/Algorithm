import java.io.*;
import java.util.*;


public class Day13_핀볼게임 {
	
	// 상수
	static final int R = 0;
	static final int C = 1;
	static final int LEFT = 0;
	static final int UP = 1;
	static final int RIGHT = 2;
	static final int DOWN = 3;

	// 좌, 상, 우, 하.
	static final int[][] dir = { { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 } };

	static int N;
	static int[][] board;
	static int[] start;
	static int max;
	static ArrayList<ArrayList<int[]>> pair;
	static int[] jumpResult;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			board = new int[N][N];

			// 웜홀 페어 저장소.
			pair = new ArrayList<>();
			for (int i = 0; i < 6; i++) {
			    pair.add(new ArrayList<int[]>());
			}
			
			ArrayList<int[]> startPoint = new ArrayList<>();
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					board[r][c] = Integer.parseInt(st.nextToken());
					if (board[r][c] == 0) {
						startPoint.add(new int[] { r, c }); // 시작점 입력받기.
						continue;
					}
					if (6 <= board[r][c] && board[r][c] <= 10) {
						// 웜홀 어떻게 저장할 건지 생각해보기 : ArrayList 에 저장.
						int index = board[r][c];
						pair.get(index%6).add(new int[] {r, c});
					}
				}
			}

			max = 0;
			jumpResult = new int[2];
			for (int i = 0; i < startPoint.size(); i++) {
				start = startPoint.get(i);
				solve(start[R], start[C]);
			}

			sb.append("#").append(t).append(" ").append(max).append("\n");

		}
		System.out.println(sb.toString());
	}

	static void solve(int r, int c) {

		for (int d = 0; d < 4; d++) {
			int sum = 0;
			
			int temp_r = r;
			int temp_c = c;
			int temp_d = d;
			

			while (true) {

				// nr, nc 부터 하는 이유는 시작점으로 시작하기 때문이다!
				int nr = temp_r + dir[temp_d][R];
				int nc = temp_c + dir[temp_d][C];
				
				// nr, nc 가 출발지점이거나, 블랙홀이거나.
				if (isRange(nr, nc) && ((nr == start[R] && nc == start[C]) || board[nr][nc] == -1))	{
					max = Math.max(max, sum);
					break;
				}
				
				
				// 벽에 부딪혔을 경우.
				if (!isRange(nr, nc)) {
					temp_d = reverse(temp_d);
					sum++;
					temp_r = nr;
					temp_c = nc;
					continue;
				}
				
				

				// 삼각형, 네모일 때 방향 전환.
				if (isChangeTarget(board[nr][nc])) {
					temp_d = changeDir(board[nr][nc], temp_d);
					temp_r = nr;
					temp_c = nc;
					sum++;
					continue;
				}

				// 웜홀일 때.
				if (isWormWhole(board[nr][nc])) {
					jump(nr, nc, board[nr][nc] % 6);
					temp_r = jumpResult[R];
					temp_c = jumpResult[C];
					continue;
				}
				
				// 0 일 경우, 다음 위치로 이동을 위한 r, c 값 업데이트
				temp_r = nr;
				temp_c = nc;
			}
		}
	}

	// 직각 방향 : 좌 상 우 하.
	static int changeDir(int num, int d) {
		// 몇 번째 삼각형인지와 어느 방향에서 온 건지 체크해서 d 갱신해주기. 점수도 카운트
		// 삼각형인데, 직각 변환일 때
		if (num == 1 && (d == DOWN || d == LEFT)) {
			return d == DOWN ? RIGHT : UP;
		} else if (num == 2 && (d == UP || d == LEFT)) {
			return d == UP ? RIGHT : DOWN;
		} else if (num == 3 && (d == UP || d == RIGHT)) {
			return d == UP ? LEFT : DOWN;
		} else if (num == 4 && (d == DOWN || d == RIGHT)) {
			return d == DOWN ? LEFT : UP;
		} else { // 삼각형인데, 반대 변환일 때(위의 경우 제외하면 다 반대 변환임)
			// 0 <-> 2 | 1 <-> 3
			return reverse(d);
		}
	}

	// 반대 방향
	static int reverse(int d) {
		return (d + 2) % 4;
	}

	// 웜홀
	static void jump(int r, int c, int index) {
		// 어떤 웜홀인이 체크하고 r, c 갱신해주기. 점수 노카운트
		int[] first = pair.get(index).get(0);
		int[] second = pair.get(index).get(1);

		jumpResult = first[R] == r && first[C] == c? second:first;
	}

	// 범위 체크 : 벽인지 아닌지 체크
	static boolean isRange(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}

	// 방향 전환해주는 것, 삼각형 어디로 돌릴지 체크.
	static boolean isChangeTarget(int num) {
		return 1 <= num && num <= 5;
	}

	static boolean isWormWhole(int num) {
		return 6 <= num && num <= 10;
	}

}
