package 서가은.day11;

import java.util.*;
import java.io.*;

public class Day11_15652 {

    static int N;
    static int M;
    // static boolean[] visited;

    static void dfs(int v, boolean[] visited, String text, int cnt) {
        // 중단점
        if (cnt == M) {
            System.out.println(text);
            return;
        }

        // 반복문
        for (int i = v; i < N + 1; i++) {
            // if(visited[i]==false) {
            // visited[i]=true;
            dfs(i, visited, text + i + " ", cnt + 1);
            // visited[i]=false;
            // };

        }
        // 조건 성립시
        // 방문처리
        // dfs 호출
        // 방문 해제
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[N + 1];
        dfs(1, visited, "", 0);

    }

}
