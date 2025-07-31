import java.util.*;
import java.io.*;

public class Main {
    static Node[] stations;
    static int maxSize = 1_000_002;
    static int head, tail, prevStation;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        stations = new Node[maxSize];

        for (int i = 0; i < N; i++) {
            int stationNumber = Integer.parseInt(st.nextToken());
            Node newStation = new Node();

            if (i == 0) {
                head = stationNumber;
                tail = stationNumber;
                newStation.next = stationNumber;
                newStation.prev = stationNumber;
            } else {
                newStation.prev = prevStation;
                newStation.next = head;
                stations[tail].next = stationNumber;
                stations[head].prev = stationNumber;
                tail = stationNumber;
            }

            stations[stationNumber] = newStation;
            prevStation = stationNumber;
        }

        // 로직

        for (int k = 0; k < M; k++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            int i, j, nextStationNum, prevStationNum;
            Node newStation;

            switch (command) {
                case "BN":
                    i = Integer.parseInt(st.nextToken());
                    j = Integer.parseInt(st.nextToken());

                    // 다음 역의 고유 번호를 출력
                    nextStationNum = stations[i].next;
                    sb.append(nextStationNum).append("\n");

                    // 역 설립
                    newStation = new Node();
                    newStation.prev = i;
                    newStation.next = nextStationNum;

                    stations[i].next = j;
                    stations[nextStationNum].prev = j;

                    stations[j] = newStation;

                    break;
                case "BP":
                    i = Integer.parseInt(st.nextToken());
                    j = Integer.parseInt(st.nextToken());

                    // 이전 역의 고유 번호를 출력
                    prevStationNum = stations[i].prev;
                    sb.append(prevStationNum).append("\n");

                    // 역 설립
                    newStation = new Node();
                    newStation.next = i;
                    newStation.prev = prevStationNum;

                    stations[prevStationNum].next = j;
                    stations[i].prev = j;

                    stations[j] = newStation;

                    break;
                case "CP":
                    i = Integer.parseInt(st.nextToken());

                    // 이전 역 출력
                    prevStationNum = stations[i].prev;
                    sb.append(prevStationNum).append("\n");

                    // 이전 역 폐쇄
                    int tempPrev = stations[prevStationNum].prev;
                    stations[tempPrev].next = i;
                    stations[i].prev = tempPrev;
                    stations[prevStationNum] = null;
                    break;
                case "CN":
                    i = Integer.parseInt(st.nextToken());
                    // 다음 역 출력
                    nextStationNum = stations[i].next;
                    sb.append(nextStationNum).append("\n");

                    // 다음 역 폐쇄
                    int tempNext = stations[nextStationNum].next;
                    stations[i].next = tempNext;
                    stations[tempNext].prev = i;
                    stations[nextStationNum] = null;

                    break;
                default:
                    break;
            }

        }
        System.out.print(sb.toString());
    }

}

class Node {
    int prev;
    int next;
}
