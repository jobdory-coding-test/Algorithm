package 서가은.day14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Day14_9663 {
    static int N;
    static boolean[] width; // 열에 체스판이 있는지 각각의 인덱스가 행임
    static boolean[] chessBoard1; // 대각선 체크1
    static boolean[] chessBoard2; // 대각선 체크2
    static int result;
    static int finalResult;

    static int getCombi(int y) {
        int cnt = 0;
        if (y == N) {
            cnt++;
        } else {
            for (int i = 0; i < N; i++) {
                if (width[i] || chessBoard1[i + y] || chessBoard2[i - y + N])
                    continue;
                width[i] = true;
                chessBoard1[i + y] = true;
                chessBoard2[i - y + N] = true;
                cnt += getCombi(y + 1);
                width[i] = false;
                chessBoard1[i + y] = false;
                chessBoard2[i - y + N] = false;
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int finalresult = 0;
        chessBoard1 = new boolean[N * 2];
        chessBoard2 = new boolean[N * 2];
        width = new boolean[N];
        finalResult += getCombi(0);

        System.out.println(finalResult);
    }
}
