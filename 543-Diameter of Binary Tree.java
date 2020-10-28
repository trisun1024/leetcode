import extensions.TreeNode;

class DiameterOfBinaryTree {

    
    // DFS. Time = O(N); Space = O(N);
    public int diameterOfBinaryTree(TreeNode root) {
        int[] max = new int[] { 1 };
        height(root, max);
        return max[0] - 1;
    }

    private int height(TreeNode root, int[] max) {
        if (root == null) {
            return 0;
        }
        int l = height(root.left, max);
        int r = height(root.right, max);
        max[0] = Math.max(max[0], l + r + 1);
        return Math.max(l, r) + 1;
    }
}