class Day19_유사칸토어비트열 {
    static StringBuilder sb = new StringBuilder();
    static int[] dp;
    public static int solution(int n, long l, long r) {
        // dp : 구간 안에 있는 1의 개수
        dp = new int[n+1];
        dp[0] = 1;
        for(int i=1; i<n; i++){
            dp[i] = dp[i-1]*4;
        }
        
        // l과 r의 인덱스가 어느 구간에 위치하는지 찾아서 : 구간 내의 1의 개수 확인
        int oneInLeft = findOneInLeft(n, l-1);
        int oneInRight = findOneInLeft(n, r);
        return oneInRight-oneInLeft;
    }
    
    static int findOneInLeft(int depth, long left) {
        if(depth == 1){
            if(left==0) return 0;
            else if(left==1) return 1;
            else if(left==2) return 2;
            else if(left==3) return 2;
            else if(left==4) return 3;
        }
        
        int total = 0;
        
        // 몇 번째 구간에 해당하는지 확인
        for(int t=0; t<5; t++){
            if(left >= Math.pow(5, depth-1)*(t+1)){
                if(t!=2) total+= dp[depth-1];
                continue;
            }
            if(t==2) break;
            long indexInRange = left%((long)Math.pow(5, depth-1));
            total += findOneInLeft(depth-1, indexInRange);
            break;
        }
        return total;
    }
}