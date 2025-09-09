package B형_병사관리;
import java.util.*;

class UserSolution
{
    static TreeSet<Integer> [][]solder;
    static int[][] rank = {{1,2,3,4,5},{1,2,3,4,5},{1,2,3,4,5},{1,2,3,4,5},{1,2,3,4,5}};
	public void init()
	{
        solder = new TreeSet[5][5];
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                solder[i][j] = new TreeSet<>();
            }
        }
	}
	
	public void hire(int mID, int mTeam, int mScore)
	{
        for(int i=0;i<5;i++){
            if(rank[mTeam-1][i] == mScore){
                solder[mTeam-1][i].add(mID);
                return;
            }
        }
        
	}
	
	public void fire(int mID)
	{
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                if(solder[i][j].contains(mID))solder[i][j].remove(mID);
            }
        }
	}

	public void updateSoldier(int mID, int mScore)
	{  
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                if(solder[i][j].contains(mID)) {
                    solder[i][j].remove(mID);
                    for(int k=0;k<5;k++){
                        if(rank[i][k] == mScore){
                            solder[i][k].add(mID);
                            return;
                        }
                    }
                }
            }
        }
	}

	public void updateTeam(int mTeam, int mChangeScore)
	{
        for(int i=0;i<5;i++){
            if(rank[mTeam-1][i]+mChangeScore>=5) rank[mTeam-1][i] = 5;
            else if(rank[mTeam-1][i]+mChangeScore<=1) rank[mTeam-1][i] = 1;
            else rank[mTeam-1][i]+= mChangeScore; 
        }
	}
	
	public int bestSoldier(int mTeam)
	{
        Queue<Integer>q = new ArrayDeque<>();
        int maxRank = 0;
        for(int i=0;i<5;i++){
            if(!solder[mTeam-1][i].isEmpty() && maxRank<rank[mTeam-1][i]) maxRank = rank[mTeam-1][i];
        }
        for(int i=0;i<5;i++){
            if(rank[mTeam-1][i] == maxRank) q.add(i);
        }
        int maxNum = 0;
        while(!q.isEmpty()){
            int r = q.poll();
            if(!solder[mTeam-1][r].isEmpty()){
                if(maxNum<solder[mTeam-1][r].last()){
                    maxNum = solder[mTeam-1][r].last();
                }
            }
        }
        return maxNum;
    }
}


