import java.util.*;
class Week01_02_단속카메라 {
    
    List<int[]> list;
    boolean[] visited;
    
    public int solution(int[][] routes) {
        int N = routes.length;
        list = new ArrayList<>();
        visited = new boolean[N];
        
        for(int i=0; i<N; i++){
            list.add(routes[i]);
        }
        Collections.sort(list, (a, b) -> Integer.compare(a[0], b[0]));
        
        int result = 0;
        int minEnd=list.get(0)[1];
        for(int i=0; ; ){
            if(i==N-1) {
                // System.out.printf("=== %d & %d ===\n", list.get(N-1)[0], minEnd);
                if(list.get(N-1)[0]>minEnd) result++;
                break;
            }
            
            minEnd = list.get(i)[1];
            // System.out.printf("[%d] start\n", i);
            
            for(int j=i+1; j<N; j++){
                // System.out.printf("(%d)%d, ", j, minEnd);
                
                i=j;
                
                if(list.get(j)[0] > list.get(j)[1]
                    || list.get(j)[0] > minEnd){
                    // System.out.println(" // break ");
                    break;
                }
                
                minEnd = Math.min(minEnd, list.get(j)[1]);
            }
            System.out.println();
            result++;
        }
        
        return result;
    }
    
}