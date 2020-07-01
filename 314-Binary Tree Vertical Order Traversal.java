import java.util.*;

class BinaryTreeVerticalOrderTraversal {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    // BFS
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(0, root));
        map.put(0, new ArrayList<>());
        map.get(0).add(root.val);
        int globalMin = 0;
        int globalMax = 0;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.node.left != null) {
                queue.offer(new Node(cur.col - 1, cur.node.left));
                globalMin = Math.min(globalMin, cur.col - 1);
                if (!map.containsKey(cur.col - 1)) {
                    map.put(cur.col - 1, new ArrayList<>());
                }
                map.get(cur.col - 1).add(cur.node.left.val);
            }
            if (cur.node.right != null) {
                queue.offer(new Node(cur.col + 1, cur.node.right));
                globalMax = Math.max(globalMax, cur.col + 1);
                if (!map.containsKey(cur.col + 1)) {
                    map.put(cur.col + 1, new ArrayList<Integer>());
                }
                map.get(cur.col + 1).add(cur.node.right.val);
            }
        }
        for (int i = globalMin; i <= globalMax; i++) {
            res.add(map.get(i));
        }
        return res;
    }

    static class Node {
        int col;
        TreeNode node;

        Node(int col, TreeNode node) {
            this.col = col;
            this.node = node;
        }
    }

    // DFS
    int min = 0;
    int max = 0;

    public List<List<Integer>> verticalOrderII(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }
        dfsRange(root, 0);

        for (int i = min; i <= max; i++) {
            ret.add(new ArrayList<>());
        }
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> index = new LinkedList<>();
        queue.offer(root);
        index.offer(-min);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            int col = index.poll();

            ret.get(col).add(curr.val);
            if (curr.left != null) {
                queue.offer(curr.left);
                index.offer(col - 1);
            }
            if (curr.right != null) {
                queue.offer(curr.right);
                index.offer(col + 1);
            }
        }
        return ret;
    }

    public void dfsRange(TreeNode root, int index) {
        if (root == null) {
            return;
        }
        min = Math.min(min, index);
        max = Math.max(max, index);
        dfsRange(root.left, index - 1);
        dfsRange(root.right, index + 1);
    }
}