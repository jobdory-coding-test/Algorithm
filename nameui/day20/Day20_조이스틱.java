import java.util.*;

class Day20_조이스틱 {

	public static int solution(String name) {

		// name = "ABAABAAABBB";

		char[] input = name.toCharArray();

		ArrayList<Integer> targets = new ArrayList<>();

		for (int i = 0; i < input.length; i++) {
			if ('A' != input[i])
				targets.add(i);
		}

		System.out.println(Arrays.toString(targets.toArray()));

		// 0. targets.size 가 1이면 안 찾고 바로 그 알파벳 계산해서 주면 됨.
		if (targets.size() == 0)
			return 0;
		if (targets.size() == 1) {
			int pos = targets.get(0);
			// 알파벳 변경 + 커서 이동(왼/오 최소) 모두 더해야 함
			return getCnt(targets, input, 1) + Math.min(pos, input.length - pos);
		}

		// 1. 가는 방법 중 최소값 찾기
		int n = targets.size();
		int size = input.length;

		int firstTarget = targets.get(0);
		int lastTarget = targets.get(n - 1);

		// 오른쪽으로 쭉 가기, 왼쪽으로 쭉 가기 경로 중에 최소 저장
		int min = Integer.MAX_VALUE;

		min = Math.min(lastTarget, min);
		min = Math.min(min, size - firstTarget);

		// 앞 쪽, 뒤 쪽 왕복하는 경우 중 이동거리 최소 선정
		min = Math.min(min, leftFirstTurn(targets, size));
		min = Math.min(min, rightFirstTurn(targets, size));

		System.out.println("거리 최소값 : " + min);

		min += getCnt(targets, input, n);

		System.out.println(Arrays.toString(targets.toArray()));

		System.out.println("결과 최소값 : " + min);

		return min;
	}

	private static int getCnt(ArrayList<Integer> targets, char[] input, int n) {
		System.out.println("getCnt 진입 --------------------");
		int min = 0;
		// targets 에 들어간 값 중 알파벳 증감 구하기.
		for (int i = 0; i < targets.size(); i++) {
			if (input[targets.get(i)] == 'A')
				continue;

			int num1 = input[targets.get(i)] - 'A';
			int num2 = 'A' - input[targets.get(i)] + 26;

			System.out.println("chr = " + (int) input[targets.get(i)]);
			System.out.println("num1 = " + num1);
			System.out.println("num2 = " + num2);

			min += Math.min(num1, num2);

			System.out.println(i + "번 : " + min);
		}
		return min;
	}

	// 왼쪽 먼저 갔다가 오른쪽으로 꺾기: 총 이동 = right + 2*left
	private static int leftFirstTurn(ArrayList<Integer> targets, int size) {
		if (targets == null || targets.size() < 2)
			return Integer.MAX_VALUE;
		int best = Integer.MAX_VALUE;
		for (int i = 0; i < targets.size() - 1; i++) {
			int right = targets.get(i);
			int left = size - targets.get(i + 1);
			int cand = right + 2 * left;
			if (cand < best)
				best = cand;
		}
		return best;
	}

	// 오른쪽 먼저 갔다가 왼쪽으로 꺾기: 총 이동 = 2*right + left
	private static int rightFirstTurn(ArrayList<Integer> targets, int size) {
		if (targets == null || targets.size() < 2)
			return Integer.MAX_VALUE;
		int best = Integer.MAX_VALUE;
		for (int i = 0; i < targets.size() - 1; i++) {
			int right = targets.get(i);
			int left = size - targets.get(i + 1);
			int cand = 2 * right + left;
			if (cand < best)
				best = cand;
		}
		return best;
	}

}