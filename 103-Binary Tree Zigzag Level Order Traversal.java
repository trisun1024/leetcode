/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    List<List<Integer>> res = new ArrayList<>();
    boolean leftToRight = false;

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null)
            return res;
        getZigzagLevel(root, 0);
        return res;
    }

    private void getZigzagLevel(TreeNode node, int n) {
        if (res.size() == n)
            res.add(new ArrayList<Integer>());

        if (n % 2 == 0)
            res.get(n).add(node.val);
        else
            res.get(n).add(0, node.val);
        if (node.left != null)
            getZigzagLevel(node.left, n + 1);
        if (node.right != null)
            getZigzagLevel(node.right, n + 1);
    }
}