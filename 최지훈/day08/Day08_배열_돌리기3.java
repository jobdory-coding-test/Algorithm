import java.util.*;
import java.io.*;

public class Main {
    private static int N, M, R;
    private static int[][] board;
    private static int[][] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<R; i++) {
            solve(Integer.parseInt(st.nextToken()));
            board = new int[result.length][result[0].length];
            for(int r=0; r<result.length; r++) {
                board[r] = result[r].clone();
            }
            N = board.length;
            M = board[0].length;
        }

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                sb.append(result[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void solve(int rNum) {
        switch (rNum) {
            case 1: case 2: reverse(rNum); break;
            case 3: case 4: rotate(rNum); break;
            case 5: case 6: four(rNum); break;
        }
    }

    // 1번 2번 연산
    private static void reverse(int rNum) {
        result = new int[N][M];
        // 상하 반전
        if(rNum == 1) {
            for(int i=0; i<N; i++) {
                for(int j=0; j<M; j++) {
                    result[i][j] = board[N-i-1][j];
                }
            }
        }
        // 좌우 반전
        else if (rNum == 2) {
            for(int i=0; i<N; i++) {
                for(int j=0; j<M; j++) {
                    result[i][j] = board[i][M-j-1];
                }
            }
        }
    }

    // 3번 4번 연산
    private static void rotate(int rNum) {
        if(rNum == 3) { // 오른쪽 90도
            result = new int[M][N];
            for(int i=0; i<N; i++) {
                for(int j=0; j<M; j++) {
                    result[j][N-i-1] = board[i][j];
                }
            }
        } else if(rNum == 4) { // 왼쪽 90도
            result = new int[M][N];
            for(int i=0; i<N; i++) {
                for(int j=0; j<M; j++) {
                    result[M-j-1][i] = board[i][j];
                }
            }
        }
    }

    // 5번 6번 연산
    private static void four(int rNum) {
        result = new int[N][M];

        if(rNum == 5) { // 오른쪽 90도
            for(int i=0; i<N/2; i++) {
                for(int j=0; j<M/2; j++) {
                    result[i][j] = board[i+N/2][j];
                }
            }

            for(int i=0; i<N/2; i++) {
                for(int j=M/2; j<M; j++) {
                    result[i][j] = board[i][j-M/2];
                }
            }

            for(int i=N/2; i<N; i++) {
                for(int j=M/2; j<M; j++) {
                    result[i][j] = board[i-N/2][j];
                }
            }

            for(int i=N/2; i<N; i++) {
                for(int j=0; j<M/2; j++) {
                    result[i][j] = board[i][j+M/2];
                }
            }
        }else if(rNum == 6) { // 왼쪽 90도
            for(int i=0; i<N/2; i++) {
                for(int j=0; j<M/2; j++) {
                    result[i][j] = board[i][j+M/2];
                }
            }

            for(int i=0; i<N/2; i++) {
                for(int j=M/2; j<M; j++) {
                    result[i][j] = board[i+N/2][j];
                }
            }

            for(int i=N/2; i<N; i++) {
                for(int j=M/2; j<M; j++) {
                    result[i][j] = board[i][j-M/2];
                }
            }

            for(int i=N/2; i<N; i++) {
                for(int j=0; j<M/2; j++) {
                    result[i][j] = board[i-N/2][j];
                }
            }
        }
    }
}
