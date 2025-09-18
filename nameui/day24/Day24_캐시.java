import java.util.*;
import java.io.*;

class Solution {
    
    static ArrayList<String> cacheList;
    
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        
        // 실제 동적으로 사용할 캐시(조회용)
        cacheList = new ArrayList<>(cacheSize);
        
        // 캐시 사이즈가 아예 0 인 경우도 처리해주기
        if(cacheSize == 0) {
            answer = cities.length * 5;
            return answer;
        }

        for(int i = 0; i<cities.length; i++) {
            String nowCity = cities[i].toLowerCase();
            
            // 캐시 먼저 탐색. cache 가 비어있지 않고, 캐시에 현재 도시가 있는 경우.
            if(!cacheList.isEmpty() && cacheList.contains(nowCity)) {
                cacheList.remove(nowCity);
                cacheList.add(nowCity);
                answer += 1; // 캐시가 존재하므로 1 더하기.
                continue;
            }

            // 캐시에 없고, 캐시가 다 차지 않음.
            if(cacheList.size() < cacheSize) { // 캐시 다 찾을 때
                // 캐시가 다 안 찼으면, 마지막에 추가하기
                cacheList.add(nowCity);
                answer += 5;
                continue;
            }
            
            // 캐시에 없고, 캐시가 다 참.
            // 캐시에서 도시 지우기
            cacheList.remove(cacheList.get(0));
            // 캐시에 새로운 도시 추가하기
            cacheList.add(nowCity);
            answer += 5;
            
        }
        
        return answer;
    }
}