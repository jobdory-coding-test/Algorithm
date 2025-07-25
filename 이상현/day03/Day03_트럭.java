import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 트럭 수
        int w = Integer.parseInt(st.nextToken()); // 다리 길이
        int L = Integer.parseInt(st.nextToken()); // 다리가 버틸 수 있는 중량
        int[] trucks = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int cur_weight = 0; // 현재 다리 위 중량
        int cur_idx = 0; // trucks 인덱스
        int answer = w;

        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < w; i++) {
            q.offer(0);
        }

        while (cur_idx < n) {
            // 전진
            cur_weight -= q.poll();

            // 다리에 올릴 수 있으면 트럭 올리기
            if (cur_idx < n && cur_weight + trucks[cur_idx] <= L) {
                q.offer(trucks[cur_idx]);
                cur_weight += trucks[cur_idx];
                cur_idx++;
            } else {
                q.offer(0);
            }

            answer++;
        }

        System.out.println(answer);
    }
}