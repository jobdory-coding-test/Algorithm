
import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {

        int n = friends.length;
        Map<String, Integer> m = new HashMap<>();

        for (int i = 0; i < n; i++) {
            m.put(friends[i], i);
        }

        int[][] a = new int[n][n];
        int[] give = new int[n];
        int[] recv = new int[n];

        for (String s : gifts) {
            String[] p = s.split(" ");
            int x = m.get(p[0]), y = m.get(p[1]);
            a[x][y]++;
            give[x]++;
            recv[y]++;
        }

        int[] score = new int[n];
        for (int i = 0; i < n; i++) {
            score[i] = give[i] - recv[i];
        }

        int[] nxt = new int[n]; // 담달 받을 선물 개수

        for (int from = 0; from < n; from++) {
            for (int to = from + 1; to < n; to++) {
                int ij = a[from][to], ji = a[to][from];
                if (ij > ji) {
                    nxt[from]++;
                } else if (ij < ji) {
                    nxt[to]++;
                } else {
                    if (score[from] > score[to]) {
                        nxt[from]++;
                    } else if (score[from] < score[to]) {
                        nxt[to]++;
                    }
                    // 같으면 둘 다 안 받음
                }
            }
        }

        int answer = 0;
        for (int x : nxt) {
            answer = Math.max(answer, x);
        }
        return answer;
    }
}
