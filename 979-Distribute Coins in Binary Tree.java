class Solution {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            this.val = x;
        }
    }

    public int distributeCoins(TreeNode root) {
        int[] res = new int[] { 0 };
        dfs(root, res);
        return res[0];
    }

    private int dfs(TreeNode root, int[] res) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left, res);
        int right = dfs(root.right, res);
        res[0] += Math.abs(left) + Math.abs(right);
        return root.val + left + right - 1;
    }

    // this question whole idea is to analysis what should we get in each level 
    // want left and right return current coins they have to share
    // current node, calculate current coins number and delete one coin for self use
    // return to parent how many coins left
}