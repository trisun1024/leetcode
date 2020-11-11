import extensions.*;

class MaximumDifferenceBetweenNodeAndAncestor {

    // Maximum+Minimum. Time = O(N); Space = O(N);
    public int maxAncestorDiff(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return helper(root, root.val, root.val);
    }

    private int helper(TreeNode node, int curMax, int curMin) {
        // if encounter leaves, return the max-min along the path
        if (node == null) {
            return curMax - curMin;
        }
        // else, update max and min
        // and return the max of left and right subtrees
        curMax = Math.max(curMax, node.val);
        curMin = Math.min(curMin, node.val);
        int left = helper(node.left, curMax, curMin);
        int right = helper(node.right, curMax, curMin);
        return Math.max(left, right);
    }

    // Recursion.
    public int maxAncestorDiffI(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] result = new int[] { 0 };
        helper(root, root.val, root.val, result);
        return result[0];
    }

    private void helper(TreeNode node, int curMax, int curMin, int[] result) {
        if (node == null) {
            return;
        }
        // update `result`
        int possibleResult = Math.max(Math.abs(curMax - node.val), Math.abs(curMin - node.val));
        result[0] = Math.max(result[0], possibleResult);
        // update the max and min
        curMax = Math.max(curMax, node.val);
        curMin = Math.min(curMin, node.val);
        helper(node.left, curMax, curMin, result);
        helper(node.right, curMax, curMin, result);
    }

}