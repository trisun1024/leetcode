/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null)
            return null;
        // check p or q is parent
        if (checkNodeIsParent(p, q))
            return p;
        if (checkNodeIsParent(q, p))
            return q;
        return getAncestor(root, p, q);
    }

    private boolean checkNodeIsParent(TreeNode p, TreeNode q) {
        if (p == null)
            return false;
        if (p.val == q.val) {
            return true;
        } else {
            return checkNodeIsParent(p.left, q) || checkNodeIsParent(p.right, q);
        }
    }

    private TreeNode getAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (checkNodeIsParent(root.left, p) && checkNodeIsParent(root.left, q))
            return getAncestor(root.left, p, q);

        if (checkNodeIsParent(root.right, p) && checkNodeIsParent(root.right, q))
            return getAncestor(root.right, p, q);

        if ((checkNodeIsParent(root.left, p) && checkNodeIsParent(root.right, q))
                || (checkNodeIsParent(root.right, p) && checkNodeIsParent(root.left, q)))
            return root;

        return null;
    }
}