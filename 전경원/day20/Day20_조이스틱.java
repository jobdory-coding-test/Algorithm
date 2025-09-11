class Solution {
    
    static int answer = 0;
    static int s, e;
    
    public int solution(String name) {
        checkUpDown(name);
        
        int N = name.length();
        int minDist = N - 1;
        
        for (int cursor = 0; cursor < N; cursor++) {
            int next = cursor + 1;

            while (next < N && name.charAt(next) == 'A') {
                next++;
            }
            int distFront = (cursor) * 2 + N - next;
            int distBack = (N - next) * 2 + cursor;
            minDist = Math.min(minDist, Math.min(distFront, distBack));
        }
        return answer + minDist;
    }
    
    static void checkUpDown(String name) {
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);      
            answer += Math.min(c - 'A', 'Z' - c + 1);
        }
    }
}