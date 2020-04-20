import java.util.*;

class Solution {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // recursion
    public TreeNode bstFromPreorder(int[] preorder) {
        int[] index = new int[] { 0 };
        return build(preorder, index, Integer.MAX_VALUE);
    }

    private TreeNode build(int[] preorder, int[] index, int max) {
        if (index[0] >= preorder.length || preorder[index[0]] >= max) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[index[0]++]);
        root.left = build(preorder, index, root.val);
        root.right = build(preorder, index, max);
        return root;
    }

    // iteration
    public TreeNode bstFromPreorderII(int[] preorder) {
        int n = preorder.length;
        if (n == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offerFirst(root);
        for (int i = 1; i < n; i++) {
            TreeNode node = stack.peekFirst();
            TreeNode child = new TreeNode(preorder[i]);
            while (!stack.isEmpty() && stack.peekFirst().val < child.val) {
                node = stack.pollFirst();
            }
            if (node.val < child.val) {
                node.right = child;
            } else {
                node.left = child;
            }
            stack.offerFirst(child);
        }
        return root;
    }

}