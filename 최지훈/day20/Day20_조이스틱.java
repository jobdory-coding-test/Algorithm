import java.util.*;
import java.io.*;

class Solution {
    public int solution(String name) {
        int answer = 0;
        int minMove = name.length()-1;
        for(int i=0; i<name.length(); i++) {
            answer += Math.min(name.charAt(i) - 'A', 'Z' - name.charAt(i) + 1);
            
            int next = i+1;
            for(int j=i+1; j<name.length(); j++) {
                if(name.charAt(j) == 'A') next++;
                else break;
            }
            minMove = Math.min(minMove, Math.min(i, name.length()-next) + i+name.length()-next);
        }
        return answer+minMove;
    }
}