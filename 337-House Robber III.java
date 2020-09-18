import extensions.*;

class HouseRobberIII {

    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] res = helper(root);
        return Math.max(res[0], res[1]);
    }

    private int[] helper(TreeNode root) {
        // int[] 0 - current sum; 1 - maximum so far
        int[] res = new int[] { 0, 0 };
        if (root == null) {
            return res;
        }
        int[] left = helper(root.left);
        int[] right = helper(root.right);
        res[0] = root.val + left[1] + right[1];
        res[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return res;
    }
}