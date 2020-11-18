import extensions.*;
import java.util.*;

class RangeSumOfBST {

    // Recursion. 
    public int rangeSumBST(TreeNode root, int low, int high) {
        int[] tot = new int[] { 0 };
        helper(root, low, high, tot);
        return tot[0];
    }

    private void helper(TreeNode root, int low, int high, int[] tot) {
        if (root == null) {
            return;
        }
        if (root.val >= low && root.val <= high) {
            tot[0] += root.val;
        }
        if (low < root.val) {
            helper(root.left, low, high, tot);
        }
        if (root.val < high) {
            helper(root.right, low, high, tot);
        }
    }

    // Iteration
    public int rangeSumBSTI(TreeNode root, int L, int R) {
        int ans = 0;
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offerFirst(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollFirst();
            if (node != null) {
                if (L <= node.val && node.val <= R) {
                    ans += node.val;
                }
                if (L < node.val)
                    stack.offerFirst(node.left);
                if (node.val < R)
                    stack.offerFirst(node.right);
            }
        }
        return ans;
    }
}