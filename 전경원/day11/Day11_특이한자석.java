import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int[][] M;
    static int K;

    public static void main(String[] args) throws IOException {        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            K = Integer.parseInt(br.readLine());

            M = new int[4][8];
            for (int i = 0; i < 4; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 8; j++) {
                    M[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int k = 0; k < K; k++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int num = Integer.parseInt(st.nextToken()) - 1;
                int dir = Integer.parseInt(st.nextToken());

                rotateAll(num, dir);
            }

            int score = calculateScore();
            System.out.println("#" + t + " " + score);
        }
    }

    static void rotateAll(int num, int dir) {
        int[] dirs = new int[4];
        dirs[num] = dir;

        for (int i = num; i > 0; i--) {
            if (M[i][6] != M[i - 1][2]) {
                dirs[i - 1] = -dirs[i];
            } else break;
        }

        for (int i = num; i < 3; i++) {
            if (M[i][2] != M[i + 1][6]) {
                dirs[i + 1] = -dirs[i];
            } else break;
        }

        for (int i = 0; i < 4; i++) {
            if (dirs[i] != 0) rotate(i, dirs[i]);
        }
    }

    static void rotate(int num, int dir) {
        if (dir == 1) {
            int tmp = M[num][7];
            for (int i = 7; i > 0; i--) {
                M[num][i] = M[num][i - 1];
            }
            M[num][0] = tmp;
        } else if (dir == -1) {
            int tmp = M[num][0];
            for (int i = 0; i < 7; i++) {
                M[num][i] = M[num][i + 1];
            }
            M[num][7] = tmp;
        }
    }

    static int calculateScore() {
        int score = 0;
        for (int i = 0; i < 4; i++) {
            if (M[i][0] == 1) {
                score += (1 << i);
            }
        }
        return score;
    }
}