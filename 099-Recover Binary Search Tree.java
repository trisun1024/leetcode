/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */

// Use Inorder to find incorrect value;
class Solution {
    TreeNode fir = null, sec = null, pre = null;

    public void recoverTree(TreeNode root) {
        if (root == null)
            return;
        // find first and second node
        findSwapNode(root);
        // swap values
        if (fir != null && sec != null) {
            int tmp = fir.val;
            fir.val = sec.val;
            sec.val = tmp;
        }
    }

    private void findSwapNode(TreeNode root) {
        if (root.left != null)
            findSwapNode(root.left);
        if (pre != null && pre.val > root.val) {
            if (fir == null)
                fir = pre;
            if (fir != null)
                sec = root;
        }
        pre = root;
        if (root.right != null)
            findSwapNode(root.right);
    }
}