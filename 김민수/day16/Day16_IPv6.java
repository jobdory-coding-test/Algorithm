import java.io.*;
import java.util.*;

public class Day16_IPv6 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		String[] constraction = br.readLine().split(":", -1);

		int count = 0;
		for (String s : constraction) {
			if (!s.equals("")) {
				count++;
			}
		}

		int zeroCount = 8 - count;
		for (String s : constraction) {
			if (s.equals("")) {
				while (zeroCount-- > 0) {
					sb.append("0000:");
				}
			} else {
				for (int i = 0; i < 4 - s.length(); i++) {
					sb.append(0);
				}
				sb.append(s).append(":");
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.print(sb.toString());
	}
}