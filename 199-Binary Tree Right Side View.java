/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        rightSideView(root, res, 1);
        return res;
    }

    private void rightSideView(TreeNode root, List<Integer> res, int level) {
        if (root == null)
            return;
        if (level > res.size()) {
            res.add(root.val);
        }
        rightSideView(root.right, res, level + 1);
        rightSideView(root.left, res, level + 1);
    }
}