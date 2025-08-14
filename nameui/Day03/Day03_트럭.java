package day03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Day03_트럭 {

    static int N, W, L;
	static int[] arr;
	static Queue<Integer> queue = new LinkedList<>();
	static int t;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());

		// N, W, L
		N = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		// 트럭 입력 
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 큐 -> W 만큼 만들기 
		for(int i = 0; i<W; i++) {
			queue.add(0);
		}
		
		solution(0, 0);
		
		System.out.println(t); 
		

	}
	
	static void solution(int sum, int now) {
		if(now >= N) {
			t += queue.size();
			return;
		}
		// 하중이 괜찮은지 
		if(sum - queue.peek() + arr[now] <= L) {
			queue.add(arr[now]);
			sum = sum - queue.poll() + arr[now];
			t += 1;
						
			solution(sum, now+1);
		}
		else {
			sum = sum - queue.poll();
			queue.add(0);
			t += 1;
			solution(sum, now);
		}
	}

}
