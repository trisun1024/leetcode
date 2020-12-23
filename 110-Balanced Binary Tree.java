import extensions.TreeNode;

class BalancedBinaryTree {

    // Recursion.
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return Math.abs(maxDepth(root.left) - maxDepth(root.right)) <= 1 && isBalanced(root.left)
                && isBalanced(root.right);
    }

    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    // Recursion Bottom-up.
    public boolean isBalancedI(TreeNode root) {
        return helper(root).balanced;
    }

    private TreeInfo helper(TreeNode root) {
        if (root == null) {
            return new TreeInfo(-1, true);
        }
        TreeInfo left = helper(root.left);
        if (!left.balanced) {
            return new TreeInfo(-1, false);
        }
        TreeInfo right = helper(root.right);
        if (!right.balanced) {
            return new TreeInfo(-1, false);
        }
        if (Math.abs(left.height - right.height) < 2) {
            return new TreeInfo(Math.max(left.height, right.height) + 1, true);
        }
        return new TreeInfo(-1, false);
    }

    static class TreeInfo {
        boolean balanced;
        int height;

        TreeInfo(int height, boolean balanced) {
            this.balanced = balanced;
            this.height = height;
        }
    }
}