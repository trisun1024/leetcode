class Solution {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            this.val = x;
        }
    }

    // DFS T = O(N) S = O(N)
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        int leftDiameter = diameterOfBinaryTree(root.left);
        int rightDiameter = diameterOfBinaryTree(root.right);
        return leftHeight != 0 && rightHeight != 0
                ? Math.max(leftHeight + rightHeight + 1, Math.max(leftDiameter, rightDiameter))
                : 0;
    }

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(height(root.left), height(root.right)) + 1;
    }
}