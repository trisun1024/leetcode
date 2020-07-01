class BinaryTreeUpsideDown {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    // recursion
    public TreeNode upsideDownBinaryTreeII(TreeNode root) {
        if (root == null || root.left == null) {
            return root;
        }
        TreeNode newRoot = upsideDownBinaryTreeII(root.left);
        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;
        return newRoot;
    }

    // iteration
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null || root.left == null) {
            return root;
        }
        TreeNode prev = null;
        TreeNode cur = root;
        TreeNode next = null;
        TreeNode right = null;
        while (cur != null) {
            next = cur.left;
            cur.left = right;
            right = cur.right;
            cur.right = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
}