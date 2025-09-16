import java.util.*;

class Solution {
    public int dfs(int node, TreeSet<Integer>[] arr, int[] ans){
        if(arr[node].isEmpty()) return 0;
        int onChild = 0;
        for(int child : arr[node]){
            onChild += dfs(child, arr, ans);
        }
        if(arr[node].size() - onChild != 0){
            ans[0]++;
            return 1;
        }
        return 0;
    }

    public int solution(int n, int[][] lighthouse) {
        int answer = 0;
        TreeSet<Integer>[] arr = new TreeSet[n+1];
        int[] parent = new int[n+1];
        
        for(int i=1; i<=n; i++){
            arr[i] = new TreeSet<>();
        }
        
        for(int i=0; i<n-1; i++){
            arr[lighthouse[i][0]].add(lighthouse[i][1]);
            arr[lighthouse[i][1]].add(lighthouse[i][0]);
        }
        
        boolean[] visited = new boolean[n+1];
        Deque<Integer> deque = new ArrayDeque<>();
        deque.offerLast(1);
        while(!deque.isEmpty()){
            int node = deque.pollFirst();
            visited[node] = true;
            Iterator<Integer> iterator = arr[node].iterator();
            while(iterator.hasNext()){
                int item = iterator.next();
                parent[item] = node;
                arr[item].remove(node);
                if(!visited[item]){
                    deque.offerLast(item);
                }
            }
        }

        int[] hold = new int[1];
        dfs(1, arr, hold);
        answer = hold[0];
        return answer;
    }
}
