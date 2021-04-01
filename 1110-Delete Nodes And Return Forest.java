import java.util.*;
import extensions.TreeNode;

class DeleteNodesAndReturnForest {

    // Recursion. 
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> to_delete_set = new HashSet<>();
        List<TreeNode> res = new ArrayList<>();
        for (int i : to_delete) {
            to_delete_set.add(i);
        }
        helper(root, true, to_delete_set, res);
        return res;
    }

    private TreeNode helper(TreeNode node, boolean is_root, Set<Integer> to_delete_set, List<TreeNode> res) {
        if (node == null) {
            return null;
        }
        boolean deleted = to_delete_set.contains(node.val);
        if (is_root && !deleted) {
            res.add(node);
        }
        node.left = helper(node.left, deleted, to_delete_set, res);
        node.right = helper(node.right, deleted, to_delete_set, res);
        return deleted ? null : node;
    }
}