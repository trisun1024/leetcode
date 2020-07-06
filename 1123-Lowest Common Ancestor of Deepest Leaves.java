class LowestCommonAncestorOfDeepestLeaves {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        int[] deepest = new int[] { 0 };
        TreeNode[] lca = new TreeNode[1];
        helper(root, 0, lca, deepest);
        return lca[0];
    }

    private int helper(TreeNode root, int depth, TreeNode[] lca, int[] deepest) {
        deepest[0] = Math.max(deepest[0], depth);
        if (root == null) {
            return depth;
        }
        int left = helper(root.left, depth + 1, lca, deepest);
        int right = helper(root.right, depth + 1, lca, deepest);
        if (left == deepest[0] && right == deepest[0]) {
            lca[0] = root;
        }
        return Math.max(left, right);
    }
}