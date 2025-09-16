import java.util.*;
import java.io.*;

class Solution {

    static final int[] dx = { 0, 0, -1, 1 };
    static final int[] dy = { 1, -1, 0, 0 };

    static int n;
    static int m;

    static boolean[][] visited;
    static int[] resultArr;
    static int answer;

    public int solution(int[][] land) {
        n = land.length;
        m = land[0].length;

        visited = new boolean[n][m];
        resultArr = new int[m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (land[i][j] == 1 && visited[i][j] == false) {
                    bfs(i, j, land);
                }
            }
        }

        return answer;
    }

    public static void bfs(int x, int y, int[][] lands) {
        int temp = 0;
        int minX = y;
        int maxX = y;

        Deque<int[]> queue = new ArrayDeque<>();
        queue.offerLast(new int[] { x, y });
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            temp++;

            int[] position = queue.pollFirst();

            for (int i = 0; i < 4; i++) {
                int ax = position[0] + dx[i];
                int ay = position[1] + dy[i];

                if (ax >= 0 && ax < n && ay >= 0 && ay < m) {
                    if (visited[ax][ay] == false && lands[ax][ay] == 1) {
                        visited[ax][ay] = true;
                        queue.offerLast(new int[] { ax, ay });
                        minX = Math.min(minX, ay);
                        maxX = Math.max(maxX, ay);

                    }
                }
            }
        }

        for (int i = minX; i <= maxX; i++) {
            resultArr[i] += temp;
            answer = Math.max(answer, resultArr[i]);
        }
    }
}