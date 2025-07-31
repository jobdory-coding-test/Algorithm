import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static int[] nums;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		nums = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		for (int w : cal()) {
			sb.append(w).append(" ");
		}
		System.out.println(sb.toString());
	}

	private static int[] cal() {
		int[] answer = new int[N];
		Arrays.fill(answer, -1);
        // 동기화 오버헤드 제거하여 실행속도를 빠르게 하기 위해 ArrayDeque 자료구조 사용
		Deque<Integer> stack = new ArrayDeque<>();

		for (int i = N - 1; i >= 0; i--) {
			if (!stack.isEmpty()) {
                // 스택이 비어있지 않으면서 스택의 최상단 값이 해당 숫자보다 커질 때까지
				while (!stack.isEmpty() && stack.peek() <= nums[i]) {
					stack.pop();
				}
                // 최상단 값이 숫자보다 커진다면 answer[index]에 peek 값 저장
				if(!stack.isEmpty()) {
					answer[i] = stack.peek();
				}
			}
            // 현재 숫자 stack에 push하기
			stack.push(nums[i]);
		}
		return answer;
	}
}