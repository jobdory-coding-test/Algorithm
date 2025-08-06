package 서가은.day04;
import java.io.*;
import java.util.*;

public class Main<E> {
	private Node<E> head;
	private Node<E> tail;
	private int size;

	public Main() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	private static class Node<E> {
		private E item;
		private Node<E> next;
		private Node<E> prev;

		Node(Node<E> prev, E item, Node<E> next) {
			this.item = item;
			this.next = next;
			this.prev = prev;
		}
	}

	// 시작할 때 맨 뒤에 넣는 메서드
	public void add(E value) {
		Node<E> new_node = new Node<>(null, value, null);
		if (head == null) {
			head = tail = new_node;
			new_node.next = new_node.prev = new_node;
		} else {
			new_node.prev = tail;
			new_node.next = head;
			tail.next = new_node;
			head.prev = new_node;
			tail = new_node;
		}
		size++;
	}

	// get + print + add
	public int BN(E index, E value) {
		Node<E> current = find(index);
		Node<E> next_node = current.next;
		Node<E> new_node = new Node<>(current, value, next_node);
		current.next = new_node;
		next_node.prev = new_node;
		if (current == tail) tail = new_node;
		size++;
		return (int) next_node.item;
	}

	// get + print + add
	public int BP(E index, E value) {
		Node<E> current = find(index);
		Node<E> prev_node = current.prev;
		Node<E> new_node = new Node<>(prev_node, value, current);
		prev_node.next = new_node;
		current.prev = new_node;
		if (current == head) head = new_node;
		size++;
		return (int) prev_node.item;
	}

	// get + delete
	public int CP(E index) {
		Node<E> current = find(index);
		Node<E> deleteNode = current.prev;

		int result = (int) deleteNode.item;

		// 노드가 1개뿐인 상황..
		if (deleteNode == current) {
			head = tail = null;
		} else {
			deleteNode.prev.next = deleteNode.next;
			deleteNode.next.prev = deleteNode.prev;
			// 머리 꼬리 처리
			if (deleteNode == head) head = deleteNode.next;
			if (deleteNode == tail) tail = deleteNode.prev;
		}

		size--;
		return result;
	}

	// get + delete
	public int CN(E index) {
		Node<E> current = find(index);
		Node<E> deleteNode = current.next;

		int result = (int) deleteNode.item;

		// 노드가 1개뿐인 상황..
		if (deleteNode == current) {
			head = tail = null;
		} else {
			deleteNode.prev.next = deleteNode.next;
			deleteNode.next.prev = deleteNode.prev;
			// 머리 꼬리 처리
			if (deleteNode == head) head = deleteNode.next;
			if (deleteNode == tail) tail = deleteNode.prev;
		}

		size--;
		return result;
	}

	// 고유 번호 노드 찾기 (순환 연결리스트 순회)
	private Node<E> find(E index) {
		Node<E> current = head;
		do {
			if (current.item.equals(index)) return current;
			current = current.next;
		} while (current != head);
		return null; // 못 찾으면 null 반환
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 리스트 수 와 공사 진행
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		// 리스트 넣기
		Main<Integer> list = new Main<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) list.add(Integer.parseInt(st.nextToken()));

		StringBuilder sb = new StringBuilder();

		// 함수 너무 많아서... if문 안쓰고 switch문 쓰기..
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			int index = Integer.parseInt(st.nextToken());

			switch (cmd) {
				case "BN":
					int val1 = Integer.parseInt(st.nextToken());
					sb.append(list.BN(index, val1)).append("\n");
					break;
				case "BP":
					int val2 = Integer.parseInt(st.nextToken());
					sb.append(list.BP(index, val2)).append("\n");
					break;
				case "CP":
					sb.append(list.CP(index)).append("\n");
					break;
				case "CN":
					sb.append(list.CN(index)).append("\n");
					break;
			}
		}
		System.out.print(sb);
	}
}
