import java.io.*;
import java.util.*;

public class Solution {
	private static int N, answer;
	private static int[][] board, stairs, dist;
	private static ArrayList<int[]> person;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			person = new ArrayList<>();
			board = new int[N][N];
			stairs = new int[2][3];
			answer = Integer.MAX_VALUE;
			int tempIdx = 0;

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					board[r][c] = Integer.parseInt(st.nextToken());

					if (board[r][c] == 1) {
						person.add(new int[] { r, c });
					} else if (board[r][c] > 1) {
						stairs[tempIdx][0] = r;
						stairs[tempIdx][1] = c;
						stairs[tempIdx++][2] = board[r][c];
					}
				}
			}

			// 사람마다 각 계단 별 거리를 저장할 배열
			dist = new int[person.size()][2];
			for (int i = 0; i < person.size(); i++) {
				int r = person.get(i)[0];
				int c = person.get(i)[1];

				for (int j = 0; j < 2; j++) {
					dist[i][j] = Math.abs(r - stairs[j][0]) + Math.abs(c - stairs[j][1]);
				}
			}

			// 각 사람별로 어떤 계단을 선택할 지 고르는 for문
			// 0은 첫 번째 계단, 1은 두 번째 계단
			for (int mask = 0; mask < (1 << person.size()); mask++) {
				answer = Math.min(answer, goDownStair(mask));
			}

			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static int goDownStair(int select) {
		PriorityQueue<Integer>[] pq = new PriorityQueue[2];
		for (int i = 0; i < 2; i++) {
			pq[i] = new PriorityQueue<>();
		}
		// 최대 시간
		int time = N << 1;

		// 시작 할 시간을 구하고, 각 사람 별로 해당 계단 앞에 대기 시키기
		for (int i = 0; i < person.size(); i++) {
			int[] curr = person.get(i);
			int idx = ((select & (1 << i)) > 0) ? 1 : 0;

			time = Math.min(time, dist[i][idx]);
			pq[idx].add(dist[i][idx]);
		}

		Queue<Integer>[] q = new ArrayDeque[2];
		for (int i = 0; i < 2; i++) {
			q[i] = new ArrayDeque<>();
		}

		int count = person.size();

		while (count >= 1) {
			time++;

			// 먼저 계단에 있는 사람 한 칸 이동한다.
			for (int i = 0; i < 2; i++) {
				// 계단에 사람이 있고 도착할 시간이라면 도착 처리 해준다.
				while (!q[i].isEmpty() && q[i].peek() + stairs[i][2] == time) {
					q[i].poll();
					count--;
				}
			}

			// 도착하거나 대기하고 있는 사람을 계단에 넣는다.
			for (int i = 0; i < 2; i++) {
				// 대기자가 없을 경우는 패스'
				while (!pq[i].isEmpty()) {
					// 이미 계단이 꽉차 있거나, 아직 출발 할 시간이 아니라면 종료 어차피 뒤에 있는 얘들도 출발 할 시간이 아니다.
					if (q[i].size() == 3 || (!pq[i].isEmpty() && pq[i].peek() + 1 > time)) {
						break;
					}
					int curr = pq[i].poll();
					q[i].add(time);
				}
			}
		}
		return time;
	}
}