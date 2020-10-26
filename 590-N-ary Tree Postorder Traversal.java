import java.util.*;

class NaryTreePostorderTraversal {

    static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    // Recursion.
    public List<Integer> postorder(Node root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    private void helper(Node root, List<Integer> res) {
        if (root == null) {
            return;
        }
        for (Node child : root.children) {
            helper(child, res);
        }
        res.add(root.val);
    }

    // Iteration.
    public List<Integer> postorderI(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<Node> stack = new ArrayDeque<>();
        stack.offerFirst(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pollFirst();
            for (Node child : cur.children) {
                stack.offerFirst(child);
            }
            res.add(0, cur.val);
        }
        return res;
    }
}
