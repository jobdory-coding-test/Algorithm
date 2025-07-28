import java.io.*;
import java.util.*;

class Day05_자동차정비소 {

	static PriorityQueue<int[]> queue;
	static int N;
	static int M;
	static int K;

	static int A;
	static int B;

	static int[] arr1_time;
	static int[] arr2_time;
	static int[][] res;

	static int[][] arr1_cnt; // 접수 창구 count
	static int[][] arr2_cnt; // 정비 창구 count
	static int last; // 마지막 숫자

	public static void main(String args[]) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));

		/*
		 * 표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T;
		T = Integer.parseInt(st.nextToken());
		/*
		 * 여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		 */

		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			arr1_time = new int[N]; // 접수 창구 N개 걸리는 시간
			arr2_time = new int[M]; // 정비 창구 M개 거리는 시간

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr1_time[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				arr2_time[i] = Integer.parseInt(st.nextToken());
			}

			// 접수 창구의 개수 N과 정비 창구의 개수 M은 1 이상 9 이하의 정수이다. (1 ≤ N, M ≤ 9)
			// 9까지니까, 접수 창구, 정비 창구 각 큐로 만들기

			queue = new PriorityQueue<>((a, b) -> {
				// 0번째 값이 다르면 그걸로 정렬
				if (a[0] != b[0])
					return Integer.compare(a[0], b[0]);
				// 0번째 값이 같으면 1번째 값으로 정렬
				return Integer.compare(a[1], b[1]);
			});

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < K; i++) {
				queue.add(new int[] {Integer.parseInt(st.nextToken()), i+1}); // 도착 시간, 고객 번호 순으로 저장 
			}

			// 각 사람마다 방문한 상담 창구 저장 2차원 배열
			res = new int[K][2];
			arr1_cnt = new int[N][2]; // 접수 창구 count, 0 은 작업 시간, 1은 그 자리에서 작업 중인 사람
			arr2_cnt = new int[M][2]; // 정비 창구 count

			solution();

			int ans = 0;

			for (int i = 0; i < K; i++) {
				if (res[i][0] == A && res[i][1] == B) {
					ans += i + 1;
				}
			}

			System.out.println(ans == 0 ? -1 : ans);
		}
	}

	static void solution() {
		int t = 0;
//		int cur = 0;

		while (true) {
			// 일단 업무 진행 중인 창구들 다 +1
			for (int n = 0; n < N; n++) {
				// 상담 창구가 0이 아닌 곳 다 +1
				if (arr1_cnt[n][0] != 0) {
					arr1_cnt[n][0] += 1;
				}
			}
			for (int m = 0; m < M; m++) {
				// 정비 창구가 0이 아닌 곳 다 +1
				if (arr2_cnt[m][0] != 0) {
					arr2_cnt[m][0] += 1;
				}
			}

			boolean check = true;
			while (check) {
				if (queue.isEmpty() || queue.peek()[0] > t) { // 도착 원소 없다면
//					t++;
					break;
				}
				for (int i = 0; i < N; i++) {
					// 0 인 값이 있으면, 빈 상담 창구가 있음
					if (arr1_cnt[i][0] == 0) {
						int[] data = queue.poll();
						arr1_cnt[i][0] += 1;
						arr1_cnt[i][1] = data[1]-1;
						res[data[1]-1][0] = i + 1; // 몇 번 접수 창구에서 했는지 저장
						check = true;
						break;
					}
					check = false;

					// 0 인 값이 없으면, 빈 상담 창구가 없음 : 아무 작업 안하고 바로 상담, 정비 창구 처리로 넘어감.
				}
			}

			// 상담 창구, 정비 창구 각각 업무 다 끝낸 애들 처리해주기
			// 끝낸 애들의 인덱스를 어떻게 알 수 있지..
			for (int n = 0; n < N; n++) {
				if (arr1_cnt[n][0] < arr1_time[n]) {
					continue;
				}
				// 상담 창구 끝난 애들 정비 창구로 이동
				for (int m = 0; m < M; m++) {
					if (arr2_cnt[m][0] == 0) {
						arr1_cnt[n][0] = 0;
						arr2_cnt[m][0] = 1;

						res[arr1_cnt[n][1]][1] = m + 1;

						break;
						// 이제 남은 작업이 없을 때. 마지막 원소까지 다 정비소에 도착했을 때.
// 여기가 잘못된듯. 마지막에 잇던 원소가 제일 늦게 나가지 않을 수도 잇름
// arr1_cnt 가 아예 비워져 있고, 대기 중인 큐가 없다면 끝난 것?
					}
				}
				
				if (check()) {
					return;
				}
			}

			for (int m = 0; m < M; m++) {
				// 정비 창구 끝난 애들 내보내기
				if (arr2_cnt[m][0] >= arr2_time[m]) {
					arr2_cnt[m][0] = 0;
				}
			}

			t++;
		}
	}

	static boolean check() {
		boolean res = true;
		for (int i = 0; i < N; i++) {
			if (arr1_cnt[i][0] != 0) {
				return false;
			}
		}

		return res && queue.isEmpty();
	}

}