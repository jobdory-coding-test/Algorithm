package 서가은.day08;

import java.io.*;
import java.util.*;

public class Day08_16935 {
    // 변환 명령 저장용 클래스
    static class Operation {
        int command; // 명령 번호
        int n, m; // 그때의 배열 크기

        Operation(int command, int n, int m) {
            this.command = command;
            this.n = n;
            this.m = m;
        }
    }

    public static void main(String[] args) throws IOException {
        // [입력]
        //
        //// 첫 줄
        // int N,M: 배열의 크기 ( N*M )
        // - 2 ≤ N, M ≤ 100
        // - N, M은 짝수
        //
        // int R: 수행해야하는 연산의 수
        // - N, M은 짝수
        //
        //
        //// 두번째 ~ N+1 번째 줄
        // int arr[][] = new int[N][M] : 숫자들이 담겨있는 배열
        //
        //
        //// N+2번째 줄
        // int commands[] = new int[R] : 수행해야하는 연산들

        // [입력]
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        // 연산의 수
        int R = Integer.parseInt(st.nextToken());

        int arr[][] = new int[N][M];

        // 배열 입력
        for (int i = 0; i < N; i++) {
            line = br.readLine();
            st = new StringTokenizer(line);
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 명령어 입력
        line = br.readLine();
        st = new StringTokenizer(line);
        int commandArr[] = new int[R];
        for (int i = 0; i < R; i++) {
            commandArr[i] = Integer.parseInt(st.nextToken());
        }

        // [구현]
        int n = N, m = M; //결과 배열 크기

        //초반에 나중에 어케 배열 크기 나올지 미리 생각을 해야함
        // 명령을 모두 적용한 후의 배열 크기 구하기
        for (int i = 0; i < R; i++) {
            int command = commandArr[i];
            if (command == 3 || command == 4) {
                int t = n;
                n = m;
                m = t;
            }
        }

        int[][] resultArr = new int[n][m];

        // 좌표 변환 공식 누적 적용
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int x = i, y = j;
                int curN = n, curM = m;
                // 모든 연산을 역순으로 적용
                for (int k = R - 1; k >= 0; k--) {
                    int command = commandArr[k];
                    switch (command) {
                        case 1: // 상하반전
                            x = curN - 1 - x;
                            break;
                        case 2: // 좌우반전
                            y = curM - 1 - y;
                            break;
                        case 4: // 오른쪽으로 90도 회전
                            int temp = x;
                            x = y;
                            y = curN - 1 - temp;
                            // 행/열 교체
                            int tmp3 = curN;
                            curN = curM;
                            curM = tmp3;
                            break;
                        case 3: // 왼쪽으로 90도 회전
                            int temp2 = y;
                            y = x;
                            x = curM - 1 - temp2;
                            // 행/열 교체
                            int tmp4 = curN;
                            curN = curM;
                            curM = tmp4;
                            break;
                        // 4개의 그룹으로 나눠서~
                        case 5: // 4개의 그룹으로 나눠서 -> 1->2 , 2->3, 3->4, 4->1
                            int h5 = curN / 2, w5 = curM / 2;
                            if (x < h5 && y < w5) { // 1번 -> 4번
                                x = x + h5;
                            } else if (x < h5 && y >= w5) { // 2번 -> 1번
                                y = y - w5;
                            } else if (x >= h5 && y >= w5) { // 3번 -> 2번
                                x = x - h5;
                            } else { // 4번 -> 3번
                                y = y + w5;
                            }
                            break;
                        case 6: // 4개의 그룹으로 나눠서 -> 1->4, 4->3, 3->2, 2->1
                            int h6 = curN / 2, w6 = curM / 2;
                            if (x < h6 && y < w6) { // 1번 -> 2번
                                y = y + w6;
                            } else if (x < h6 && y >= w6) { // 2번 -> 3번
                                x = x + h6;
                            } else if (x >= h6 && y >= w6) { // 3번 -> 4번
                                y = y - w6;
                            } else { // 4번 -> 1번
                                x = x - h6;
                            }
                            break;
                        default:
                            break;
                    }
                }
                // 변환된 좌표에서 값 복사
                resultArr[i][j] = arr[x][y];
            }
        }

        // [출력]
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(resultArr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
