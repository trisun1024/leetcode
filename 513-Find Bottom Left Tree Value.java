import java.util.*;
import extensions.TreeNode;

class FindBottomLeftTreeValue {

    // Level Order.
    public int findBottomLeftValue(TreeNode root) {
        int val = root.val;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean found = false;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (i == 0 || !found) {
                    val = cur.val;
                    found = true;
                }
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
        }
        return val;
    }

    // Recursion. Time = O(log(N)); Space = O(log(N));
    public int findBottomLeftValueI(TreeNode root) {
        int[] max = new int[] { 0, root.val };
        helper(root, 0, max);
        return max[1];
    }

    private void helper(TreeNode root, int depth, int[] max) {
        if (root == null) {
            return;
        }
        if (depth >= max[0]) {
            max[1] = root.val;
        }
        max[0] = Math.max(max[0], depth + 1);
        helper(root.left, depth + 1, max);
        helper(root.right, depth + 1, max);
    }
}