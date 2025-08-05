package 서가은.day10;

import java.io.*;
import java.util.*;

public class Day10_15651 {
    static int N;
    static int M;
    static StringBuilder result = new StringBuilder();

    static void DFS(int node, int cnt, StringBuilder text) { // 그냥 result에 append하는 방식으로
        // 탈출 지점 넣기
        if (cnt == M) {
            result.append(text).append("\n");
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        for (int i = 1; i < N + 1; i++) {
            // 얘를 객체할당 적당히 시키는 방법
            sb2.setLength(0);
            sb2.append(text);
            sb2.append(i);
            sb2.append(" ");
            DFS(node + 1, cnt + 1, sb2);
        }

    }

    public static void main(String[] args) throws IOException {

        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // dfs실행
        DFS(1, 0, new StringBuilder());
        System.out.println(result.toString());
        // stringBuilder를 여기서 한번만 출력
    }
}
