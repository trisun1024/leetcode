import extensions.TreeNode;
import java.util.*;

class FindDuplicateSubtrees {

    // DFS.
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String, Integer> count = new HashMap<>();
        List<TreeNode> res = new ArrayList<>();
        dfs(root, count, res);
        return res;
    }

    private String dfs(TreeNode root, Map<String, Integer> count, List<TreeNode> res) {
        if (root == null) {
            return "#";
        }
        String leftString = dfs(root.left, count, res);
        String rightString = dfs(root.right, count, res);
        String s = new StringBuilder().append(root.val).append(",").append(leftString).append(",").append(rightString)
                .toString();
        count.put(s, count.getOrDefault(s, 0) + 1);
        if (count.get(s) == 2) {
            res.add(root);
        }
        return s;
    }
}