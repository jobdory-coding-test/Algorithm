import java.io.*;
import java.util.*;

public class Main {
	// 철도 클래스
	static class Rail {
		int prevNum = 0;
		int nextNum = 0;
		
		Rail(int prevNum, int nextNum) {
			this.prevNum = prevNum;
			this.nextNum = nextNum;
		}
	}
	
	static int N, M, size;
	static Rail[] rail;
	static int[] status;
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		size = 0;
		
		rail = new Rail[1000003];
		status = new int[1000003];
		st = new StringTokenizer(br.readLine());
		int[] tmp = new int[N];
		for(int i=0; i<N; i++) {
			tmp[i] = Integer.parseInt(st.nextToken());
			status[tmp[i]] = 1;
			size++;
		}
		
		// rail 배열에 역 연결
		for(int i=0; i<N; i++) {
			int prev = tmp[((i-1) + N)%N];
			int next = tmp[(i+1)%N];
			rail[tmp[i]] = new Rail(prev, next);
		}
		
		// 공사 시작
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			String ctr = st.nextToken();
			int cur = 0;
			int next = 0;
			if(ctr.equals("BN") || ctr.equals("BP")) {
				cur = Integer.parseInt(st.nextToken());
				next = Integer.parseInt(st.nextToken());
				railAdd(ctr, cur, next);
			} else if(size >= 2 && (ctr.equals("CN") || ctr.equals("CP"))) {
				cur = Integer.parseInt(st.nextToken());
				railRemove(ctr, cur);
			}
		}
		
		System.out.println(sb);
		br.close();
	}
	
	static void railAdd(String ctr, int cur, int next) {
		// next 이미 설립된 역
		if(status[next] == 1) return;
		
		// 1. cur번호 nextNum역 번호 출력 -> cur과 nextNum 사이에 next 역 추가
		if(ctr.equals("BN")) {
			sb.append(rail[cur].nextNum).append("\n");
			rail[next] = new Rail(cur, rail[cur].nextNum);
			rail[rail[cur].nextNum].prevNum = next;
			rail[cur].nextNum = next;
		}
		// 2. cur번호 prevNum역 번호 출력 -> cur과 nextNum 사이에 next 역 추가
		else if(ctr.equals("BP")) {
			sb.append(rail[cur].prevNum).append("\n");
			rail[next] = new Rail(rail[cur].prevNum, cur);
			rail[rail[cur].prevNum].nextNum = next;
			rail[cur].prevNum = next;
		}
		status[next] = 1;
		size++;
	}
	
	static void railRemove(String ctr, int cur) {
		// 1. cur번호 nextNum역 번호 출력 -> nextNum 역 폐쇄
		if(ctr.equals("CN")) {
			sb.append(rail[cur].nextNum).append("\n");
			// 현재 위치에서 다다음 역 번호
			int next = rail[cur].nextNum;
			int next2 = rail[rail[cur].nextNum].nextNum;
			rail[next2].prevNum = cur;
			rail[cur].nextNum = next2;
			rail[next].prevNum = 0;
			rail[next].nextNum = 0;
			status[next] = 0;
		}
		// 2. cur번호 prevNum역 번호 출력 -> prevNum 역 폐쇄
		else if(ctr.equals("CP")) {
			sb.append(rail[cur].prevNum).append("\n");
			// 현재 위치에서 전전 역 번호
			int prev = rail[cur].prevNum;
			int prev2 = rail[rail[cur].prevNum].prevNum;
			rail[prev2].nextNum = cur;
			rail[cur].prevNum = prev2;
			rail[prev].prevNum = 0;
			rail[prev].nextNum = 0;
			status[prev] = 0;
		}
		size--;
	}

}
