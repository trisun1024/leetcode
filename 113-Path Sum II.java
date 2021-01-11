import extensions.TreeNode;
import java.util.*;

class PathSumII {

    // Recursion. Time = O(N^2);
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        helper(root, sum, cur, res);
        return res;
    }

    private void helper(TreeNode root, int sum, List<Integer> cur, List<List<Integer>> res) {
        if (root == null) {
            return;
        }
        cur.add(root.val);
        sum -= root.val;
        if (root.left == null && root.right == null && sum == 0) {
            res.add(new ArrayList<>(cur));
        }
        helper(root.left, sum, cur, res);
        helper(root.right, sum, cur, res);

        cur.remove(cur.size() - 1);

    }
}