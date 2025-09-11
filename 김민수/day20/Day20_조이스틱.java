class Day20_조이스틱 {
    public int solution(String name) {
        int n = name.length();
        int answer = 0;
        
        for(int i = 0; i < n; i++) {
            char c = name.charAt(i);
            answer += Math.min(c - 'A', 'Z' - c + 1);
        }
        
        int count = n - 1;
        for(int pos = 0; pos < n; pos++) {
            int next = pos + 1;
            
            while(next < n && name.charAt(next) == 'A') {
                next++;
            }
            count = Math.min(count, Math.min(2 * pos + (n - next), pos + 2 * (n - next)));
            pos = next - 1;
        }
        
        return answer + count;
    }
}