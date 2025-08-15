package Day15;
import java.util.*;
import java.io.*;

public class Day15_이진검색트리 {
    static class Node{
        int v;
        Node back;
        Node left;
        Node right;

        Node(int v){
            this.v = v;
            this.back = null;
            this.left = null;
            this.right = null;
        }
    }

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        Node root = new Node(Integer.parseInt(input)); 

        while(true){
            input = br.readLine();
            if(input == null || input == "") break;
            Node temp = new Node(Integer.parseInt(input));
            jun(root,temp);
        }
        huu(root);
        
    }
    public static void jun(Node cur, Node temp){
        while(true){
            if(cur.v > temp.v){
                if(cur.left == null){
                    cur.left = temp;
                    temp.back = cur;
                    return;
                }
                cur = cur.left;
            }
            else{
                if(cur.right == null){
                    cur.right = temp;
                    temp.back = cur;
                    return;
                }
                cur = cur.right;
            }
        }
    }
    public static void huu(Node cur){
        if(cur.left != null){
            huu(cur.left);
        }
        if(cur.right!=null){
            huu(cur.right);
        }
        System.out.println(cur.v);
    }
}
