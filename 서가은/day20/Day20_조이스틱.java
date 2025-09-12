import java.util.*;
import java.io.*;

class Solution {
    public int solution(String name) {

        int len = name.length();
        int answer = 0;
        int idxA = -1;
        int seqA = -1;
        int Acnt = 0;
        int maxGo = len - 1;

        for (int i = 0; i < len; i++) {
            char item = name.charAt(i);
            int go = len - 1;

            // 위아래 키 세팅
            int startA = item - 'A';
            int startZ = 'Z' - item + 1;
            answer += Math.min(startA, startZ);

            // 값이 A인 경우
            if (startA == 0) {
                Acnt++;

                // 연속적인 A인 경우
                if (idxA + seqA + 1 == i)
                    seqA++;

                // 갑자기 나타난 A인 경우
                else {
                    idxA = i;
                    seqA = 0;
                }
            }

            // A는 아닌데, 그 전 수가 A였던 경우
            else if (idxA != -1) {

                // 0~1인덱스에 A가 나타난 경우
                if (idxA <= 1)
                    go = len - (idxA + seqA + 1);
                else {
                    int foward = (idxA - 1);
                    int backward = (len - 1) - (idxA + seqA + 1);
                    go = Math.min(foward * 2 + backward + 1, (backward + 1) * 2 + foward);
                }

                // 연속 세팅 초기화
                idxA = -1;
                seqA = -1;

                if (maxGo > go)
                    maxGo = go;
            }

            // A로 끝나는 경우
            if (idxA + seqA == len - 1)
                maxGo = Math.min(idxA - 1, maxGo);

        }

        // A가 아예 없거나, 다 A인 경우
        if (Acnt == 0)
            return answer + (len - 1);
        else if (Acnt == len)
            return 0;
        else
            return answer + maxGo;
    }
}