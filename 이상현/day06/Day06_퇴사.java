import java.util.*;
import java.io.*;

public class Main {
    static int N, term, price, answer;
    static int[][] sangdams;
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        sangdams = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            term = Integer.parseInt(st.nextToken());
            price = Integer.parseInt(st.nextToken());
            sangdams[i] = new int[] { term, price };
        }

        // 날짜, 총 금액
        dfs(0, 0);

        System.out.println(answer);
    }

    private static void dfs(int day, int sum) {
        if (day == N) {
            answer = Math.max(answer, sum);
            return;
        } else if (day > N) {
            return;
        }

        // 선택 X
        dfs(day + 1, sum);

        // 선택 O
        dfs(day + sangdams[day][0], sum + sangdams[day][1]);
    }
}