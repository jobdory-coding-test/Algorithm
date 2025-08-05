package 서가은.day02;
import java.util.*;
import java.io.*;

public class Day02_17298 {
	//	완전탐색
		//	역순으로 가
	public static void main(String args[]) throws IOException {
	
		
		//BufferedReader를 사용해서 입력값 받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int len = Integer.parseInt(br.readLine());
		String[] input = br.readLine().split(" ");
		
		List<Integer> arr1 = new ArrayList<>();
		for (int j = 0; j < len; j++) {
			arr1.add(Integer.parseInt(input[j]));
		}
//		6, 5, 3, 8, 2, 7
 
//		8, 8, 8, -1, 7, -1
//		 8   8    8  , -1 , 7 ,-1
		
		Stack<Integer> result = new Stack<>();
		
//		int len = arr1.size(); //배열 크기 
		Stack<Integer> stack = new Stack<>();
		result.push(-1); //일단 -1 넣고 시작 
		stack.push(arr1.get(len-1));
		
		int i = len-2;
		//역순으로 완전 탐			
//		while(!(stack.isEmpty())) {
		while(i >= 0) {  //런타임 에러나서 빈 배열 검사(!(stack.isEmpty()))를 i가 0위에 있는가 로 바꿨더니 해결
				int keyInt = arr1.get(i); //키 
				
			//만약 스택에 있는 숫자보다 클때
				//본인보다 작은 수 다 pop하고 큰 수 peak
				//그리고 본인 수 넣기
//				System.out.printf("## %d %d\n\n",keyInt, arr1.get(i));
				
				while(!stack.isEmpty() && stack.peek() <= arr1.get(i)) {
				    stack.pop();
				}
				if(stack.isEmpty()) {
					result.push(-1);
				}
				else {
					result.push(stack.peek());
				}
				stack.push(keyInt);
				i--;
		}
		
		
		
		//최종출

		List<Integer> list = new ArrayList<>();
		while (!result.isEmpty()) {
		    list.add(result.pop());
		}
		
		

		StringBuilder sb = new StringBuilder();
		for (int num : list) {
		    sb.append(num).append(" ");
		}
		
		
		
		
		
		//진짜끝 
		System.out.println(sb.toString().trim());

	}
}