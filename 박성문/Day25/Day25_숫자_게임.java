package 박성문.Day25;

import java.util.Arrays;

public class Day25_숫자_게임 {
    static int answer = 0;
    static boolean[] isUsed;

    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);

        int aLen = A.length;
        int bLen = B.length;
        isUsed = new boolean[bLen];
        int lowNumPointer = 0;
        for(int i = aLen - 1; i >= 0; i--) {
            for(int j = bLen - 1; j >= 0; j--) {
                if(isUsed[j] || B[j] == A[i]) continue;
                if(B[j] - A[i] < 0) {
                    isUsed[lowNumPointer++] = true;
                    break;
                } else {
                    isUsed[j] = true;
                    answer++;
                    break;
                }
            }
        }
        return answer;
    }
}
