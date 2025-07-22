import java.util.Scanner;

class Solution {
    
    //초기 세팅
    // 지도 크기랑 공사 가능 깊이, 제일 높 봉우리
    static int n, k, top;  
    static int[][]  map;
    static boolean[][] chk; //방문 체크
    static int answer = 0;      // 답 담을 곳

    static int[] dx = {-1, 1, 0, 0}; // 위 아래
    static int[] dy = {0, 0, -1, 1}; // 좌 우

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt(); // 테케 수

        for (int tc = 1; tc <= t; tc++) {
            n = sc.nextInt();
            k = sc.nextInt();

            map = new int[n][n];
            chk = new boolean[n][n];
            top = 0;
            answer = 0;

            // 입력 받으면서 제일 높은 곳 찾기
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    map[i][j] = sc.nextInt();
                    if (map[i][j] > top) top = map[i][j];
                }
            }

            // 시작점 후보들 순회 
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j] == top) {
                        chk[i][j] = true;
                        dfs(i, j, 1, false); // x, y, 길이, 공사했는지
                        chk[i][j] = false;
                    }
                }
            }

            System.out.println("#" + tc + " " + answer);
        }
    }

    //리얼 DFS
    //DFS ( 현재 위치, 현재 이동 거리, 채광권){
    //	일단 현재 위치 방문처리
    //	이동거리++
    //
    //	//빠져나가는 문
    //	사방이 (이미 방문 함 || 나보다 크거나 같음 || 범위 벗어남){ 
    //		현재 이동 거리 리턴
    //	}
    //
    //	//아니다?
    //	방문처리 true
    //	DFS (만약 아직 그 채굴? 그거 안했으면 그거 포함해서 돌리기)
    //	방문처리 false
    //
    //}
    static void dfs(int x, int y, int len, boolean cut) {
        if (len > answer) answer = len;

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
            if (chk[nx][ny]) continue;

            if (map[nx][ny] < map[x][y]) {
                chk[nx][ny] = true;
                dfs(nx, ny, len + 1, cut);
                chk[nx][ny] = false;
            }
            else {
                if (!cut && map[nx][ny] - k < map[x][y]) {
                    int temp = map[nx][ny];
                    map[nx][ny] = map[x][y] - 1;
                    chk[nx][ny] = true;
                    dfs(nx, ny, len + 1, true);
                    chk[nx][ny] = false;
                    map[nx][ny] = temp;
                }
            }
        }
    }
}
