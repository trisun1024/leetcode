import extensions.TreeNode;
import java.util.*;

class DeleteLeavesWithGivenValue {

    // Recursion. Time = O(N);
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if (root == null) {
            return root;
        }
        if (root.left != null) {
            root.left = removeLeafNodes(root.left, target);
        }
        if (root.right != null) {
            root.right = removeLeafNodes(root.right, target);
        }
        if (root.left == null && root.right == null && root.val == target) {
            return null;
        }
        return root;
    }

    // Iteration. (Similar with post-order iteration)
    public TreeNode removeLeafNodesI(TreeNode root, int target) {
        if (root == null) {
            return root;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        pushLeft(stack, root);

        TreeNode lastNode = null;
        while (!stack.isEmpty()) {
            while (lastNode != stack.peekFirst().right && stack.peekFirst().right != null) {
                pushLeft(stack, stack.peekFirst().right);
            }

            TreeNode curr = stack.pollFirst();
            if (curr.left == null && curr.right == null && curr.val == target) {
                if (stack.isEmpty())
                    return null;
                if (stack.peekFirst().left == curr)
                    stack.peekFirst().left = null;
                else
                    stack.peekFirst().right = null;
            }
            lastNode = curr;
        }

        return root;
    }

    private void pushLeft(Deque<TreeNode> stack, TreeNode node) {
        while (node != null) {
            stack.offerFirst(node);
            node = node.left;
        }
    }
}