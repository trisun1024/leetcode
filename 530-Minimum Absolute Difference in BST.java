import extensions.TreeNode;
import java.util.*;

class MinimumAbsoluteDifferenceInBST {

    // Recursion.
    public int getMinimumDifference(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] ans = new int[] { Integer.MAX_VALUE };
        helper(root, ans);
        return ans[0];
    }

    private int[] helper(TreeNode root, int[] ans) {
        if (root.left == null && root.right == null) {
            return new int[] { root.val, root.val };
        }

        int min = root.val;
        int max = root.val;

        if (root.left != null) {
            int[] l = helper(root.left, ans);

            ans[0] = Math.min(ans[0], Math.abs(root.val - l[1]));
            min = Math.min(min, l[0]);
        }

        if (root.right != null) {
            int[] r = helper(root.right, ans);
            ans[0] = Math.min(ans[0], Math.abs(root.val - r[0]));
            max = Math.max(max, r[1]);
        }

        return new int[] { min, max };
    }

    // Iteration + In-order. 
    public int getMinimumDifferenceI(TreeNode root) {
        TreeNode prev = null;
        int min = Integer.MAX_VALUE;
        Deque<TreeNode> stack = new ArrayDeque<>();

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.offerFirst(root);
                root = root.left;
            }
            TreeNode popped = stack.pollFirst();
            if (prev != null) {
                min = Math.min(min, popped.val - prev.val);
            }
            prev = popped;
            root = popped.right;
        }
        return min;
    }
}