import java.io.*;
import java.util.*;
 
public class Day01_등산로조성 {
    private static int N, K, answer;
    private static int[][] board;
    private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    private static boolean[][] visited;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
 
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            board = new int[N][N];       
            int max = 0;
 
            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    board[r][c] = Integer.parseInt(st.nextToken());
                    max = Math.max(max, board[r][c]);
                }
            }
            ArrayList<int[]> high = new ArrayList<>();
 
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (board[r][c] == max) {
                        high.add(new int[] { r, c });
                    }
                }
            }
            visited = new boolean[N][N];
            
            for (int[] curr : high) {
                visited[curr[0]][curr[1]] = true;
                dfs(curr[0], curr[1], false, 1, max);
                visited[curr[0]][curr[1]] = false;
            }
            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }
        System.out.println(sb.toString());
    }
 
    private static void dfs(int r, int c, boolean hasCut, int distance, int preH) {
        answer = Math.max(answer, distance);
         
        for (int idx = 0; idx < 4; idx++) {
            int nr = r + delta[idx][0];
            int nc = c + delta[idx][1];
            if (isRange(nr, nc) && !visited[nr][nc]) {
                if (board[nr][nc] < preH) {
                    visited[nr][nc] = true;
                    dfs(nr, nc, hasCut, distance + 1, board[nr][nc]);
                    visited[nr][nc] = false;
                } else {
                    if (!hasCut && board[nr][nc] - K < preH) {
                        hasCut = true;
                        visited[nr][nc] = true;
                        dfs(nr, nc, hasCut, distance + 1, preH - 1);
                        visited[nr][nc] = false;
                        hasCut = false;
                    } 
                }
            }
        }
    }
 
    private static boolean isRange(int r, int c) {
        return (r >= 0 && r < N && c >= 0 && c < N);
    }
}