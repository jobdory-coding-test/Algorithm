package 서가은.Day05;

import java.io.*;
import java.util.*;

public class SnakeGame3190 {
    // 방향 담는 클래스 position
    static class Position {
        int x, y;
        Position(int x, int y) { this.x = x; this.y = y; }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // [기본 입력]
        int N = Integer.parseInt(br.readLine()); // 보드 크기
        int K = Integer.parseInt(br.readLine()); // 사과 개수

        boolean[][] gameBoard = new boolean[N][N]; // 사과 위치
        int[][] nowVisiting = new int[N][N]; // 뱀 위치

        // 사과 위치 입력
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            gameBoard[r][c] = true;
        }

        int L = Integer.parseInt(br.readLine()); // 방향 변환 횟수

        // 방향 넣는 곳
        ArrayList<Integer> switchTime = new ArrayList<>(L); // ""게임 시작 시간""부터 x초가
        ArrayList<Character> switchPosition = new ArrayList<>(L); // C 방향으로 회전

        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());// ""게임 시작 시간""부터 x초가
            char C = st.nextToken().charAt(0);// C 방향으로 회전
            switchTime.add(X);
            switchPosition.add(C);
        }

        // 머리 넣고 꼬리 빼는 용도의 큐
        Queue<Position> nextStep = new ArrayDeque<>();

        int[] dx = { 0, 1, 0, -1 };
        int[] dy = { 1, 0, -1, 0 };

        int dir = 0;
        int x = 0, y = 0;
        int result = 0;

        // 초기 세팅 머리 넣고 현재 위치 체크
        nextStep.add(new Position(x, y));
        nowVisiting[x][y] = 1;

        int switchIdx = 0;

        while (true) {
            result++;

            int nx = x + dx[dir];
            int ny = y + dy[dir];

            // 3. 이동 위치 벗어나면 break
            if (nx < 0 || nx >= N || ny < 0 || ny >= N)
                break;
            // 자기 몸과 충돌해도 break
            if (nowVisiting[nx][ny] == 1)
                break;

            // 4. 머리 이동 위치 큐에 추가, 방문 표시
            nextStep.add(new Position(nx, ny));
            nowVisiting[nx][ny] = 1;

            // 5. 사과 유무
            if (gameBoard[nx][ny]) {
                gameBoard[nx][ny] = false; // 사과 먹고 꼬리 그대로 (길이 +1)
            } else {
                // 꼬리 빼기 (몸길이 유지)
                Position tail = nextStep.poll();
                nowVisiting[tail.x][tail.y] = 0;
            }

            // 6. 방향 전환
            if (switchIdx < L && result == switchTime.get(switchIdx)) {
                if (switchPosition.get(switchIdx) == 'D') {// 오른쪽
                    dir = (dir + 1) % 4;
                } else if (switchPosition.get(switchIdx) == 'L') {// 왼쪽
                    dir = (dir + 3) % 4;
                }
                switchIdx++;
            }

            x = nx;
            y = ny;
        }

        System.out.println(result);
    }
}
