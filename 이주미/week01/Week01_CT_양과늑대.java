class Week01_CT_양과늑대 {
    
    int N, maxSheep;
    int[] node;
    int[][] edge;
    boolean[] visited;
        
    public int solution(int[] info, int[][] edges) {
        N = info.length; maxSheep = 0;
        node = info;
        edge = edges;
        visited = new boolean[N];
        
        visited[0] = true;
        dfs(0, 1, 0);
        
        return maxSheep;
    }
    
    private void dfs(int idx, int sheep, int wolf){
        
        if(sheep <= wolf){
            return;
        }
        
        maxSheep = Math.max(maxSheep, sheep);
        
        for(int[] ed : edge){
            if(visited[ed[0]] && !visited[ed[1]]){
                visited[ed[1]] = true;
                
                if(node[ed[1]]==0) dfs(ed[1], sheep+1, wolf);
                else dfs(ed[1], sheep, wolf+1);
                
                visited[ed[1]] = false;
            }
        }
    }
}