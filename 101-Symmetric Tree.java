import java.util.*;

/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
// Recursive
class SymmetricTree {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }

    private boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null)
            return true;
        if (t1 == null || t2 == null)
            return false;
        return (t1.val == t2.val) && isMirror(t1.right, t2.left) && isMirror(t1.left, t2.right);
    }

    // Iterative

    // Using Queue
    public boolean isSymmetricI(TreeNode root) {
        Queue<Pair<TreeNode, TreeNode>> queue = new LinkedList<>();
        queue.add(new Pair(root, root));
        while (!queue.isEmpty()) {
            Pair<TreeNode, TreeNode> nodes = queue.poll();
            TreeNode n1 = nodes.getKey();
            TreeNode n2 = nodes.getValue();
            if (n1 != null || n2 != null) {
                if (n1 != null && n2 != null && n1.val == n2.val) {
                    queue.add(new Pair<>(n1.left, n2.right));
                    queue.add(new Pair<>(n1.right, n2.left));
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    // Using Stack
    public boolean isSymmetricII(TreeNode root) {
        Stack<Pair<TreeNode, TreeNode>> stack = new Stack<>();
        stack.push(new Pair(root, root));
        while (!stack.isEmpty()) {
            Pair<TreeNode, TreeNode> nodes = stack.pop();
            TreeNode n1 = nodes.getKey();
            TreeNode n2 = nodes.getValue();
            if (n1 != null || n2 != null) {
                if (n1 != null && n2 != null && n1.val == n2.val) {
                    stack.push(new Pair<>(n1.left, n2.right));
                    stack.push(new Pair<>(n1.right, n2.left));
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
