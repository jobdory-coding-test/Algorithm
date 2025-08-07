import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Day12_숫자만들기 {
	
	static int TC, N, resNow, resMin, resMax;
	static int[] numbers;
	static int[] opCnt;
	static char[] oplist = {'+', '-', '*', '/'};
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		TC = Integer.parseInt(st.nextToken());
		
		for(int T=1; T<=TC; T++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			numbers = new int[N];
			opCnt = new int[4];
			
			// 사용할 수식의 개수
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<opCnt.length; i++) {
				opCnt[i] = Integer.parseInt(st.nextToken());
			}
			
			// 수식에 사용될 숫자
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				numbers[i] = Integer.parseInt(st.nextToken());
			}
			
			resMin = Integer.MAX_VALUE; resMax = Integer.MIN_VALUE;
			
			dfs(0, numbers[0]);
			sb.append("#"+T+" ").append(resMax-resMin).append('\n');
		}
		System.out.println(sb);
	}
	
	
	private static void dfs(int depth, int res) {
		if(depth == N-1) {
			resMin = Math.min(res, resMin);
			resMax = Math.max(res, resMax);
//			sb.append("====["+depth+"] res: "+res+", resMin:"+resMin+", resMax:"+resMax+"\n");
			return;
		}
		
		for(int i=0; i<4; i++) {
			if(opCnt[i]>0) {
//				sb.append(depth+" : "+res+"  "+oplist[i]+"  "+numbers[depth+1]+" : "+opCnt[i]+"\n");
				opCnt[i]--;
				dfs(depth+1, calc(res, numbers[depth+1], oplist[i]));
				opCnt[i]++;
			}
		}
	}
	
	private static int calc(int a, int b, char c) {
		switch(c) {
			case '+': 
				return a+b;
			case '-':
				return a-b;
			case '*':
				return a*b;
			case '/':
				return a/b;
		}
		return 0;
	}
	

}
