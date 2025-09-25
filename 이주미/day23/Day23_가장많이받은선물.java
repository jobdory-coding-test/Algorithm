import java.util.*;
class Day23_가장많이받은선물 {
    
    static StringTokenizer st;
    static String[] names;
    static int N;
    static int[] rating;
    static int[][] arr;
    
    public int solution(String[] friends, String[] gifts) {
        N = friends.length;
        names = friends;
        rating = new int[N];
        arr = new int[N][N];
        
        int from, to;
        for(int i=0; i<gifts.length; i++){
            st = new StringTokenizer(gifts[i]);
            from = nameIndex(st.nextToken());
            to = nameIndex(st.nextToken());
            
            arr[from][to] ++;
            rating[from]++; rating[to]--;
        }
        
        //printarr();
        //System.out.println("==========");
        
        return solve();
    }
    
    static private void printarr(){
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                System.out.printf("%3d", arr[i][j]);
            }
            System.out.println();
        }
    }
    
    static private int nameIndex(String str){
        for(int i=0; i<N; i++){
            if(str.equals(names[i])){
                return i;
            }
        }
        return -1;
    }
    
    static private int solve(){
        int answer = 0, temp;
        for(int i=0; i<N; i++){
            temp = 0;
            for(int j=0; j<N; j++){
                if(i==j) continue;
                
                // 내가 더 많이 줬을 때
                if(arr[i][j]>arr[j][i]){
                    temp++; continue;
                }
                
                // 주고 받은 적 없거나 같을 때 : 선물지수 비교
                if(arr[i][j]==arr[j][i]){
                    if(rating[i] > rating[j]) temp++;
                    continue;
                }
            }
            answer = Math.max(answer, temp);
        }
        return answer;
    }
}