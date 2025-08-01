import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Day05_뱀 {


    private static int d = 0;
    private static int[] dr = {0, 1, 0, -1};
    private static int[] dc = {1, 0, -1, 0};

    private static int N, K, L;
    private static int[][] apples;
    private static int[][] arr; // 사과 있으면 1, 몸이 있으면 -1
    private static int[] workX;
    private static boolean[] workC; //왼쪽이면 false 오른쪽이면 true
    private static int bodyLen = 1;
    private static Deque<int[]> body;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());

//		apples = new int[K][2];
        arr = new int[N+2][N+2];
        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int appleR = Integer.parseInt(st.nextToken());
            int appleC = Integer.parseInt(st.nextToken());
            arr[appleR][appleC] = 1;
        }

        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());

        workX = new int[L+1];
        workC = new boolean[L+1];
        for(int i=1; i<=L; i++) {
            st = new StringTokenizer(br.readLine());
            workX[i] = Integer.parseInt(st.nextToken());
            String workD = st.nextToken();
            workC[i] = workD.equals("L") ? false : true;
        }

        snake_time();

    }

    private static void snake_time() {
        body = new ArrayDeque<>();
        body.add(new int[]{1, 1});
        arr[1][1]=-1;

        int workIdx=1;
        int time = 0;
        int r=1, c=1;
        int n_r, n_c;
        int[] lastBody = new int[2];

        while(true) {
            time++;

            n_r = r+dr[d];
            n_c = c+dc[d];

            // 유효성 검증
            if(n_r<1 || n_r>N || n_c<1 || n_c>N) {
                break;
            } else if(arr[n_r][n_c] < 0) {
                break;
            }

            r = n_r;
            c = n_c;

            // 사과 아닐 때 꼬리 제어
            if(arr[r][c] < 1) {
                lastBody = body.removeLast();
                arr[lastBody[0]][lastBody[1]] = 0;
            }

            arr[r][c] = -1;
            body.addFirst(new int[] {r, c});

            if (workIdx!= 0 && time==workX[workIdx]) {
                if(workC[workIdx]) {
                    d = (d+1)%4;
                } else {
                    d = (d+3)%4;
                }
                workIdx = (workIdx+1)%(L+1);
            }

        }

        System.out.println(time);

    }


    private static void snake() {
        body = new ArrayDeque<>();
        body.add(new int[]{1, 1});

        int workIdx=1;
        int time = 0;
        int r=1, c=1;
        int n_r, n_c;
        int[] lastBody = new int[2];

        while(true) {
            time++;
            workX[workIdx]--;

            n_r = r+dr[d];
            n_c = c+dc[d];

            // 유효성 검증 : 벽 맞거나 몸이 있으
            if(n_r<1 || n_r>N || n_c<1 || n_c>N) {
                break;
            } else if(arr[n_r][n_c] < 0) {
                break;
            }

            r = n_r;
            c = n_c;

            if(arr[r][c] > 0) {
                bodyLen++;
            } else {
                lastBody = body.removeLast();
                arr[lastBody[0]][lastBody[1]] = 0;
            }

            arr[r][c] = -1;
            body.addFirst(new int[] {r, c});

            if(workIdx == 0) {
                continue;
            }

            if(workX[workIdx]==0) {
                if(workC[workIdx]) {
                    d = (d+1)%4;
                } else {
                    d = (d+3)%4;
                }
                workIdx = (workIdx+1)%(L+1);
            }

        }

        System.out.println(time);

    }


}
