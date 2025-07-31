package 서가은.Day06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Day06_3987 {

    static char[] record = { 'U', 'R', 'D', 'L' };
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };
    // 행, 열, 방향 기준으로 방문 여부
    static boolean[][][] visited;

    // 배열, 크기
    static int[][] arr;
    static int n, m;

    public static void main(String[] args) throws IOException {

        // 입력 값
        // [입력]
        //
        // int N,M :
        // - N*M 배열
        //
        // char arr[][] universe :
        // - / \ C . 로 구성, / \ 은 행성 C는 블랙홀 . 은 빈칸
        // (1 ≤ N, M ≤ 500)
        //
        //
        // int PR, PC :
        // - arr[PR][PC]에 탐사선 있다는 뜻
        // (1 ≤ PR ≤ N, 1 ≤ PC ≤ M)

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);

        // 배열 크기
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 배열 생성
        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            line = br.readLine();
            for (int j = 0; j < m; j++) {
                switch (line.charAt(j)) {
                    case '/':
                        arr[i][j] = 1;
                        break;
                    case '\\':
                        arr[i][j] = 2;
                        break;
                    case 'C':
                        arr[i][j] = 3;
                        break;
                    default:
                        break;
                }
            }
        }

        line = br.readLine();
        st = new StringTokenizer(line);

        // 시작 위치
        int x = Integer.parseInt(st.nextToken()) - 1;
        int y = Integer.parseInt(st.nextToken()) - 1;

        int maxNum = 0;
        int maxIndex = -1;

        // 크게 위, 오른쪽, 아래, 왼쪽(U, R, D, L) 방향 순으로 4번 반복(for 문으로 4번 int i)
        for (int i = 0; i < 4; i++) {

            visited = new boolean[n][m][4]; // 초기화
            int count = 0;
            int nx = x;
            int ny = y;
            int dir = i;

            while (true) {
                // 범위 밖
                if (nx < 0 || ny < 0 || nx >= n || ny >= m)
                    break;
                // 블랙홀
                if (arr[nx][ny] == 3)
                    break;

                count++;

                // 만약 visited!=0
                if (visited[nx][ny][dir]) {
                    // 만약 visited[][][들어온 위치(상,하,좌,우==1,2,3,4)]==True;
                    // count=-1;
                    System.out.println(record[i]);
                    System.out.println("Voyager");
                    return;
                }

                // visited[][][들어온 위치(상,하,좌,우==1,2,3,4)]=True
                visited[nx][ny][dir] = true;

                // 함수 실행(방향 바꾸기)
                dir = turn(dir, arr[nx][ny]);

                // 설정된 방향으로 한칸 앞으로 가기
                nx += dx[dir];
                ny += dy[dir];
            }

            // 만약 count > maxNum?
            if (count > maxNum) {
                // maxNum, maxIndex 갱신
                maxNum = count;
                maxIndex = i;
            }
        }

        // record[maxIndex]출력
        System.out.println(record[maxIndex]);
        // maxNum출력
        System.out.println(maxNum);
    }

    // 함수(인풋으로 오는 방향,행성 모양(\,/) 돌아가는 방향 값 리턴해주는)
    static int turn(int dir, int type) {
        if (type == 1) { // '/'
            if (dir == 0)
                return 1;
            if (dir == 1)
                return 0;
            if (dir == 2)
                return 3;
            if (dir == 3)
                return 2;
        } else if (type == 2) { // '\'
            if (dir == 0)
                return 3;
            if (dir == 3)
                return 0;
            if (dir == 1)
                return 2;
            if (dir == 2)
                return 1;
        }
        return dir;
    }
}
