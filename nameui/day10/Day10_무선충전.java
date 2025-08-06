import java.io.*;
import java.util.*;

public class Day10_무선충전 {

	static final int Y = 0; // Y 인덱스 저장.
	static final int X = 1; // X 인덱스 저장.

	static int A, M;
	static int[] userA; // A 사용자 경로.
	static int[] userB; // B 사용자 경로.

	static int[][] center; // 좌표
	static int[] range; // 각 센터 유효 거리.
	static int[] power; // 각 센터 충전 성능.
	static int ans; // 최종 답.

	static int[] UA; // A 사용자 시작점.
	static int[] UB; // B 사용자 시작점.

	static int[][] dir = { { 0, 0 }, { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; // 좌표 이동.

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			ans = 0;
			
			// 사용자 이동 정보 받아오기.
			userA = new int[M + 1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i < M + 1; i++) {
				userA[i] = Integer.parseInt(st.nextToken());
			}
			
			userB = new int[M + 1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i < M + 1; i++) {
				userB[i] = Integer.parseInt(st.nextToken());
			}

			// 충전소 위치, 유효 거리, 성능 받아오기.
			center = new int[A + 1][2];
			range = new int[A + 1];
			power = new int[A + 1];
			for (int i = 1; i < A + 1; i++) {
				st = new StringTokenizer(br.readLine());
				// 충전소 좌표
				center[i][X] = Integer.parseInt(st.nextToken());
				center[i][Y] = Integer.parseInt(st.nextToken());

				// 충전소 유효 거리
				range[i] = Integer.parseInt(st.nextToken());

				// 충전소 성능
				power[i] = Integer.parseInt(st.nextToken());
			}

			// 출발 전, 즉 초기 위치에서도 충전 가능하므로 0부터 시작 ㅇㅇ.
			UA = new int[] { 1, 1 };
			UB = new int[] { 10, 10 };
			
			for (int s = 0; s < M + 1; s++) {
				charge(s);
			}
			
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.println(sb.toString());
	}

	static void charge(int sec) {
		// sec 에 따라 이동
		int a = userA[sec]; // 이동 유형 받아오기.
		int b = userB[sec]; // 이동 유형 받아오기.

		// 이동.
		UA[Y] += dir[a][Y];
		UA[X] += dir[a][X];
		
		UB[Y] += dir[b][Y];
		UB[X] += dir[b][X];
		
		// 가능한 충전소 인덱스 저장하는 곳.
		ArrayList<Integer> AC = new ArrayList<>();
		ArrayList<Integer> BC = new ArrayList<>();

		// 가능한 충전소 찾기.
		for(int i = 1; i<A+1; i++) {
			int da = Math.abs(center[i][Y] - UA[Y]) + Math.abs(center[i][X] - UA[X]);
			int db = Math.abs(center[i][Y] - UB[Y]) + Math.abs(center[i][X] - UB[X]);
			
			if(da <= range[i]) {
				AC.add(i);
			}
			if(db <= range[i]) {
				BC.add(i);
			}
			
		}	

		int max = getMax(AC, BC);
		
		// 최종적으로 큰 값을 최종 답에 누적한다.
		ans += max;
				
	}

	static int getMax(ArrayList<Integer> a, ArrayList<Integer> b) {
		int max = 0;
		
		// 둘 다 안될 때
		if(a.size() == 0 && b.size() == 0) return max;
		
		// a or b 중에 하나만 가능할 때.
		if(a.size() == 0) {
			int mb = 0;
			for(int i = 1; i<b.size(); i++) {
				if(power[b.get(mb)] < power[b.get(i)]) {
					mb = i;
				}
			}
			
			max += power[b.get(mb)];
			
			return max;
		}
		if(b.size() == 0) {
			int ma = 0;
			for(int i = 1; i<a.size(); i++) {
				if(power[a.get(ma)] < power[a.get(i)]) {
					ma = i;
				}
			}
			max += power[a.get(ma)];
			return max;
		}
		
		// A, B 가능한 충전소들 개수로 조합으로 max 값 구하기
		for (int i = 0; i < a.size(); i++) {
			for (int j = 0; j < b.size(); j++) {
				int powerA = a.get(i);
				int powerB = b.get(j);

				// 만약 조합에서 둘 다 선택한 충전소 번호가 같으면 충전소 하나만 더해주고 max 값 갱신해본다.
				if (powerA == powerB) {
					max = Math.max(max, power[powerA]);
					continue;
				}
				
				max = Math.max(max, power[powerA] + power[powerB]);
			}
		}

		return max;
	}
}
