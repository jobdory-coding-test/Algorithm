import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Day16_보물상자비밀번호 {
	
	static StringBuilder sb = new StringBuilder();
	static int T, N, K;
	static char[] inp, carr;
	static Integer[] resarr;
	static Set<Integer> numbers;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		T = Integer.parseInt(st.nextToken());
		
		for(int tc=1; tc<=T; tc++) {
			sb.append("#"+tc+" ");
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			inp = br.readLine().toCharArray();
			carr = new char[N + (N/4)];
			numbers = new HashSet<>();
			System.arraycopy(inp, 0, carr, 0, N);
			System.arraycopy(inp, 0, carr, N, N/4);
			
			addnumber();
			sb.append(findnumber()).append('\n');
		}
		System.out.println(sb);
	}


	private static void addnumber() {
		StringBuilder hexa = new StringBuilder();
		int start;
		for(int i=0; i<N/4; i++) {
			for(int j=0; j<4; j++) {
				start = i+j*N/4;
				
				hexa.setLength(0);
				for(int k=0; k<N/4; k++) {
					hexa.append(carr[start+k]);
				}
				numbers.add(Integer.parseInt(hexa.toString(), 16));
			}
		}
		return;
	}
	
	private static int findnumber() {
		resarr = numbers.toArray(new Integer[0]);
		Arrays.sort(resarr);
		
		return resarr[resarr.length-K];
	}
	
	

}
