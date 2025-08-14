import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Day16_IPv6 {

	static StringBuilder sb = new StringBuilder();
	static String[] ipv6;
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ipv6 = br.readLine().split(":");

		if(ipv6.length!=8) fillLen();
		fillZero();
		
		for(int i=0; i<ipv6.length-1; i++) {
			sb.append(ipv6[i]).append(':');
		}
		sb.append(ipv6[ipv6.length-1]);
		
		System.out.println(sb);
	}

	private static void fillLen() {
		String[] ipv6Full = new String[8];
		int size = ipv6.length;
		
		// :: 의 형태일 때
		if(size==0) {
			ipv6 = new String[] {"0000", "0000", "0000", "0000", "0000", "0000", "0000", "0000"};
			return;
		}
		
		int left;
		for(left=0; left<size; left++) {
			if(ipv6[left].length()==0) break;
		}
		
		System.arraycopy(ipv6, 0, ipv6Full, 0, left);
		
		for(int i=0; left+i<8; i++) {
			ipv6Full[left+i] = "0000";
		}
		
		// 1::의 형태일 때
		if(left!=size) {
			System.arraycopy(ipv6, left+1, ipv6Full, left+8-size+1, size-left-1);
		}
		
		ipv6 = ipv6Full;
	}
	
	private static void fillZero() {
		String onezero = "0";
		String twozero = "00";
		String threezero = "000";
		String fourzero = "0000";
		
		for(int i=0; i<ipv6.length; i++) {
			switch(ipv6[i].length()) {
				case 0:
					ipv6[i] = fourzero; break;
				case 1:
					ipv6[i] = threezero+ipv6[i]; break;
				case 2:
					ipv6[i] = twozero+ipv6[i]; break;
				case 3:
					ipv6[i] = onezero+ipv6[i]; break;
			}
		}
		
	}
	
}
