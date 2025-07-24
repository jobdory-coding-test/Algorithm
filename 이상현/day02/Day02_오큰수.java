import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] answer = new int[N];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = N - 1; i >= 0; i--) {
            int num;
            while (true) {
                if (stack.isEmpty()) {
                    num = -1;
                    break;
                }

                if (stack.peek() <= arr[i]) {
                    stack.pop();
                } else {
                    num = stack.peek();
                    break;
                }
            }

            stack.push(arr[i]);
            answer[i] = num;
        }

        for (int n : answer) {
            sb.append(n + " ");
        }
        System.out.print(sb.toString());
    }
}
