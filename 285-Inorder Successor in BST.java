import java.util.*;
import extensions.*;

class InorderSuccessorInBST {

    // Traverse. Time O(N);
    // 1. If current node's value is less than or equal to p's value, we know our
    // answer must be in the right subtree.
    // 2. If current node's value is greater than p's value, current node is a
    // candidate. Go to its left subtree to see if we can find a smaller one.
    // 3. If we reach null, our search is over, just return the candidate.

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode candidate = null;
        TreeNode cur = root;
        while (cur != null) {
            if (cur.val > p.val) {
                candidate = cur;
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return candidate;
    }

    // Stack
    public TreeNode inorderSuccessorI(TreeNode root, TreeNode p) {
        // the successor is somewhere lower in the right subtree
        // successor: one step right and then left till you can
        if (p.right != null) {
            p = p.right;
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        int inorder = Integer.MIN_VALUE;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.offerFirst(root);
                root = root.left;
            }
            root = stack.pollFirst();
            if (inorder == p.val) {
                return root;
            }
            inorder = root.val;
            root = root.right;
        }
        return null;
    }
}