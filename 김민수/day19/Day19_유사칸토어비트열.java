class Day19_유사칸토어비트열 {
    public int solution(int n, long l, long r) {
        int answer = 0;
        
        for(long i = l - 1; i < r; i++) {
            answer += cal(i);
        }
        
        return answer;
    }
    
    private static long cal(long x) {
        if(x % 5 == 2) {
            return 0;
        }
        
        if(x == 0) return 1;
        
        return cal(x / 5);
    }
}