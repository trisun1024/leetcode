import extensions.TreeNode;

class LowestCommonAncestorOfBinaryTree {

    // Recursion.
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // base case
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // if left and right both not null, then root is common ancestor
        if (left != null && right != null) {
            return root;
        }
        return left == null ? right : left;
    }

}