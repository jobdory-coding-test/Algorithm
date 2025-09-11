import java.util.*;

class Day20_조이스틱 {

    static boolean[] visited;
    static int N, answer;
    static int[] alphaCnt;
    
    public int solution(String name) {
        N = name.length();
        answer = 0;
        
        visited = new boolean[N];
        
        for(int i=0; i<N; i++){
            // 알파벳 변경으로 인한 결과값은 미리 누적
            if(name.charAt(i)!='A') {
                answer += Math.min(name.charAt(i)-'A', 26+'A'-name.charAt(i));
                continue;
            }
            
            visited[i] = true;
        }
        
        if(answer==0) return 0;
        
        int mincycle=Integer.MAX_VALUE, cycle, rangeOfAs;
        for(int i=0; i<N; i++){
            // i이후부터 연속되는 A구간을 제외하는 경우
            if(visited[i]) continue;
            
            rangeOfAs = 0;
            cycle = 0;
            
            // 연속된 A 개수를 찾고
            for(int j=i+1; j<N; j++){
                if(!visited[j]) break;
                rangeOfAs++;
            }
            
            // 두 가지 탐색 방식에 대한 최소값
            cycle = N-rangeOfAs-1;
            cycle += Math.min(i, N-rangeOfAs-i-1);
            mincycle = Math.min(cycle, mincycle);
        }
        
        answer+= mincycle;
        return answer;
    }
    

}