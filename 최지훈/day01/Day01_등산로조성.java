import java.io.*;
import java.util.*;

public class Solution {

    static StringBuilder sb = new StringBuilder();
    static int T, N, K;
    static int[][] board;
    static int maxMt;
    static int minMt;
    static int maxCnt;

    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    // 이전 좌표, 현재 좌표, 깎음 여부, 나아간 거리, 방문
    static void dfs(int x, int y, boolean cut, int dist, boolean[][] visited) {
        maxCnt = Math.max(maxCnt, dist);

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny])
                continue;

            if(board[x][y] > board[nx][ny]) {
                visited[nx][ny] = true;
                dfs(nx, ny, cut, dist + 1, visited);
                visited[nx][ny] = false;
            }
            else if(!cut && board[x][y] > board[nx][ny] - K) {
                int origin = board[nx][ny];
                board[nx][ny] = board[x][y] - 1; // 가장 작게 1만 컷팅
                visited[nx][ny] = true;
                dfs(nx, ny, true, dist + 1, visited);
                visited[nx][ny] = false;
                board[nx][ny] = origin;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            maxMt = Integer.MIN_VALUE;
            minMt = Integer.MAX_VALUE;
            maxCnt = Integer.MIN_VALUE;

            board = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    maxMt = Math.max(maxMt, board[i][j]);
                    minMt = Math.min(minMt, board[i][j]);
                }
            }

            sb.append("#").append(t).append(" ");

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (maxMt == board[i][j]) {
                        boolean[][] visited = new boolean[N][N];
                        visited[i][j] = true;
                        dfs(i, j, false, 1, visited);
                    }
                }
            }

            sb.append(maxCnt).append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}
