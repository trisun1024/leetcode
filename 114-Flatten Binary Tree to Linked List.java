import java.util.*;
import extensions.TreeNode;

class FlattenBinaryTreeToLinkedList {

    // recursion
    // Time O(N) Space O(N)
    public void flatten(TreeNode root) {
        flat(root);
    }

    private TreeNode flat(TreeNode root) {
        if (root == null) {
            return root;
        }
        if (root.left == null && root.right == null) {
            return root;
        }
        TreeNode left = flat(root.left);
        TreeNode right = flat(root.right);
        if (left != null) {
            left.right = root.right;
            root.right = root.left;
            root.left = null;
        }
        return right == null ? left : right;
    }

    // iteration using Stack
    // Time O(N) Space O(N)
    public void flattenII(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode dupliRoot = root;
        helper(dupliRoot, stack);
        TreeNode node = stack.pop();
        root = node;
        while (!stack.isEmpty()) {
            node.right = stack.pop();
            node.left = null;
            node = node.right;
        }
        node.left = null;
        node.right = null;
    }

    private void helper(TreeNode root, Deque<TreeNode> stack) {
        if (root == null) {
            return;
        }
        helper(root.right, stack);
        helper(root.left, stack);
        // System.out.println(root.val);
        stack.offerFirst(root);
    }

    // iteration
    // Time O(N) Space O(1)
    public void flattenIII(TreeNode root) {
        if (root == null) {
            return;
        }
        while (root != null) {
            if (root.left != null) {
                TreeNode rightMost = root.left;
                while (rightMost.right != null) {
                    rightMost = rightMost.right;
                }
                rightMost.right = root.right;
                root.right = root.left;
                root.left = null;
            }
            root = root.right;
        }
    }

}
