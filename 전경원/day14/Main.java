import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
    static int N;
    static long count = 0;
    static boolean[] col;
    static boolean[] d1;
    static boolean[] d2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        col = new boolean[N];
        d1 = new boolean[2 * N];
        d2 = new boolean[2 * N];

        dfs(0);
        System.out.println(count);
    }

    static void dfs(int row) {
        if (row == N) {
            count++;
            return;
        }

        for (int c = 0; c < N; c++) {
            int diag1 = row + c;
            int diag2 = row - c + N - 1;

            if (col[c] || d1[diag1] || d2[diag2]) continue;

            col[c] = d1[diag1] = d2[diag2] = true;
            dfs(row + 1);
            col[c] = d1[diag1] = d2[diag2] = false;
        }
    }
}