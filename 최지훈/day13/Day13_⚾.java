import java.util.*;
import java.io.*;

public class Main {
	private static int N, maxValue = Integer.MIN_VALUE;
	private static int[][] inning;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		inning = new int[N][9];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<9; j++) {
				inning[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] playerArr = new int[9];
		boolean[] visited = new boolean[9];
		visited[0] = true;
		nextPerm(0, visited, playerArr);
		
		System.out.print(maxValue);
	}
	
	private static void nextPerm(int cnt, boolean[] visited, int[] playerArr) {
		// 순열 경우의 수 하나 완성하면 N번의 이닝을 돌며 최댓값 갱신
		if (cnt == 9) {
			maxValue = Math.max(maxValue, calculateInning(playerArr));
			return;
		}
		
		// 1번 선수는 4번 타자 고정
		if(cnt == 3) {
			playerArr[3] = 0;
			nextPerm(cnt+1, visited, playerArr);
		}
		else {
			for(int i=1; i<9; i++) {
				if(!visited[i]) {
					visited[i] = true;
					playerArr[cnt] = i;
					nextPerm(cnt+1, visited, playerArr);
					visited[i] = false;
				}
			}
		}
	}
	
	// 이닝 돌기
	private static int calculateInning(int[] playerArr) {
		int sum = 0;
		int idx = 0;
		for(int i=0; i<N; i++) {
			int[] state = new int[3]; // 1루, 2루, 3루 상태
			int outCnt = 0;
			while(true) {
				// 얻는 점수
				int num = inning[i][playerArr[idx]];
				int playerNum = playerArr[idx] + 1;
				// i번째 이닝에서 idx번 타자가 아웃이라면
				if(num == 0) {
					outCnt++;
				} else if (num == 1) { // 안타
					if(state[2] > 0) sum++;
					for(int k=2; k>0; k--) {
						state[k] = state[k-1];
					}
					state[0] = playerNum;
				} else if (num == 2) { // 2루타
					for(int k=2; k>0; k--) {
						if(state[k] > 0) sum++;
					}
					state[2] = state[0];
					state[1] = playerNum;
					state[0] = 0;
				} else if (num == 3) { // 3루타
					for(int k=2; k>=0; k--) {
						if(state[k] > 0) {
							sum++;
							state[k] = 0;
						}
					}
					state[2] = playerNum;
				} else if (num == 4) { // 홈런
					sum++; // 타자
					for(int k=2; k>=0; k--) {
						if(state[k] > 0) {
							sum++;
							state[k] = 0;
						}
					}
				}
				idx = (idx + 1) % 9;
				
				if(outCnt == 3) break;
			}
		}
		
		return sum;
	}
}
