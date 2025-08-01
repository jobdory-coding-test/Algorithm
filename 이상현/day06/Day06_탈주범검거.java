import java.util.*;
import java.io.*;

public class Solution {
    static int[][] map;
    static int N, M, R, C, L, answer;
    static boolean[][] visited;
    static Map<Integer, int[][]> pipes = new HashMap<>();

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 파이프 초기화
        pipes.put(0, new int[][] {}); // empty
        pipes.put(1, new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }); // 상 하 좌 우
        pipes.put(2, new int[][] { { -1, 0 }, { 1, 0 } }); // 상 하
        pipes.put(3, new int[][] { { 0, -1 }, { 0, 1 } }); // 좌 우
        pipes.put(4, new int[][] { { -1, 0 }, { 0, 1 } }); // 상 우
        pipes.put(5, new int[][] { { 1, 0 }, { 0, 1 } }); // 하 우
        pipes.put(6, new int[][] { { 1, 0 }, { 0, -1 } }); // 하 좌
        pipes.put(7, new int[][] { { -1, 0 }, { 0, -1 } }); // 상 좌

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            answer = 1;
            map = new int[N][M];
            visited = new boolean[N][M];

            for (int i = 0; i < N; i++) {
                map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            bfs(R, C, L);

            sb.append("#" + test_case + " " + answer + "\n");
        }

        System.out.print(sb.toString());
    }

    private static void bfs(int r, int c, int maxTime) {
        Queue<int[]> q = new ArrayDeque<>();
        visited[r][c] = true;
        q.add(new int[] { r, c, 1 });

        while (!q.isEmpty()) {
            int[] yx = q.poll();
            int y = yx[0];
            int x = yx[1];
            int time = yx[2];
            int pipeNum = map[y][x];

            if (time == maxTime) {
                return;
            }

            for (int i = 0; i < pipes.get(pipeNum).length; i++) {
                int ny = y + pipes.get(pipeNum)[i][0];
                int nx = x + pipes.get(pipeNum)[i][1];
                if (isValid(ny, nx) && isPipeConnected(y, x, ny, nx)) {
                    visited[ny][nx] = true;
                    q.add(new int[] { ny, nx, time + 1 });
                    answer += 1;
                }
            }
        }
    }

    private static boolean isPipeConnected(int y, int x, int ny, int nx) {
        int pipeNum = map[ny][nx];

        for (int i = 0; i < pipes.get(pipeNum).length; i++) {
            int cy = ny + pipes.get(pipeNum)[i][0];
            int cx = nx + pipes.get(pipeNum)[i][1];
            if (cy == y && cx == x) {
                return true;
            }
        }
        return false;
    }

    private static boolean isValid(int ny, int nx) {
        return ny >= 0 && ny < N && nx >= 0 && nx < M && !visited[ny][nx] && map[ny][nx] != 0;
    }

}