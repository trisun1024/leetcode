import extensions.TreeNode;

class BinaryTreeMaximumPathSum {

    // Time = O(N); Space = O(log(N));
    public int maxPathSum(TreeNode root) {
        int[] max = new int[] { Integer.MIN_VALUE };
        helper(root, max);
        return max[0];
    }

    private int helper(TreeNode root, int[] max) {
        // base case
        if (root == null) {
            return 0;
        }
        // what we want from both left and right subtrees
        int left = helper(root.left, max);
        int right = helper(root.right, max);
        // update left and right if smaller than zero than is zero
        left = left < 0 ? 0 : left;
        right = right < 0 ? 0 : right;
        // update max
        max[0] = Math.max(max[0], left + right + root.val);
        // return the largest sum branch
        return Math.max(left, right) + root.val;
    }

}