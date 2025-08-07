import java.io.*;
import java.util.*;

public class SWEA4013_2 {

	// 상수
	static final int N = 0;
	static final int S = 1;

	static final int TARGET = 0;
	static final int DIRECTION = 1;

	// 점수표 : 바퀴 별로 점수 기록.
	static final int[][] score = new int[][] { { 0, 1 }, { 0, 2 }, { 0, 4 }, { 0, 8 } };

	static int K;
	static int[][] arr;
	static int[] arrow; // 화살표가 어디를 가르키는지 담는 배열.
	static int[] info; // 어느 방향으로 이동했는지 담는 배열.
	static int[][] dir; // 어느 톱니바퀴를, 어느 방향으로 이동하는지 담는 배열.

	static int ans;

	public static void main(String[] args) throws IOException {

		System.setIn(new FileInputStream("src/input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());

			// 회전 횟수 받기.
			K = Integer.parseInt(st.nextToken());

			// 자석 상태 받기.
			arr = new int[4][8];
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 초기화
			arrow = new int[4]; // 0, 0, 0, 0 으로 시작.

			// 회전 정보 받기.
			dir = new int[K][2];
			for (int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine());
				dir[k][TARGET] = Integer.parseInt(st.nextToken()) - 1;
				dir[k][DIRECTION] = Integer.parseInt(st.nextToken());
			}

			// 각 회전 수행
			for (int k = 0; k < K; k++) {
				info = new int[4];
				solution(dir[k][TARGET], dir[k][DIRECTION]);
				cal(); // arrow 위치 업데이트
			}

			// 점수 계산
			ans = 0;
			for (int i = 0; i < 4; i++) {
				int index = arrow[i];
				if (arr[i][index] == S) {
					ans += score[i][S];
				} else {
					ans += score[i][N];
				}
			}

			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}

		System.out.println(sb.toString());
	}

	static void solution(int target, int dir) {
		info[target] = dir;

		// 왼쪽 방향
		for (int i = target - 1; i >= 0; i--) {
			int right = (arrow[i] + 2) % 8;
			int left = (arrow[i + 1] + 6) % 8;

			if (arr[i][right] != arr[i + 1][left]) {
				info[i] = -1 * info[i + 1]; // 반대 방향으로 회전
			} else {
				break;
			}
		}

		// 오른쪽 방향으로 전파 확인
		for (int i = target + 1; i < 4; i++) {
			int left = (arrow[i] + 6) % 8;
			int right = (arrow[i - 1] + 2) % 8;

			if (arr[i][left] != arr[i - 1][right]) {
				info[i] = -1 * info[i - 1];
			} else {
				break;
			}
		}
	}

	static void cal() {
		for (int i = 0; i < 4; i++) {
			if (info[i] != 0) {
				arrow[i] = (arrow[i] - info[i] + 8) % 8;
			}
		}
	}
}