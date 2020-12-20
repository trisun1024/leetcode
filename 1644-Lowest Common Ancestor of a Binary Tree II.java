import extensions.TreeNode;

class LowestCommonAncestorOfBinaryTreeII {

    // Recursion.
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // res possible
        TreeNode res = lca(root, p, q);
        // either p (or q) is lca or p (or q) is not in the tree
        // (test lca(p, q, q) or lca(q, p, p))
        if (res == p) {
            if (lca(p, q, q) == null) {
                return null;
            }
        } else if (res == q) {
            if (lca(q, p, p) == null) {
                return null;
            }
        }
        return res;
    }

    private TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
        // base case
        if (root == null) {
            return null;
        }
        // if p or q == root, then return root;
        if (root == p || root == q) {
            return root;
        }
        TreeNode left = lca(root.left, p, q);
        TreeNode right = lca(root.right, p, q);
        // both leaves are not null, then return current root
        if (left != null && right != null) {
            return root;
        }
        // then lca will be left or right
        return left == null ? right : left;
    }
}