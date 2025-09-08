import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[] pre;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> list = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;
            list.add(Integer.parseInt(line));
        }

        pre = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
        	pre[i] = list.get(i);
        }

        solve(0, pre.length);

        System.out.print(sb);
    }

    static void solve(int s, int e) {
        if (s >= e) return;

        int root = pre[s];
        int m = s + 1;

        while (m < e && pre[m] < root) m++;

        solve(s + 1, m);
        solve(m, e);

        sb.append(root).append('\n');
    }
}