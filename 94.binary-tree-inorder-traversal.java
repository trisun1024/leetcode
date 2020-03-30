import java.util.*;

class Solution {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // Iteration
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.offerFirst(root);
                root = root.left;
            }
            root = stack.pollFirst();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }

    // Recursion
    public List<Integer> inorder(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        helper(root, res);
        return res;
    }

    private void helper(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        helper(root.left, res);
        res.add(root.val);
        helper(root.right, res);
    }
}
