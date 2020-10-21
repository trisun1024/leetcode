import java.util.*;
import extensions.TreeNode;

class ConvertBSTtoGreaterTree {

    // Recursion. Time = O(N); Space = O(N);
    public TreeNode convertBST(TreeNode root) {
        int[] sum = new int[] { 0 };
        helper(root, sum);
        return root;
    }

    private void helper(TreeNode root, int[] sum) {
        if (root == null) {
            return;
        }
        helper(root.right, sum);
        sum[0] += root.val;
        root.val = sum[0];
        helper(root.left, sum);
    }

    // Iteration. Time = O(N); Space = O(N);
    public TreeNode convertBSTI(TreeNode root) {
        int sum = 0;
        TreeNode cur = root;
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.offerFirst(cur);
                cur = cur.right;
            }
            cur = stack.pollFirst();
            sum += cur.val;
            cur.val = sum;
            cur = cur.left;
        }
        return root;
    }

    // Reverse Morris In-order Traversal. Time = O(N); Space = O(1);
    public TreeNode convertBSTII(TreeNode root) {
        int sum = 0;
        TreeNode node = root;

        while (node != null) {
            /*
             * If there is no right subtree, then we can visit this node and continue
             * traversing left.
             */
            if (node.right == null) {
                sum += node.val;
                node.val = sum;
                node = node.left;
            }
            /*
             * If there is a right subtree, then there is at least one node that has a
             * greater value than the current one. therefore, we must traverse that subtree
             * first.
             */
            else {
                TreeNode succ = getSuccessor(node);
                /*
                 * If the left subtree is null, then we have never been here before.
                 */
                if (succ.left == null) {
                    succ.left = node;
                    node = node.right;
                }
                /*
                 * If there is a left subtree, it is a link that we created on a previous pass,
                 * so we should unlink it and visit this node.
                 */
                else {
                    succ.left = null;
                    sum += node.val;
                    node.val = sum;
                    node = node.left;
                }
            }
        }

        return root;
    }

    /* Get the node with the smallest value greater than this one. */
    private TreeNode getSuccessor(TreeNode node) {
        TreeNode succ = node.right;
        while (succ.left != null && succ.left != node) {
            succ = succ.left;
        }
        return succ;
    }

}
