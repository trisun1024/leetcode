import extensions.TreeNode;

class LCAofBST {

    // Recursion.
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Value of current node or parent node.
        int parentVal = root.val;
        // Value of p
        int pVal = p.val;
        // Value of q;
        int qVal = q.val;
        if (pVal > parentVal && qVal > parentVal) {
            // If both p and q are greater than parent
            return lowestCommonAncestor(root.right, p, q);
        } else if (pVal < parentVal && qVal < parentVal) {
            // If both p and q are lesser than parent
            return lowestCommonAncestor(root.left, p, q);
        } else {
            // We have found the split point, i.e. the LCA node.
            return root;
        }
    }

    // Iteravtive
    public TreeNode lowestCommonAncestorItr(TreeNode root, TreeNode p, TreeNode q) {
        int min = Math.min(p.val, q.val);
        int max = Math.max(p.val, q.val);
        while (root != null) {
            if (root.val < min) {
                root = root.right;
            } else if (root.val > max) {
                root = root.left;
            } else {
                return root;
            }
        }
        return null;
    }
}