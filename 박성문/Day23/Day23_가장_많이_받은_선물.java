package 박성문.Day23;

import java.util.HashMap;

public class Day23_가장_많이_받은_선물 {
    static HashMap<String, int[]> humanInfo;
    static int[] giftPoint;

    static int getAnswer(String[] friends) {
        int answer = 0;
        for(int i = 0; i < friends.length; i++) {
            int temp = 0;
            for(int j = 0; j < humanInfo.get(friends[i]).length; j++) {
                if(i == j) continue;
                if(humanInfo.get(friends[i])[j] > humanInfo.get(friends[j])[i]) {
                    temp++;
                } else if(humanInfo.get(friends[i])[j] == humanInfo.get(friends[j])[i]) {
                    if(giftPoint[i] > giftPoint[j]) temp++;
                }
            }
            answer = Math.max(answer, temp);
        }
        return answer;
    }

    static void updatePoint(String[] friends) {
        for(int i = 0; i < friends.length; i++) {
            for(int j = 0; j < humanInfo.get(friends[i]).length; j++) {
                if(i == j) continue;
                giftPoint[i] += humanInfo.get(friends[i])[j];
                giftPoint[i] -= humanInfo.get(friends[j])[i];
            }
        }
    }

    static int getFriendsIndex(String[] f, String name) {
        for(int i = 0; i < f.length; i++) {
            if(f[i].equals(name)) return i;
        }
        return -1;
    }

    static void giftsAction(String[] friends, String[] gifts) {
        for(int i = 0; i < gifts.length; i++) {
            String[] info = gifts[i].split(" ");
            humanInfo.get(info[0])[getFriendsIndex(friends, info[1])]++;
        }
    }

    static void init(String[] friends, String[] gifts) {
        giftPoint = new int[friends.length];
        humanInfo = new HashMap<>();

        for(String name : friends) humanInfo.putIfAbsent(name, new int[friends.length]);
    }

    public int solution(String[] friends, String[] gifts) {
        init(friends, gifts);
        giftsAction(friends, gifts);
        updatePoint(friends);
        return getAnswer(friends);
    }
}
