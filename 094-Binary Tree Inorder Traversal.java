import java.util.*;
import extensions.TreeNode;

/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */

class BinaryTreeInorderTraversal {
    // Recursive
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        getInorder(root, res);
        return res;
    }

    private void getInorder(TreeNode root, List<Integer> res) {
        if (root != null) {
            if (root.left != null) {
                getInorder(root.left, res);
            }
            res.add(root.val);
            if (root.right != null) {
                getInorder(root.right, res);
            }
        }
    }

    // Iterative
    public List<Integer> inorderTraversalItr(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.offerFirst(curr);
                curr = curr.left;
            }
            curr = stack.pollFirst();
            res.add(curr.val);
            curr = curr.right;
        }
        return res;
    }

}