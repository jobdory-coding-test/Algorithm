import java.util.*;

class Day24_캐시 {
    
    int chdiff = 'a'-'A';
    int max, result;
    Queue<String> q;
    StringBuilder sb;
    
    public int solution(int cacheSize, String[] cities) {
        sb = new StringBuilder();
        max = cacheSize;
        result = 0;
        q = new ArrayDeque<>();
        
        int len;
        boolean cacheHit=false;
        String name, temp;
        for(String city : cities){
            name = getName(city);
            cacheHit = false;
            // 같은 게 있으면 -> 지금 안 추가하고 마지막에 추가
            // 다 다르면 -> 다 넣고 마지막에 빼기
            len = q.size();
            for(int i=0; i<len; i++){
                temp = q.poll();
                
                if(temp.equals(name)){
                    cacheHit = true;
                    continue;
                }
                q.offer(temp);
            }
            
            q.offer(name);
            
            if(cacheHit){
                result++;
                continue;
            }
            
            result+= 5;
            if(q.size()>max){
                q.poll();
            }
        }
        return result;
    }
    
    private String getName(String str){
        sb.setLength(0);
        for(int i=0; i<str.length(); i++){
            if(str.charAt(i)-'a' >= 0) {
                sb.append(str.charAt(i));
            } else {
                sb.append((char)(str.charAt(i)+chdiff));
            }
        }
        return sb.toString();
    }
}