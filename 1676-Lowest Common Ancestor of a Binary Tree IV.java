import extensions.TreeNode;
import java.util.*;

class LowestCommonAncestorOfBinaryTreeIV {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        Set<TreeNode> set = new HashSet<>();
        for (TreeNode node : nodes) {
            set.add(node);
        }
        return helper(root, set);
    }

    private TreeNode helper(TreeNode root, Set<TreeNode> set) {
        if (root == null) {
            return null;
        }
        if (set.contains(root)) {
            return root;
        }
        TreeNode left = helper(root.left, set);
        TreeNode right = helper(root.right, set);
        if (left != null && right != null) {
            return root;
        }
        return left == null ? right : left;
    }
}