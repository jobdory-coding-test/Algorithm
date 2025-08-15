package 서가은.day15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Day15_5639 {
    // [입력]
    // Int before[N] : 트리를 전위 순회한 결과
    //
    //
    // [구현]
    // 노드 왼쪽 서브트리 모든 키 < 노드의 키
    // 노드 오른쪽 서브트리 모든 키 > 노드의 키
    // 왼 오 모두 이진
    //
    //
    //
    // [출력]
    // 후위 순회한 결과를 한 줄씩

    // 이진트리 노드
    public static class Node {
        int key;
        Node left;
        Node right;

        Node(int k) {
            this.key = k;
        }

    }

    static Node root;

    // 검색
    public boolean search(int key) {
        Node cur = root;

        while (cur != null) {
            if (key == cur.key) {
                return true;
            }

            if (key < cur.key) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }

        }
        return false;
    }

    // 삽입
    static Node insert(Node n, int key) {
        if (n == null) {
            return new Node(key);
        }
        if (key < n.key) {
            n.left = insert(n.left, key);
        } else if (key > n.key) {
            n.right = insert(n.right, key);
        }

        return n;
    }


    // 후위 순회 재귀~
    private static void postorder(Node n) {
        if (n == null)
            return;
        postorder(n.left);
        postorder(n.right);
        System.out.println(n.key);
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        root = new Node(Integer.parseInt(br.readLine()));
        //한 줄 읽기
        String line = br.readLine();

        //굳이 StringTokenizer를 쓸 필요가 없음
        while (line != null && !line.isEmpty()) {

            int i = Integer.parseInt(line);
            root = insert(root, i);
            line = br.readLine();

        }

        postorder(root);

    }

}
