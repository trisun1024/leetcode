import java.util.*;
import extensions.*;

class DeepestLeavesSum {

    // BFS.
    public int deepestLeavesSum(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int sum = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                sum += cur.val;
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
        }
        return sum;
    }

    // DFS.
    public int deepestLeavesSumI(TreeNode root) {
        int[] deepestLevel = new int[] { 0 };
        int currentLevel = 0;
        int[] sum = new int[] { 0 };
        helper(root, currentLevel, deepestLevel, sum);
        return sum[0];
    }

    private void helper(TreeNode root, int currentLevel, int[] deepestLevel, int[] sum) {
        if (root == null) {
            return;
        }
        if (currentLevel == deepestLevel[0]) {
            sum[0] += root.val;
        }
        if (currentLevel > deepestLevel[0]) {
            sum[0] = root.val;
            deepestLevel[0] = currentLevel;
        }
        helper(root.left, currentLevel + 1, deepestLevel, sum);
        helper(root.right, currentLevel + 1, deepestLevel, sum);
    }
}