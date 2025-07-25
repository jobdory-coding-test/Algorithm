import java.io.*;
import java.util.*;

public class Day03_트럭 {
	private static int N, W, L;
	private static int[] trucks;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		trucks = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			trucks[i] = Integer.parseInt(st.nextToken());
		}
		System.out.println(simulate());
	}

	private static int simulate() {
		int time = 0;
		int idx = 0;
		Queue<Integer> bridge = new ArrayDeque<>();
        
        // 먼저 다리에 0을 w만큼 넣습니다.
		for (int i = 0; i < W; i++) {
			bridge.add(0);
		}
		int l = 0;

        /**
         * while 동작 원리
         * 1. 도착지에 도착한 것 빼주기
         * 2. 현재 다리에 올라간 트럭의 무게 + 출발지에 대기하고 있는 트럭 무게가 최대 하중을 버틸 수 있는지 검사
         * 3-1. 버틸 수 있을 때 대기하고 있는 트럭 다리(큐)에 넣기
         * 3-2. 버틸 수 없을 때 다리(큐)에 0넣기
         * 인덱스가 배열의 마지막 인덱스로 왔을 경우 break
         * 마지막 트럭은 어차피 w만큼 이동해야 종료된다.
         * return 값은 time + w로 한다.
         */
		while (true) {
			if (idx == N) {
				break;
			}

			time++;
			l -= bridge.poll();
			
			if (l + trucks[idx] <= L) {
				l += trucks[idx];
				bridge.add(trucks[idx++]);
			} else {
				bridge.add(0);
			}
		}
		return time + W;
	}
}