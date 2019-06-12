/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
// Recursive
public class Solution {
    public int closestValue(TreeNode root, double target) {
        int a = root.val;
        if (a == target)
            return a;
        TreeNode kid = a < target ? root.right : root.left;
        if (kid == null)
            return a;
        int b = closestValue(kid, target);
        return Math.abs(a - target) < Math.abs(b - target) ? a : b;
    }
}
// Iterative
class Solution1 {
    public int closestValue(TreeNode root, double target) {
        int ret = root.val;
        while (root != null) {
            if (Math.abs(target - root.val) < Math.abs(target - ret)) {
                ret = root.val;
            }
            root = root.val > target ? root.left : root.right;
        }
        return ret;
    }
}