import java.util.*;

class Solution {

    static boolean[][] visited;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};

    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] != 0 && !visited[i][j]) {
                    numberOfArea++;
                    int size = bfs(i, j, m, n, picture);
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, size);
                }
            }
        }

        return new int[] { numberOfArea, maxSizeOfOneArea };
    }

    static int bfs(int r, int c, int m, int n, int[][] picture) {
        Deque<int[]> dq = new ArrayDeque<>();
        visited[r][c] = true;
        dq.offer(new int[] { r, c });

        int color = picture[r][c];
        int total = 0;

        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            int sr = cur[0];
            int sc = cur[1];

            total++;

            for (int d = 0; d < 4; d++) {
                int nr = sr + dr[d];
                int nc = sc + dc[d];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                if (!visited[nr][nc] && picture[nr][nc] == color) {
                    visited[nr][nc] = true;
                    dq.offer(new int[] { nr, nc });
                }
            }
        }
        return total;
    }
}