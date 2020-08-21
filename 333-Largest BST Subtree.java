import extensions.*;

class LargestBSTSubtree {

    // Recursion.
    public int largestBSTSubtree(TreeNode root) {
        int[] max = new int[] { 0 };
        helper(root, max);
        return max[0];
    }

    private int[] helper(TreeNode root, int[] max) {
        // 1. what we get from parents
        int[] res = new int[3];
        if (root == null) {
            return res;
        }
        // 2. what we get from left and right
        int[] left = helper(root.left, max);
        int[] right = helper(root.right, max);
        // 3. what we do on current level when we have left and right values
        if ((left[0] == 0 || left[0] > 0 && left[2] < root.val)
                && (right[0] == 0 || right[0] > 0 && right[1] > root.val)) {
            int size = left[0] + right[0] + 1;
            max[0] = Math.max(max[0], size);
            int leftBoundary = left[0] == 0 ? root.val : left[1];
            int rightBoundary = right[0] == 0 ? root.val : right[2];
            res[0] = size;
            res[1] = leftBoundary;
            res[2] = rightBoundary;
        } else {
            res[0] = -1;
        }
        return res;
    }

    // Recursion
    public int largestBSTSubtreeI(TreeNode root) {
        int[] ans = dfs(root);
        return ans[2];
    }

    private int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[] { Integer.MAX_VALUE, Integer.MIN_VALUE, 0 };
        }
        if (root.left == null && root.right == null) {
            return new int[] { root.val, root.val, 1 };
        }
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);
        if (root.val > left[1] && root.val < right[0]) {
            return new int[] { Math.min(left[0], root.val), Math.max(right[1], root.val), left[2] + right[2] + 1 };
        }
        return new int[] { Integer.MIN_VALUE, Integer.MAX_VALUE, Math.max(left[2], right[2]) };
    }
}