/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode() {} TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left
 * = left; this.right = right; } }
 */
class CountUnivalueSubtrees {

    static class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // DFS
    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] count = new int[] { 0 };
        helper(root, count);
        return count[0];
    }

    private boolean helper(TreeNode root, int[] count) {
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            count[0]++;
            return true;
        }
        boolean flag = true;
        if (root.left != null) {
            flag = helper(root.left, count) && flag && root.left.val == root.val;
        }
        if (root.right != null) {
            flag = helper(root.right, count) && flag && root.right.val == root.val;
        }
        if (!flag) {
            return false;
        }
        count[0]++;
        return true;
    }
}