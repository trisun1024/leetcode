import extensions.TreeNode;

class InsertIntoBST {

    // Recursion. Time = O(H); Space = O(H);
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (root.val < val) {
            root.right = insertIntoBST(root.right, val);
        } else {
            root.left = insertIntoBST(root.left, val);
        }
        return root;
    }

    // Iteration.
    public TreeNode insertIntoBSTI(TreeNode root, int val) {
        TreeNode node = root;
        while (node != null) {
            // insert into the right subtree
            if (val > node.val) {
                // insert right now
                if (node.right == null) {
                    node.right = new TreeNode(val);
                    return root;
                } else
                    node = node.right;
            }
            // insert into the left subtree
            else {
                // insert left now
                if (node.left == null) {
                    node.left = new TreeNode(val);
                    return root;
                } else
                    node = node.left;
            }
        }
        return new TreeNode(val);
    }
}