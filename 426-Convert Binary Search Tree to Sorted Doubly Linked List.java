import java.util.*;

class ConvertBinarySearchTreetoSortedDoublyLinkedList {

    // Node representation
    static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    // Iteration
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return root;
        }
        Node dummy = new Node(0);
        Node prev = dummy;
        Deque<Node> stack = new ArrayDeque<>();
        Node cur = root;
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.offerFirst(cur);
                cur = cur.left;
            }
            cur = stack.pollFirst();
            prev.right = cur;
            cur.left = prev;
            prev = cur;
            cur = cur.right;
        }
        dummy.right.left = prev;
        prev.right = dummy.right;
        return dummy.right;
    }

    // Recursion
    public Node treeToDoublyListI(Node root) {
        if (root == null) {
            return root;
        }
        Node[] prev = new Node[2]; // first, last
        helper(root, prev);
        prev[1].right = prev[0];
        prev[0].left = prev[1];
        return prev[0];
    }

    private void helper(Node root, Node[] prev) {
        if (root == null) {
            return;
        }
        // go left
        helper(root.left, prev);
        if (prev[1] != null) {
            // link previous node(last) to current node
            prev[1].right = root;
            root.left = prev[1];
        } else {
            // keep the smallest node to close DLL
            prev[0] = root;
        }
        prev[1] = root;
        // go right
        helper(root.right, prev);
    }
}