import extensions.*;

class BinaryTreeLongestConsecutiveSequence {

    // DFS Top-down.
    public int longestConsecutive(TreeNode root) {
        int[] max = new int[] { 0 };
        dfs(root, null, 0, max);
        return max[0];
    }

    private void dfs(TreeNode root, TreeNode parent, int length, int[] max) {
        if (root == null) {
            return;
        }
        length = (parent != null) && root.val == parent.val + 1 ? length + 1 : 1;
        max[0] = Math.max(max[0], length);
        dfs(root.left, root, length, max);
        dfs(root.right, root, length, max);
    }

    // DFS Bottom-up.
    public int longestConsecutiveI(TreeNode root) {
        int[] max = new int[] { 0 };
        helper(root, max);
        return max[0];
    }

    private int helper(TreeNode root, int[] max) {
        if (root == null) {
            return 0;
        }
        int left = helper(root.left, max) + 1;
        int right = helper(root.right, max) + 1;
        if (root.left != null && root.val + 1 != root.left.val) {
            left = 1;
        }
        if (root.right != null && root.val + 1 != root.right.val) {
            right = 1;
        }
        int len = Math.max(left, right);
        max[0] = Math.max(max[0], len);
        return len;
    }
}