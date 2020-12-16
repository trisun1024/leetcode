import java.util.*;
import extensions.TreeNode;
class ValidateBinarySearchTree {
 
    // recursion Time = O(N) Space = O(N)
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return helper(root, null, null);
    }

    private boolean helper(TreeNode root, Integer left, Integer right) {
        if (root == null) {
            return true;
        }
        int val = root.val;
        if (left != null && val <= left)
            return false;
        if (right != null && val >= right)
            return false;

        if (!helper(root.right, val, right))
            return false;
        if (!helper(root.left, left, val))
            return false;
        return true;
    }

    // iteration inorder traversal Time = O(N) Space = O(N)
    public boolean isValidBSTII(TreeNode root) {
        if (root == null) {
            return true;
        }
        // here we use inorder traversal
        long left = Long.MIN_VALUE;
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode prev = root;
        while (!stack.isEmpty() || prev != null) {
            if (prev != null) {
                stack.offerFirst(prev);
                prev = prev.left;
            } else {
                prev = stack.pollFirst();
                if (prev.val <= left) {
                    return false;
                }
                left = (long) prev.val;
                prev = prev.right;
            }
        }
        return true;
    }

}
