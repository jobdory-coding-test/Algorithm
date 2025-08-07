import java.io.*;
import java.security.CryptoPrimitive;
import java.util.*;
 
public class Solution {
    private static int N;
    private static int[] nums;
    private static int[] cnt;
    private static int max, min;
 
    public static void main(String[] args) throws IOException {
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
 
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            nums = new int[N];
            cnt = new int[4];
            max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;
 
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 4; i++) {
                cnt[i] = Integer.parseInt(st.nextToken());
            }
 
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }
 
            dfs(0, nums[0]);
            sb.append("#").append(t).append(" ").append(max - min).append("\n");
        }
        System.out.println(sb.toString());
    }
 
    private static void dfs(int depth, int result) {
        if (depth == N - 1) {
            min = Math.min(min, result);
            max = Math.max(max, result);
        } else {
            for (int i = 0; i < 4; i++) {
                if (cnt[i] == 0)
                    continue;
 
                int tmp = result;
                result = cal(result, i, depth + 1);
                cnt[i]--;
                dfs(depth + 1, result);
                result = tmp;
                cnt[i]++;
            }
        }
    }
 
    private static int cal(int sum, int operator, int index) {
        switch (operator) {
        case 0:
            return sum += nums[index];
        case 1:
            return sum -= nums[index];
        case 2:
            return sum *= nums[index];
        case 3:
            return sum /= nums[index];
        }
        return 0;
    }
