import java.util.*;
import java.io.*;

public class Main {
	private static class Node {
		Node parent;
		Node left;
		Node right;
		int nodeNum;
		boolean visited;
		
		public Node(Node parent, Node left, Node right, int nodeNum, boolean visited) {
			this.parent = parent;
			this.left = left;
			this.right = right;
			this.nodeNum = nodeNum;
			this.visited = visited;
		}
		
		// 노드 삽입
		public void insert(int num) {
			//오른쪽 자식 노드
			if(num < this.nodeNum) {
				if(left == null) {
					this.left=new Node(this, null, null, num, false);
				} else {
					left.insert(num);
				}
				
			} else if(num > this.nodeNum) {
				if(right == null) {
					this.right=new Node(this, null, null, num, false);
				} else {
					right.insert(num);
				}
			}
		}
	}
	
	private static Node rootNode;
	private static int rootNum;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		rootNum=Integer.parseInt(br.readLine());
		rootNode = new Node(null, null, null, rootNum, false);
		while(true) {
			String inputStr = br.readLine();
			if(inputStr == null || inputStr.equals("")) break;
			int num = Integer.parseInt(inputStr);
			
			rootNode.insert(num);
		}
		printTree();
	}
	
	private static void printTree() {
		StringBuilder sb = new StringBuilder();
		
		// 가장 왼쪽 노드에서 시작
		while(rootNode.left != null) {
			rootNode = rootNode.left;
		}
		
		while(true) {
			boolean check = false;
			if(rootNode.parent == null) {
				check = true;
				// 왼쪽 노드를 탐색하지 않았다면 더 탐색
				if(rootNode.left != null && !rootNode.left.visited) {
					check = false;
				}
				// 오른쪽 노드를 탐색하지 않았다면 더 탐색
				if(rootNode.right != null && !rootNode.right.visited) {
					check = false;
				}
			}
			if(check) break;
			
			rootNode.visited = true;
			if(rootNode.left != null && !rootNode.left.visited) {
				rootNode = rootNode.left;
			} else if(rootNode.right != null && !rootNode.right.visited) {
				rootNode = rootNode.right;
			} else { // 부모노드로 이동
				sb.append(rootNode.nodeNum).append("\n");
				rootNode = rootNode.parent;
			}
		}
		sb.append(rootNode.nodeNum);
		
		System.out.print(sb);
	}
}