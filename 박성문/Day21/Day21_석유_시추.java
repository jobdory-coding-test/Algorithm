package 박성문.Day21;

import java.util.ArrayList;
import java.util.HashMap;

public class Day21_석유_시추 {
    static int answer, row, column, keyCnt, count; // keyCnt는 현재까지 발견한 석유의 고유번호
    static HashMap<Integer, Integer> mapInfo; //각 석유별로 고유번호를 key로, 해당 지역의 석유 크기를 value로
    static int[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    static void dfs(int r, int c, int key) {
        map[r][c] = key;
        count++;
        for(int i = 0; i < 4; i++) {
            int tempR = r + dr[i];
            int tempC = c + dc[i];
            if(!checked(tempR,tempC) || map[tempR][tempC] != 1) continue;
            dfs(tempR, tempC, key);
        }
    }

    static boolean checked(int r, int c) {
        return r >= 0 && r < row && c >= 0 && c < column;
    }

    static void init(int[][] land) {
        keyCnt = 2; // 석유의 고유번호
        row = land.length;
        column = land[0].length;
        mapInfo = new HashMap<>();
        map = land.clone();
    }

    public int solution(int[][] land) {
        init(land);

        for(int i = 0; i < column; i++) {
            int temp = 0; // 한 시추관에서 얻은 석유를 임시 저장

            ArrayList<Integer> tempArray = new ArrayList<>();
            for(int j = 0; j < row; j++) {
                count = 0; // dfs를 실행하며 발견한 석유의 크기를 저장
                if(map[j][i] == 0 || tempArray.contains(map[j][i])) continue;
                if(map[j][i] > 1) temp += mapInfo.getOrDefault(map[j][i], 0);
                else {
                    dfs(j, i, keyCnt);
                    mapInfo.put(keyCnt,count);
                    keyCnt++;
                }
                tempArray.add(map[j][i]);
                temp += count;
            }
            answer = Math.max(answer, temp);
        }
        return answer;
    }
}
