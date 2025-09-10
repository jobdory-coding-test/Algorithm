public class Day19_유사_칸토어_비트열 {
    public static void main(String[] args) {
        System.out.println(solution(2, 4, 17));
    }
    public static long solution(int n, long l, long r) {
        long answer = 0L;
        
        answer = dfs(r, n) - dfs((l-1L), n);
        
        return answer;
    }
    
    private static long dfs(long index, int n) {
		if(index == 0L) return 0L;
		if(n == 0L) return 1L;
		
		long divide = (long) Math.pow(5, n-1);
		
		long size = index / divide;		
        long rem = index % divide;


		long num = (long) Math.pow(4, n-1);
		long cnt = num * (size >= 3L ? size - 1L : size);
        
        if(size == 2L) return cnt;
	
		return cnt + dfs(rem, n-1);
    }
}
