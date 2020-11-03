import extensions.TreeNode;
import java.util.*;
class ConstructStringFromBinaryTree {

    // Recursion. Time = O(N); Space = O(N);
    public String tree2str(TreeNode t) {
        StringBuilder res = new StringBuilder();
        helper(t, res);
        return res.toString();
    }

    private void helper(TreeNode root, StringBuilder res) {
        if (root == null) {
            return;
        }
        res.append(root.val);
        if (root.left != null || root.right != null) {
            res.append("(");
            helper(root.left, res);
            res.append(")");
            if (root.right != null) {
                res.append("(");
                helper(root.right, res);
                res.append(")");
            }
        }
    }

    // Iteration.
    public String tree2strI(TreeNode t) {
        if(t == null) {
            return "";
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offerFirst(t);
        StringBuilder res = new StringBuilder();
        
        return res.toString();
    }
}