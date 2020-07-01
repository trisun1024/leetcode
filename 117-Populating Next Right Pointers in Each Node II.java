import java.util.*;

class PopulatingNextRightPointersInEachNodeII {

    // level order
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            Node next = null;
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                cur.next = next;
                next = cur;
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
            }
        }
        return root;
    }

    // recursion
    public Node connectII(Node root) {
        Node cur = root;
        Node tail = new Node(0);
        Node dummy = tail;
        while (cur != null) {
            tail.next = cur.left;
            if (tail.next != null) {
                tail = tail.next;
            }
            tail.next = cur.right;
            if (tail.next != null) {
                tail = tail.next;
            }
            cur = cur.next;
            if (cur == null) {
                tail = dummy;
                cur = dummy.next;
            }
        }
        return root;
    }
}

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}