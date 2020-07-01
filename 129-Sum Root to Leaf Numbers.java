import java.util.*;


class SumRootToLeafNumbers {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    // recursion
    public int sumNumbers(TreeNode root) {
        return helper(root, 0);
    }

    private int helper(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        sum = sum * 10 + root.val;
        System.out.println(sum);
        if (root.left == null && root.right == null) {
            return sum;
        }
        return helper(root.left, sum) + helper(root.right, sum);
    }

    // iteration
    public int sumNumbersII(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(root, 0));
        int sum = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                if (cur.node.left != null) {
                    queue.offer(new Node(cur.node.left, cur.sum * 10 + cur.node.val));
                }
                if (cur.node.right != null) {
                    queue.offer(new Node(cur.node.right, cur.sum * 10 + cur.node.val));
                }
                if (cur.node.left == null && cur.node.right == null) {
                    sum += cur.sum * 10 + cur.node.val;
                }
            }
        }
        return sum;
    }

    static class Node {
        TreeNode node;
        int sum;

        Node(TreeNode node, int sum) {
            this.node = node;
            this.sum = sum;
        }
    }
}