import java.util.*;

class Solution {

    // recursion
    public int kthSmallest(TreeNode root, int k) {
        int leftCount = countSub(root.left);
        if (leftCount == k - 1) {
            return root.val;
        } else if (leftCount > k - 1) {
            return kthSmallest(root.left, k);
        } else {
            return kthSmallest(root.right, k - leftCount - 1);
        }
    }

    private int countSub(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = countSub(root.left);
        int r = countSub(root.right);
        return l + r + 1;
    }

    // iteration
    public int kthSmallestII(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (true) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            root = stack.removeLast();
            if (--k == 0)
                return root.val;
            root = root.right;
        }
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {

    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

}