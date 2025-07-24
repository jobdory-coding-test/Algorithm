import java.io.*;
import java.util.*;

public class Day03_13335 {
	public static void main(String args[]) throws IOException{
		
		
	//백준 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		//[기본으로 입력 받아야하는 값]		
		int N = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());

		String[] input = br.readLine().split(" ");
		ArrayList<Integer> trucks = new ArrayList<>();	//ArrayList<INTEGER> trucks : 트럭들
		for (int i=0; i<N; i++) {
			trucks.add(Integer.parseInt(input[i]));
		}
		

		
		
	//[다리 배열 관련 변수]
		
		//다리 배열, 다리 길이 만큼 0 삽입해두고 시작(아 근데 여기서 메모리 초과 나려나..?), 지금 로직에서는 길이가 동적으로 가니깐 ArrayList
		ArrayList<Integer> bridge = new ArrayList<>();
		for(int i=0; i<W; i++) {
			bridge.add(0);
		}
		
		int bridgeFront = 0; //현재 다리 가장 앞 인덱스
		int waitNow = 0; //대기열 현재 인덱스
		int bridgeWeight = 0; //다리 현재 하중
		int result=0;
		
		
	//[구현]
		while (waitNow < N) {

			int target = trucks.get(waitNow);
			int outNext = bridge.get(bridgeFront); // 곧 나갈 애
//			System.out.printf("대기 맨 앞 트럭 %d   그 다음 대기 %d  ", target,outNext);
			
			if(L < (target+bridgeWeight-outNext)) { // 곧 나갈 애를 빼줬어야함..
				bridge.add(0);
			}
			else {
				bridge.add(target);
				bridgeWeight+=target;
				waitNow++;
			}
			
			
			bridgeWeight-=outNext;
			bridgeFront++;
			result++;	
//			System.out.println(bridge.subList(bridgeFront, bridgeFront+W));

		}
		
		//마지막 트럭 이동 하는 것까지 더하기
		result+=W;
		System.out.println(result);
		
		
		
		
        }	
}