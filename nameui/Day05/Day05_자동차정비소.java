import java.io.*;
import java.util.*;

public class Day05_자동차정비소 {

	static int N, M, K, A, B;
	static int[] receptionTime;
	static int[] repairTime;
	static boolean[] reception;
	static boolean[] repair;

	// 큐 선언
	static PriorityQueue<int[]> pq; // 현재 사람의 인덱스, 접수 창구 번호, 끝나는 시간 - 여기는 들어가는 대기열
	static Queue<int[]> pq2; // 현재 사람의 인덱스, 정비 창구 번호, 정비 끝나는 시간 - 여기는 repair 하는 창구
	static Queue<int[]> receptionWait;
	static Queue<int[]> repairWait; // 현재 사람의 인덱스, 접수 창구 번호, 끝나는 시간
	static int[][] result;
	static int cnt; // 몇 명까지 끝났는지 확인
	static int res;

	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("src/input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		for (int t = 1; t <= T; t++) {

			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			init();
			
			// 상담 창구 소요 시간
			st = new StringTokenizer(br.readLine());
			receptionTime = new int[N];
			for (int i = 0; i < N; i++) {
				receptionTime[i] = Integer.parseInt(st.nextToken());
			}

			// 정비 창구 소요 시간
			st = new StringTokenizer(br.readLine());
			repairTime = new int[M];
			for (int i = 0; i < M; i++) {
				repairTime[i] = Integer.parseInt(st.nextToken());
			}

			// 1. 도착 시간 순서대로 비어 있는 대기열에 추가
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < K; i++) {
				receptionWait.add(new int[] { i, Integer.parseInt(st.nextToken()) }); // 고객 번호, 도착 시간
			}

			
			simulation();
			
			res = res == 0 ? -1 : res;
			System.out.println("#" + t + " " + res);
			

		}
	}
	
	static void init() {
		pq2 = new PriorityQueue<>(
				(a, b) -> {
					return Integer.compare(a[2], b[2]);
				}
		);
		receptionWait = new ArrayDeque<>();
		repairWait = new ArrayDeque<>();
		pq = new PriorityQueue<>((a, b) ->{
			
			if(a[2] == b[2]) {
				return Integer.compare(a[1], b[1]);
			}
			return Integer.compare(a[2], b[2]);
		
		});
		cnt = 0;
		res = 0;
		result = new int[K][2];
		reception = new boolean[N];
		repair = new boolean[M];
	}

	static void simulation() {
		int time = 0;

		while (true) {
			
			if(cnt >= K) {
				break;
			}
			
			// 접수 창구에서 볼 일 다 본 사람 빼주기
			moveToRepairWait(time);
			// 빈 접수 창구에 대기자 넣어주기
			startReception(time);
			// 정비 창구에서 볼 일 다 본 사람 빼주기
			ask(time);
			// 빈 정비 창구에 대기자 넣어주기
			startRepair(time);
			
			time++;
			
		}
	}

	// 여기서는 현재 시간과 같은 것들 repairWait 로 넣어주는 것?
	static void moveToRepairWait(int time) {
		// 현재 사람의 인덱스, 접수 창구 번호, 정비 끝나는 시간
		while (!pq.isEmpty() && pq.peek()[2] <= time) {
			int[] target = pq.poll();
			result[target[0]][0] = target[1];
			reception[target[1]] = false;
			repairWait.add(new int[] { target[0], target[1], target[2] });
		}
	}

	// 여기서 우선순위 큐에 들어가도록 계산하는 것?
	static void startReception(int time) {
		while (!receptionWait.isEmpty() && receptionWait.peek()[1] <= time) {
			boolean isPoss = false;

			for (int i = 0; i < N; i++) {
				if (reception[i]) { // 사용 중이라면
					continue;
				}

				reception[i] = true;
				int[] target = receptionWait.poll();
				pq.add(new int[] { target[0], i, time + receptionTime[i] });
				isPoss = true;
				break;
			}
			
			if(!isPoss) {
				break;
			}
		}
	}

	// 정비 창구에서 볼 일 다 본 사람 빼주기
	static void ask(int time) {
		while(!pq2.isEmpty() && pq2.peek()[2] <= time ) {
			int[] target = pq2.poll();
			result[target[0]][1] = target[1]; // 접수 창구 번호 기록
			repair[target[1]] = false; // 나갔으니까 다시 돌려놓기
			
			if(A == result[target[0]][0] + 1 && B == result[target[0]][1] + 1) {
				res += target[0]+1;
			}
			cnt++;
		}
	}

	// 정비 창구에 안내
	static void startRepair(int time) {
		while (!repairWait.isEmpty()) {
			boolean isPoss = false;

			for (int i = 0; i < M; i++) {
				if (repair[i]) {
					continue;
				}
				
				repair[i] = true;
				int[] target = repairWait.poll();
				pq2.add(new int[] {target[0], i, time + repairTime[i]});
				isPoss = true;
				break;
			}
			
			if(!isPoss) {
				break;
			}
		}
	}
}
