import java.util.*;
import java.io.*;

public class Main {
    static int N, K, L, y, x, dir, time = 1;
    // 우,하 좌 상
    static int[] dy = { -0, 1, 0, -1 };
    static int[] dx = { 1, 0, -1, 0 };
    static boolean[][] apples;
    static boolean[][] snakeMap;
    static Map<Integer, Character> commands = new HashMap<>();
    static Queue<int[]> snakeBodys = new ArrayDeque<>();
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        apples = new boolean[N][N];
        snakeMap = new boolean[N][N];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int ay = Integer.parseInt(st.nextToken()) - 1;
            int ax = Integer.parseInt(st.nextToken()) - 1;
            apples[ay][ax] = true;
        }

        L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int key = Integer.parseInt(st.nextToken());
            char value = st.nextToken().charAt(0);
            commands.put(key, value);
        }

        snakeBodys.offer(new int[] { 0, 0 });
        snakeMap[0][0] = true;

        while (true) {
            // 좌표 계산
            int ny = y + dy[dir];
            int nx = x + dx[dir];

            // 벽에 부딪히거나 몸에 부딪히면 종료
            if (isValid(ny, nx) && !snakeMap[ny][nx]) {
                // 머리 이동
                snakeBodys.offer(new int[] { ny, nx });
                snakeMap[ny][nx] = true;

                // 사과 여부에 따른 분기
                if (apples[ny][nx]) {
                    apples[ny][nx] = false;
                } else {
                    int[] tail = snakeBodys.poll();
                    snakeMap[tail[0]][tail[1]] = false;
                }

                // 방향 전환 여부에 따른 분기
                if (commands.containsKey(time)) {
                    if (commands.get(time) == 'D') {
                        dir += 1;
                    } else {
                        dir -= 1;
                    }
                    dir = (dir + 4) % 4;
                }

                time++;
                y = ny;
                x = nx;
            } else {
                break;
            }
        }

        System.out.println(time);
    }

    private static boolean isValid(int ny, int nx) {
        return ny >= 0 && ny < N && nx >= 0 && nx < N;
    }
}
