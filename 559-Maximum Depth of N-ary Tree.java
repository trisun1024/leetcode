import java.util.*;

class MaximumDepthOfNaryTree {
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

    // Recursion. Time = O(N);
    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        int depth = 0;
        for (Node node : root.children) {
            depth = Math.max(depth, maxDepth(node));
        }
        return 1 + depth;
    }

    // Iteration + Level Order. Time = O(N);
    public int maxDepthI(Node root) {
        if (root == null) {
            return 0;
        }
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                for (Node node : cur.children) {
                    queue.offer(node);
                }
            }
            depth++;
        }
        return depth;
    }
}