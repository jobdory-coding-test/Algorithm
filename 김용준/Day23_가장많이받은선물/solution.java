package Day23_가장많이받은선물;

import java.util.*;
class Solution {
    static int [][]giveget;
    static int [] givelate;
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        giveget = new int[friends.length][friends.length];
        givelate = new int[friends.length];
        
        for(String g : gifts){
            StringTokenizer st = new StringTokenizer(g);
            String give = st.nextToken();
            String get = st.nextToken();
            int givei = findindex(give, friends);
            int geti = findindex(get, friends);
            giveget[givei][geti]++;
            givelate[givei]++;
            givelate[geti]--;
        }
        int [] result = new int[friends.length];
        for(int i=0;i<friends.length;i++){
            for(int j=0;j<friends.length;j++){
                if(i==j) continue;
                if(giveget[i][j] > giveget[j][i]) result[i]++;
                else if(giveget[i][j] < giveget[j][i]) result[j]++;
                else{
                    if(givelate[i] > givelate[j]) result[i]++;
                    else if(givelate[i]<givelate[j]) result[j]++;
                }
            }
        }
        Arrays.sort(result);
        return result[result.length-1]/2;
    }
    public static int findindex(String n, String[] friends){
        for(int i=0;i<friends.length;i++){
            if(friends[i].equals(n)) return i;
        }
        return 0;
    }
}
