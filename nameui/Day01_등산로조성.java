import java.io.*;
import java.util.*;

public class Day01_등산로조성 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static int K;

    static int cur = 0;
    static int ans = 0;
    static int[][] board;
    static boolean[][] check;
    static boolean isChanged;

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
	        isChanged = false;
	        cur = 0;
	        ans = 0;
	
	        int max = 0;
	    
	        // 입력 받기
	        for(int i = 0; i<N; i++) {
	            st = new StringTokenizer(br.readLine());
	            for(int j = 0; j<N; j++) {
	                board[i][j] = Integer.parseInt(st.nextToken());
	                // 최댓값 한번 알아내기
	                if(max < board[i][j]) {
	                    max = board[i][j];
	                }
	            }
	        }
	
	        // 시작 점 찾기
	        for(int i = 0; i<N; i++) {
	            for(int j = 0; j<N; j++) {
	                if(max == board[i][j]) {
	                	cur = 0;
	                    solution(i, j, 21);
	                }
	            }
	        }
	
	        System.out.printf("#%d %d\n", t, ans);
        }
    }

    // 이게 일단, 한 보드에서 최대 값 찾아내는 것..?
    static void solution(int r, int c, int before) {
        if(check[r][c]) {
            return;
        }
        if(before <= board[r][c]) {
            if(board[r][c] - before < K && !isChanged) {
            	isChanged = true;
                for(int k = 1; k <= board[r][c] - before + 1; k++) {
                	int temp = board[r][c];
                    board[r][c] = board[r][c] - k;
                    
                    solution(r, c, before);

                    board[r][c] = temp;
                }
                isChanged = false;
            }
            return;
        }

        check[r][c] = true;
        if(ans < ++cur) {
            ans = cur;
        }

        for(int i = 0; i<4; i++) {
            int nr = dr[i] + r;
            int nc = dc[i] + c;

            if(isDir(nr, nc)) {
                before = board[r][c];
                solution(nr, nc, before);
            }
        }

        check[r][c] = false;
        cur--;
    }

    static boolean isDir(int nr, int nc) {
        return nr >= 0 && nr < N && nc >= 0 && nc < N;
    }
}