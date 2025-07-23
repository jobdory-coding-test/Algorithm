package Day01;
import java.io.*;
import java.util.*;

public class Day01_등산로조성 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static int K;
    static int ans;

    static int[][] board;
    static boolean[][] check;

    // 위, 아래, 왼, 오른
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String args[]) throws IOException {
        
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        
        for(int t = 1; t<=T; t++) {
	        st = new StringTokenizer(br.readLine());
	        N = Integer.parseInt(st.nextToken());
	        K = Integer.parseInt(st.nextToken());
	
	        // 값 초기화
	        board = new int[N][N];
	        check = new boolean[N][N];
	        ans = 0;
	
	        int max = 0;
	    
	        // 입력 받기
	        for(int i = 0; i<N; i++) {
	            st = new StringTokenizer(br.readLine());
	            for(int j = 0; j<N; j++) {
	                board[i][j] = Integer.parseInt(st.nextToken());
	                
	                max = Math.max(max, board[i][j]);
	            }
	        }
		        
	        // 시작 점 찾기
	        for(int i = 0; i<N; i++) {
	            for(int j = 0; j<N; j++) {
	                if(max == board[i][j]) {
	                    solution(i, j, 21, 0, false);
	                }
	            }
	        }
	
	        System.out.printf("#%d %d\n", t, ans);
        }
    }

    static void solution(int r, int c, int before, int cur, boolean isChanged) {
        if(!isDir(r, c) || check[r][c]) {
            return;
        }
        
        if(before > board[r][c]) {
        	check[r][c] = true;
        	
        	ans = Math.max(ans, cur+1);
        	
        	// 네 방향으로 감
        	for(int i = 0; i<4; i++) {
        		int nr = dr[i] + r;
        		int nc = dc[i] + c;
        		
        		before = board[r][c];
        		
        		solution(nr, nc, before, cur+1, isChanged);
        	}
        	
        	check[r][c] = false;
        }
        else {
        	/**
        	 * 내 로직 : board[r][c] 를  before 보다 작아질 때까지 깎음
        	 * 다른 사람 로직 : board[r][c] 를 before 보다 작아질 때까지 깎는게 아니라 그냥 바로 이전 값보다 하나만 작게 세팅
        	 * -> 이전 값 보다 1만 작은게 더 이득이고, board[r][c] 에서 K 를 뺐을 때 before 보다 작으면 당연히 그 -1된 값이 될 수 있으므로!
        	 */
            if(!isChanged && board[r][c] - K < before) {
            	isChanged = true;
            	int origin = board[r][c];
            	board[r][c] = before - 1;
            	solution(r, c, before, cur, isChanged);
            	board[r][c] = origin;
            	isChanged = false;
            }
        }
    }

    static boolean isDir(int nr, int nc) {
        return nr >= 0 && nr < N && nc >= 0 && nc < N;
    }
}