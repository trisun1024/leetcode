import java.util.*;
import extensions.TreeNode;

class SumOfNodesWithEvenValuedGrandparent {

    // DFS.
    public int sumEvenGrandparent(TreeNode root) {
        int[] sum = new int[] { 0 };
        helper(root, false, sum);
        return sum[0];
    }

    private void helper(TreeNode root, boolean parent, int[] sum) {
        if (root == null) {
            return;
        }
        if (parent) {
            sum[0] += root.left == null ? 0 : root.left.val;
            sum[0] += root.right == null ? 0 : root.right.val;
        }
        helper(root.left, root.val % 2 == 0 ? true : false, sum);
        helper(root.right, root.val % 2 == 0 ? true : false, sum);
    }

    // BFS.
    public int sumEvenGrandparentI(TreeNode root) {
        int sum = 0;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.left != null) {
                queue.offer(cur.left);
                if (cur.val % 2 == 0) {
                    sum += cur.left.left == null ? 0 : cur.left.left.val;
                    sum += cur.left.right == null ? 0 : cur.left.right.val;
                }
            }
            if (cur.right != null) {
                queue.offer(cur.right);
                if (cur.val % 2 == 0) {
                    sum += cur.right.left == null ? 0 : cur.right.left.val;
                    sum += cur.right.right == null ? 0 : cur.right.right.val;
                }
            }
        }
        return sum;
    }
}