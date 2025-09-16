package 박성문.Day22;

import java.util.ArrayList;

public class Day22_등대 {
    static ArrayList<Integer>[] road;
    static int[] tree;
    static boolean[] isVisited;

    static void dfs(int pointer) {
        ArrayList<Integer> treeInfo = road[pointer];
        isVisited[pointer] = true;

        for(Integer i : treeInfo) {
            if(isVisited[i]) continue;
            dfs(i);
        }
        if(tree[pointer] == 1) return; // 현재 위치가 이미 등대라면 더 이상 볼 필요없이 종료
        if(treeInfo.size() == 1) { // 단말 노드라면 부모 노드가 등대로
            tree[treeInfo.get(0)] = 1;
            return;
        }
        for(Integer i : treeInfo) { // 현재 노드와 간선으로 연결된 노드들 중 등대가 아닌 노드가 있다면 해당 노드를 등대로
            if(tree[i] == 1) continue;
            tree[i] = 1;
        }
    }

    static void init(int n, int[][] input) {
        isVisited = new boolean[n + 1];
        road = new ArrayList[n + 1];
        tree = new int[n + 1];
        for(int i = 1; i <= n; i++) road[i] = new ArrayList<>();

        for(int i = 0; i < n - 1; i++) {
            int tempA = input[i][0];
            int tempB = input[i][1];
            road[tempA].add(tempB);
            road[tempB].add(tempA);
        }
    }

    static int getAnswer(int n) { // tree 배열을 반복문을 통하여 1, 즉 등대를 찾아 개수를 구함
        int answer = 0;
        for(int i = 1; i <= n; i++) answer += tree[i] == 1? 1 : 0;
        return answer;
    }
    public int solution(int n, int[][] input) {
        init(n, input);
        dfs(1);
        return getAnswer(n);
    }
}
