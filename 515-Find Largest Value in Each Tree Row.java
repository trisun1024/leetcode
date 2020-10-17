import extensions.TreeNode;
import java.util.*;

class FindLargestValueInEachTreeRow {

    // Level Order Traversal Iteration.
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                max = Math.max(max, cur.val);
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            res.add(max);
        }
        return res;
    }

    // Recursion.
    public List<Integer> largestValuesI(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, 0, res);
        return res;
    }

    private void helper(TreeNode root, int depth, List<Integer> res) {
        if (root == null) {
            return;
        }
        if (res.size() == depth) {
            res.add(Integer.MIN_VALUE);
        }
        res.set(depth, Math.max(root.val, res.get(depth)));
        helper(root.left, depth + 1, res);
        helper(root.right, depth + 1, res);
    }
}