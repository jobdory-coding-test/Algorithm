package Day24_캐시;

import java.util.*;
class Solution {
    public int solution(int cacheSize, String[] cities) {
        Set <String> s = new HashSet<>();
        List <String> l = new ArrayList<>();
        int cnt = 0;
        for(String city : cities){
            String cc = city.toUpperCase();
            if(s.contains(cc)){
                cnt++;
                l.remove(cc);
                l.add(cc);
            }
            else if(s.size() == cacheSize){
                cnt+=5;
                if(cacheSize == 0) continue;
                String temp = l.get(0);
                s.remove(temp);
                l.remove(temp);
                s.add(cc);
                l.add(cc);
            }
            else{
                cnt+=5;
                s.add(cc);
                l.add(cc);
            }
        }
        return cnt;
    }
}
