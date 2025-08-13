import java.io.*;
import java.util.*;

public class Day15_이진검색트리 {
	private static class Node {
		int value;
		Node head, left, right;

		public Node(int value) {
			this.value = value;
			this.head = null;
			this.left = null;
			this.right = null;
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		String input = br.readLine();
		Node root = new Node(Integer.parseInt(input));

		while ((input = br.readLine()) != null) {
			Node node = new Node(Integer.parseInt(input));
			insertValue(root, node);
		}
		postOrder(root);
		System.out.println(sb.toString());

	}

	private static void insertValue(Node node, Node curr) {
		while (true) {
			if (node.value > curr.value) { // 현재 노드보다 curr value가 작을 때
				if (node.left == null) {
					node.left = curr;
					curr.head = node;
					return;
				}
				node = node.left;
			} else { // 현재 노드보다 curr.value가 클 때
				if (node.right == null) {
					node.right = curr;
					curr.head = node;
					return;
				}
				node = node.right;
			}
		}
	}

	private static StringBuilder sb = new StringBuilder();

	private static void postOrder(Node node) {
		if (node.left != null) {
			postOrder(node.left);
		}

		if (node.right != null) {
			postOrder(node.right);
		}

		sb.append(node.value).append("\n");
	}
}