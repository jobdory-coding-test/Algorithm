import java.util.*;
import java.io.*;

public class Solution {

    private static int N, K;
    private static String secrets;
    private static TreeSet<Integer> ts;

    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            secrets = br.readLine();

            // secrets을 N/4번 밀면서 확인
            sb.append("#").append(t).append(" ").append(sw()).append("\n");
        }
        System.out.print(sb);
    }

    private static int sw() {
        ts = new TreeSet<>(Collections.reverseOrder());
        int base = N/4;

        for(int i=0; i<N/4; i++) {
            StringBuilder s4 = new StringBuilder();
            int n1 = toTenFromHexa(secrets.substring(i, i+base));
            int n2 = toTenFromHexa(secrets.substring(i+base, i+base*2));
            int n3 = toTenFromHexa(secrets.substring(i+base*2, i+base*3));
            for(int j=i+N/4*3; j<N+i; j++) {
                s4.append(secrets.charAt(j%N));
            }
            int n4 = toTenFromHexa(s4.toString());

            ts.add(n1);
            ts.add(n2);
            ts.add(n3);
            ts.add(n4);
        }

        for(int i=0; i<K-1; i++) {
            ts.pollFirst();
        }
        return ts.pollFirst();
    }

    private static int toTenFromHexa(String str) {
        int sum = 0;
        for(int i=0; i<str.length(); i++) {
            char c = str.charAt(i);
            int tmp;
            if(c <= 'F' && c >= 'A') {
                tmp = ((c-'A') + 10);
            } else {
                tmp = (c-'0');
            }
            sum = (sum << 4) | tmp; // *2^4 + tmp => 처음 & 마지막 16 배수 x
        }
        return sum;
    }
}