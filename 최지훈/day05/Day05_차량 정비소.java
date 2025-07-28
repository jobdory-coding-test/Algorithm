import java.util.*;
import java.io.*;

public class Solution {
    static StringBuilder sb = new StringBuilder();
    static int T, N, M, K, A, B;
    static List<int[]> recDesk, repDesk; // 접수 창구, 정비 창구
    static List<int[]> customer;
    static Queue<int[]> cusQ; // 접수 창고 대기 큐
    static Queue<int[]> repQ; // 정비 창고 대기 큐

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());

        for(int t=1; t<=T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            recDesk = new ArrayList<>();
            repDesk = new ArrayList<>();
            // 접수 창구 대기 시간
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) {
                int time = Integer.parseInt(st.nextToken());
                recDesk.add(new int[] {0,time,0}); // {진행 시간, 기준 시간, 고객 번호}
            }
            // 정비 창구 대기 시간
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<M; i++) {
                int time = Integer.parseInt(st.nextToken());
                repDesk.add(new int[] {0,time,0});
            }

            customer = new ArrayList<>();
            // 고객 도착 시간 (고객 번호는 1부터)
            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=K; i++) {
                int time = Integer.parseInt(st.nextToken());
                customer.add(new int[] {time, i}); // {도착 시간, 고객 번호}
            }

            // 고객 정렬 후 큐에 담기
            cusQ = new ArrayDeque<>();
            sortAndQ();

            sb.append("#").append(t).append(" ").append(cusProcess()).append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    static void sortAndQ() {
        Collections.sort(customer, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                // 도착 시간이 다르면 오름차순
                if(a[0] != b[0]) return Integer.compare(a[0], b[0]);
                    // 같으면 고객 번호 순
                else return Integer.compare(a[1], b[1]);
            }
        });

        cusQ.addAll(customer);
    }

    static int cusProcess() {
        int sum = 0;
        int nowTime = -1;

        // 정비 창고 대기 큐
        repQ = new ArrayDeque<>();
        while(true) {
            nowTime++;

            // 1. 접수 대기 큐와 접수 창구 배열에 고객이 없다면 종료
            if(check()) {
                break;
            }

            List<int[]> repPrev = new ArrayList<>(); // 접수가 끝난 고객들 정렬 예정 배열
            // 2. 접수 및 정비 창구 배열 진행 시간 늘리기 -> 접수 끝나면 접수 창구에서 정비 창구로 이동
            for(int i=0; i<N; i++) {
                if(recDesk.get(i)[2] != 0) {
                    int[] newArr;
                    // 접수가 종료 되었다면 비우기 & 정비 창고 정렬 배열에 추가 {고객 번호, 이용 접수 창구 번호}
                    if(recDesk.get(i)[0] + 1 == recDesk.get(i)[1]) {
                        newArr = new int[] {0, recDesk.get(i)[1], 0};
                        repPrev.add(new int[] {recDesk.get(i)[2], i});
                    }
                    else newArr = new int[] {recDesk.get(i)[0] + 1, recDesk.get(i)[1], recDesk.get(i)[2]};
                    recDesk.set(i, newArr);
                }
            }
            for(int i=0; i<M; i++) {
                if(repDesk.get(i)[2] != 0) {
                    int[] newArr = null;
                    // 접수가 종료 되었다면 비우기
                    if(repDesk.get(i)[0] + 1 == repDesk.get(i)[1]) {
                        newArr = new int[] {0, repDesk.get(i)[1], 0};
                    }
                    else newArr = new int[] {repDesk.get(i)[0] + 1, repDesk.get(i)[1], repDesk.get(i)[2]};
                    repDesk.set(i, newArr);
                }
            }

            // 3. 접수 창구 순서대로 순회하며 비어있으면 고객 추가 -> cusQ에서 빼기
            for(int i=0; i<N; i++) {
                if(recDesk.get(i)[2] == 0) {
                    // 큐의 top 요소가 들어가도 되는 시간이라면 추가 후 큐에서 삭제
                    if(!cusQ.isEmpty() && cusQ.peek()[0] <= nowTime) {
                        recDesk.set(i, new int[] {0, recDesk.get(i)[1], cusQ.peek()[1]});
                        //if(i + 1 == A) sum += cusQ.peek()[1]; // sum에 고객 번호 추가
                        cusQ.poll();
                    }
                }
            }

            // 4. 정비 창고 큐 삽입 전 배열 정렬 -> 창구 번호 순 정렬 (1번 인덱스)
            Collections.sort(repPrev, (o1, o2) -> {
                return Integer.compare(o1[1], o2[1]);
            });
            // 정비 창구 큐 삽입
            repQ.addAll(repPrev);

            // 5. 정비 창구 순서대로 돌면서 비어있으면 고객 추가 -> repQ에서 빼기
            for(int i=0; i<M; i++) {
                if(repDesk.get(i)[2] == 0) {
                    // 큐의 top 요소가 들어가도 되는 시간이라면 추가 후 큐에서 삭제
                    if(!repQ.isEmpty()) {
                        repDesk.set(i, new int[] {0, repDesk.get(i)[1], repQ.peek()[0]});
                        // 접수 창고 번호와 정비 창구 번호가 같다면
                        if(i + 1 == B && repQ.peek()[1] + 1 == A) {
                            sum += repQ.peek()[0]; // sum에 고객 번호 추가
                        }
                        repQ.poll();
                    }
                }
            }
        }

        if(sum == 0) return -1;

        return sum;
    }

    static boolean check() {
        if(!cusQ.isEmpty() || !repQ.isEmpty()) {
            return false;
        }
        for(int i=0; i<N; i++) {
            if(recDesk.get(i)[2] != 0) return false;
        }
        for(int i=0; i<M; i++) {
            if(repDesk.get(i)[2] != 0) return false;
        }
        return true;
    }
}