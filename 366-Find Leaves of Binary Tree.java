/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        getHeight(res, root);
        return res;
    }

    private int getHeight(List<List<Integer>> res, TreeNode root) {
        if (root == null) {
            return -1;
        }

        int height = 1 + Math.max(getHeight(res, root.left), getHeight(res, root.right));

        if (height == res.size()) {
            res.add(new ArrayList<>());
        }

        res.get(height).add(root.val);
        root.left = root.right = null;
        return height;
    }
}