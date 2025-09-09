import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[][] info, int n, int m) {
        int[][] dp = new int[info.length][121];
        for(int i=0; i<info.length; i++) Arrays.fill(dp[i], 121);
        
        if(info[0][0] < n) dp[0][0] = info[0][0]; // A 선택
        if(info[0][1] < m) dp[0][info[0][1]] = 0; // B 선택
        
        for(int i=1; i<info.length; i++) {
            for(int j=0; j<121; j++) {
                if(dp[i-1][j] < 0) continue; // 선택하지 않은 흔적
                
                // A 흔적을 선택할 때, n이 넘어가지 않으면 값 업데이트
                if(dp[i-1][j] + info[i][0] < n) dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + info[i][0]);
                
                // B 흔적을 선택할 때, m이 넘어가지 않으면 값 업데이트
                if(j+info[i][1] < m) dp[i][j+info[i][1]] = dp[i-1][j];
            }
        }
        
        // info.length개 만큼 선택했을 때 가장 작은 값
        int answer = 121;
        for(int i=0; i<121; i++) answer = Math.min(answer, dp[info.length-1][i]);
        return answer == 121 ? -1 : answer;
    }
}