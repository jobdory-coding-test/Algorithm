import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Day07_보이저1호 {
	
	private static int N, M;
	private static int[][] arr; // C는 -1 '/'는 1, '\'는 2
	private static boolean[][][] visited;
	
	// 위 오른쪽 아래 왼쪽
	private static int maxtime;
	private static int maxdir;
	private static int[] dr = {-1, 0, 1, 0};
	private static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1][M+1];
		
		String str;
		char[] carr = new char[M];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			str = st.nextToken();
			carr = str.toCharArray();
			for(int j=0; j<M; j++) {
				switch(carr[j]) {
					case 'C': arr[i][j+1] = -1; break;
					case '/': arr[i][j+1] = 1; break;
					case '\\': arr[i][j+1] = 2; break;
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int PR = Integer.parseInt(st.nextToken());
		int PC = Integer.parseInt(st.nextToken());
		
		boolean isInfinite=false;
		for(int dir=0; dir<4; dir++) {
			// U, R, D, L 순서로 탐색, 무한 루프 있으면 중단
			visited = new boolean[N+1][M+1][4];
			int res = voyage(PR, PC, dir);
			
			if(res <0) {
				maxdir = dir;
				isInfinite = true;
				break;
			}
			
			if(res>maxtime) {
				maxdir = dir;
				maxtime = res;
			}
		}
		
		switch(maxdir) {
			case 0: sb.append("U\n"); break;
			case 1: sb.append("R\n"); break;
			case 2: sb.append("D\n"); break;
			case 3: sb.append("L\n"); break;
		}
		
		if(isInfinite) {
			sb.append("Voyager");
		} else {
			sb.append(maxtime);
		}
		
		System.out.println(sb.toString());
	}
	
	private static int voyage(int r, int c, int d) {
		int time=1;
		while(true) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			
			// 앞 위치가범위 밖이거나  블랙홀이거면 중단
			if(nr<1 || nr>N || nc<1 || nc>M) {
				break;
			} else if(arr[nr][nc]<0) {
				break;
			}

			// 실제 이동
			r = nr;
			c = nc;
			
			// visited 확인으로 왔었는지 검증
			if(visited[r][c][d]) {
				time = -1;
				break;
			} else {
				visited[r][c][d] = true;
			}

			
			// 앞 위치에 장애물이 있으면 경우의 수를 고려해서 방향 회전
			if(arr[nr][nc]>0) {
				d = changeDir(d, arr[nr][nc]);
			}
						
			// 시간 증가
			time++;
		}
		return time;
	}
	
	// 위:0,  오른쪽:1,  아래:2,  왼쪽:3
	private static int changeDir(int origindir, int planet) {
		if(planet==1) {
			// 행성이 '/'
			switch(origindir) {
				case 0: return 1;
				case 1: return 0;
				case 2: return 3;
				case 3: return 2;
			}
		} else {
			// 행성이 '\'
			switch(origindir) {
				case 0: return 3;
				case 1: return 2;
				case 2: return 1;
				case 3: return 0;
			}
		}
		return -1;
	}

}
