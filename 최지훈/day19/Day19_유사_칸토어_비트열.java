import java.util.*;
import java.io.*;

class Solution {
    public int solution(int n, long l, long r) {
        
        return kanto(n, l, r);
    }
    
    private static int kanto(int n, long l, long r) {
        if(n==0) return 1;
        
        int sum = 0;
        long div = (long)Math.pow(5, n-1);
        
        // 인덱스가 1부터 시작하므로 -1로 보정
        long iStart = (l - 1) / div;
        long iEnd   = (r - 1) / div;
        
        for(long i=iStart; i<=iEnd; i++) {
            if(i==2) continue; // 0인 경우
            
            long blockL = i * div + 1; // 현재 탐색 블록의 시작 인덱스
            long blockR = (i + 1) * div; // 끝 인덱스
            
            // lr 기준 실제 구간 기준 하위 레벨 좌표로 변환 후 재귀
            long nl = Math.max(l, blockL) - blockL + 1;
            long nr = Math.min(r, blockR) - blockL + 1;
            sum += kanto(n - 1, nl, nr);
        }
        
        return sum;
    }
}