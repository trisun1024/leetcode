import extensions.TreeNode;

class SecondMinimumNodeInBinaryTree {

    // Recursion. Time = O(N);
    public int findSecondMinimumValue(TreeNode root) {
        // each node in this tree has exactly two or zero sub-node
        if (root == null || root.left == null) {
            return -1;
        }
        int l = root.left.val == root.val ? findSecondMinimumValue(root.left) : root.left.val;
        int r = root.right.val == root.val ? findSecondMinimumValue(root.right) : root.right.val;
        return l == -1 || r == -1 ? Math.max(l, r) : Math.min(l, r);
    }

}