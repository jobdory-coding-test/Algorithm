package 박성문.Day24;

import java.util.Deque;
import java.util.LinkedList;

public class Day24_캐시 {
    static int answer;
    static Deque<String> cache;

    static void run(int cacheSize, String[] cities) {
        cache = new LinkedList();
        for(String cityName : cities) {
            cityName = cityName.toLowerCase();
            if(cache.contains(cityName)) {
                answer++;
                cache.remove(cityName);
                cache.offer(cityName);
                continue;
            }
            answer += 5;
            if(cache.size() < cacheSize) {
                cache.offer(cityName);
            } else {
                cache.remove();
                cache.offer(cityName);
            }
        }
    }

    public int solution(int cacheSize, String[] cities) {
        if(cacheSize == 0) return cities.length * 5;
        run(cacheSize, cities);
        return answer;
    }
}
