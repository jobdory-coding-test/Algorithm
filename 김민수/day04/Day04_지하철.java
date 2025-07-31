import java.io.*;
import java.util.*;

public class Day04_지하철 {
	private static int N, M, count;
	private static int[][] arr;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[1_000_001][2];

		int pre = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			addNext(pre, num);
			pre = num;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			int numA = 0;
			int numB = 0;
			if(str.charAt(0) == 'B' ) {
				numA = Integer.parseInt(st.nextToken());
				numB = Integer.parseInt(st.nextToken());
			} else {
				numA = Integer.parseInt(st.nextToken());
			}
			switch (str) {
			case "BN":
				sb.append(addNext(numA, numB)).append("\n");
				break;
			case "BP":
				sb.append(addPre(numA, numB)).append("\n");
				break;
			case "CN":
				sb.append(removeNext(numA)).append("\n");
				break;
			case "CP":
				sb.append(removePre(numA)).append("\n");
				break;
			}
		}
		System.out.println(sb.toString());
	}

	private static int addNext(int idx, int num) {
		if (idx == 0) {
			arr[num][0] = num;
			arr[num][1] = num;
			return num;
		}

		// 추가하는 station의 이전 station 기록
		arr[num][0] = idx;
		int next = arr[idx][1];
		// 이전 station의 next 수정
		arr[idx][1] = num;
		// 추가하는 station의 next 수정
		arr[num][1] = next;
		// 추가하는 station의 next의 이전 station 수정
		arr[next][0] = num;
		return next;
	}

	private static int addPre(int idx, int num) {
		int pre = arr[idx][0];
		arr[pre][1] = num;
		arr[num][0] = pre;
		arr[num][1] = idx;
		arr[idx][0] = num;
		return pre;
	}

	private static int removePre(int idx) {
		int remove = arr[idx][0];
		int newPre = arr[remove][0];
		arr[idx][0] = newPre;
		arr[newPre][1] = idx;
		return remove;
	}

	private static int removeNext(int idx) {
		int remove = arr[idx][1];
		int newNext = arr[remove][1];
		arr[idx][1] = newNext;
		arr[newNext][0] = idx;
		return remove;
	}
}