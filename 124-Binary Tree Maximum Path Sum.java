/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
// time O(n) space O(log(n))
class Solution {
    int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        getSum(root);
        return max;
    }

    private int getSum(TreeNode root) {
        if (root == null)
            return 0;

        int left = Math.max(getSum(root.left), 0);
        int right = Math.max(getSum(root.right), 0);

        max = Math.max(max, root.val + left + right);

        return root.val + Math.max(left, right);
    }
}