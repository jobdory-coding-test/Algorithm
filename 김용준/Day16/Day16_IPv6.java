package Day16;
import java.util.*;
import java.io.*;

public class Day16_IPv6 {
    static char[][]result;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String [] temp = input.split("::");
        result = new char[8][4];

        if(temp.length == 1){
            int cnt = 0;
            char[] cc_front = temp[0].toCharArray();
            for(char c : cc_front){
                if(c == ':') cnt++;
            }
            makeup(input,0);
            for(int i=cnt+1;i<8;i++){
                    Arrays.fill(result[i],'0');
                }
        }
        else{
            int cnt_front = 0;
            int cnt_back =0;

            char[] cc_front = temp[0].toCharArray();
            for(char c : cc_front){
                if(c == ':') cnt_front++;
            }
            char[] cc_back = temp[1].toCharArray();
            for(char c : cc_back){
                if(c == ':') cnt_back++;
            }

            if(cnt_front == 0){
                if(temp[0] == ""){
                    for(int i=0;i<8-cnt_back-1;i++){
                        Arrays.fill(result[i],'0');
                    }
                    makeup(temp[1],8-cnt_back-1);
                }
                else{
                    makeup(temp[0],0);
                    for(int i=1;i<8-cnt_back-1;i++){
                        Arrays.fill(result[i],'0');
                    }
                    makeup(temp[1],8-cnt_back-1);
                }
                
            }
            else{
                makeup(temp[0],0);
                for(int i=cnt_front+1;i<8-cnt_back-1;i++){
                    Arrays.fill(result[i],'0');
                }
                makeup(temp[1],8-cnt_back-1);
            }
            
            
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<7;i++){
            for(char  c : result[i]){
                sb.append(c);
            }
            sb.append(':');
        }
        for(char  c : result[7]){
                sb.append(c);
        }
        System.out.println(sb);
    }

    public static void makeup(String input, int r){
        char [] temp_char = input.toCharArray();
            Deque <Character> stack = new ArrayDeque<>();
            for(char c: temp_char){
                if(c == ':'){
                    for(int i=3;i>=0;i--){
                        if(stack.isEmpty()) result[r][i] = '0';
                        else result[r][i] = stack.pop();
                    }
                    r++;
                }
                else stack.push(c);
            }
            if(!stack.isEmpty()){
                for(int i=3;i>=0;i--){
                    if(stack.isEmpty()) result[r][i] = '0';
                    else result[r][i] = stack.pop();
                }
            }
           
    }
}
