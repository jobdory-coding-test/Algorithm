import java.util.*;

class Solution {
    static final int GIVE_NUM = 0;
    static final int RECEIVE_NUM = 1;
    static final int SCORE = 2;
    
    static HashMap<String, int[]> map;
    static HashMap<String, HashMap<String, Integer>> giftRecord; // 누가 누구에게 줬는지.
    
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        
        // 맵 초기화
        map = new HashMap<>();
        giftRecord = new HashMap<>();
        
        for(int i = 0; i<friends.length; i++) {
            String name = friends[i];
            map.put(name, new int[3]);
            giftRecord.put(name, new HashMap<>());
        }
        
        for(int i = 0; i<gifts.length; i++) {
            String[] target = gifts[i].split(" ");
            map.get(target[0])[GIVE_NUM]++;
            map.get(target[1])[RECEIVE_NUM]++;
            
            // 선물 기록
            giftRecord.get(target[0]).put(target[1], giftRecord.get(target[0]).getOrDefault(target[1], 0) + 1);
        }
        
        // 선물지수 카운트
        for(int i = 0; i<friends.length; i++) {
            String name = friends[i];
            map.get(name)[SCORE] = map.get(name)[GIVE_NUM] - map.get(name)[RECEIVE_NUM];
        }
        
        // 다음 달 선물 계산
        int[] res = new int[friends.length];
        for(int i = 0; i < friends.length; i++) {
            for(int j = i + 1; j < friends.length; j++) {
                String a = friends[i];
                String b = friends[j];
                
                int toB = giftRecord.get(a).getOrDefault(b, 0);
                int toA = giftRecord.get(b).getOrDefault(a, 0);
                
                if(toB > toA) {
                    res[i]++;
                } else if(toA > toB) {
                    res[j]++;
                } else {
                    if(map.get(a)[SCORE] > map.get(b)[SCORE]) {
                        res[i]++;
                    } else if(map.get(b)[SCORE] > map.get(a)[SCORE]) {
                        res[j]++;
                    }
                }
            }
        }
        
        // 최대값 찾기
        for(int count : res) {
            answer = Math.max(answer, count);
        }
        
        return answer;
    }
}