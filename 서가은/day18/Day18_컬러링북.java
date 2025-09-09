import java.util.*;

class Solution {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    static int bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        visited[x][y] = true;
        queue.add(new int[] { x, y });

        int area = 1;

        while (!queue.isEmpty()) {
            int[] target = queue.poll();
            int x1 = target[0];
            int y1 = target[1];

            for (int d = 0; d < 4; d++) {
                int nx = x1 + dx[d];
                int ny = y1 + dy[d];

                if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    if (map[nx][ny] == map[x1][y1] && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        queue.add(new int[] { nx, ny });
                        area++;
                    }
                }
            }
        }
        return area;
    }

    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        N = m;
        M = n;
        map = picture;
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0 && !visited[i][j]) {
                    int area = bfs(i, j);
                    numberOfArea++;
                    if (maxSizeOfOneArea < area) {
                        maxSizeOfOneArea = area;
                    }
                }
            }
        }

        return new int[] { numberOfArea, maxSizeOfOneArea };
    }
}
