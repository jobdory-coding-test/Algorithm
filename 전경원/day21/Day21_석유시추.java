import java.util.*;

class Solution {
    static int n, m;
    static boolean[][] visited;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};

    public int solution(int[][] land) {
        int answer = 0;
        n = land.length;
        m = land[0].length;
        visited = new boolean[n][m];

        int[] colSum = new int[m];
        int[] colSeen = new int[m];
        int mark = 1;

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (land[r][c] == 1 && !visited[r][c]) {
                    ArrayList<Integer> cols = new ArrayList<>();
                    int size = bfs(r, c, land, cols, colSeen, mark);

                    for (int col : cols) colSum[col] += size;

                    mark++;
                }
            }
        }

        for (int col = 0; col < m; col++) {
            answer = Math.max(answer, colSum[col]);
        }
        return answer;
    }

    static int bfs(int r, int c, int[][] land, List<Integer> cols, int[] colSeen, int mark) {
        Queue<int[]> q = new ArrayDeque<>();
        visited[r][c] = true;
        q.offer(new int[] {r, c});
        int size = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cr = cur[0], cc = cur[1];
            size++;

            if (colSeen[cc] != mark) {
                colSeen[cc] = mark;
                cols.add(cc);
            }

            for (int d = 0; d < 4; d++) {
                int nr = cr + dr[d];
                int nc = cc + dc[d];
                
                if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                if (land[nr][nc] == 1 && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.offer(new int[] {nr, nc});
                }
            }
        }
        return size;
    }
}