import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Day04_철도공사 {

    // 1차 풀이
    private static int MAX_NUM = 1000000;
    private static int N, M;
    private static int[] location; // 존재하면 1, 아니면 0
    private static List<Integer> train;
    private static String[] constructTypes = {"BN", "BP", "CN", "CP"};
    private static int[][] work;

    //2차 풀이
    private static int[] train_set;
    private static int[] prev;
    private static int[] next;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        location = new int[MAX_NUM+1];
        prev = new int[MAX_NUM+1];
        next = new int[MAX_NUM+1];

        train = new LinkedList<>();
        train_set = new int[N];
        work = new int[M][3];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            int val = Integer.parseInt(st.nextToken());
//            train.add(val);
            train_set[i] = val;
            location[val] = 1;
        }

        for (int i=0; i<N; i++) {
            prev[train_set[i]] = train_set[(i+N-1) % N];
            next[train_set[i]] = train_set[(i+1) % N];
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            String constructType = st.nextToken();
            for(int j=0; j<4; j++){
                if(constructType.equals(constructTypes[j])){
                    work[i][0] = j;
                    work[i][1] = Integer.parseInt(st.nextToken());
                    if(j <= 1){
                        work[i][2] = Integer.parseInt(st.nextToken());
                    }
                    break;
                }
            }
        }

        construction();

    }

    private static void construction() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<M; i++){
            // singlework[1]: 고유번호 i, singlework[2] : 고유번호 j
            int new_loc_num = -1;
            int val_i = work[i][1];
            int val_j = work[i][2];
            switch (work[i][0]){
                case 0:
                    new_loc_num = next[val_i];
                    sb.append(new_loc_num).append("\n");

                    next[val_i] = val_j;
                    prev[val_j] = val_i;
                    next[val_j] = new_loc_num;
                    prev[new_loc_num] = val_j;

                    location[val_j] = 1;
                    break;
                case 1:
                    new_loc_num = prev[val_i];
                    sb.append(new_loc_num).append("\n");

                    next[new_loc_num] = val_j;
                    prev[val_j] = new_loc_num;
                    next[val_j] = val_i;
                    prev[val_i] = val_j;

                    location[val_j] = 1;
                    break;
                case 2:
                    new_loc_num = next[val_i];
                    sb.append(new_loc_num).append("\n");

                    next[val_i] = next[new_loc_num];
                    prev[next[new_loc_num]] = val_i;

                    location[new_loc_num] = 0;
                    break;
                case 3:
                    new_loc_num = prev[val_i];
                    sb.append(new_loc_num).append("\n");

                    prev[val_i] = prev[new_loc_num];
                    next[prev[new_loc_num]] = val_i;

                    location[new_loc_num] = 0;
                    break;
            }
        }
        System.out.println(sb);
    }

    private static void construction_arraylist() {
        for(int i=0; i<M; i++){
            int new_loc_num=-1;
            int loc = location[work[i][1]];
            int find_loc;
            switch (work[i][0]){
                // singlework[1]: 고유번호 i, singlework[2] : 고유번호 j
                case 0:
//                    new_loc_num = train.get(location[work[i][1]]+1);
                    find_loc = (loc+1) % train.size();
                    new_loc_num = train.get(find_loc);
                    train.add(new_loc_num, work[i][2]);
                    break;
                case 1:
//                    new_loc_num = train.get(location[work[i][1]]-1);
                    find_loc = (loc+train.size()-1) % train.size();
                    new_loc_num = train.get(find_loc);
                    train.add(location[work[i][1]]-1, work[i][2]);
                    break;
                case 2:
//                    new_loc_num = train.get(location[work[i][1]]+1);
                    find_loc = (loc+1) % train.size();
                    new_loc_num = train.get(find_loc);
                    train.remove(find_loc);
                    location[new_loc_num]=-1;
                    break;
                case 3:
//                    new_loc_num = train.get(location[work[i][1]-1]);
                    find_loc = (loc+ train.size()-1) % train.size();
                    new_loc_num = train.get(find_loc);
                    train.remove(find_loc);
                    location[new_loc_num]=-1;
                    break;
            }

            // location[] 갱신
            for (int j = 0; j < train.size(); j++) {
                location[train.get(j)] = j;
            }

            System.out.println(new_loc_num);
        }
    }

}
