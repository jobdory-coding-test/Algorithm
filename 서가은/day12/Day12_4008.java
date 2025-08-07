package 서가은.day12;

//시간을 줄여보자!!

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Day12_4008 {
    static int T;
    static int N;
    static int[] numbers;
    static int maxNum = Integer.MAX_VALUE;
    static int minNum = Integer.MIN_VALUE;
    static boolean[] visited;

    public static void DFS(int calType, int cnt, int calNum, int[] calcul) {
        int temp = calNum;
        int[] tempArr = calcul.clone();
        --tempArr[calType];

        // '+' , '-' , '*', '/' 순으로
        switch (calType) {
            case 0:
                temp = calNum + numbers[cnt];
                break;
            case 1:
                temp = calNum - numbers[cnt];
                break;
            case 2:
                temp = calNum * numbers[cnt];
                break;
            case 3:
                temp = (int) (calNum / numbers[cnt]);
                break;
            default:
                break;
        }

        if (cnt == N - 1) { // 맨 끝 숫자까지 갔을 때
            maxNum = Math.max(maxNum, temp);
            minNum = Math.min(minNum, temp);
            return;
        }

        for (int y = 0; y < 4; y++) {
            if (tempArr[y] > 0) {
                DFS(y, cnt + 1, temp, tempArr);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        StringBuffer sb = new StringBuffer();

        // 테케
        T = Integer.parseInt(st.nextToken());
        int[] result = new int[T + 1];
        for (int x = 1; x < T + 1; x++) {
            N = 0;
            maxNum = -9999;
            minNum = 999999;

            line = br.readLine();
            st = new StringTokenizer(line);

            N = Integer.parseInt(st.nextToken());
            line = br.readLine();

            numbers = new int[N];
            visited = new boolean[N - 1]; // 연산자 방문 체크
            int[] calculation = new int[4];

            // 각 연산자의 개수 ( '+' , '-' , '*', '/' 순으로)
            st = new StringTokenizer(line);
            for (int i = 0; i < 4; i++) {
                calculation[i] = Integer.parseInt(st.nextToken());
            }

            line = br.readLine();
            st = new StringTokenizer(line);
            // 수식에 사용되는 숫자
            for (int i = 0; i < N; i++) {
                numbers[i] = Integer.parseInt(st.nextToken());
            }

            // 이거를 인덱스 하나하나 DFS
            for (int i = 0; i < 4; i++) {
                if (calculation[i] > 0) {
                    DFS(i, 1, numbers[0], calculation);
                }
            }

            result[x] = maxNum - minNum;
            sb.append("#").append(x).append(" ").append(result[x]).append("\n");
        }
        System.out.println(sb.toString());
    }

}
