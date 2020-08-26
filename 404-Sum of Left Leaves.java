import java.util.*;
import extensions.*;

class SumOfLeftLeaves {

    // recursive
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null)
            return 0;
        int ans = 0;
        if (root.left != null) {
            if (root.left.left == null && root.left.right == null)
                ans += root.left.val;
            else
                ans += sumOfLeftLeaves(root.left);
        }
        ans += sumOfLeftLeaves(root.right);

        return ans;
    }

    // Iterative
    public int sumOfLeftLeavesI(TreeNode root) {
        if (root == null)
            return 0;
        int ans = 0;
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        stack.offerFirst(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pollFirst();
            if (node.left != null) {
                if (node.left.left == null && node.left.right == null)
                    ans += node.left.val;
                else
                    stack.offerFirst(node.left);
            }
            if (node.right != null) {
                if (node.right.left != null || node.right.right != null)
                    stack.offerFirst(node.right);
            }
        }
        return ans;
    }
}
