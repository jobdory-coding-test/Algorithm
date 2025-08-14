import java.util.*;
import java.io.*;

public class Main {
	private static int N, resultCnt;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		resultCnt = 0;
		nQueenBits(0, 0, 0);
		System.out.print(resultCnt);
	}
	
	// 현재까지 선택한 열, 왼쪽 대각선, 오른쪽 대각선
	private static void nQueenBits(int col, int diag1, int diag2) {
		// 모두 선택했다면
		if(col == (1<<N) - 1) {
			resultCnt++;
			return;
		}
		
		int possible = ~(col | diag1 | diag2) & ((1<<N)-1);
		
		// 선택 가능한 곳이 없을 때까지
		while(possible != 0) {
			// possible의 맨 오른쪽 1 비트 위치 뽑기 => 선택할 열의 위치
			int p = possible & -possible;
			possible -= p; // 뽑은 위치 제거
			
			nQueenBits(col | p, (diag1 | p)<<1, (diag2 | p)>>1);
		}
	}
}

/* 개선 전 코드 */
/*
public class Main {
	private static int N, resultCnt;
	private static int[] queenState; // 각 행에서 퀸의 위치 저장
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		resultCnt = 0;
		queenState = new int[N];
		Arrays.fill(queenState, -1);
		nQueenDfs(0);
		System.out.print(resultCnt);
	}
	
	private static void nQueenDfs(int cnt) {
		if(cnt == N) {
			resultCnt++;
			return;
		}
		
		for(int c=0; c<N; c++) {
			if(!isQueen(cnt, c)) {
				queenState[cnt] = c;
				nQueenDfs(cnt+1);
			}
		}
	}
	
	private static boolean isQueen(int row, int col) {
		for(int i=0; i<row; i++) {
			if(queenState[i] == col || Math.abs(queenState[i] - col) == Math.abs(row-i)) {
				return true;
			}
		}
		return false;
	}
}
*/