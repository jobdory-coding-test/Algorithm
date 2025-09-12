
import java.io.*;
import java.util.*;

class Solution {
    public int solution(int n, long l, long r) {

        long dp[] = new long[n + 2];
        long maxIndex[] = new long[n + 2];

        int x = 0;
        while (maxIndex[x] < r) {
            ++x;
            maxIndex[x] = (long) Math.pow(5, x - 1);
            dp[x] = (long) Math.pow(4, x - 1);
        }

        long answer = dp[x];
        int part;
        long range;

        int depth = x;
        long startPoint = 0;

        while (depth >= 2) {
            range = maxIndex[depth] / (long) 5;

            if (l <= range + startPoint)
                part = 0;
            else if (l <= range * 2 + startPoint)
                part = 1;
            else if (l <= range * 3 + startPoint) {
                answer -= (dp[depth - 1] * 2);
                break;
            } else if (l <= range * 4 + startPoint)
                part = 3;
            else
                part = 4;

            startPoint += (range * part);
            answer -= (dp[depth - 1] * (part - 1));
            if (part <= 1)
                answer -= dp[depth - 1];
            depth--;
        }

        depth = x;
        startPoint = 0;
        while (depth >= 2) {
            range = maxIndex[depth] / (long) 5;

            if (r <= range + startPoint)
                part = 0;
            else if (r <= range * 2 + startPoint)
                part = 1;
            else if (r <= range * 3 + startPoint) {
                answer -= (dp[depth - 1] * 2);
                break;
            } else if (r <= range * 4 + startPoint)
                part = 3;
            else
                part = 4;

            startPoint += (range * part);
            answer -= (dp[depth - 1] * (4 - part));
            if (part <= 1)
                answer += dp[depth - 1];
            depth--;
        }

        return (int) answer;
    }
}
