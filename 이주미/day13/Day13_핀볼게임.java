import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Day13_핀볼게임 {
	
	static StringBuilder sb = new StringBuilder();
	static int T, N;
	static int[][] arr;
//	static List<int[][]> wormholes = new LinkedList<>();
	static int[][][] wormholes; 
	static int[] dr = {1, 0, -1, 0}; //아래, 왼, 위, 오
	static int[] dc = {0, -1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		T = Integer.parseInt(st.nextToken());
		
		for(int tc=1; tc<=T; tc++) {
			sb.append("#"+tc+" ");
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			arr = new int[N][N];
			
			// 웜홀 페어 등록 전 초기화
			wormholes = new int[6][2][2];
			for(int i=0; i<6; i++) {
				wormholes[i][0][0] = -1;
				wormholes[i][0][1] = -1;
				wormholes[i][1][0] = -1;
				wormholes[i][1][1] = -1;
			}
			
			// arr 입력받기
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					
					// 웜홀 페어 등록
					if(arr[i][j] >=6 && arr[i][j] <=10) {
						int hole = arr[i][j]-6;
						if(wormholes[hole][0][0] == -1) {
							wormholes[hole][0][0] = i;
							wormholes[hole][0][1] = j;
						} else {
							wormholes[hole][1][0] = i;
							wormholes[hole][1][1] = j;
						}
					}
				}
			}
			
			sb.append(rungame()).append('\n');
		}
		System.out.println(sb);
	}
	
	private static int rungame() {
		int maxpoint = Integer.MIN_VALUE;
		
		// 모든 위치에 대해
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(arr[i][j]==0) {
					// 모든 방향에서
					for(int d=0; d<4; d++) {
						maxpoint = Math.max(maxpoint, runround(i, j, d));
					}
				}
			}
		}
		return maxpoint;
	}
	
	private static int runround(int r, int c, int d) {
		int originR = r, originC = c;
		int point = 0;
		int nr, nc;
		int[] pair;
		
		arr[originR][originC]=-1;
		while(true) {
			nr = r+dr[d];
			nc = c+dc[d];
			
			if(!isInMap(nr, nc)) {
				// map 밖이면 역방향 & point++
				d = (d+2)%4;
				nr = r; nc = c;
				point++;
			}
			
			if(arr[nr][nc]==-1) {
				// 블랙홀이면 종료
				break;
			} else if(arr[nr][nc]>=1 && arr[nr][nc]<=5) {
				d = changedir(arr[nr][nc], d);
				point++;
			} else if(arr[nr][nc]>=6 && arr[nr][nc]<=10) {
				pair = findPair(arr[nr][nc]-6, nr, nc);
				nr = pair[0];
				nc = pair[1];
			}
			
			r=nr; c=nc;
		}
		arr[originR][originC]=0;
		return point;
	}
	
	private static int changedir(int block, int d) {
		switch(block) {
			case 1:
				if(d==0) return 3;
				else if(d==1) return 2;
				else return (d+2)%4;
			case 2:
				if(d==2) return 3;
				else if(d==1) return 0;
				else return (d+2)%4;
			case 3:
				if(d==2) return 1;
				else if(d==3) return 0;
				else return (d+2)%4;
			case 4:
				if(d==0) return 1;
				else if(d==3) return 2;
				else return (d+2)%4;
			case 5:
				return (d+2)%4;
		}
		return 0;
	}
	
	private static int[] findPair(int hole, int r, int c) {
		if(wormholes[hole][0][0]==r && wormholes[hole][0][1]==c) {
			return new int[] {wormholes[hole][1][0], wormholes[hole][1][1]};
		}
		
		return new int[] {wormholes[hole][0][0], wormholes[hole][0][1]};
	}
	
	private static boolean isInMap(int r, int c) {
		return r>=0 && r<N && c>=0 && c<N; 
	}
}
