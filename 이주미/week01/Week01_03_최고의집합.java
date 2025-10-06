import java.util.*;

class Week01_03_최고의집합 {
    
    public int[] solution(int n, int s) {
        
        if(n>s) return new int[] {-1};
        
        int[] res = new int[n];
        
        int base = s/n;
        int rem = s%n;
        
        for(int i=0; i<n-rem; i++){
            res[i] = base;
        }
        
        for(int i=n-rem; i<n; i++){
            res[i] = base+1;
        }
        return res;
    }
    
}