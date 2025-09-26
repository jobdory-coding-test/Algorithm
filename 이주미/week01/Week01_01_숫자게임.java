import java.util.*;
class Week01_01_숫자게임 {
    
    public int solution(int[] A, int[] B) {
        int N = A.length;
        
        Arrays.sort(A);
        Arrays.sort(B);

        int left = 0, right = N-1, numA, numB, idx;
        boolean smallerFound;
        for(int i=0; i<N; i++){
            numB = B[i];
            numA = A[left];
            
            if(numA < numB){
                left++;
            } else {
                right--;
            }
        }
        
        return left;
    }
}