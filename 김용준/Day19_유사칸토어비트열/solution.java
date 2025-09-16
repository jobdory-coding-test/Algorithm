package Day19_유사칸토어비트열;

import java.util.*;
class Solution {
    static int cnt;
    public int solution(int n, long l, long r) {
        Queue<long[]> q = new ArrayDeque<>();
        q.add(new long[] {l,r,(long)Math.pow(5,n),(long)n});
        while(!q.isEmpty()){
            long[] cur = q.poll();
            long start = cur[0];
            long end = cur[1];
            long leng = cur[2];
            long N = cur[3];
            
            if(N == 0){
                cnt++;
                continue;
            }
            if(start<=leng/5){
                if(end<=leng/5) {
                    q.add(new long[] {start,end,leng/5,N-1});
                }
                else if(end>leng/5 && end<=leng/5*2) {
                    q.add(new long[] {start,leng/5,leng/5,N-1});
                    q.add(new long[] {1,end-leng/5,leng/5,N-1});
                }
                else if(end>leng/5*2 && end<=leng/5*3){
                    q.add(new long[]{start,leng/5,leng/5,N-1});
                    cnt+=(int)Math.pow(4,N-1);
                }
                else if(end>leng/5*3 && end<=leng/5*4){
                    q.add(new long[] {start,leng/5,leng/5,N-1});
                    cnt+=(int)Math.pow(4,N-1);
                    q.add(new long[] {1,end-leng/5*3,leng/5,N-1});
                }
                else if(end>leng/5*4){
                    q.add(new long[] {start,leng/5,leng/5,N-1});
                    cnt+=(int)Math.pow(4,N-1)*2;
                    q.add(new long[] {1,end-leng/5*4,leng/5,N-1});
                }
            }
            else if(start>leng/5 && start<=leng/5*2){
                if(end<=leng/5*2){
                    q.add(new long[] {start-leng/5,end-leng/5,leng/5,N-1});
                }
                else if(end>leng/5*2 && end<=leng/5*3){
                    q.add(new long[] {start-leng/5,leng/5,leng/5,N-1});
                }
                else if(end>leng/5*3 && end<=leng/5*4){
                    q.add(new long[] {start-leng/5,leng/5,leng/5,N-1});
                    q.add(new long[] {1,end-leng/5*3,leng/5,N-1});
                }
                else if(end>leng/5*4){
                    q.add(new long[] {start-leng/5,leng/5,leng/5,N-1});
                    cnt+=(int)Math.pow(4,N-1);
                    q.add(new long[] {1,end-leng/5*4,leng/5,N-1});

                }
            }
            else if(start >leng/5*2 && start <= leng/5*3){
                if(end>leng/5*3 && end<=leng/5*4){
                    q.add(new long[] {1,end-leng/5*3,leng/5,N-1});
                }
                else if(end>leng/5*4){
                    q.add(new long[] {1,end-leng/5*4,leng/5,N-1});
                    cnt+=(int)Math.pow(4,N-1);
                }
            }
            else if(start>leng/5*3 && start<=leng/5*4){
                if(end<=(leng/5)*4) {
                    q.add(new long[] {start-leng/5*3,end-leng/5*3,leng/5,N-1});
                }
                else if (end>leng/5*4){
                    q.add(new long[] {start-leng/5*3,leng/5,leng/5,N-1});
                    q.add(new long[] {1,end-leng/5*4,leng/5,N-1});
                }
            }
            else if(start>leng/5*4){
                q.add(new long[] {start-leng/5*4,end-leng/5*4,leng/5,N-1});
            } 
        }
            
        
        return cnt;
    }

}


