import extensions.TreeNode;
import java.util.*;

class PathSum {

    // Recursion.
    public boolean hasPathSum(TreeNode root, int sum) {
        // base case
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null && root.val == sum) {
            return true;
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    // Iteration.
    public boolean hasPathSumI(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        Deque<Element> stack = new ArrayDeque<>();
        stack.offerFirst(new Element(root, sum - root.val));
        while (!stack.isEmpty()) {
            Element cur = stack.pollFirst();
            if (cur.node.left == null && cur.node.right == null && cur.sum == 0) {
                return true;
            }
            if (cur.node.right != null) {
                stack.offerFirst(new Element(cur.node.right, cur.sum - cur.node.right.val));
            }
            if (cur.node.left != null) {
                stack.offerFirst(new Element(cur.node.left, cur.sum - cur.node.left.val));
            }
        }
        return false;
    }

    static class Element {
        TreeNode node;
        int sum;

        Element(TreeNode node, int sum) {
            this.node = node;
            this.sum = sum;
        }
    }
}