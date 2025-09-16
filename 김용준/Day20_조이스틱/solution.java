package Day20_조이스틱;

import java.util.*;
class Solution {
    static char[] names;
    static boolean[] visited;
    static int answer;
    static int ans;
    public int solution(String name) {
        answer = 0;
        names = name.toCharArray();
        visited = new boolean[names.length];
        for(int i=0;i<names.length;i++){
            if(names[i] != 'A'){
                calcu(i);
            }
            else visited[i] = true;
        }
        
        int now;
        int count;
        int direct;
        ans = Integer.MAX_VALUE;
        for(int i=0;i<names.length;i++){
            boolean []temp = Arrays.copyOf(visited,visited.length);
            now = 0;
            count = 0;
            direct = 0;
            while(!check_visit(temp)){
                if(now == i) direct = 1;
                count++;
                temp[now] = true;
                if(direct == 0) now++;
                else{
                    now--;
                    if(now == -1) now = names.length-1;
                }
            }
            if(count !=0) count--;
            ans = Math.min(ans,count);
        }
        
        for(int i=names.length-1;i>=0;i--){
            boolean []temp = Arrays.copyOf(visited,visited.length);
            now = 0;
            count = 0;
            direct = 0;
            while(!check_visit(temp)){
                if(now == i) direct = 1;
                count++;
                temp[now] = true;
                if(direct == 0){
                    now--;
                    if(now == -1) now = names.length-1;
                } 
                else {
                    now++;
                    if(now == names.length) now = 0;
                }
            }
            if(count !=0) count--;
            ans = Math.min(ans,count);
        }
        
        System.out.println(answer+" "+ans);
        return answer+ans;
    }
    public static boolean check_visit(boolean[] V){
        for(boolean v : V){
            if(!v) return false;
        }
        return true;
    }
    public static void calcu(int n){
        if(names[n]<='M'){
            answer += names[n]-'A';
        }else{
            answer += 'Z'-names[n]+1;
        }
    }
}