package Day22_등대;

import java.util.*;
class Solution {
    class Node{
        int index;
        Node back;
        List<Node> nexts = new ArrayList<>();
        public Node(int index, Node back, Node next){
            this.index = index;
            this.back = back;
            if(next!=null) nexts.add(next);
        }
    }
    static boolean[] visited;
    static Node[] node;
    static Set<Integer> s;
    
    public int solution(int n, int[][] lighthouse) {
        node = new Node[n];
        for(int i=0;i<n;i++) node[i] = new Node(i,null,null);
        
        for(int[] temp : lighthouse){
            node[temp[0]-1].nexts.add(node[temp[1]-1]);
            node[temp[1]-1].back = node[temp[0]-1];   
            node[temp[1]-1].nexts.add(node[temp[0]-1]);
            node[temp[0]-1].back = node[temp[1]-1];
        }
        visited = new boolean[n];
        s = new HashSet<>();
        visited[node[0].index] = true;   
        dfs(node[0]);
    
        return s.size();
    }
    
    public static void dfs(Node node){
        int count = 0;
        for(Node n : node.nexts){
            if(!visited[n.index]){
                visited[n.index] = true;
                count++;
                dfs(n);
            }
        }
        int cnt = 0;
        for(Node n : node.nexts){
            if(s.contains(n.index)) cnt++;
        }
        if(cnt < count) {
            s.add(node.index);
        }
    }
}
