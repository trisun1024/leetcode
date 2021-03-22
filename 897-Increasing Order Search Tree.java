import extensions.TreeNode;
import java.util.*;

class IncreasingOrderSearchTree {

    // Inorder Traversal. Time = O(N);
    public TreeNode increasingBSTI(TreeNode root) {
        List<Integer> vals = new ArrayList<>();
        inorder(root, vals);
        TreeNode ans = new TreeNode(0), cur = ans;
        for (int v : vals) {
            cur.right = new TreeNode(v);
            cur = cur.right;
        }
        return ans.right;
    }

    public void inorder(TreeNode node, List<Integer> vals) {
        if (node == null) {
            return;
        }
        inorder(node.left, vals);
        vals.add(node.val);
        inorder(node.right, vals);
    }

    // Recursion.
    public TreeNode increasingBST(TreeNode root) {
        // base case
        if (root == null || (root.left == null && root.right == null)) {
            return root;
        }
        TreeNode left = increasingBST(root.left); // return left;
        TreeNode right = increasingBST(root.right); // return right;
        TreeNode cur = left;
        if (cur != null) {
            // move to the right most in left subtree
            while (cur.right != null) {
                cur = cur.right;
            }
            cur.right = root;// connect root
            root.left = null;// set root.left to null
            root.right = right; // connect right
        } else {
            root.right = right;
            root.left = null;
            left = root;
        }
        return left;
    }
}