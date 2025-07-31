import java.io.*;
import java.util.*;

public class Solution {
	private static int N, M, K, A, B;
	private static int[] infoA, infoB;
	private static boolean[] visitedA, visitedB;
	private static Queue<int[]> guest;
	private static PriorityQueue<int[]> workA;
	private static PriorityQueue<int[]> workB;
	private static Queue<int[]> waitB;
	private static int count;

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
			K = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			init();

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				infoA[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= M; i++) {
				infoB[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= K; i++) {
				int time = Integer.parseInt(st.nextToken());
				guest.add(new int[] { i, time });
			}
			sb.append("#").append(t).append(" ").append(simulate()).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static int nextTime(int time) {
		int result = Integer.MAX_VALUE;
		if (!workA.isEmpty()) {
			result = Math.min(result, workA.peek()[1]);
		}

		if (!workB.isEmpty()) {
			result = Math.min(result, workB.peek()[1]);
		}

		if (!guest.isEmpty() && guest.peek()[1] > time) {
			result = Math.min(result, guest.peek()[1]);
		}
		return (result > time) ? result : time + 1;
	}

	private static int simulate() {
		int time = guest.peek()[1];
		int result = 0;

		while (count < K) {
			// 접수 창구가 끝났다면 차량 정비 대기 줄에 진입
			moveToRepairWaitingLine(time);
			// 손님이 도착 한 시간에 접수 창구가 비어있다면 접수 창구에 넣기
			assignArrivalsToReceptions(time);
			// 정비 창구에서 할 일을 끝낸 사람은 설문조사
			result += processRepairCompletions(time);
			// 차량 정비 대기하는 사람들을 순서대로 차량 정비 시작
			startRepairsForWaitingGuests(time);
			// 다음 시간으로 이동
			time = nextTime(time);
		}
		return result == 0 ? -1 : result;
	}

	private static void moveToRepairWaitingLine(int time) {
		while (!workA.isEmpty() && workA.peek()[1] <= time) {
			int[] curr = workA.poll();
			// 정비 창구 접수 종료했기 때문에 false 처리
			visitedA[curr[2]] = false;
			waitB.add(curr);
		}
	}

	private static void assignArrivalsToReceptions(int time) {
		while (!guest.isEmpty() && guest.peek()[1] <= time) {
			boolean isPoss = false;

			for (int i = 1; i <= N; i++) {
				// 만약 현재 index 창구가 접수 중이라면 건너띄기
				if (visitedA[i]) {
					continue;
				}
				// 비어있는 접수창구로 가고
				// workA 우선순위큐에 넣기
				int[] curr = guest.poll();
				visitedA[i] = true;
				isPoss = true;
				workA.add(new int[] { curr[0], time + infoA[i], i });
				break;
			}
			// 만약 모든 접수 창구가 이용중이라면 대기하기
			if (!isPoss) {
				break;
			}
		}
	}

	private static int processRepairCompletions(int time) {
		int result = 0;

		// 차량 정비가 다 마무리 된다면
		while (!workB.isEmpty() && workB.peek()[1] <= time) {
			int[] curr = workB.poll();
			visitedB[curr[3]] = false;
			count++;
			
			// A 창구와 B 창구를 들렸다면 result에 더하기
			if (curr[2] == A && curr[3] == B) {
				result += curr[0];
			}
		}
		return result;
	}

	private static void startRepairsForWaitingGuests(int time) {
		while (!waitB.isEmpty()) {
			boolean isPoss = false;

			for (int i = 1; i <= M; i++) {
				// 해당 정비 창구가 이용중이라면 건너띄기
				if (visitedB[i]) {
					continue;
				}

				// 해당 정비 창구에
				int[] curr = waitB.poll();
				isPoss = true;
				visitedB[i] = true;
				workB.add(new int[] { curr[0], time + infoB[i], curr[2], i });
				break;
			}

			// 모든 정비 창구가 이용중이라면 대기
			if (!isPoss) {
				break;
			}
		}
	}

	private static void init() {
		guest = new ArrayDeque<>();
		infoA = new int[N + 1];
		infoB = new int[M + 1];
		visitedA = new boolean[N + 1];
		visitedB = new boolean[M + 1];
		workA = new PriorityQueue<>((a, b) -> a[1] == b[1] ? a[2] - b[2] : a[1] - b[1]);
		workB = new PriorityQueue<>((a, b) -> a[1] - b[1]);
		waitB = new ArrayDeque<>();
		count = 0;
	}
}