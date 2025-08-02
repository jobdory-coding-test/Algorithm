import java.util.*;
import java.io.*;

public class Main {
    static int N, M, PY, PX, answerDir, answerTime, tempDir;
    static char[][] map;
    // 상:0, 우:1, 하:2, 좌:3
    static int[] dy = { -1, 0, 1, 0 };
    static int[] dx = { 0, 1, 0, -1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        st = new StringTokenizer(br.readLine());
        PY = Integer.parseInt(st.nextToken()) - 1;
        PX = Integer.parseInt(st.nextToken()) - 1;

        for (int i = 0; i < 4; i++) {
            boolean[][][] cVisited = new boolean[N][M][4];
            tempDir = i;
            dfs(PY, PX, i, 1, cVisited);
            if (answerTime == -1)
                break;
        }

        switch (answerDir) {
            // 상:0, 우:1, 하:2, 좌:3
            case 0:
                System.out.println('U');
                break;
            case 1:
                System.out.println('R');
                break;
            case 2:
                System.out.println('D');
                break;
            case 3:
                System.out.println('L');
                break;

            default:
                break;
        }

        if (answerTime == -1) {
            System.out.println("Voyager");
        } else {
            System.out.println(answerTime);
        }
    }

    private static void dfs(int y, int x, int dir, int time, boolean[][][] cVisited) {
        int ny = y + dy[dir];
        int nx = x + dx[dir];

        // 맵 밖이거나 블랙홀일 경우 종료
        if (!isValid(ny, nx) || isBlackHole(ny, nx)) {
            if (answerTime < time) {
                answerDir = tempDir;
            } else if (answerTime == time) {
                answerDir = Math.min(answerDir, tempDir);
            }
            answerTime = Math.max(answerTime, time);
            return;
        }

        // 행성을 같은 방향으로 똑같이 접근했을 경우 무한이라고 판단
        if (cVisited[ny][nx][dir]) {
            answerTime = -1;
            answerDir = tempDir;
            return;
        }

        // 빈칸을 만날 경우
        if (map[ny][nx] == '.') {
            dfs(ny, nx, dir, time + 1, cVisited);
        }
        // 행성을 만날 경우
        else if (map[ny][nx] == '/') {
            cVisited[ny][nx][dir] = true;
            if (dir == 0) {
                dir = 1;

            } else if (dir == 1) {
                dir = 0;
            } else if (dir == 2) {
                dir = 3;
            } else if (dir == 3) {
                dir = 2;
            }
            dfs(ny, nx, dir, time + 1, cVisited);

        } else if (map[ny][nx] == '\\') {
            cVisited[ny][nx][dir] = true;
            if (dir == 0) {
                dir = 3;
            } else if (dir == 1) {
                dir = 2;
            } else if (dir == 2) {
                dir = 1;
            } else if (dir == 3) {
                dir = 0;
            }

            dfs(ny, nx, dir, time + 1, cVisited);
        }
    }

    private static boolean isBlackHole(int ny, int nx) {
        return map[ny][nx] == 'C';
    }

    private static boolean isValid(int ny, int nx) {
        return ny >= 0 && ny < N && nx >= 0 && nx < M;
    }
}
