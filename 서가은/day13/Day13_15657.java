
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Day13_15657 {

	static int N;
	static int M;
	static ArrayList<Integer> arr;
	static int[] path;
	static StringBuilder result = new StringBuilder(); 
	
	
	static void dfs(int node, int depth) {
		//수 여러번 갖다 쓰기 가능
		//중복 수열 금지
		//사전순
//		System.out.println(arr.length);
//		System.out.println(path.length);
//		System.out.printf("%d %d\n",node,depth);
		
		
		
		if(depth == M) {
			for(int item:path) {
				result.append(item);
				result.append(" ");
			}
			result.append("\n");
			return;
		}
		

		for(int i = node; i<N; i++) {
			path[depth] = arr.get(i);
			dfs(i,depth+1);
		}
	}
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) throws IOException {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr=new ArrayList<Integer>();
		path=new int[M];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			arr.add(Integer.parseInt(st.nextToken()));			
		}
		
		arr.sort(null);
		dfs(0,0);
		
		System.out.println(result.toString());
		
		
		
	}
}
