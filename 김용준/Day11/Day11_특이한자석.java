package Day11;

import java.io.*;
import java.util.*;

public class Day11_특이한자석 {
    static int K;
    static List<Integer> [] mag = (List<Integer>[]) new LinkedList[4];
    static int count;
    static boolean []visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for(int test_case=1;test_case<=T;test_case++){
            st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            for(int i=0;i<4;i++){
                st = new StringTokenizer(br.readLine());
                mag[i] = new LinkedList<>();
                for(int j=0;j<8;j++){
                    mag[i].add(Integer.parseInt(st.nextToken()));
                }
            }
            count = 0;
            for(int i=0;i<K;i++){  //K번 회전 진행
                st = new StringTokenizer(br.readLine());
                int move_mag = Integer.parseInt(st.nextToken())-1;
                int dir = Integer.parseInt(st.nextToken());
                
                // 회전
                visited = new boolean[4];
                turn(move_mag, dir);
            }
            //점수확인
                for(int j=0;j<4;j++){
                    if(mag[j].get(0) == 1){
                        count+=(int)Math.pow(2,j);
                    }
                }

            StringBuilder sb = new StringBuilder();
            sb.append("#").append(test_case).append(" ").append(count);
            System.out.println(sb);
        }
    }
    public static void turn(int n, int dir){
        visited[n] = true;
        //좌우확인
        if(n-1>=0 && !visited[n-1] && mag[n-1].get(2) != mag[n].get(6)){
            turn(n-1,-dir);
        }
        if(n+1<4 && !visited[n+1] && mag[n+1].get(6) != mag[n].get(2)){
            turn(n+1,-dir);
        }
        //회전
        if(dir == 1){
            mag[n].add(0,mag[n].remove(7));
        }else{
            mag[n].add(mag[n].remove(0));
        }
    }
}
