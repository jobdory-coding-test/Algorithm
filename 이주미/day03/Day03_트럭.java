import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Day03_트럭 {

    private static int N, W, L;
    private static int[] arr;
    private static int res;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(truck());
    }


    private static int truck() {
        Queue<Integer> queue = new ArrayDeque<>();

        // 다리 길이를 0으로 채워둠
        for(int i=0; i<W; i++) {
            queue.offer(0);
        }

        int idx=0, sum=0;
        int weight_check;
        res = W;

        while(idx<N) {
            res++;

            sum -= queue.poll();
            weight_check=sum+arr[idx];

            if(weight_check <= L) {
                sum+= arr[idx];
                queue.offer(arr[idx++]);
            } else {
                queue.offer(0);
            }

        }

        return res;
    }

    private static int truck_first() {
        Queue<Integer> queue = new ArrayDeque<>();

        int idx=0, sum=0;
        int weight_check;

        while(idx<N) {
            res++;
            if(queue.size() == 0) {
                sum+= arr[idx];
                queue.offer(arr[idx++]);
            } else if (queue.size()==W) {
                sum -= queue.poll();
            } else {
                weight_check=sum+arr[idx];
                if(weight_check <= L) {
                    // 무게 버틸 수 있으면 추가
                    sum+= arr[idx];
                    queue.offer(arr[idx++]);
                } else {
                    // 무게 못 버티면 하나 빼기
                    sum -= queue.poll();
                }
            }

        }

        return res;
    }

}