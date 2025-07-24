import java.io.*;
import java.util.*;

public class Main {
	static int N, W, L;
	static int[] truck;

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		truck = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			truck[i] = Integer.parseInt(st.nextToken());
		}

		simulate();

		br.close();
	}

	static void simulate() {
		/**
		 * 1. 큐에 담긴 요소들에 대해서 나갈 요소들이 있는지 확인 -> 삭제 
		 * 2. 큐 요소 합 + 들어갈 트럭 무게 <= L && 큐의 길이 < W -> 삽입
		 * { 무게, 투입 시간 }
		 */
		Queue<int[]> q = new ArrayDeque<>();

		int sum = 0, nowTime = 0, idx = 0;

		// 트럭 배열 순회
		while (idx < N) {
			// 맨 앞 트럭이 다리를 모두 건넜다면 삭제
			if (!q.isEmpty() && nowTime - q.peek()[1] >= W) {
				sum -= q.peek()[0];
				q.poll();
			}
			// 트럭이 다리를 건널 수 있다면 삽입
			if (sum + truck[idx] <= L && q.size() < W) {
				q.add(new int[] { truck[idx], nowTime });
				sum += truck[idx];
				idx++;
			}
			nowTime++;
		}
		
		// 다리에 남아있는 트럭 처리
		while(!q.isEmpty()) {
			if (nowTime - q.peek()[1] >= W) {
				q.poll();
			}
			nowTime++;
		}

		System.out.println(nowTime);
	}
}