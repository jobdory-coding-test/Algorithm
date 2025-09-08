import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[][] info, int n, int m) {
        int items = info.length;
        final int INF = 1_000_000_000;


        int[][] dp = new int[items + 1][m];
        for (int i = 0; i <= items; i++) Arrays.fill(dp[i], INF);
        dp[0][0] = 0;

        for (int i = 0; i < items; i++) {
            int aTrace = info[i][0];
            int bTrace = info[i][1];

            for (int b = 0; b < m; b++) {
                if (dp[i][b] == INF) continue;

                int nb = b + bTrace;
                if (nb < m) {
                    dp[i + 1][nb] = Math.min(dp[i + 1][nb], dp[i][b]);
                }

                int na = dp[i][b] + aTrace;
                if (na < n) {
                    dp[i + 1][b] = Math.min(dp[i + 1][b], na);
                }
            }
        }

        int answer = INF;
        for (int b = 0; b < m; b++) {
            answer = Math.min(answer, dp[items][b]);
        }
        return (answer == INF) ? -1 : answer;
    }
}