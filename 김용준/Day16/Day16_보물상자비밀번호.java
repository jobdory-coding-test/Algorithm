package Day16;
import java.util.*;
import java.io.*;
public class Day16_보물상자비밀번호 {
    static int N,K;
    static char []n;
    
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for(int test_case = 1;test_case<=T;test_case++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            n = new char[N];
            String input = br.readLine();
            n = input.toCharArray();
            int range = N/4;
            Set<Integer> nums = new HashSet<>();
            for(int i=0;i<N;i++){
                char []temp = new char[range];
                for(int j=0;j<range;j++){
                    temp[j] = n[(i+j)%N];
                }
               nums.add(changeNum(temp));
            }
            
            int[]list = new int[nums.size()];
            int cnt = 0;
            for(int n : nums){
                list[cnt++] = n;
            }
            Arrays.sort(list);
            sb.append("#").append(test_case).append(" ").append(list[list.length-K]).append("\n");
        }
        System.out.println(sb);
    }
    public static int changeNum(char[] temp){
        int result = 0;
        int tt = 0;
        int r = temp.length-1;
        for(char t : temp){
            if(t >=65 && t <= 70) tt = t-55;
            else tt = t-'0';
            result += tt*Math.pow(16,r--);
        }
        return result;
    }
    
}
