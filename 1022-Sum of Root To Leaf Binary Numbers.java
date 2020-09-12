import extensions.*;

class SumOfRootToLeaf {

    public int sumRootToLeaf(TreeNode root) {
        return dfs(root, 0);
    }

    public int dfs(TreeNode root, int val) {
        if (root == null) {
            return 0;
        }
        val = val * 2 + root.val;
        return root.left == root.right ? val : dfs(root.left, val) + dfs(root.right, val);
    }

    public int sumRootToLeafI(TreeNode root) {
        int[] sum = new int[] { 0 };
        helper(root, 0, sum);
        return sum[0];
    }

    private void helper(TreeNode root, int cur, int[] sum) {
        if (root == null) {
            return;
        }
        if (root.left == root.right) {
            cur = (cur >> 1) + root.val;
            sum[0] += cur;
        }
        cur = (cur >> 1) + root.val;
        helper(root.left, cur, sum);
        helper(root.right, cur, sum);
    }
}