import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Day15_이진검색트리 {

	static StringBuilder sb = new StringBuilder();
	static int N;
	static Node rootNode;
	static List<Integer> weights;
	
	static class Node{
		int weight;
		Node root, left, right;

		Node(int weight){
			this.weight = weight;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		weights = new ArrayList<>();
		while(true) {
			String input = br.readLine();
			if(input == null || input.equals("")) break;
			weights.add(Integer.parseInt(input));
		}
		
		rootNode = new Node(weights.get(0));
		addNode2();
		postorder(rootNode);
		
		System.out.println(sb);
	}
	
	static void addNode2() {
	    for (int i = 1; i < weights.size(); i++) {
	        int weight = weights.get(i);
	        Node root = rootNode;
	        while (true) {
	            if (weight < root.weight) {
	            	// 왼쪽에 추가할 수 있을 때
	                if (root.left == null) {
	                    root.left = new Node(weight);
	                    break;
	                }
	                root = root.left;
	            } else {
	            	// 오른쪽에 추가할 수 있을 때
	                if (root.right == null) {
	                    root.right = new Node(weight);
	                    break;
	                }
	                root = root.right;
	            }
	        }
	    }
	}

	static void addNode() {
		Node parent = rootNode;
		int weight;
		for(int i=1; i<weights.size(); i++) {
			weight = weights.get(i);
			while(true) {
				if(weight < parent.weight) {
					// 왼쪽에 추가할 수 있을 때
					parent.left = new Node(weight);
					parent.left.root = parent;
					parent = parent.left;
					break;
				} else {
					// 오른쪽에 추가할 수 있을 때
					if(parent.root != null && weight > parent.root.weight) {
						parent = parent.root;
						continue;
					}
					parent.right = new Node(weight);
					parent.right.root = parent;
					parent = parent.right;
					break;
				}
			}
		}
	}
	
	static void postorder(Node node) {
		// 좌측 자식
		if(node.left != null) {
			postorder(node.left);
			node.left = null;
		}
		
		// 우측 자식
		if(node.right != null) {
			postorder(node.right);
			node.right = null;
		}
		
		if(node.left == null && node.right == null) {
			sb.append(node.weight).append('\n');
			return;
		}
	}

}
